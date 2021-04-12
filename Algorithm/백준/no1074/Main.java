package baekjoon.no1074;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int answer = 0;

		int Idx = N-1;
		while(Idx>=0) {
			if((r>>Idx & 1) != 0) {
				answer += Math.pow(2, Idx*2+1);
			}
			if((c>>Idx & 1) != 0) {
				answer += Math.pow(2, Idx*2);
			}
			Idx--;
		}
		System.out.println(answer);
	}

}
