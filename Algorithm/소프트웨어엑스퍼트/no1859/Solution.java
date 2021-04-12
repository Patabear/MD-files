package sw.no1859;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
	
		for(int testcase = 1; testcase <= T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int Prices[] = new int [N];
			int nowMax = 0;
			long answer = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				Prices[i] = Integer.parseInt(st.nextToken());
			}
			
			
			long temp_sum = 0;
			for(int i = N-1; i >= 0; i--) {
				if(nowMax < Prices[i]) {
					answer += temp_sum;
					temp_sum = 0;
					nowMax = Prices[i];
				}
				else {
					temp_sum += nowMax-Prices[i];
				}
			}
			if(temp_sum != 0) answer += temp_sum;
			
			System.out.println("#" + testcase + " " + answer);
		}
	}
}
