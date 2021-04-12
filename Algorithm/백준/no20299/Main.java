package baekjoon.no20299;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, S, M;								// 신청한 동아리수, S조건, M조건
	static int COUNT;								// 가입이 가능한 동아리수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 버퍼드리더를 이용한 입력
		StringBuilder sb = new StringBuilder();
		
		// input
		StringTokenizer st = new StringTokenizer(br.readLine());		// 첫줄입력
		N = Integer.parseInt(st.nextToken());							// 동아리수 입력
		S = Integer.parseInt(st.nextToken());							// 팀합산점수 커트라인
		M = Integer.parseInt(st.nextToken());							// 개인점수 커트라인
		COUNT = 0;														// 가입가능 동아리수 초기화
		
		for(int i = 0; i < N; i++) {									// 동아리학생들의 능력치 입력
			st = new StringTokenizer(br.readLine());
			int X1 = Integer.parseInt(st.nextToken());
			int X2 = Integer.parseInt(st.nextToken());
			int X3 = Integer.parseInt(st.nextToken());
			
			if(X1 >= M && X2 >= M && X3 >= M) {							// 각 학생이 개인 능력치 커트라인을 넘는다면
				int sum = X1 + X2 + X3;									// 동아리 능력치 합산
				if(sum >= S) {
					COUNT++;
					sb.append(X1 + " " + X2 + " " + X3 + " ");			// 동아리 능력치가 커트라인을 넘는다면
				}
			}															// 가입가능 동아리
		}
		
		System.out.println(COUNT);										// 가입가능한 동아리 수 출력
		System.out.print(sb);

	}
}