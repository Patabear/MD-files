package bj.no13300;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int gender_grade[][] = new int[2][6];
		int answer = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken());
			int grade = Integer.parseInt(st.nextToken());
			gender_grade[gender][grade-1]++;
		}
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 6; j++) {
				answer += Math.ceil(gender_grade[i][j]/(double)K);
//				int q = gender_grade[i][j]/K;
//				if(gender_grade[i][j]%K == 0) answer += q;
//				else answer += (q+1);
			}
		}
		
		System.out.println(answer);
	}
}
