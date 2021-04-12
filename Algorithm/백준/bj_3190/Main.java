package bj_3190;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] map;
	static int L;
	static Queue<dir> instruction;
	static Queue<pos> snake_body;
	static int direction; // 0: up 1: right 2: down 3: left
	final static int[][] d = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// input
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;
		}

		L = Integer.parseInt(br.readLine());
		instruction = new LinkedList<dir>();

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			instruction.add(new dir(x, c));
		}

		// solve
		
		// initiate
		snake_body = new LinkedList<pos>();
		snake_body.add(new pos(1, 1));
		direction = 1;
		
		int pos_i = 1;
		int pos_j = 1;
		int time = 1;
		int next_instruction_time = instruction.peek().X;
		
		// simulate
		while(true) {
			int next_i = pos_i + d[direction][0];
			int next_j = pos_j + d[direction][1];
			
			// wall collision check
			if(next_i < 1 || next_j < 1 || next_i >= N+1 || next_j >= N+1) break;
			
			// snake body collision check
			Iterator<pos> iter = snake_body.iterator();
			boolean collision_test = false;
			
			while(iter.hasNext()) {
				pos temp = iter.next();
				if(temp.i == next_i && temp.j == next_j) {
					collision_test = true;
					break;
				}
			}
			
			if(collision_test) break;
			
			// apple check
			snake_body.add(new pos(next_i, next_j));
			if(map[next_i][next_j] == 0) {
				snake_body.poll();
			} else {
				map[next_i][next_j] = 0;
			}
			
			// turn direction
			if(time == next_instruction_time) {
				dir temp = instruction.poll();
				turn(temp.C);
				if(instruction.peek() != null) {
					next_instruction_time = instruction.peek().X;
				} else {
					next_instruction_time = -1;
				}
			}
			
			// save state
			pos_i = next_i;
			pos_j = next_j;
			
			time++;
		}
		
		
		// output
		System.out.println(time);

	}

	static class dir {
		int X;
		char C;

		public dir(int x, char c) {
			this.X = x;
			this.C = c;
		}
	}

	static class pos{
		int i;
		int j;

		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static void turn(char c) {
		if (c == 'L') {
			direction = (direction + 4 - 1) % 4;
		} else if (c == 'D') {
			direction = (direction + 1) % 4;
		}
	}
}
