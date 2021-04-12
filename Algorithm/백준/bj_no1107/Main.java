package bj_no1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static boolean[] broken;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		broken = new boolean[10];
		answer = Math.abs(N-100);
		
		// 입력
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		for (int i = 0; i <= 1000000; i++) {
	        int c = i;
	        int len = check(c);
	        if (len > 0) {
	            int press = Math.abs(c - N);
	            answer = Math.min((press + len), answer);
	        }
		}
		
		System.out.println(answer);

	}
	
	static int check(int n) {
	    if (n == 0) {
	        if (broken[0]) {
	            return 0;
	        }
	        else {
	            return 1;
	        }
	    }
	    int len = 0;
	    while (n > 0) {
	        if (broken[n % 10]) return 0;
	        n = n / 10;
	        len += 1;
	    }

	    return len;
	}

}
