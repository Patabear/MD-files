package swacademy.no1210;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[] di = new int[] {-1,1,0,0}; // [i][j] up down left right
		int[] dj = new int[] {0,0,-1,1};
		int[][] map;
		int T = 0, start_from = 0, testcase = 10;
		boolean leftgoing, rightgoing;
		
		//System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(testcase-->0) {
			T = Integer.parseInt(br.readLine());
			map = new int[100][100];
			int i, j;
			
			// 전체 사다리를 받음 막줄빼고
			for(i = 0; i < 99; i++) {
				st = new StringTokenizer(br.readLine());
				for(j = 0; j < 100; j++)
				{
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 마지막줄에 있는 2를 찾으러 별도입력
			st = new StringTokenizer(br.readLine());
			for(j = 0; j < 100; j++)
			{
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				if(temp == 2) start_from = j;
			}
			
			// 0 up | 1 down | 2 left | 3 right
			i = 99; j = start_from; leftgoing = false; rightgoing = false;
			while(i > 0) {
				int tempi_left, tempj_left, tempi_right, tempj_right;
				boolean leftblocked = false, rightblocked = false; // 벽에 부딪힘
				
				// 좌우 움직일경우 다음 좌표
				tempi_left = i+di[2];
				tempj_left = j+dj[2];
				tempi_right = i+di[3];
				tempj_right = j+dj[3];
				
				// 벽에 부딪혔을경우를 미리계산
				if(tempj_left < 0) leftblocked = true;
				if(tempj_right >= 100) rightblocked = true;
				
				if(!leftblocked) { // 왼쪽벽에 안 부딪혔다면
					if(leftgoing) { // 왼쪽으로 가던중이었니?
						if(map[tempi_left][tempj_left] == 1) { // 그렇구나 뚫렸으면 가렴
							i = tempi_left;
							j = tempj_left;
							continue;
						}
						else { // 막혔구나 그냥 위로가렴
							leftgoing = false;
							i = i+di[0];
							j = j+dj[0];
							continue;
						}
					}
				}
				
				// 왼쪽으로 가고있었는데 왼쪽벽에 부딪힌경우
				else if(leftblocked && leftgoing) { 
					leftgoing = false;
					i = i+di[0];
					j = j+dj[0];
					continue;
				}
				
				if(!rightblocked) { // 오른쪽이 벽에 부딪히지 않았다면
					if(rightgoing) { // 오른쪽으로 가던중이었니?
						if(map[tempi_right][tempj_right] == 1) { // 오른쪽이 뚫려있니?
							i = tempi_right;
							j = tempj_right;
							continue;
						}
						else { // 막혔구나 위로가렴
							rightgoing = false;
							i = i+di[0];
							j = j+dj[0];
							continue;
						}
					}
				}
				
				// 오른쪽으로 가고있었는데 오른쪽벽에 부딪힌경우
				else if(rightblocked && rightgoing) {
					rightgoing = false;
					i = i+di[0];
					j = j+dj[0];
					continue;
				}
				
				if(!leftblocked) { // 왼쪽으로가볼래?
					if(map[tempi_left][tempj_left] == 1) { 
						leftgoing = true;
						i = tempi_left;
						j = tempj_left;
						continue;
					}
				}
				
				if(!rightblocked)  { // 오른쪽으로 가볼래?
					if(map[tempi_right][tempj_right] == 1) {
						rightgoing = true;
						i = tempi_right;
						j = tempj_right;
						continue;
					}
				}
				
				// 다 안되면 그냥 위로가렴
				i = i+di[0];
				j = j+dj[0];
				continue;
			}
			
			System.out.println("#" + T + " " + j);
		}
		
		br.close();
		
	}

}
