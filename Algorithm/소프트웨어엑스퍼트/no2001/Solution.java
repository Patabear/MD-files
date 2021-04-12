package swacademy.no2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T, N, M, sum; 									// T테스트케이스수, N장판크기, M파리채크기, sum파리합
		int[][] map;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());			// 테스트케이스 수
		
		for(int testcase = 1; testcase <= T; testcase++) {
			// input
			String[] string = br.readLine().split(" ");	
			N = Integer.parseInt(string[0]);			// 장판크기
			M = Integer.parseInt(string[1]);			// 파리채크기
			sum = 0;
			
			map = new int[N][N];
			for(int i = 0; i < N; i++) {				// 장판에 파리 마리수 입력
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// solve
			int U, D, L, R;			// 파리채 네모서리
			
			// 파리채 바운더리 이동
			for(U = 0, D = M-1; D < N; U++,D++) {		// 파리채 위아래 바운더리
				for(L = 0, R = M-1; R < N; L++,R++) {	// 파리채 좌우 바운더리
					
					// 파리채 내부 합
					int pari = 0;
					for(int i = U; i <= D; i++) {
						for(int j = L; j <= R; j++) {
							pari += map[i][j];			// 잡을 수 있는 파리들의 합
						}
					}
					sum = Math.max(pari, sum);			// 최대값 저장
					
				}
			}
			
			
			// output
			System.out.println("#" + testcase + " " + sum);
		}
		
		br.close();
	}

}
