package baekjoon.bj17472;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] map;
	static ArrayList<ArrayList<pos>> Island;
	static boolean[][] visit_island;
	static int Island_cnt;
	static PriorityQueue<node> pq;
	static int[] parent;
	final static int[][] d = { {1,0}, {0,1}, {-1,0}, {0,-1} };
	final static int INF = 1000;
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		Island = new ArrayList<ArrayList<pos>>();
		visit_island = new boolean[N][M];
		pq = new PriorityQueue<>();
		 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		
		// 섬들을 찾음
		find_Island();
		
		// 섬들 개수로 임의의 그래프를 만듬
		Island_cnt = Island.size();
		parent = new int[Island_cnt];
		makeSet();
		
		// 그래프의 가중치를 찾음
		for(int i = 0; i < Island_cnt; i++) {
			get_weight(i);
		}
		
		int answer = 0;
		// 그래프로 MST 찾기
		while(!pq.isEmpty()) {
			node temp = pq.poll();
			if(findSet(temp.a) == findSet(temp.b)) continue;
			answer += temp.dist;
			union(temp.a, temp.b);
		}
		
		boolean one_piece = true;
		for(int i = 0; i < Island_cnt; i++) {
			if(findSet(i) != 0) one_piece = false;
		}
		if(one_piece) System.out.println(answer);
		else System.out.println("-1");
		

	}
	
	// 섬들을 찾는 함수
	static void find_Island() {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] && !visit_island[i][j]) find_land(new pos(i,j)); 
			}
		}
	}

	// 육지들로 이루어진 하나의 섬들의 좌표를 모두 얻어 하나의 ArrayList에 저장
	static void find_land(pos start) {
		ArrayList<pos> temp_island = new ArrayList<pos>();
		Queue<pos> q = new LinkedList<pos>();
		visit_island[start.i][start.j] = true;
		q.add(start);
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			temp_island.add(temp);
			
			for (int i = 0; i < 4; i++) {
				int next_i = temp.i+d[i][0];
				int next_j = temp.j+d[i][1];
				if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M) continue;
				if(!map[next_i][next_j] || visit_island[next_i][next_j]) continue;
				
				visit_island[next_i][next_j] = true;
				q.add(new pos(next_i, next_j));
			}
		}
		Island.add(temp_island);
	}
	
	// 섬들에게 번호를 매겨 그래프화, 가중치를 찾는 함수
	static void get_weight(int idx) {
		ArrayList<pos> lands = Island.get(idx);
		
		for(int k = 0; k < lands.size(); k++) {
			pos temp = lands.get(k);
			
			for (int i = 0; i < 4; i++) {
				int next_i = temp.i+d[i][0];
				int next_j = temp.j+d[i][1];
				if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M) continue;
				if(map[next_i][next_j]) continue;
				// 현재 next는 육지옆 물 칸임
				// 다리짓기 시작
				int length = 1;
				boolean find = false;
				int connected_island_idx = -1;
				while(true) {
					next_i = next_i+d[i][0];
					next_j = next_j+d[i][1];
					if(next_i < 0 || next_j < 0 || next_i >= N || next_j >= M) break; // 다리짓다 끝에도달
					if(map[next_i][next_j]) {
						connected_island_idx = get_Island_idx(new pos(next_i, next_j));
						find = true;
						break;
					}
					length++;
				}
				
				if(!find) continue;
				if(length == 1) continue;
				
				pq.add(new node(idx, connected_island_idx, length));
			}
		}
	}
	
	// 다리를 짓다가 만난 섬이 몇번 섬인지를 알아내는 함수
	static int get_Island_idx(pos point) {
		for(int i = 0; i < Island_cnt; i++) {
			ArrayList<pos> temp = Island.get(i);
			for(pos x: temp) {
				if(x.i == point.i && x.j == point.j) return i;
			}
		}
		return -1;
	}
	
	static void makeSet() {
		for(int i = 0; i < Island_cnt; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static void union(int x,  int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
	
	static class node implements Comparable<node>{
		int a, b, dist;
		public node(int a, int b, int dist) {
			this.a = a;
			this.b = b;
			this.dist = dist;
		}
		@Override
		public int compareTo(node o) {
			return this.dist < o.dist ? -1 : 1;
		}
	}
	
	static class pos {
		int i,j;
		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

}
