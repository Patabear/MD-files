package swacademy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_Contact_1238 {
	
	static int L, S, maxNum, maxDepth;
	static boolean[][] graph;  // graph[3][7]  3 --> 7
	static Queue<Node> queue = new LinkedList<Node>(); // bfs
	static boolean[] visit;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			
			graph = new boolean[101][101]; 
			visit = new boolean[101];
			maxNum = Integer.MIN_VALUE;
			maxDepth = Integer.MIN_VALUE;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < L/2; i++) {
				graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			visit[S] = true;
			queue.offer(new Node(S, 0));
			
			while( ! queue.isEmpty() ) {
				Node node = queue.poll();
				
				if( node.d > maxDepth ) {
					maxDepth = node.d;
					maxNum = node.n;
				}else if( node.d == maxDepth ) {
					maxNum = Math.max(maxNum, node.n);
				}
				
				for (int i = 1; i <= 100; i++) {
					if( graph[ node.n ][i] && ! visit[i] ) {
						visit[i] = true;
						queue.offer(new Node(i, node.d+1));
					}
				}
			}
			
			System.out.println("#" + t + " " + maxNum);
		}
	}
	
	
	static class Node{
		int n;
		int d;
		public Node(int n, int d) {
			this.n = n;
			this.d = d;
		}
	}
}
