package bj_9205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BFS {
	static int N;
	static pos home;
	static pos[] conv;
	static pos fest;
	static boolean[] visited;
	static Queue<pos> q;
	static boolean is_happy;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			conv = new pos[N];
			visited = new boolean[N];
			q = new LinkedList<pos>();
			is_happy = false;
			
			st = new StringTokenizer(br.readLine());
			home = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				conv[i] = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(br.readLine());
			fest = new pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			q.add(home);
			
			while(!q.isEmpty()) {
				pos start = q.poll();
				
				if(is_reachable(start, fest)) {
					is_happy = true;
					break;
				}
				
				for(int i = 0; i < N; i++) {
					if(!visited[i] && is_reachable(start, conv[i])) {
						visited[i] = true;
						q.add(conv[i]);
					}
				}
			}
			
			if(is_happy) System.out.println("happy");
			else System.out.println("sad");
			
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
	
	static boolean is_reachable(pos a, pos b) {
		if(Math.abs(a.i-b.i) + Math.abs(a.j-b.j) > 1000) {
			return false;
		}
		else return true;
	}

}
