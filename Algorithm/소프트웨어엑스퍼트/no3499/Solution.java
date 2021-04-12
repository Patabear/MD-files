package swacademy.no3499;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int T, N;
	static String[] card;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			
			sb.setLength(0);
			
			N = Integer.parseInt(br.readLine());
			
			card = new String[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				card[i] = st.nextToken();
			}
			
			sb.append("#" + t + " ");
			
			for (int i = 0; i < N/2; i++) {
				
				if( N%2 == 0 ) {
					
					sb.append(card[i]).append(" ");
					sb.append(card[N/2 + i]).append(" ");

				}else {
					
					sb.append(card[i]).append(" ");
					sb.append(card[N/2 + i + 1]).append(" ");
					
				}
			}
			
			if( N%2 == 1 ) {
				
				sb.append(card[N/2]).append(" ");

			}
			
			System.out.println(sb);
		}
	}
}
