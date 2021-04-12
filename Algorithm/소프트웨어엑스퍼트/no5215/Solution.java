package swacademy.no5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, L;			// 테케, 재료개수, 총제한칼로리
	static int[][] score_cal;	// [index][0:score 1:cal]
	static int max_score;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			score_cal = new int[N][2];
			max_score = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				score_cal[i][0] = Integer.parseInt(st.nextToken());
				score_cal[i][1] = Integer.parseInt(st.nextToken());
			}
			
			comb(0, 0, 0);
			
			System.out.println("#" + testcase + " " + max_score);
		}

	}
	
	static void comb(int srcIdx, int score_sum, int cal_sum) {
		if(cal_sum > L) return;
		
		if(srcIdx == N) {
			max_score = Math.max(max_score, score_sum);
			return;
		}
		
		comb(srcIdx+1, score_sum + score_cal[srcIdx][0], cal_sum + score_cal[srcIdx][1]);
		comb(srcIdx+1, score_sum, cal_sum);
	}

}