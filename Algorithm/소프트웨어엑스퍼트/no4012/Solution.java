package swacademy.no4012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[][] SynergyMap;	// 시너지값 저장배열
	static int min_diff;		// 답이 될 변수 시너지값차이 최소값
	static boolean[] selected;	// comb에 사용할 bool배열
	static int tgt_length;		// N/2값을 가질예정
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());// 재료 개수 입력
			SynergyMap = new int [N][N];		// 시너지값 저장할 배열
			min_diff = (int) Math.pow(2, 31);	// 최소 차이값을 저장할 변수
			selected = new boolean[N];			// comb에 사용할 bool배열
			tgt_length = N/2;					// 한 요리의 재료는 전체의 반
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					SynergyMap[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 재료를 반으로 나눠 가지는 방법으로
			// 첫번째 재료를 가졌느냐 안가졌느냐로 나눠서 계산
			selected[0] = true;
			comb(1, 1);
			
			System.out.println("#" + testcase + " " + min_diff);
			}
	}
	
	static void comb(int tgtIdx, int srcIdx) {
		// N/2개를 뽑은경우
		if(tgtIdx == tgt_length) {
			calcSynergy();
			return;
		}
		
		// srcIdx가 N개를 넘어가버린경우
		if(srcIdx >= N) return;
		
		// comb 실시
		selected[srcIdx] = true;
		comb(tgtIdx+1, srcIdx+1);
		selected[srcIdx] = false;
		comb(tgtIdx, srcIdx+1);
		
	}
	
	static void calcSynergy() {
		// dish1과 dish2는 각 요리에 들어가는 재료들의 index를 가집니다.
		int[] dish1 = new int[N/2];
		int[] dish2 = new int[N/2];
		// 인덱스를 나눠 저장할때 쓸 임시 변수입니다.
		int dish1Idx = 0;
		int dish2Idx = 0;
		// 각 요리의 시너지 합을 저장할 변수입니다.
		int dish1Synergy = 0;
		int dish2Synergy = 0;
		// 시너지 차이  입니다.
		int synergyDiff = 0;
		
		// comb에서 뽑은애들은 dish1, 안뽑은애는 dish2로 나눕니다.
		for(int i = 0; i < N; i++) {
			if(selected[i])	dish1[dish1Idx++] = i;
			else dish2[dish2Idx++] = i;
		}
		
		// 첫번째 요리의 재료들로 시너지의 합을 구합니다.
		for(int i = 0; i < N/2; i++) {
			for(int j = i+1; j < N/2; j++) {
				dish1Synergy += SynergyMap[dish1[i]][dish1[j]];
				dish1Synergy += SynergyMap[dish1[j]][dish1[i]];
			}
		}
		
		// 두번째 요리의 재료들로 시너지의 합을 구합니다.
		for(int i = 0; i < N/2; i++) {
			for(int j = i+1; j < N/2; j++) {
				dish2Synergy += SynergyMap[dish2[i]][dish2[j]];
				dish2Synergy += SynergyMap[dish2[j]][dish2[i]];
			}
		}
		
		synergyDiff = Math.abs(dish1Synergy-dish2Synergy);
		
		min_diff = Math.min(min_diff, synergyDiff);
	}
	

}
