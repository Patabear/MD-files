package baekjoon.bj1149;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	final static int INF = 10000000;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
			map[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int Rend = map[0][0];
		int Gend = map[0][1];
		int Bend = map[0][2];
		
		for(int i = 1; i < N; i++) {
			int temp_Rend = Math.min(Bend+map[i][0], Gend+map[i][0]);
			int temp_Gend = Math.min(Rend+map[i][1], Bend+map[i][1]);
			int temp_Bend = Math.min(Rend+map[i][2], Gend+map[i][2]);
			
			Rend = temp_Rend;
			Gend = temp_Gend;
			Bend = temp_Bend;
		}
		
		answer = Math.min(Math.min(Rend, Gend), Bend);
		
		System.out.println(answer);

	}

}
