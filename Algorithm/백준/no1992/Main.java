package baekjoon.no1992;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static boolean[][] map;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String temp = br.readLine();
			for(int j = 0; j < N; j++) {
				if(temp.charAt(j) == '1') map[i][j] = true;	// 숫자가 1이면 true 0이면 false로 저장
				else map[i][j] = false;
			}
		}
		
		// map 내의 모든숫자가 같으면 하나의 숫자로 표현하기 위한 전처리
		boolean perfect_same = true;
		boolean perfect_same_state = map[0][0];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != perfect_same_state) {
					perfect_same = false;
					break;
				}
			}
			if(!perfect_same) break;
		}
		
		// map 내의 모든 숫자가 같은경우
		if(perfect_same) {
			if(perfect_same_state) System.out.println("1");
			else System.out.println("0");
		}
		else {
			sb.append("(");
			if(1 < N) recursion(N, 0, 0);
			else sb.append(map[0][0]);
			sb.append(")");
			System.out.println(sb);
		}
	}
	
	// 쿼드트리 체크
	static void recursion(int rec_N, int start_i, int start_j) {
		int mid_i = rec_N/2+start_i;
		int mid_j = rec_N/2+start_j;
		
		// 1사분면
		boolean state = map[start_i][start_j];		// 첫 상태 기억
		boolean all_same = true;
		for(int i = start_i; i < mid_i; i++) {
			
			for(int j = start_j; j < mid_j; j++) {
				if(map[i][j] == state) continue;	// 모든 원소가 같은 숫자를 표현중임
				else {	// 하나라도 다른숫자가 나오면
					all_same = false;				// 다름을 표시 후
					sb.append("(");
					recursion(rec_N/2, start_i, start_j);	// 그 내부사각형 확인
					sb.append(")");
					break;
				}
			}
			
			if(all_same == false) break;	// 하나라도 다른숫자가 나와 2중 반복문 탈출
		}
		
		// 사분면 안의 모든 숫자가 같으면 하나의 숫자로 표현
		if(all_same) {
			if(state) sb.append("1");
			else sb.append("0");
		}
		
		
		
		// 2사분면
		state = map[start_i][mid_j];
		all_same = true;
		for(int i = start_i; i < mid_i; i++) {
			
			for(int j = mid_j; j < rec_N+start_j; j++) {
				if(map[i][j] == state) continue;
				else {
					all_same = false;
					sb.append("(");
					recursion(rec_N/2, start_i, mid_j);
					sb.append(")");
					break;
				}
			}
			
			if(all_same == false) break;
		}
			
		if(all_same) {
			if(state) sb.append("1");
			else sb.append("0");
		}
		
		
		// 3사분면
		state = map[mid_i][start_j];
		all_same = true;
		for(int i = mid_i; i < rec_N+start_i; i++) {
			
			for(int j = start_j; j < mid_j; j++) {
				if(map[i][j] == state) continue;
				else {
					all_same = false;
					sb.append("(");
					recursion(rec_N/2, mid_i, start_j);
					sb.append(")");
					break;
				}
			}
			
			if(all_same == false) break;
		}
			
		if(all_same) {
			if(state) sb.append("1");
			else sb.append("0");
		}
			
		// 4사분면
		state = map[mid_i][mid_j];
		all_same = true;
		for(int i = mid_i; i < rec_N+start_i; i++) {
			
			for(int j = mid_j; j < rec_N+start_j; j++) {
				if(map[i][j] == state) continue;
				else {
					all_same = false;
					sb.append("(");
					recursion(rec_N/2, mid_i, mid_j);
					sb.append(")");
					break;
				}
			}
			
			if(all_same == false) break;
		}
			
		if(all_same) {
			if(state) sb.append("1");
			else sb.append("0");
		}
	}

}
