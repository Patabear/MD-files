package bj_2573;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int garo;
	static int sero;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<pos> edges;
	static int time;
	final static int[][] d = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		map = new int[sero][garo];
		time = 0;
		
		for(int i = 0; i < sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < garo; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean no_iceberg = false;
		
		while(!no_iceberg) {
			visited = new boolean[sero][garo];
			edges = new ArrayList<pos>();
			
			boolean melt_once = false;
			boolean melt_twice = false; 
			
			for(int i = 0; i < sero; i++) {
				if(melt_twice) break;
				for(int j = 0; j < garo; j++) {
					if(!visited[i][j] && map[i][j] > 0) {
						if(melt_once) {
							melt_twice = true;
							break;
						}
						find_edges(new pos(i,j));
						melt_once = true;
					}
				}
			}
			
			if(melt_twice) {
				System.out.println(time);
				return;
			}
			
			if(melt_edges()) time++;
			else {
				no_iceberg = true;
			}
			
		}
		
		System.out.println("0");
	}
	
	static void find_edges(pos start) {
		Queue<pos> q= new LinkedList<pos>();
		visited[start.i][start.j] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int next_i = temp.i + d[i][0];
				int next_j = temp.j + d[i][1];
				
				if(next_i < 0 || next_j < 0 || next_i >= sero || next_j >= garo) continue;
				if(visited[next_i][next_j]) continue;
				
				if(map[next_i][next_j] <= 0) edges.add(temp);
				else {
					visited[next_i][next_j] = true;
					q.add(new pos(next_i, next_j));
				}
			}
		}
	}
	
	static boolean melt_edges() {
		if(edges.size() == 0) return false;
		for(pos x: edges) {
			map[x.i][x.j]--;
		}
		return true;
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
	
