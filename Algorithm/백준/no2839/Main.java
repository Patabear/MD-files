package baekjoon.no2839;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int answer = -1;
		
		int extra = N%5;
		
		if(extra == 0) answer = N/5;
		else if(extra == 1) {
			if(N>=6) {
				answer = (N-5)/5 + 2;
			}
		}
		else if(extra == 2) {
			if(N>=12) {
				answer = (N-10)/5 + 4;
			}
		}
		else if(extra == 3) {
			answer = N/5 + 1;
		}
		else if(extra == 4) {
			if(N>=9) {
				answer = (N-5)/5 + 3;
			}
		}
		
		System.out.println(answer);
	}

}


//public class Main {
//
//	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		
//		int five_times = -1, three_times = 0;
//		while(true) {
//			if(N<0) break;
//			
//			if(N%5 == 0) {
//				five_times = N/5;
//				break;
//			}
//			
//			N -= 3;
//			three_times++;
//		}
//		
//		if(five_times == -1) System.out.println("-1");
//		else System.out.println(five_times + three_times);
//	}
//
//}