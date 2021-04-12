package no1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M, N, K;				// 가로 세로 배추개수
	static boolean[][] betU;		// 벳 츄 ~

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());	// 테케 수

		for(int testcase = 0; testcase < T; testcase++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			betU = new boolean[M][N];	// 맵상 배추좌표
			int answer = 0;				// 지렁지렁 몇마리?

			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				betU[x][y] = true;
			}

			// 모든위치에서 배추가 있다면 출발
			for(int i = 0; i < M; i++) {
				for(int j = 0; j < N; j++) {
					if(betU[i][j]) {
						YouKillYouDie(i, j);
						answer++;
					}
				}
			}

			System.out.println(answer);
		}
	}

	// 지렁이가 갈수있는 배추 모두  false처리
	static void YouKillYouDie(int i, int j) {

		if(i < M-1) {
			if(betU[i+1][j]) {
				betU[i+1][j] = false;
				YouKillYouDie(i+1, j);
			}
		}

		if(i > 0) {
			if(betU[i-1][j]) {
				betU[i-1][j] = false;
				YouKillYouDie(i-1, j);
			}
		}

		if(j < N-1) {
			if(betU[i][j+1]) {
				betU[i][j+1] = false;
				YouKillYouDie(i, j+1);
			}
		}

		if(j > 0) {
			if(betU[i][j-1]) {
				betU[i][j-1] = false;
				YouKillYouDie(i, j-1);
			}
		}
	}
}
