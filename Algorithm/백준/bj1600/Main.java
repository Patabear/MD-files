package baekjoon.bj1600;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int K;
	static int H, W;
	static boolean[][] map;
	static boolean[][][] visit;
	static Queue<pos> q;
	final static int[][] d_horse = { {-2,-1}, {-1,-2}, {1,-2}, {-2, 1}, {-1, 2}, {2, -1}, {1, 2}, {2, 1} };
	final static int[][] d_monkey = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new boolean[H][W];
		visit = new boolean[H][W][K+1];
		q = new LinkedList<pos>();
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		
		q.offer(new pos(0, 0, 0, 0));
		
		while(!q.isEmpty()) {
			pos temp = q.poll();
			
			if(temp.i == H-1 && temp.j == W-1) {
				System.out.println(temp.depth);
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int next_i = temp.i + d_monkey[i][0];
				int next_j = temp.j + d_monkey[i][1];
				if( next_i < 0 ||  next_i >= H || next_j < 0 || next_j >= W) continue;
				if(map[next_i][next_j] || visit[next_i][next_j][temp.joker_used]) continue;
				
				visit[next_i][next_j][temp.joker_used] = true;
				q.offer(new pos(next_i, next_j, temp.joker_used, temp.depth+1));
			}
			
			if(temp.joker_used == K) continue;
			
			for(int i = 0; i < 8; i++) {
				int next_i = temp.i + d_horse[i][0];
				int next_j = temp.j + d_horse[i][1];
				if( next_i < 0 ||  next_i >= H || next_j < 0 || next_j >= W) continue;
				if(map[next_i][next_j] || visit[next_i][next_j][temp.joker_used+1]) continue; 
				
				visit[next_i][next_j][temp.joker_used+1] = true;
				q.offer(new pos(next_i, next_j, temp.joker_used+1, temp.depth+1));
			}
			
		}
		
		System.out.println("-1");
	}
	
	static class pos {
		int i;
		int j;
		int joker_used;
		int depth;
		public pos(int i, int j, int joker_used, int depth) {
			this.i = i;
			this.j = j;
			this.joker_used = joker_used;
			this.depth = depth;
		}
	}

}

