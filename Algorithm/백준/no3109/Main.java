package baekjoon.no3109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C;			// 맵 가로 세로
	static boolean[][] map;		// 1이면 벽 0이면 빈칸
	static int pipe_installed;	// 답
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];
		pipe_installed = 0;
		
		//long before = System.currentTimeMillis();
		
		for(int i = 0; i < R; i++) {
			char[] temp = new char[C];
			temp = br.readLine().toCharArray();
			for(int j = 0; j < C; j++) {
				if(temp[j] == 'x') map[i][j] = true;
			}
		}
		
		for(int i = 0; i < R; i++) {
			if(find_route(i, 0)) pipe_installed++;
		}
		
		System.out.println(pipe_installed);
		
		//long after = System.currentTimeMillis();
		//System.out.println("시간차이 : " + ((after-before)/1000));

	}
	
	// 파이프가 끝까지 설치되면 쭉 true리턴 아니라면 false
	static boolean find_route(int i, int j) {
		// 나야나씨 식당에 도달
		if(j == (C-1)) {
			return true;
		}
		
		// 오른쪽 위
		if((i-1) >= 0) {					// 맵의 테두리에 닿지않고
			if(!map[i-1][j+1]) {			// 벽이 없다면
				map[i-1][j+1] = true;		// 파이프가 지나가니 벽 표시를 하고
				if(find_route(i-1, j+1)) {	// 파이프설치가 완료되면 
					return true;			// return true;
				}
			}
		}
		
		// 오른쪽
		if(!map[i][j+1]) {			// 벽이 없다면
			map[i][j+1] = true;		// 파이프가 지나가니 벽 표시를 하고
			if(find_route(i, j+1)) {	// 파이프설치가 완료되면 
				return true;			// return true;
			}
		}
		
		// 오른쪽 아래
		if((i+1) < R) {					// 맵의 테두리에 닿지않고
			if(!map[i+1][j+1]) {			// 벽이 없다면
				map[i+1][j+1] = true;		// 파이프가 지나가니 벽 표시를 하고
				if(find_route(i+1, j+1)) {	// 파이프설치가 완료되면 
					return true;			// return true;
				}
			}
		}
		// 셋다 false면 false리턴
		return false;
	}
		
}
