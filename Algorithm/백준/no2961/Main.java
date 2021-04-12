package baekjoon.no2961;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] S, B;
	static int diff;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		B = new int[N];
		diff = 1000000000;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0,0);
		
		System.out.println(diff);
		
	}
	
	static void perm(int srcIdx, int mask) {
		if(srcIdx == N) {
			if(mask == 0) return;
			int tempS = 1, tempB = 0;
			for(int i = 0; i < N; i++) {
				if((mask & 1<<i) != 0) {
					tempS *= S[i];
					tempB += B[i];
				}
			}
			int tempDiff = tempS > tempB ? tempS-tempB : tempB-tempS;
			diff = tempDiff < diff ? tempDiff : diff;
			return;
		}
		
		perm(srcIdx+1, mask);
		perm(srcIdx+1, mask|1<<srcIdx);
	}

}
