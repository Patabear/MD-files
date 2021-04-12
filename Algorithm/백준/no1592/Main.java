package no1592;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] people = new int[N];
		
		int idx = 0;
		int answer = 0;
		while(people[idx] < M-1) {
			// 공을 받음
			people[idx]++;
			answer++;
			
			// 시계로 L만큼
			if((people[idx] & 1) == 1) {
				idx = (idx+L)%N;
			}
			// 반시계로 L만큼
			else {
				idx = (idx+(N-L))%N;
			}
		}
		
		System.out.println(answer);
	}

}
