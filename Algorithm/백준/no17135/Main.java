package baekjoon.no17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, D;
	static Enemy[] enemy;
	static int enemyCnt;
	static Archer[] archer;
	static boolean[] selected;
	static int MaxKill;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		enemy = new Enemy[N*M];
		enemyCnt = 0;
		archer = new Archer[3];
		selected = new boolean[M];
		MaxKill = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) enemy[enemyCnt++] = new Enemy(i, j, N-i);
			}
		}
		
		// 적들을 성벽으로부터의 거리순으로 정렬
		Arrays.sort(enemy, 0, enemyCnt);
		combOfArchers(0, 0);
		
		System.out.println(MaxKill);

	}
	
	// 궁수 3명의 위치를 조합으로 확정
	static void combOfArchers(int tgtIdx, int srcIdx) {
		if(tgtIdx == 3) { // 궁수는 3명
			int archerIdx = 0;
			
			// 궁수 3명 위치배정
			for(int i = 0; i < M; i++) {
				if(selected[i]) {
					archer[archerIdx++] = new Archer(i);
				}
			}
			
			// 궁수 위치에 따른 각 적들의 거리 계산
			for(int i = 0; i < enemyCnt; i++) {
				enemy[i].setDist();
			}
			
			// 궁수들로 적들을 쏨
			killEnemy();
			return;
		}
		
		else if(srcIdx >= M) return;
		
		selected[srcIdx] = true;
		combOfArchers(tgtIdx+1, srcIdx+1);
		selected[srcIdx] = false;
		combOfArchers(tgtIdx, srcIdx+1);
		
	}
	
	static void killEnemy() {
		int KillCnt = 0;
		boolean[] kill = new boolean[enemyCnt];
		
		// 적이 행군함 궁수로부터의 거리를 1씩 줄이는데 사용
		for(int march = 0; march < N; march++) {
			// 이번 행진간 각 궁수가 쏠 적을 찾았는지 확인
			boolean found_enemy_L = false;
			boolean found_enemy_M = false;
			boolean found_enemy_R = false;
			// 각 궁수가 쏠 적의 index
			int min_dist_Idx_L = 0;
			int min_dist_Idx_M = 0;
			int min_dist_Idx_R = 0;
			// 각 궁수가 쏠 적의 거리값
			int min_dist_L = N+M+1;
			int min_dist_M = N+M+1;
			int min_dist_R = N+M+1;
			
			for(int i = 0; i < enemyCnt; i++) {
				// 성벽에 이미 부딪힌애들 무시
				if((N-march) <= enemy[i].posi) continue;
				if(kill[i]) continue;
				
				// 맨 왼쪽 궁수부터 시작
				// 사정거리 안에 들어왔다면
				if((enemy[i].dist_L-march) <= D) {
					// 이미 찍어놓은적보다 더 가까운 적이라면 갱신
					if((enemy[i].dist_L-march) < min_dist_L) {
						min_dist_L = enemy[i].dist_L-march;
						min_dist_Idx_L = i;
					}
					// 이미 찍어놓은적과 거리가 같은데
					if((enemy[i].dist_L-march) == min_dist_L) {
						// 더 왼쪽에 있는 적인경우 갱신
						if(enemy[min_dist_Idx_L].posj > enemy[i].posj) {
							min_dist_L = enemy[i].dist_L-march;
							min_dist_Idx_L = i;
						}
					}
					// 적을 찾았음을 표시
					found_enemy_L = true;
				}
				
				// 가운데 궁수
				if((enemy[i].dist_M-march) <= D) {
					if((enemy[i].dist_M-march) < min_dist_M) {
						min_dist_M = enemy[i].dist_M-march;
						min_dist_Idx_M = i;
					}
					if((enemy[i].dist_M-march) == min_dist_M) {
						if(enemy[min_dist_Idx_M].posj > enemy[i].posj) {
							min_dist_M = enemy[i].dist_M-march;
							min_dist_Idx_M = i;
						}
					}
					found_enemy_M = true;
				}
				
				// 오른쪽 궁수
				if((enemy[i].dist_R-march) <= D) {
					if((enemy[i].dist_R-march) < min_dist_R) {
						min_dist_R = enemy[i].dist_R-march;
						min_dist_Idx_R = i;
					}
					if((enemy[i].dist_R-march) == min_dist_R) {
						if(enemy[min_dist_Idx_R].posj > enemy[i].posj) {
							min_dist_R = enemy[i].dist_R-march;
							min_dist_Idx_R = i;
						}
					}
					found_enemy_R = true;
				}
			}
			
			if(found_enemy_L) {
				// 죽이는 적이 겹치지는 않는지 체크
				if(!kill[min_dist_Idx_L]) {
					kill[min_dist_Idx_L] = true;
					KillCnt++;
				}
			}
			
			if(found_enemy_M) {
				if(!kill[min_dist_Idx_M]) {
					kill[min_dist_Idx_M] = true;
					KillCnt++;
				}
			}
			
			if(found_enemy_R) {
				if(!kill[min_dist_Idx_R]) {
					kill[min_dist_Idx_R] = true;
					KillCnt++;
				}
			}
		}
		
		MaxKill = Math.max(KillCnt, MaxKill);
		//System.out.println("KillCnt" + KillCnt);
		
	}
	
	// 적 클래스, 적 현위치와 각 궁수와의 거리 표시
	static class Enemy implements Comparable<Enemy>{
		int posi, posj;
		int dist, dist_L, dist_M, dist_R;
		
		public Enemy(int posi, int posj, int dist) {
			this.posi = posi;
			this.posj = posj;
			this.dist = dist;
		}
		
		public void setDist() {
			this.dist_L = archer[0].posi-this.posi + Math.abs(archer[0].posj-this.posj);
			this.dist_M = archer[1].posi-this.posi + Math.abs(archer[1].posj-this.posj);
			this.dist_R = archer[2].posi-this.posi + Math.abs(archer[2].posj-this.posj);
		}

		@Override
		public int compareTo(Enemy enemy) {
			if (this.dist < enemy.dist) {
				return -1;
			}
			else if (this.dist == enemy.dist) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	
	// 궁수 클래스, 궁수 위치와 현재 위치에서 쏠수 있는 적들을 포함
	static class Archer {
		int posi, posj;
		
		public Archer(int posj) {
			this.posi = N;
			this.posj = posj;
		}
	}

}
