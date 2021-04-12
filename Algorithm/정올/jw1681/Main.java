package jungol.jw1681;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static boolean[] visited;
	final static int INF = 10000000;
	static int min_answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		visited = new boolean[N];
		min_answer = INF;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		find_min_route(0, 0, 0);
		
		System.out.println(min_answer);

	}
	
	
	static void find_min_route(int start, int sum, int srcIdx) {
		if(srcIdx == N-1) {
			if(visited[0]) {
				return;
			}
			else {
				if(map[start][0] != 0) {
					sum += map[start][0];
					min_answer = Math.min(min_answer, sum);
				}
				return;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(i == start && i == 0) continue;
			if(!visited[i] && map[start][i] != 0) {
				visited[i] = true;
				sum += map[start][i];
				if(sum < min_answer) find_min_route(i, sum, srcIdx+1);
				sum -= map[start][i];
				visited[i] = false;
			}
		}
	}
}
