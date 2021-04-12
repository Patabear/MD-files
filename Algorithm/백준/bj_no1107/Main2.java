package bj_no1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
	static int N;
	static int M;
	static int[] broken;
	static int start_from_bottom;
	static int start_from_top;
	static boolean start_from_bottom_valid;
	static boolean start_from_top_valid;
	static int max_BT;
	static int min_BT;
	static int button_pressCnt;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		broken = new int[M];
		start_from_bottom = 0;
		start_from_top = 0;
		start_from_bottom_valid = true;
		start_from_top_valid = true;
		max_BT = -1;
		min_BT = 10;
		button_pressCnt = 0;
		answer = 500000;

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
		
		
		boolean isbroken = false;
		for(int i = 0; i < 7; i++) {
			int div = (int) Math.pow(10, i);
			int comp = (N/div)%10;
			
			if(isbroken) {
				start_from_top += min_BT*div;
				start_from_bottom += max_BT*div;
			}
			
			else {
				boolean isbreak = false;
				for(int j = 0; j < M; j++) {
					if(comp == broken[j]) {
						isbroken = true;
						int[] temp = new int[2];
						temp = find_close_BT(comp);
						if(temp[0] == -1) {
							start_from_bottom_valid = false;
						}
						if(temp[1] == 100) {
							if(start_from_top > 0) {
								
							}
							
							start_from_top_valid = false;
						}
						start_from_top += temp[1]*div;
						start_from_bottom += temp[0]*div;
						
						isbreak = true;
						break;
					}
				}
				if(isbreak) continue;
				
				start_from_top += (N/div)%10*div;
				start_from_bottom += (N/div)%10*div;
			}
		}
		
//		System.out.println(start_from_top_valid);
//		System.out.println(start_from_bottom_valid);
//		
		System.out.println("start_from_top" + start_from_top);
		System.out.println("start_from_bot" + start_from_bottom);
		
		if(start_from_top_valid) {
			answer = Math.min(answer, Math.abs(N-start_from_top)+button_pressCnt);
		}
		if(start_from_bottom_valid) {
			answer = Math.min(answer, Math.abs(N-start_from_bottom)+button_pressCnt);
		}
		
		answer = Math.min(answer, Math.abs(N-100));
		
		System.out.println(answer);
	}
	
	// 지금 눌러야하는 버튼과 가까운 숫자들을 찾는 함수 
	// 0은 버튼보다 작은수중 가까운놈
	// 1은 버튼보다 큰수중 가까운놈
	static int[] find_close_BT(int comp) {
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
