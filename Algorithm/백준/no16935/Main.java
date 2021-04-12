package baekjoon.no16935;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] tgt;
	static int N, M, R;		// 가로, 세로, 연산수
	static int order;		// 연산들

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());		// 가로
		M = Integer.parseInt(st.nextToken());		// 세로
		R = Integer.parseInt(st.nextToken());		// 연산 개수
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 입력
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) {
			order = Integer.parseInt(st.nextToken());	// 연산입력
			if(order == 1) one();
			else if(order == 2) two();
			else if(order == 3) three();
			else if(order == 4) four();
			else if(order == 5) five();
			else if(order == 6) six();
		}
		
		for(int[] x: tgt) {		// 출력
			for(int y: x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}

	}
	
	// 상하 반전
	static void one() {
		tgt = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tgt[N-i-1][j] = map[i][j];	// i값을 N/2기준으로 반전
			}
		}
		map = tgt.clone();					// 복사
	}
	
	// 좌우 반전
	static void two() {
		tgt = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tgt[i][M-j-1] = map[i][j];	// j값을 M/2기준으로 반전
			}
		}
		map = tgt.clone();					// 복사
	}
	
	// 오른쪽으로 90도 회전
	static void three() {
		tgt = new int[M][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tgt[j][N-i-1] = map[i][j];	// i,j값을 서로 바꾼뒤 시작하는 위치 반전 (대각반전 후 상하반전이라 생각하면 편함)
			}
		}
		map = tgt.clone();					// 복사
		int temp = M;						// N, M 값 서로 바꿈
		M = N;
		N = temp;
	}
	
	// 왼쪽으로 90도 회전
	static void four() {
		tgt = new int[M][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				tgt[M-j-1][i] = map[i][j];	// i,j값을 서로 바꾼뒤 시작하는 위치 반전 (대각반전 후 좌우반전이라 생각하면 편함)
			}
		}
		map = tgt.clone();					// 복사
		int temp = M;						// N, M 값 서로 바꿈
		M = N;
		N = temp;
	}
	
	// 1->2, 2->3, 3->4, 4->1  시계방향
	static void five() {
		tgt = new int[N][M];
		for(int rot = 0; rot < 4; rot++) { 						// 4분할로 움직일 4번 따로 계산
			int i_start = 0, i_end = 0, j_start = 0, j_end = 0;	// i와 j 의 시작, 끝위치를 조정
			int tgt_i_weight = 0, tgt_j_weight = 0;				// tgt배열에 넣을때 4분할만큼 움직여서 넣을 가중치
			if(rot == 0) {
				i_start = 0; i_end = N/2;						// 1분할 위치 선정
				j_start = 0; j_end = M/2;
				tgt_j_weight = M/2;								// tgt를 2분할 위치로 선정하게할 가중치
			}
			else if(rot == 1) {
				i_start = 0; i_end = N/2;						// 위와 동일 값만 변형
				j_start = M/2; j_end = M;
				tgt_i_weight = N/2;
			}
			else if(rot == 2) {
				i_start = N/2; i_end = N;
				j_start = M/2; j_end = M;
				tgt_j_weight = -(M/2);
			}
			else if(rot == 3) {
				i_start = N/2; i_end = N;
				j_start = 0; j_end = M/2;
				tgt_i_weight = -(N/2);
			}
			
			for(int i = i_start; i < i_end; i++) {
				for(int j = j_start; j < j_end; j++) {
					tgt[i+tgt_i_weight][j+tgt_j_weight] = map[i][j]; // 이동하여 반영
				}
			}
		}
		map = tgt.clone(); // 복사
	}
	
	// 1->4, 4->3, 3->2, 2->1  반시계방향
	static void six() {
		tgt = new int[N][M];
		for(int rot = 0; rot < 4; rot++) {						// 위와 같고 값만 알맞게 변경
			int i_start = 0, i_end = 0, j_start = 0, j_end = 0;
			int tgt_i_weight = 0, tgt_j_weight = 0;
			if(rot == 0) {
				i_start = 0; i_end = N/2;
				j_start = 0; j_end = M/2;
				tgt_i_weight = N/2;
			}
			else if(rot == 1) {
				i_start = N/2; i_end = N;
				j_start = 0; j_end = M/2;
				tgt_j_weight = M/2;
			}
			else if(rot == 2) {
				i_start = N/2; i_end = N;
				j_start = M/2; j_end = M;
				tgt_i_weight = -(N/2);
			}
			else if(rot == 3) {
				i_start = 0; i_end = N/2;
				j_start = M/2; j_end = M;
				tgt_j_weight = -(M/2);
			}
			
			for(int i = i_start; i < i_end; i++) {
				for(int j = j_start; j < j_end; j++) {
					tgt[i+tgt_i_weight][j+tgt_j_weight] = map[i][j];
				}
			}
		}
		map = tgt.clone(); // 복사
	}
}
