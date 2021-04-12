package bj.no2477;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();			// 참외밭한칸에 참외수
		int max_x = 0;					// 큰사각형 크기용 가로
		int max_y = 0;					// 세로
		int map[][] = new int[6][2]; 	// 횟수 - 방향,길이
		
		for(int i = 0; i < 6; i++) {
			int dir = sc.nextInt();
			int val = sc.nextInt();
			map[i][0] = dir;
			map[i][1] = val;
			if(dir == 4 || dir == 3) max_x = Math.max(max_x, val);
			else max_y = Math.max(max_y, val);
		}
		
		// ex) 방향이 3 1 3 1 일 경우 가운데에 1 3 이 잘린 작은 사각형
		// 이렇게 반복될때 가운데 낀 index를 찾는 logic
		int idx1 = 4;
		int idx2 = 5;
		int cutidx1 = 0, cutidx2 = 0;
		for(int i = 0; i < 6; i++) {
			int next_i = (i+1)%6;
			if(map[idx1][0] == map[i][0] && map[idx2][0] == map[next_i][0]) {
				cutidx1 = idx2;
				cutidx2 = i;
				break;
			}
			idx1 = (idx1+1)%6;
			idx2 = (idx2+1)%6;
		}
		
		int answer = K*((max_x*max_y)-(map[cutidx1][1]*map[cutidx2][1]));
		
		System.out.println(answer);
		
		sc.close();
	}

}
