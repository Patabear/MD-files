package baekjoon.bj14889;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] S;
	static boolean[] selected;
	static boolean[] selected_comb;
	static int[] team1;
	static int[] team2;
	static int team1_score;
	static int team2_score;
	static int min_diff;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		S = new int[N][N];
		selected = new boolean[N];
		min_diff = 4000000;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				S[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0,0);
		
		System.out.println(min_diff);

	}
	
	
	static void comb(int srcIdx, int tgtIdx) {
		if(tgtIdx == N/2) {
			team1_score = 0;
			team2_score = 0;
			team1 = new int[N/2];
			team2 = new int[N/2];
			int cnt1 = 0;
			int cnt2 = 0;
			for(int i = 0; i < N; i++) {
				if(selected[i]) {
					team1[cnt1++] = i;
				}
				else {
					team2[cnt2++] = i;
				}
			}
			
			selected_comb = new boolean[N/2];
			comb_team(0, 0);
			
			min_diff = Math.min(min_diff, Math.abs(team1_score-team2_score));
			return;
		}
		
		if(srcIdx == N) return;
		
		selected[srcIdx] = true;
		comb(srcIdx+1, tgtIdx+1);
		selected[srcIdx] = false;
		comb(srcIdx+1, tgtIdx);
	}
	
	static void comb_team(int srcIdx, int tgtIdx) {
		if(tgtIdx == 2) {
			int[] pair1 = new int[2];
			int[] pair2 = new int[2];
			int cnt1 = 0;
			int cnt2 = 0;
			for(int i = 0; i < N/2; i++) {
				if(selected_comb[i]) {
					pair1[cnt1++] = team1[i];
					pair2[cnt2++] = team2[i];
				}
			}
			
			int sum = 0;
			sum += S[pair1[0]][pair1[1]];
			sum += S[pair1[1]][pair1[0]];
			team1_score += sum;
			
			sum = 0;
			sum += S[pair2[0]][pair2[1]];
			sum += S[pair2[1]][pair2[0]];
			team2_score += sum;
			
			return;
		}
		
		if(srcIdx == N/2) {
			return;
		}
		
		selected_comb[srcIdx] = true;
		comb_team(srcIdx+1, tgtIdx+1);
		selected_comb[srcIdx] = false;
		comb_team(srcIdx+1, tgtIdx);
	}

}
