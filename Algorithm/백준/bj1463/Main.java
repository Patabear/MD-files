package baekjoon.bj1463;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] cnt;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		cnt = new int[input+1];
		
		cnt[1] = 0;
		
		for(int i = 2; i <= input; i++) {
			cnt[i] = cnt[i-1] + 1;
			
			if(i % 3 == 0) cnt[i] = Math.min(cnt[i], cnt[i/3]+1);
			if(i % 2 == 0) cnt[i] = Math.min(cnt[i], cnt[i/2]+1);
		}
		
		System.out.println(cnt[input]);
	}

}
