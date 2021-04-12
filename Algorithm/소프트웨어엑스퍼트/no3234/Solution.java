package swacademy.no3234;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] weight;
	static int weight_sum;
	static int answer_Cnt;
	static boolean[] selected;
	static int[] pow = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
	static int[] facto = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// input
		int T = Integer.parseInt(br.readLine());				// 테스트케이스 개수 입력
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			weight = new int[N];
			weight_sum = 0;
			answer_Cnt = 0;
			selected = new boolean[N];
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
				weight_sum += weight[i];
			}
			
			long before = System.currentTimeMillis();
			
			loop(0, 0, 0, weight_sum);
			
			System.out.println("#" + testcase + " " + answer_Cnt);
			
			long after = System.currentTimeMillis();
			System.out.println("time : " + (after-before));
		}

	}
	
	static void loop(int tgtIdx, int left_arm, int right_arm, int remain) {
		// 이미 왼쪽이 너무 무거워서 남은 추로 무슨짓을 해도 결과가 바뀌지 않는경우
		// 이 때 남은 추의 개수를 이용해 수학적으로 계산
		if(remain + right_arm <= left_arm) {
			// 남은 추의 개수 N-tgtIdx
			answer_Cnt += pow[N-tgtIdx] * facto[N-tgtIdx];
			return;
		}
		
		// 끝까지 무사히 도착하면 하나의 경우로 확인
		if(tgtIdx == N) {
			answer_Cnt++;
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			
			// 새로운거 왼팔에 올리기~
			loop(tgtIdx+1, left_arm + weight[i], right_arm, remain-weight[i]);
			
			// 새로운거 오른팔에 올리기~
			if( left_arm >= right_arm + weight[i] ) {
				loop(tgtIdx+1, left_arm, right_arm + weight[i], remain-weight[i]);
			}
			
			selected[i] = false;
		}
	}

}

// 여기서 더 시간을 줄이는 방법
// 이미 왼쪽이 너무 무거워서 남은 추로 무슨짓을 해도 결과가 바뀌지 않는경우를 줄임
// 이 때 남은추를 퍼뮤값을 수학적으로 계산후 종료
// 위의 remain-weight로 나눠서 수학적으로 뽑아내는 것이 생각치 못한것... 애바참치마요네즈샐러드