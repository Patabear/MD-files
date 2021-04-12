package baekjoon.no17135;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_캐슬디펜스_17135 {

	static int N, M, D, max;
	static int[][] map;	
	static int[] archer = new int[3]; //tgt
	
	static List<Enemy> enemy = new ArrayList<>();
	static PriorityQueue<Enemy> pqueue = new PriorityQueue<>(
			(e1, e2) -> e1.d == e2.d ? e1.x - e2.x : e1.d - e2.d
			);
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열 너비
		D = Integer.parseInt(st.nextToken()); // 거리
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		
		//조합
		comb(0,0);

		System.out.println(max);
	}

	// 궁사 3명이 뽑히면 호출
	static void check() {
		// 초기화
		enemy.clear(); // ArrayList (적)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if( map[i][j] == 1 ) enemy.add( new Enemy(i, j));
			}
		}
		
		
		// 시물레이션 진행
		int dead = 0;
		while(true) {
			// dead 계산
			
			// 궁수가 돌아가면서 발사
			// 궁수 한명 한명 당
			
			// PriorityQueue 필요 Enemy 담습니다. 우선 순위 : 궁수와의 거리
			
			for (int i = 0; i < archer.length; i++) {
				// enemy list 순회 적 하나에 대해서 현재 (i) 궁수와의 거리 계산
				// 거리가 D 범위안에 오는 것 그렇지 않은 것 구분
				
				// D 안에 오지 않은 적 ==> for 문 밖에서 1칸 내려온다. (신경X)
				// D 안에 있는 적  ==> 우선 순위 선택 ??? 결정 바로 삭제? 삭제 마크만 O
				// 우선순위에 맞게 적 하나를 결정 문제
				
				pqueue.clear();				
				int ac = archer[i];
				
				// 적을 순회하면서 현재 궁사와의 거리를 계산 D 안에 들어오면 pqueue 에 넣는다.
				for (int j = 0; j < enemy.size(); j++) {
					Enemy e = enemy.get(j);
					e.d = Math.abs( ac - e.x ) + Math.abs( N - e.y);
					
					if( e.d <= D ) pqueue.offer(e);
				}
				
				// 확인
				if( ! pqueue.isEmpty() ) pqueue.poll().target = true; // 마킹
				
			}
			
			// 사망 삭제
			// 안사망 한칸 내림
			Iterator<Enemy> iter = enemy.iterator();
			while(iter.hasNext()) {
				
				Enemy e = iter.next();
				if( e.target ) {
					iter.remove();
					dead++;
				}else if( e.y == N-1) {
					iter.remove();
				}else {
					e.y++;
				}
			}
			
			
			// 종료조건
			if( enemy.size() == 0 ) break;
		}
		
		// max 값 갱신
		max = Math.max(max, dead);
	}
	
	// 궁사 3자리 <--M
	static void comb(int srcIdx, int tgtIdx) {
		// 기저조건
		if( tgtIdx == 3) {
			// complete code
			check();
			return;
		}
		
		if( srcIdx == M ) return;
				
		archer[tgtIdx] = srcIdx; // 궁사 위치 결정
		
		comb(srcIdx + 1, tgtIdx + 1); // 현재 선택
		comb(srcIdx + 1, tgtIdx); // 현재 선택하지 않는다.
	}
	
	
	static class Enemy {
		int y, x, d; // d : 궁수와의 거리
		boolean target; // 타깃, 사망여부
		
		public Enemy(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
