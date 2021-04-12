package swacademy.no1954;

import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		int[][] map;
		boolean[][] map_check;
		int T, N;
		
		// index => 0:right | 1:down | 2:left | 3:right 
		int[] di = new int[] {0,1,0,-1};
		int[] dj = new int[] {1,0,-1,0};
		
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = sc.nextInt();
			map = new int[N][N];
			map_check = new boolean[N][N];
			
			int t = N*N;
			int i = 0, j = 0, num = 1, going = 0, bound = N-1;
			// going -> 0:right | 1:down | 2:left | 3:right 
			while(t-->0) {
				// 숫자채움
				map_check[i][j] = true;
				map[i][j] = num;
				num++;
				
				// i,j 이동
				if(going == 0) {
					// 벽에부딪힘
					if((j+dj[0]) > bound) {
						i += di[1];
						going = 1;
						continue;
					}
					// 이미 숫자가 들어있는 칸에 부딪힘
					else if(map_check[i][j+dj[0]] == true) {
						i += di[1];
						going = 1;
						continue;
					}
					else {
						j += dj[0];
					}
				}
				
				else if(going == 1) {
					if((i+di[1]) > bound) {
						j += dj[2];
						going = 2;
						continue;
					}
					else if(map_check[i+di[1]][j] == true) {
						j += dj[2];
						going = 2;
						continue;
					}
					else {
						i += di[1];
					}
				}
				
				else if(going == 2) {
					if((j+dj[2]) < 0) {
						i += di[3];
						going = 3;
						continue;
					}
					else if(map_check[i][j+dj[2]] == true) {
						i += di[3];
						going = 3;
						continue;
					}
					else {
						j += dj[2];
					}
				}
				
				else if(going == 3) {
					if((i+di[3]) < 0) {
						j += dj[0];
						going = 0;
						continue;
					}
					else if(map_check[i+di[3]][j] == true) {
						j += dj[0];
						going = 0;
						continue;
					}
					else {
						i += di[3];
					}
				}
			}
			
			System.out.println("#" + testcase);
			for(i = 0; i < N; i++) {
				for(j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			
			
		}
		
		sc.close();
	}

}
