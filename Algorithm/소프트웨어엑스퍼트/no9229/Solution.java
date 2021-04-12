package swacademy.no9229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M;
	static int[] a;
	static boolean[] select;
	static int Max_weight;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			a = new int[N];
			select = new boolean[N];
			Max_weight = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			comb(0, 0);
			
			if( Max_weight != 0 ) System.out.println("#" + testcase + " " + Max_weight);
			else System.out.println("#" + testcase + " -1");
		}

	}
	
	static void comb(int tgtIdx, int srcIdx) {
		if(tgtIdx == 2) {						// 양손을 뽑습니다
			int weight_sum = 0;
			for(int i = 0; i < N; i++) {
				if(select[i]) {
					weight_sum += a[i];			// 더해서
				}
			}
			if(weight_sum <= M) {				// 제한을 안넘으면
				Max_weight = Math.max(weight_sum, Max_weight);	// 갱신
			}
			return;
		}
		
		if(srcIdx < N) {
			select[srcIdx] = true;
			comb(tgtIdx+1, srcIdx+1);
			select[srcIdx] = false;
			comb(tgtIdx, srcIdx+1);
		}
		
	}

}


//			교수님의 코드 배열 두개로 나누어서 돌려버림 아따 개쩌네;
//public class SW_한빈이와SpotMart_9229_Array {
//
//	static int T, N, M, ans;
//	public static void main(String[] args) throws Exception {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		T = Integer.parseInt(br.readLine());
//		
//		for (int t = 1; t <= T; t++) {
//			
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			N = Integer.parseInt(st.nextToken()); // 과자 봉수 개수
//			M = Integer.parseInt(st.nextToken()); // 무게 제한
//			
//			int[] p1 = new int[N];
//			int[] p2 = new int[N];
//
//			ans = -1;
//
//			st = new StringTokenizer(br.readLine());
//			for (int i = 0; i < N; i++) {
//				
//				p1[i] = Integer.parseInt(st.nextToken());
//				p2[i] = p1[i];
//			}
//			
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if( i == j ) continue;
//					if( p1[i] + p2[j] <= M && p1[i] + p2[j] > ans) ans = p1[i] + p2[j];
//				}
//			}
//			
//			System.out.println("#" + t + " " + ans);
//		}
//	}
//
//}

