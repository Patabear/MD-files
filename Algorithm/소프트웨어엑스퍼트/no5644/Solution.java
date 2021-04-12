package swacademy.no5644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int M, A;			// 총 시간, BC개수
	static int[][] user_move;
	static ArrayList<AP>[][] arrayList;
	static int now;
	final static int d[][] = { {1,0}, {-1,0}, {0,1}, {0,-1} };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			
			user_move = new int[2][M];
			arrayList = new ArrayList[10][10];
			now = 0;
			
			for(int ind = 0; ind < 2; ind++) {
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < M; i++) {
					user_move[ind][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int k = 0; k < A; k++) {
				st = new StringTokenizer(br.readLine());
				int j = Integer.parseInt(st.nextToken());
				int i = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
			}
			
			while(now < M) {
				
			}
			
			
		}

	}
	
	static void mark_AP(int index, int i, int j, int c, int p) {
		for(int x = 1; x <= c; x++) {
			for(int k = 0; k < 4; k++) {
				int next_i = i + d[k][0]*x;
				int next_j = j + d[k][1]*x;
				if(next_i < 0 || next_j < 0 || next_i >= 10 || next_j >= 10) continue;
				
					
			}
		}
	}
	
	static class AP {
		int i;
		int j;
		int c;
		int p;
		public AP(int i, int j, int c, int p) {
			this.i = i;
			this.j = j;
			this.c = c;
			this.p = p;
		}
	}
}