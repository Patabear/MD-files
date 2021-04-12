package bj_9205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_DFS {
	static int N;
	static pos home;
	static pos[] conv;
	static pos fest;
	static boolean[] visited;
	static boolean[] dead_way;
	static boolean is_happy;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			conv = new pos[N];
			visited = new boolean[N];
			dead_way = new boolean[N];
			is_happy = false;
			
			st = new StringTokenizer(br.readLine());
			home = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				conv[i] = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			fest = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			find_way(home, -1);
			
			if(is_happy) System.out.println("happy");
			else System.out.println("sad");
			
		}

	}
	
	static void find_way(pos start, int conv_idx) {
		if(is_reachable(start, fest) || is_happy) {
			is_happy = true;
			return;
		}
		
		boolean no_way = true;
		
		for(int i = 0; i < N; i++) {
			if(!dead_way[i] && !visited[i] && is_reachable(start, conv[i])) {
				visited[i] = true;
				find_way(conv[i], i);
				visited[i] = false;
				no_way = false;
			}
		}
		
		if(no_way && conv_idx != -1) dead_way[conv_idx] = true;
	}
	
	static class pos {
		int i;
		int j;
		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static boolean is_reachable(pos a, pos b) {
		if(Math.abs(a.i-b.i) + Math.abs(a.j-b.j) > 1000) {
			return false;
		}
		else return true;
	}

}
