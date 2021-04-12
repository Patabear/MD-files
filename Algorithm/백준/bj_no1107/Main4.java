package bj_no1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main4 {
	static int N;
	static int M;
	static int[] broken;
	static int start_from;
	static int start_from_bottom;
	static int start_from_top;
	static boolean start_from_valid;
	static boolean start_from_bottom_valid;
	static boolean start_from_top_valid;
	static int max_BT;
	static int min_BT;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		broken = new int[M];
		start_from = 0;
		start_from_bottom = 0;
		start_from_top = 0;
		start_from_valid = false;
		start_from_bottom_valid = false;
		start_from_top_valid = false;
		max_BT = -1;
		min_BT = 10;
		answer = 0;
		
		// 입력
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				broken[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누를수 있는 버튼중 가장 큰수와 작은수
		for (int i = 0; i < 10; i++) {
			boolean isbroken = false;
			for(int x: broken) {
				if(x == i) {
					isbroken = true;
					break;
				}
			}
			if(!isbroken) {
				max_BT = Math.max(max_BT, i);
				min_BT = Math.min(min_BT, i);
			}
		}
		
//		System.out.println("Max Min " + max_BT + min_BT);
		
		// 해당버튼이 있으면 그대로 진행
		// 해당버튼이 없으면 위나 아래 버튼으로 가는데
		// 아래버튼일경우 이후 최댓값 찾기
		// 위버튼일경우 이후 최소값 찾기
		boolean number_started = false;
		for(int i = 5; i >= 0; i--) {
			int div = (int) Math.pow(10, i);
			int comp1 = (N/div)%10;
			boolean brokenBT = false;
			
			// 숫자가 시작이 안되었는데 0이 나온경우 무시
			if(!number_started && comp1 == 0) {
				if(i != 0) {
					continue;
				}
			}
			
			// 처음으로 0이 아니라서 숫자가 시작된경우
			if(comp1 != 0 && !number_started) {
				number_started = true;
				answer += i+1;
			}
			
			else if(comp1 == 0 && i == 0 && !number_started) {
				number_started = true;
				answer += i+1;
			}
			
			// 고장난 버튼인지 확인
			for(int j = 0; j < M; j++) {
				if(comp1 == broken[j]) {
					brokenBT = true;
					break;
				}
			}
			
//			System.out.println("answer = " + answer);
			
			// 고장난 버튼이라면
			if(brokenBT) {
				start_from_bottom = start_from; // 여태까지 누른 채널은 그대로 받아옴
				start_from_top = start_from;
				start_from_valid = false; // 정확하게 맞은 채널을 켤수 없게됨
				int[] BT = new int[2];
				BT = find_close_BT(comp1);
				// comp1보다 작은수부터 위로 올라감
				if(BT[0] != -1) {
					start_from_bottom += BT[0]*div;
					for(int k = div/10; k >= 1; k/=10) {
						start_from_bottom += max_BT*k;
					}
					start_from_bottom_valid = true;
				}
				// comp1보다 큰수부터 아래로 내려감
				if(BT[1] != 100) {
					start_from_top += BT[1]*div;
					for(int k = div/10; k >= 1; k/=10) {
						start_from_top += min_BT*k;
					}
					start_from_top_valid = true;
				}
				// 최대 또는 최솟값 찾기
				break;
			}
			// 고장나지 않은 버튼이라면
			else {
				start_from += (N/div)%10*div;
				start_from_valid = true;
			}

//			System.out.println("answer = " + answer);
			
		}
		
//		System.out.println(start_from_valid);
//		System.out.println(start_from_top_valid);
//		System.out.println(start_from_bottom_valid);
//		System.out.println("start_from_top" + start_from_top);
//		System.out.println("start_from_bot" + start_from_bottom);
		
//		System.out.println("answer = " + answer);
		
		if(start_from_top > 500000) start_from_top_valid = false;
		if(start_from_bottom > 500000) start_from_bottom_valid = false;
		
		// 아예 찾지를 못함
		if(!start_from_valid && !start_from_top_valid && !start_from_bottom_valid) {
			answer = Math.abs(N-100);
		}
		
		// 정확한 채널을 누른 경우
		else if(start_from_valid) {
			// nothing
		}
		
		// 정확한 채널을 누르지 못한경우
		else {
			// 위아래로 찾은경우 차이가 적은쪽 채용
			if(start_from_bottom_valid && start_from_top_valid) {
				int min = Math.min(Math.abs(N-start_from_bottom), Math.abs(N-start_from_top));
				//System.out.println("min = " + min);
				answer += min;
			}
			else if(start_from_top_valid) {
				answer += Math.abs(N-start_from_top);
			}
			else if(start_from_bottom_valid) {
				answer += Math.abs(N-start_from_bottom);
			}
		}
		
		answer = Math.min(answer, Math.abs(N-100));
		
		System.out.println(answer);

	}
	
	// 지금 눌러야하는 버튼과 가까운 숫자들을 찾는 함수 
	// 0은 버튼보다 작은수중 가까운놈
	// 1은 버튼보다 큰수중 가까운놈
	static int[] find_close_BT(int comp) {
//		System.out.println("comp = " + comp);
		int return_val[] = new int[2];
		return_val[0] = -1;
		return_val[1] = 100;
		
		for(int i = comp-1; i >= 0; i--) {
			boolean isbroken = false;
			for(int j = 0; j < M; j++) {
				if(broken[j] == i) {
					isbroken = true;
					break;
				}
			}
			
			if(isbroken) continue;
			else {
				return_val[0] = i;
				break;
			}
		}
		
		for(int i = comp+1; i <= 9; i++) {
			boolean isbroken = false;
			for(int j = 0; j < M; j++) {
				if(broken[j] == i) {
					isbroken = true;
					break;
				}
			}
			
			if(isbroken) continue;
			else {
				return_val[1] = i;
				break;
			}
		}
		
//		System.out.println(Arrays.toString(return_val));
		return return_val;
		
	}

}
