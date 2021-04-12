package swacademy.no1873;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static char[][] map;							// map
	static char[] orders;							// 전차 명령어
	static char direction;							// 전차가 바라보는 방향
	static int T, H, W, N;							// 테스트케이스수, map의 높이, 너비, 전차명령어개수
	static int positionI, positionJ;				// 전차의 현재위치
	static final int[] di = new int[] {-1,1,0,0};	// 움직이는 방향에 따른 di dj
	static final int[] dj = new int[] {0,0,-1,1};	// 0:UP 1:DOWN 2:LEFT 3:RIGHT

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());		// 테스트케이스 개수 입력
		
		for(int testcase = 1; testcase <= T; testcase++) {
			
			// input
			String[] string = br.readLine().split(" ");
			H = Integer.parseInt(string[0]); W = Integer.parseInt(string[1]);	// 맵의 높이 너비
			map = new char[H][W];
			
			for(int i = 0; i < H; i++) {
				String temp = br.readLine();		// 한줄씩 받아서
				for(int j = 0; j < W; j++) {
					char tmpchr = temp.charAt(j);	// 한글자씩 뗴서 입력
					map[i][j] = tmpchr;
					if(tmpchr == '^' || tmpchr =='v' || tmpchr == '<' || tmpchr == '>') {
						direction = tmpchr;
						positionI = i;				// 전차위치가 확인된다면 따로 저장
						positionJ = j;
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());	// 전차 명령어 개수
			orders = new char[N];
			
			String temp = br.readLine();				// 명령어들을 한줄로 받아서
			for(int i = 0; i < N; i++) {
				orders[i] = temp.charAt(i);			// 한글자씩 떼서 입력
			}
			
			
			// solve
			for(char order: orders) {
				if(order == 'S') shoot(direction, positionI, positionJ);	// 빵야
				else {
					move(order, positionI, positionJ);						// 무브무브
				}
			}
			
			System.out.print("#" + testcase + " ");
			for(char[] x: map) {
				for(char y: x) {
					System.out.print(y);
				}
				System.out.println();
			}
			
			
		}
		br.close();
	}
	
	// 0:UP 1:DOWN 2:LEFT 3:RIGHT
	// 포탄 푸슝빠슝
	static void shoot(char dir, int posi, int posj) {
		int dir_num = 0;
		if(dir == '^') dir_num = 0;						// 방향에 따라 int값 미리구함
		else if(dir == 'v') dir_num = 1;
		else if(dir == '<') dir_num = 2;
		else if(dir == '>') dir_num = 3;
		
		boolean bullet = true;
		while(bullet) {
			int nextI = posi + di[dir_num];				// 탄이 날아갈 칸 위치계산
			int nextJ = posj + dj[dir_num];
			
			// 맵밖으로 나가버림
			if(nextI < 0 || nextI >= H || nextJ < 0 || nextJ >= W) {
				bullet = false;
				continue;
			}
			
			char tile = map[nextI][nextJ];				// 여러번 부르지 않도록 임시저장
			// 벽돌로 만들어진 벽에 부딪힘
			if(tile == '*') {
				map[nextI][nextJ] = '.';
				bullet = false;
			}
			
			// 강철로된 벽에 부딪힘
			else if(tile == '#') {
				bullet = false;
			}
			
			// 슈웅
			else {
				posi = nextI;
				posj = nextJ;
			}
		}
		
	}
	
	// 전차 부릉부릉
	static void move(char dir, int posi, int posj) {
		int dir_num = 0;
		if(dir == 'U') { dir_num = 0; dir = '^'; }
		else if(dir == 'D') { dir_num = 1; dir = 'v'; }
		else if(dir == 'L') { dir_num = 2; dir = '<'; }
		else if(dir == 'R') { dir_num = 3; dir = '>'; }
		
		int nextI = posi + di[dir_num];
		int nextJ = posj + dj[dir_num];
		
		// 맵밖으로 나가버림
		if(nextI < 0 || nextI >= H || nextJ < 0 || nextJ >= W) {
			update(dir, posi, posj); 							// 전차 방향만 돌려줌
			return;
		}
		
		char tile = map[nextI][nextJ];						// 여러번 부르지 않도록 임시저장
		
		// 갈곳이 못갈곳임
		if(tile == '*' || tile == '#' || tile == '-') {
			update(dir, posi, posj);						// 전차 방향만 돌려줌
			return;
		}
		
		// 평지를 만남
		if(tile == '.') {
			map[posi][posj] = '.';							// 지나간 위치에 평지표시
			update(dir, nextI, nextJ);						// 현위치 업데이트
			return;
		}
	}
	
	static void update(char dir, int i, int j) {
		map[i][j] = dir;
		positionI = i;
		positionJ = j;
		direction = dir;
	}
}
