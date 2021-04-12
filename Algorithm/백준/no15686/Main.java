package baekjoon.no15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, houseCnt, chicCnt;
	static int[][] house;
	static int[][] chic;
	static int[][] tgt_chic;
	static boolean[] selected;
	static int dist_sum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		houseCnt = 0;
		chicCnt = 0;
		house = new int[N*N][2];
		chic = new int[N*N][2];
		tgt_chic = new int[M][2];
		selected = new boolean[N*N];
		dist_sum = 5051;
		          
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					house[houseCnt][0] = i;
					house[houseCnt][1] = j;
					houseCnt++;
				}
				else if(temp == 2) {
					chic[chicCnt][0] = i;
					chic[chicCnt][1] = j;
					chicCnt++;
				}
			}
		}
		
		comb(0,0);
		
		System.out.println(dist_sum);

	}
	
	static void comb(int tgtIdx, int srcIdx) {
		if(srcIdx > chicCnt) return;
		
		else if(tgtIdx == M) {
			int tgt_chicCnt = 0;
			int dist_min = 0;
			for(int i = 0; i < chicCnt; i++) {
				if(selected[i]) {
					tgt_chic[tgt_chicCnt][0] = chic[i][0];
					tgt_chic[tgt_chicCnt][1] = chic[i][1];
					tgt_chicCnt++;
				}
			}
			
			for(int i = 0; i < houseCnt; i++) {
				int dist = 101;
				for(int j = 0; j < tgt_chicCnt; j++) {
					int dist_temp = 0;
					dist_temp += Math.abs(house[i][0] - tgt_chic[j][0]);
					dist_temp += Math.abs(house[i][1] - tgt_chic[j][1]);
					dist = Math.min(dist, dist_temp);
				}
				dist_min += dist;
			}
			dist_sum = Math.min(dist_min, dist_sum);
			return;
		}
		
		selected[srcIdx] = true;
		comb(tgtIdx+1, srcIdx+1);
		selected[srcIdx] = false;
		comb(tgtIdx, srcIdx+1);
		
	}

}
