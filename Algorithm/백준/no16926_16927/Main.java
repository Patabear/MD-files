package baekjoon.no16926_16927;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, R;		// 배열 가로,세로,반시계회전수
	static int[][] map;		// 입력받는 배열
	static int[][] tgt;		// 돌리고 난 배열
	static int lane_cnt;	// 배열 껍질 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		tgt = new int[N][M];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 입력
			}
		}
		
		lane_cnt = (Math.min(N,M))/2;							// 배열껍질 lane의 개수
		
		for(int lane_num = 0; lane_num < lane_cnt; lane_num++) {		// lane수만큼 돌아감
			
			// index 세팅
			int lane_length = (N+M)*2-4-8*lane_num;						// lane의 길이
			int shift_cnt = R%lane_length;								// 반시계로 돌아가는 횟수를 lane의 길이로 나누어 나머지
			
			int i = lane_num; int j = lane_num; int direction = 0;				// 0 오  1 아래  2 왼  3 위
			int tgt_i = lane_num; int tgt_j = lane_num; int tgt_direction = 0;	// 돌아간 배열을 저장할 배열 tgt의 index
			
			int right_bound = M-1-lane_num;										// 지금 lane의 오른쪽 벽
			int right_length = M-1-2*lane_num;									// 지금 lane의 가로길이
			int down_bound = N-1-lane_num;										// 지금 lane의 아래 벽
			int down_length = N-1-2*lane_num;									// 지금 lane의 세로길이
			int double_length = right_length + down_length;						// 가로세로길이 합친것 계산수 줄이기 위함
			
			if(shift_cnt < right_length) {										// 가장왼쪽상단을 기준으로 돌렸을때 head가 아직 윗변일때
				j += shift_cnt;
			}
			else if(shift_cnt < double_length) {								// 오른쪽 변일때
				j = right_bound;
				i += shift_cnt-right_length;
				direction = 1;
			}
			else if(shift_cnt < (double_length + right_length)) {				// 아래 변일때
				i = down_bound;
				j += right_length-(shift_cnt-double_length);
				direction = 2;
			}
			else {
				i += down_length-(shift_cnt-(double_length + right_length));	// 왼쪽 변일때
				direction = 3;
			}
			
			// 현상태 i,j는 돌아간 배열의 index를 가리키고있는상황
			
			// 계산은 바움쿠헨 한줄씩 먹듯 계산, 그 한줄을 돌리는 식
			// 시작은 lane_num,lane_num  lane_num = i라 했을때,  i,i -> i,M-1-i -> N-1-i,M-1-i -> N-1-i,i -> i+1,i  까지
			// 이것을 R번 회전시킨 값으로 시작하면 돌렸을때의 한줄을 알 수 있음
			
			
			for(int element = 0; element < lane_length; element++) {	// lane 길이만큼 돔
				tgt[tgt_i][tgt_j] = map[i][j];							// 돌아간 배열을 tgt배열에 복사
				
				// map index rotation
				if(direction == 0) {									// 오른쪽으로 가는중이면
					j++;
					if(j == M-1-lane_num) direction = 1;				// 끝에 닿으면 방향전환 아래로
				}
				
				else if(direction == 1) {
					i++;
					if(i == N-1-lane_num) direction = 2;				// 방향전환 왼쪽으로
				}
				
				else if(direction == 2) {
					j--;
					if(j == lane_num) direction = 3;					// 방향전환 위쪽으로
				}
				
				else {
					i--;
					if(i == lane_num) direction = 0;					// 방향전환 오른쪽으로
				}
				
				// tgt index rotation
				if(tgt_direction == 0) {								// 위와 동일
					tgt_j++;											// 오른쪽으로 진행
					if(tgt_j == M-1-lane_num) tgt_direction = 1;						
				}
				
				else if(tgt_direction == 1) {
					tgt_i++;
					if(tgt_i == N-1-lane_num) tgt_direction = 2;						
				}
				
				else if(tgt_direction == 2) {
					tgt_j--;
					if(tgt_j == lane_num) tgt_direction = 3;
				}
				
				else {
					tgt_i--;
					if(tgt_i == lane_num) tgt_direction = 0;
				}
			}
		}
		
		for(int[] x: tgt) {
			for(int y: x) {
				System.out.print(y + " ");
			}
			System.out.println();
		}
		
		
	}
}
