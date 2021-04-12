package bj_16234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static HashMap<Integer, ArrayList<pos>> unions; 
	static int times;
	static int uni_cnt;
	static boolean is_moved;
	final static int[][] d = { {1,0}, {0,1}, {-1,0}, {0,-1} };

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		unions = new HashMap<>();
		times = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			visited = new boolean[N][N];
			unions.clear();
			uni_cnt = 0;
			is_moved = false;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						open_gate(new pos(i, j));
					}
				}
			}
			
			if(!is_moved) break;
			
			Set<Integer> keys = unions.keySet();
			
			for(int x: keys) {
				ArrayList<pos> arr = unions.get(x);
				int popu_sum = 0;
				
				for(pos p: arr) popu_sum += map[p.i][p.j];
				int popu_avg = popu_sum/arr.size();
				for(pos p: arr) map[p.i][p.j] = popu_avg;
			}
			times++;
		}
		
		System.out.println(times);
	}
	
	static void open_gate(pos start) {
		Queue<pos> q = new LinkedList<pos>();
		ArrayList<pos> arr = new ArrayList<>();
		arr.add(new pos(start.i, start.j));
		visited[start.i][start.j] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			int now_popu = map[temp.i][temp.j];
			
			for (int i = 0; i < 4; i++) {
				int next_i = temp.i+d[i][0];
				int next_j = temp.j+d[i][1];
				if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= N) continue;
				if(visited[next_i][next_j]) continue;
				int diff_popu = Math.abs(now_popu - map[next_i][next_j]);
				if(diff_popu < L || diff_popu > R) continue;
				
				visited[next_i][next_j] = true;
				arr.add(new pos(next_i, next_j));
				q.add(new pos(next_i, next_j));
				is_moved = true;
			}
		}
		
		unions.put(uni_cnt++, arr);
	}
	
	static class pos {
		int i, j;
		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
