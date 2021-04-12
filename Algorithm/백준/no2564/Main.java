package no2564;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(br.readLine());
		int[][] store = new int [S+1][3];		// 0:북동남서  1:항상 자기기준 왼쪽길이 저장  2:오른쪽길이
		int[] DG = new int[3];
		int[] opposite_point = new int[4];		// 0:북동남서 1:왼쪽길이 2:오른쪽길이 3:사이에낀 긴 경계길이
		int answer = 0;
		
		for(int i = 0; i <= S; i++) {
			st = new StringTokenizer(br.readLine());
			int temp = Integer.parseInt(st.nextToken());
			int temp2 = Integer.parseInt(st.nextToken());
			// 북동남서로 재배치, 본인기준 1:왼쪽길이 2:오른쪽길이
			if(temp == 1) { // 북
				store[i][0] = 0;
				store[i][1] = temp2;
				store[i][2] = N-temp2;
			}
			else if(temp == 2) { // 남
				store[i][0] = 2;
				store[i][1] = N-temp2;
				store[i][2] = temp2;
			}
			else if(temp == 3) { // 서
				store[i][0] = 3;
				store[i][1] = M-temp2;
				store[i][2] = temp2;
			}
			else if(temp == 4) { // 동
				store[i][0] = 1;
				store[i][1] = temp2;
				store[i][2] = M-temp2;
			}
		}
		DG[0] = store[S][0];
		DG[1] = store[S][1];
		DG[2] = store[S][2];
		
		// 동근이와 대칭된 반대편 점
		opposite_point = get_opposite_point(DG);
		
		for(int i = 0; i < S; i++) {
			// 같은경계
			if(store[i][0] == DG[0]) {
				answer += Math.abs(store[i][1] - DG[1]);
			}
			// 동근기준 시계방향으로 한칸 간 경계
			else if(store[i][0] == ((DG[0]+1)%4)) {
				answer += (DG[2] + store[i][1]);
			}
			// 동근기준 건너편 경계
			else if(store[i][0] == ((DG[0]+2)%4)) {
				// 반대편점 기준 왼쪽에 있다면
				if(opposite_point[1] > store[i][1]) {
					answer += (DG[2] + opposite_point[3] + store[i][1]);
				}
				// 반대편점 기준 오른쪽에 있다면
				else {
					answer += (DG[1] + opposite_point[3] + store[i][2]);
				}
			}
			// 동근기준 반시계방향으로 한칸 간 경계
			else if(store[i][0] == ((DG[0]+3)%4)) {
				answer += (DG[1] + store[i][2]);
			}
		}
		
		System.out.println(answer);
		
		

	}
	
	static int[] get_opposite_point(int[] pt) {
		int[] point = new int[4];
		
		if(pt[0] == 0) {
			point[0] = 2;
			point[1] = pt[1];
			point[2] = pt[2];
			point[3] = M;
		} 
		else if(pt[0] == 1) {
			point[0] = 3;
			point[1] = pt[1];
			point[2] = pt[2];
			point[3] = N;
		}
		else if(pt[0] == 2) {
			point[0] = 0;
			point[1] = pt[1];
			point[2] = pt[2];
			point[3] = M;
		}
		else if(pt[0] == 3) {
			point[0] = 1;
			point[1] = pt[1];
			point[2] = pt[2];
			point[3] = N;
		}
		return point;
		
	}

}
