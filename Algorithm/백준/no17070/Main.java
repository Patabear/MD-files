package no17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[][] map;
	static int state;		// 0:가로 1:세로 2:대각
	static int answer;
	public static void main(String[] args) throws Exception{
		// 1,2에서 시작하여
		// 가로일 경우 가로로 ,대각으로 1칸
		// 대각일경우  가로로,세로로 ,대각으로 1칸
		// 세로일경우 세로로,대각으로 1칸
		// 가는길이 막히지 않았다면 무슨짓을해도 벽지는 안찢어짐
		// 그냥 가고싶은대로간다 문도 하면 될듯
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		state = 0;
		answer = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) map[i][j] = true;
			}
		}
		
		move(0,1);
		
		System.out.println(answer);
		
	}
	
	static void move(int i, int j) {
		if(i == N-1 && j == N-1) {
			answer++;
			return;
		}
		
		boolean right = true;
		boolean down = true;
		if(state == 0) down = false;
		else if(state == 1) right = false;
		
		// 오른쪽으로 이동
		if(right) {
			if((j+1) < N) {
				if(!map[i][j+1]) {
					int before_state = state;	// 상태 저장후 복구용
					state = 0;
					move(i, j+1);
					state = before_state;
				}
			}
		}
		
		if(down) {
			if((i+1) < N) {
				if(!map[i+1][j]) {
					int before_state = state;
					state = 1;
					move(i+1, j);
					state = before_state;
				}
			}
		}
		
		if((i+1) < N && (j+1) < N) {
			if(!map[i+1][j+1] && !map[i+1][j] && !map[i][j+1]) {
				int before_state = state;
				state = 2;
				move(i+1, j+1);
				state = before_state;
			}
		}
	}

}
