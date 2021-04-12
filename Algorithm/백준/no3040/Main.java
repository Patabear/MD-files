package baekjoon.no3040;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N = 9, T = 7;
	static int[] src, tgt, ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		src = new int[9];
		tgt = new int[7];
		ans = new int[7];
		
		for(int i = 0; i < 9; i++) {
			src[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0, 0);
		
		for(int x: ans)	System.out.println(x);
	}
	
	static void comb(int srcIdx, int tgtIdx, int mask) {
		if(tgtIdx == T) {					// 다 뽑은 경우
			int tempsum = 0, tempIdx = 0;
			for(int i = 0; i < N; i++) {
				if((mask & 1<<i) != 0) {
					tgt[tempIdx++] = src[i];
					tempsum += src[i];
				}
			}
			if(tempsum == 100) ans = tgt.clone();
			return;
		}
		
		// srcIdx가 N에 도달하여 무쓸모
		// 9명중 7명을 뽑아야하는데 이미 너무 덜뽑아서 이 뒤로 뽑아도 7명이 안될경우를 제외
		if((N-srcIdx+tgtIdx) < T || srcIdx == N) return;	
		
		comb(srcIdx+1, tgtIdx,  mask);
		comb(srcIdx+1, tgtIdx+1, mask|1<<srcIdx);
	}

}
