package baekjoon.bj1753;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int[] cost;
	static int V;
	static int E;
	static int K;
	static boolean[] visit;
	static PriorityQueue<Edge> queue;
	static HashMap<Integer, HashMap<Integer, Integer>> map;
	final static int INF = 100000000;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		cost = new int[V+1];
		visit = new boolean[V+1];
		queue = new PriorityQueue<Edge>();
		map = new HashMap<Integer, HashMap<Integer, Integer>>();
		for(int i = 0; i <= V; i++) {
			cost[i] = INF;
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(map.containsKey(u)) {
				if(map.get(u).containsKey(v)) {
					if(w < map.get(u).get(v)) map.get(u).replace(v, w); }
				else {
					map.get(u).put(v, w);
				}
			}
			else {
				HashMap<Integer, Integer> temp = new HashMap<>();
				temp.put(v, w);
				map.put(u, temp);
			}
		}
		
		dijkstra();
		
		for(int i = 1; i <= V; i++)	{
			if(cost[i] == INF) System.out.println("INF");
			else System.out.println(cost[i]);
		}


	}
	
	static void dijkstra() {
		cost[K] = 0;
		queue.offer(new Edge(K,0));
		
		while( !queue.isEmpty() ) {
			Edge pe = queue.poll();
			
			// visit
			if( visit[pe.v] ) continue;
			visit[pe.v] = true;
			
			if(!map.containsKey(pe.v)) continue;
			
			HashMap<Integer, Integer> list = map.get(pe.v);
			Set<Integer> keyset = list.keySet();
			
			for(int x: keyset) {
				int c = list.get(x);
				if( c + cost[pe.v] < cost[x] ) {
					cost[x] = c + cost[pe.v];
					c = cost[x];
					queue.offer(new Edge(x, c));
				}
			}
		}
	}

	static class Edge implements Comparable<Edge>{
		int v, c;
		public Edge(int v, int c) {
			this.v = v; this.c = c;
		}
		@Override
		public int compareTo(Edge o) {
			return this.c < o.c ? -1 : 1;
		}
	}
}
