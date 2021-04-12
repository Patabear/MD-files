package swacademy.no1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int T, N;							// 테케, 개수
	static int[][] map;							// 입력시 받는 배열
	static boolean[][] visited;					// 방문여부
	static int[][] memory;						// 본인번호에서부터 시작할때 가장 긴 거리일때의 값을 갖고있는 배열, 메모이제이션을 위한 배열
	static int[] di = {-1,1,0,0}; 				// 0123 -> UP DOWN LEFT RIGHT
	static int[] dj = {0,0,-1,1};
	static int longest_route;					// 답 가장 긴거리
	static int longest_route_index;				// 답 해당위치의 번호

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			
			// input & initialize
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];										// 맵 초기화
			visited = new boolean[N][N];								// 방문배열 초기화
			memory = new int[N][N];										// 답들을 넣는 배열 메모이제이션을 위한 배열
			longest_route = 0;
			longest_route_index = 1000;
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// solve 
			// 방식은 본래 문제에서 설명한 해당 방번호보다 1큰 방을 찾아서 이어나가는 것이 아닌
			// 해당 방번호보다 1작은 방을 찾아서 가면서 depth값을 매겨주고 나오는 식입니다. 거꾸로생각하시면 됩니다.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) find_long_route(1, i, j, map[i][j]+1);	// 이미 계산된 방이 아니라면 각 자리마다 출발시켜봄
				}
			}
			
			System.out.println("#" + testcase + " " + longest_route_index + " " + longest_route);
			
			
		}

	}
	
	static void find_long_route(int depth, int i, int j, int before_val) {
		// 기저 조건
		if(map[i][j] != (before_val-1)) return;		// 조건에 맞지않음 (1작은 방이 아님)
		
		if(visited[i][j]) {
			if( depth == memory[i][j] ) return;		// 1작은방은 맞지만 누군가 이미 depth값을 찍고감, 메모이제이션값과 같은경우 지금부터는 이미 계산된 방들이므로 return
		}
		
		visited[i][j] = true;						// 새로운 값을 저장
		memory[i][j] = depth;
		
		if(longest_route == depth) longest_route_index = Math.min(map[i][j], longest_route_index); // depth가 같은때 index가 작은쪽
		else {
			if(depth > longest_route) {				// depth가 더 크면 갱신
				longest_route = depth;
				longest_route_index = map[i][j];
			}
		}
		
		for(int k = 0; k < 4; k++) {				// 4방향 체크
			int ni = i + di[k];
			int nj = j + dj[k];
			
			if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;	// index boundary 체크
			
			find_long_route(depth+1, ni, nj, map[i][j]);			// 재귀
		}
		
		
		
	}
}
