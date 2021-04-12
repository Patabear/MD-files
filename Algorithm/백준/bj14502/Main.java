package baekjoon.bj14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[] comb_visit;
	static ArrayList<walls> wall_arr;
	static ArrayList<pos> empty_arr;
	static int empty_cnt;
	static int wall_cnt;
	static int virus_cnt;
	static ArrayList<pos> virus;
	static int[][] d = { {1,0}, {0,1}, {-1,0}, {0,-1} };
	static int max_safe_area;
	static Queue<pos> q = new LinkedList<pos>();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		wall_arr = new ArrayList<walls>();
		empty_arr = new ArrayList<pos>();
		wall_cnt = 0;
		virus = new ArrayList<pos>();
		max_safe_area = 0;
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) empty_arr.add(new pos(i, j));
				else if(map[i][j] == 1) wall_cnt++;
				else if(map[i][j] == 2) virus.add(new pos(i, j));
			}
		}
		
		empty_cnt = empty_arr.size();
		virus_cnt = virus.size();
		comb_visit = new boolean[empty_cnt];
		
		comb(0, 0);
		
		for(walls x: wall_arr) {
			bfs(x);
		}
		
		System.out.println(max_safe_area);

	}
	
	static void bfs(walls w) {
		int[][] mini_map = new int[N][M];
		// 2중포인터라 생각하면 쉬움, 1차배열은 값을 복사해주지만 2차배열은 메모리주소값을 복사해주므로 2차배열은 같은것을 참조하게 되어버림
		for(int i = 0; i < N; i++) mini_map[i] = map[i].clone();
		mini_map[w.w1.i][w.w1.j] = 1;
		mini_map[w.w2.i][w.w2.j] = 1;
		mini_map[w.w3.i][w.w3.j] = 1;
		int infected_area = -virus_cnt;
		
		q.clear();
		for(pos x: virus) q.add(x);
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			infected_area++;
			
			for (int i = 0; i < 4; i++) {
				int next_i = temp.i+d[i][0];
				int next_j = temp.j+d[i][1];
				if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M) continue;
				if(mini_map[next_i][next_j] > 0) continue;
				
				mini_map[next_i][next_j] = 2;
				q.add(new pos(next_i, next_j));
			}
		}
		int safe_area = empty_cnt - 3 - infected_area;
		
		max_safe_area = Math.max(max_safe_area, safe_area);
		return;
	}
	
	static void comb(int tgtIdx, int srcIdx) {
		if(tgtIdx == 3) {
			pos[] temp_pos = new pos[3];
			int cnt = 0;
			for(int i = 0; i < empty_cnt; i++) {
				if(comb_visit[i])
					temp_pos[cnt++] = empty_arr.get(i);
			}
			walls temp = new walls(temp_pos[0], temp_pos[1], temp_pos[2]);
			wall_arr.add(temp);
			return;
		}
		
		if(srcIdx == empty_cnt) return;
		
		comb_visit[srcIdx] = true;
		comb(tgtIdx+1, srcIdx+1);
		comb_visit[srcIdx] = false;
		comb(tgtIdx, srcIdx+1);
	}
	
	static class walls {
		pos w1;
		pos w2;
		pos w3;
		public walls(pos w1, pos w2, pos w3) {
			this.w1 = w1;
			this.w2 = w2;
			this.w3 = w3;
		}	
	}
	
	static class pos {
		int i;
		int j;
		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
