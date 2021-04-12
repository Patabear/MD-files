package no2579;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int Max_score;
	static int[] step_score;
	static int[] stair_score;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Max_score = 0;
		step_score = new int[N];
		stair_score = new int[N];
		
		for(int i = 0; i < N; i++) {
			step_score[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
		
		System.out.println(Max_score);	
	}
	
	// 지금 srcIdx값은 한계단 밟았을때의 index입니다.	
	static void comb(int srcIdx, int stepCnt, int score_sum) {
		// 마지막 계단을 이미 밟고옴, 완료한 경우
		if(srcIdx == N) {
			Max_score = Math.max(Max_score, score_sum);
			return;
		}
		
		// 계단을 1개 오른 경우
		if(stepCnt < 2) {	// 연속으로 1계단씩 2번 밟지 않았다면
			int score_sum_by_one_step = calcScore(score_sum, srcIdx);
			comb(srcIdx+1, stepCnt+1, score_sum_by_one_step);
		}
		
		// 계단을 2개 오른 경우
		if(srcIdx+1 < N) {	// 2계단 위에가 아직 있을경우
			int score_sum_by_two_step = calcScore(score_sum, srcIdx+1);
			
			// 두계단을 올라서 왔는데 이미 여기까지 와본놈이 있었다면
			// 비교후에 지금 가려는 애가 더크다면 진행, 아니라면 진행 X
			// 계단 1개오른경우를 비교하지않는 이유 : 1개오르면 어차피 다음엔 무조건 2개 올라야해서
			// 근데 2계단 오른경우에는 또 2계단 올라도 되니 제약조건 X
			if(score_sum_by_two_step > stair_score[srcIdx+1]) {
				stair_score[srcIdx+1] = score_sum_by_two_step;
				comb(srcIdx+2, 1, score_sum_by_two_step);
			}
		}
	}
	
	static int calcScore(int score_sum, int step_score_idx) {
		return score_sum + step_score[step_score_idx];
	}
}
