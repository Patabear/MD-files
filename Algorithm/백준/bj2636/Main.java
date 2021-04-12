package baekjoon.bj2636;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int garo;
	static int sero;
	static int[][] map;
	static boolean[][] air;
	static boolean[][] visited;
	static Set<pos> edges;
	static int time;
	static int cut_cheese;

	public static void main(String[] args) throws Exception{
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
		
		boolean no_cheese = false;
		
		while(!no_cheese) {
			find_air();
			visited = new boolean[sero][garo];
			edges = new HashSet<>();
			
			for(int i = 0; i < sero; i++) {
				for(int j = 0; j < garo; j++) {
					if(!visited[i][j] && map[i][j] == 1) {
						find_edges(new pos(i,j));
					}
				}
			}
			
			if(cut_edges()) time++;
			else no_cheese = true;
			
		}
		
		System.out.println(time);
		System.out.println(cut_cheese);

	}
	
	static boolean cut_edges() {
		if(edges.size() == 0) return false;
		cut_cheese = edges.size();
		for(pos x: edges) {
			map[x.i][x.j] = 0;
		}
		return true;
	}
	
	static void find_edges(pos start) {
		Queue<pos> q= new LinkedList<>();
		visited[start.i][start.j] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			
			if(temp.i > 0 && !visited[temp.i-1][temp.j]) {
				if(air[temp.i-1][temp.j]) edges.add(temp);
				else if(map[temp.i-1][temp.j] == 1) {
					visited[temp.i-1][temp.j] = true;
					q.add(new pos(temp.i-1, temp.j));
				}
			}
			if(temp.j > 0 && !visited[temp.i][temp.j-1] && map[temp.i][temp.j-1] == 0) {
				if(air[temp.i][temp.j-1]) edges.add(temp);
				else if(map[temp.i][temp.j-1] == 1) {
					visited[temp.i][temp.j-1] = true;
					q.add(new pos(temp.i, temp.j-1));
				}
			}
			if(temp.i < sero-1 && !visited[temp.i+1][temp.j] && map[temp.i+1][temp.j] == 0) {
				if(air[temp.i+1][temp.j]) edges.add(temp);
				else if(map[temp.i+1][temp.j] == 1) {
					visited[temp.i+1][temp.j] = true;
					q.add(new pos(temp.i+1, temp.j));
				}
			}
			if(temp.j < garo-1 && !visited[temp.i][temp.j+1] && map[temp.i][temp.j+1] == 0) {
				if(air[temp.i][temp.j+1]) edges.add(temp);
				else if(map[temp.i][temp.j+1] == 1) {
					visited[temp.i][temp.j+1] = true;
					q.add(new pos(temp.i, temp.j+1));
				}
			}
		}
	}
	
	static void find_air() {
		Queue<pos> q= new LinkedList<>();
		air = new boolean[sero][garo];
		air[0][0] = true;
		q.add(new pos(0,0));
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			
			if(temp.i > 0 && !air[temp.i-1][temp.j] && map[temp.i-1][temp.j] == 0) {
				air[temp.i-1][temp.j] = true;
				q.add(new pos(temp.i-1, temp.j));
			}
			if(temp.j > 0 && !air[temp.i][temp.j-1] && map[temp.i][temp.j-1] == 0) {
				air[temp.i][temp.j-1] = true;
				q.add(new pos(temp.i, temp.j-1));
			}
			if(temp.i < sero-1 && !air[temp.i+1][temp.j] && map[temp.i+1][temp.j] == 0) {
				air[temp.i+1][temp.j] = true;
				q.add(new pos(temp.i+1, temp.j));
			}
			if(temp.j < garo-1 && !air[temp.i][temp.j+1] && map[temp.i][temp.j+1] == 0) {
				air[temp.i][temp.j+1] = true;
				q.add(new pos(temp.i, temp.j+1));
			}
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
