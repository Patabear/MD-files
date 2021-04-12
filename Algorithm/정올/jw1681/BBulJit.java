package jungol.jw1681;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BBulJit {
	static int N;
	static int[][] map;
	//static PriorityQueue<pos> PQ;
	static boolean[] visited;
	//static int[][] distance;
	final static int INF = 10000000;
	static int min_answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		//PQ = new PriorityQueue<>();
		visited = new boolean[N];
		//distance = new int[N][N];
		min_answer = INF;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//distance[i][j] = INF;
				if(i == 0 && map[i][j] != 0) {
					//PQ.add(new pos(j, map[i][j]));
				}
			}
		}
		
//		for(int i = 0; i < N; i++) {
//			dijkstra(i);
//		}
		
		find_min_route(0, 0, 0);
		
//		System.out.println(Arrays.toString(distance[0]));
//		System.out.println(Arrays.toString(distance[1]));
//		System.out.println(Arrays.toString(distance[2]));
//		System.out.println(Arrays.toString(distance[3]));
//		System.out.println(Arrays.toString(distance[4]));
		
		System.out.println(min_answer);
		

	}
	
//	static void dijkstra(int start) {
//		distance[start][start] = 0;
//		
//		PQ.add(new pos(start, 0));
//		
//		while(!PQ.isEmpty()) {
//			pos temp = PQ.poll();
//			
//			if(distance[start][temp.dest] < temp.dist) continue;
//			
//			for(int i = 0; i < N; i++) {
//				if(map[temp.dest][i] != 0) {
//					int next_dest = i;
//					int next_dist = temp.dist + map[temp.dest][i];
//					
//					if(next_dist < distance[start][next_dest]) {
//						distance[start][next_dest] = next_dist;
//						PQ.add(new pos(next_dest, next_dist));
//					}
//				}
//			}
//			
//		}
//	}
	
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
	
	static class pos implements Comparable<pos>{
		int dest;
		int dist;
		public pos(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}
		@Override
		public int compareTo(pos o) {
			return this.dist < o.dist ? this.dist : o.dist ;
		}
	}

}
