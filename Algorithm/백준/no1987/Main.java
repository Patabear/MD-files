package baekjoon.no1987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;			// 가로 세로 길이
	static char[][] board;		// 입력받은 보드
	static boolean[] alphabet;	// 지나온 알파벳들을 기억할 배열
	static int Max_moveCnt;		// 답
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		alphabet = new boolean[26];
		Max_moveCnt = 0;
		
		for(int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		// 첫 칸의 알파벳을 기억한 후 1칸움직인것으로 치고 출발
		alphabet[board[0][0]-'A'] = true;
		move(0, 0, 1);
		
		System.out.println(Max_moveCnt);
		
	}
	
	static void move(int i, int j, int moveCnt) {
		boolean noway_to_go = true;		// 어느 방향도 가지 못했을 경우를 위한 변수
		
		// up
		if(i > 0) {
			// up한 칸의 알파벳이 이미 지난 알파벳인지 확인
			if(!alphabet[board[i-1][j]-'A']) {
				// 갈수 있는 칸이므로 체크하고 진입
				alphabet[board[i-1][j]-'A'] = true;
				move(i-1, j, moveCnt+1);
				alphabet[board[i-1][j]-'A'] = false;
				noway_to_go = false;	// 어느방향이든 가긴 갔음을 기억
			}
		}
		
		// down
		if(i < (R-1)) {
			if(!alphabet[board[i+1][j]-'A']) {
				alphabet[board[i+1][j]-'A'] = true;
				move(i+1, j, moveCnt+1);
				alphabet[board[i+1][j]-'A'] = false;
				noway_to_go = false;
			}
		}
		
		// left
		if(j > 0) {
			if(!alphabet[board[i][j-1]-'A']) {
				alphabet[board[i][j-1]-'A'] = true;
				move(i, j-1, moveCnt+1);
				alphabet[board[i][j-1]-'A'] = false;
				noway_to_go = false;
			}
		}
		
		// right
		if(j < (C-1)) {
			if(!alphabet[board[i][j+1]-'A']) {
				alphabet[board[i][j+1]-'A'] = true;
				move(i, j+1, moveCnt+1);
				alphabet[board[i][j+1]-'A'] = false;
				noway_to_go = false;
			}
		}
		
		// 더 갈곳이 없음 여기서 지나온 Cnt값을 비교후 저장
		if(noway_to_go) {
			Max_moveCnt = Math.max(Max_moveCnt, moveCnt);
			return;
		}
	}
}
