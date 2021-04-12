package swacademy.no1247;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int work_i, work_j;
	static int home_i, home_j;
	static int[][] customers;	// 0:i 1:j
	static int answer_dist;
	static boolean[] select;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			customers = new int[N][2];
			select = new boolean[N];
			answer_dist = 30000;
			
			st = new StringTokenizer(br.readLine());
			work_i = Integer.parseInt(st.nextToken());
			work_j = Integer.parseInt(st.nextToken());
			home_i = Integer.parseInt(st.nextToken());
			home_j = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < N; i++) {
				customers[i][0] = Integer.parseInt(st.nextToken());
				customers[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < N; i++) {
				select[i] = true;
				find_road(1, calc_dist(customers[i][0], customers[i][1], -2), customers[i][0], customers[i][1]);
				select[i] = false;
			}
			
			System.out.println("#" + testcase + " " + answer_dist);
			
		}

	}
	
	static void find_road(int srcIdx, int dist, int i, int j) {
		if(answer_dist < dist) return;
		if(N == srcIdx) {
			dist += calc_dist(i, j, -1);
			if(dist < answer_dist) answer_dist = dist;
			return;
		}
		
		for(int k = 0; k < N; k++) {
			if(select[k]) continue;
			
			select[k] = true;
			find_road(srcIdx+1, dist+calc_dist(i, j, k), customers[k][0], customers[k][1]);
			select[k] = false;
		}
		
	}
	
	static int calc_dist(int i, int j, int Idx) {
		if(Idx == -1) return (Math.abs(home_i-i) + Math.abs(home_j-j));
		else if(Idx == -2) return (Math.abs(work_i-i) + Math.abs(work_j-j));
		else return (Math.abs(customers[Idx][0]-i) + Math.abs(customers[Idx][1] - j));
	}

}
