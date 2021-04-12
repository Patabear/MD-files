package swacademy.sw1263;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] map;
	final static int BIG = 10000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	      StringTokenizer st;
	      int T = Integer.parseInt(br.readLine());
	      
	      for(int testcase = 1; testcase <= T; testcase++) {
	    	 st = new StringTokenizer(br.readLine());
	         N = Integer.parseInt(st.nextToken());
	         map = new int[N][N];
	         
	         for(int i = 0; i < N; i++) {
	        	 for(int j = 0; j < N; j++) {
	        		 int n = Integer.parseInt(st.nextToken());
	        		 if( i != j && n == 0) map[i][j] = BIG;
	        		 else map[i][j] = n;
	        	 }
	         }
	         
	         for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if(k == i) continue;
					for (int j = 0; j < N; j++) {
						if(i == j || k == j) continue;
						map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					}
				}
			}
	         
	         int min = Integer.MAX_VALUE;
	         for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += map[i][j];
				}
				min = Math.min(min, sum);
			}
	         
	        System.out.println("#" + testcase + " " + min); 
	         
	         
	         
	         
	         
	         
	      }

	}

}
