package bj_no4153;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a == 0 && b == 0 && c == 0) break;
			
			a *= a;
			b *= b;
			c *= c;
			
			if((a+b==c) || (a+c==b) || (b+c==a)) System.out.println("right");
			else System.out.println("wrong");
		}
		

	}

}
