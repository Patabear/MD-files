package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_아기상어_16236 {

	static int N, sy, sx, sSize, sEatCnt, ans;
	
	static int[][] map;
	static boolean[][] visit;
	
	static Queue<Node> queue; 
	
	// 상, 좌, 우, 하
	static int[] dy = { -1, 0,  0, 1 };
	static int[] dx = {  0, -1, 1, 0 };
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(st.nextToken());
				if ( n == 9 ) {
					sy = i;
					sx = j;
				}
				
				map[i][j] = n;
			}
		}

		sSize = 2;
		go();
		
		System.out.println(ans);
	}
	
	// simulation
	// 먹이를 계속 찾는 작업 반복 : 더이상 먹을 수 없을 때까지
	static void go() {
		
		// 1회 : 물고기 1마리 잡으러 가는 ...
		while(true) {
			int cnt = bfs(); // 한 마리 먹으러 가는.
			if( cnt == 0 ) break;
			ans += cnt; // 누적 거리
		}
	}
	
	static void initVisit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				visit[i][j] = false;
			}
		}
	}
	
	static int bfs() {
		// 변수
		int minY = Integer.MAX_VALUE;
		int minX = Integer.MAX_VALUE;
		int minDis = Integer.MAX_VALUE;
		
		initVisit();
		visit[sy][sx] = true;
		
		queue = new LinkedList<Node>();
		queue.offer(new Node(sy, sx, 0 ));
		
		while( ! queue.isEmpty() ) {
			
			Node node = queue.poll();
			
			// 먹을 수 있는 물고기
			if( map[node.y][node.x] < sSize && map[node.y][node.x] != 0 ) {
				// 거리 체크
				if( node.dis < minDis ) {
					minDis = node.dis;
					minY = node.y;
					minX = node.x;
				}else if( node.dis == minDis ) {
					if( node.y < minY ) {
						minDis = node.dis;
						minY = node.y;
						minX = node.x;
					}else if( node.y == minY ) {
						if( node.x < minX ) {
							minDis = node.dis;
							minY = node.y;
							minX = node.x;
						}
					}
				}
			}
			
			if( node.dis + 1 >= minDis ) continue;
			
			for (int i = 0; i < 4; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				
				if( ny < 0 || nx < 0 || ny >= N || nx >= N || visit[ny][nx] || map[ny][nx] > sSize ) continue;
				
				visit[ny][nx] = true;
				queue.offer(new Node( ny, nx, node.dis + 1 ));
			}
		}
		
		if( minDis == Integer.MAX_VALUE ) return 0;
		else {
			sEatCnt++;
			if(sEatCnt == sSize ) {
				sSize++;
				sEatCnt = 0;
			}
			
			map[minY][minX] = 0;
			map[sy][sx] = 0;
			sy = minY;
			sx = minX;
		}
		
		return minDis;
	}
	
	static class Node{
		int y;
		int x;
		int dis;
		
		public Node(int y, int x, int dis) {
			this.y = y;
			this.x = x;
			this.dis = dis;
		}
	}
}
