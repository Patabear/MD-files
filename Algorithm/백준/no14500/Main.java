package no14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 각 테트로미노의 꼬다리 한쪽을 잡고 신나게 돌립니다
	// 구현 ㅈ된다..
	
	// 4방향이 아닌 2방향만 체크하면서 내려감
	// 긴다리 네모 제외 나머지 3개는 6칸을 차지한다고 봄
	
	// ㅁ ㅁ    ㅁ ㅁ ㅁ
	// ㅁ ㅁ    ㅁ ㅁ ㅁ 
	// ㅁ ㅁ
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int answer = 0;
		
		// 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				// 막대
				// 세로로 두었을 때
				if(i+3 < N) {
					int temp_sum = map[i][j]+map[i+1][j]+map[i+2][j]+map[i+3][j];
					answer = Math.max(temp_sum, answer);
				}
				// 가로로 두었을 때
				if(j+3 < M) {
					int temp_sum = map[i][j]+map[i][j+1]+map[i][j+2]+map[i][j+3];
					answer = Math.max(temp_sum, answer);
				}
				
				// 네모
				if(i+1 < N && j+1 < M) {
					int temp_sum = map[i][j]+map[i][j+1]+map[i+1][j]+map[i+1][j+1];
					answer = Math.max(temp_sum, answer);
				}
				
				// 6칸짜리
				// 세로
				if(i+2 < N && j+1 < M) {
					int temp_sum;
					// ㄱ자
					temp_sum = map[i][j]+map[i][j+1]+map[i+1][j]+map[i+2][j];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j]+map[i][j+1]+map[i+1][j+1]+map[i+2][j+1];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j]+map[i+1][j]+map[i+2][j]+map[i+2][j+1];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+1]+map[i+1][j+1]+map[i+2][j]+map[i+2][j+1];
					answer = Math.max(temp_sum, answer);
					
					// 지그재그
					temp_sum = map[i][j]+map[i+1][j]+map[i+1][j+1]+map[i+2][j+1];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+1]+map[i+1][j]+map[i+1][j+1]+map[i+2][j];
					answer = Math.max(temp_sum, answer);
					
					// 법규
					temp_sum = map[i][j]+map[i+1][j]+map[i+1][j+1]+map[i+2][j];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+1]+map[i+1][j]+map[i+1][j+1]+map[i+2][j+1];
					answer = Math.max(temp_sum, answer);
				}
				//가로
				if(i+1 < N && j+2 < M) {
					int temp_sum;
					// ㄱ자
					temp_sum = map[i][j]+map[i][j+1]+map[i][j+2]+map[i+1][j+2];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j]+map[i][j+1]+map[i][j+2]+map[i+1][j];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j]+map[i+1][j]+map[i+1][j+1]+map[i+1][j+2];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+2]+map[i+1][j]+map[i+1][j+1]+map[i+1][j+2];
					answer = Math.max(temp_sum, answer);
					
					// 지그재그
					temp_sum = map[i][j]+map[i][j+1]+map[i+1][j+1]+map[i+1][j+2];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+1]+map[i][j+2]+map[i+1][j]+map[i+1][j+1];
					answer = Math.max(temp_sum, answer);
					
					// 법규
					temp_sum = map[i][j]+map[i][j+1]+map[i][j+2]+map[i+1][j+1];
					answer = Math.max(temp_sum, answer);
					temp_sum = map[i][j+1]+map[i+1][j]+map[i+1][j+1]+map[i+1][j+2];
					answer = Math.max(temp_sum, answer);
				}
			}
		} // for문 End
		
		System.out.println(answer);
	}
}
