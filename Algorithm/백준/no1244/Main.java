package baekjoon.no1244;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int switch_num;		// 스위치 개수
		boolean[] state;	// 스위치들 *(1부터시작 한칸 밀어서 받기)*
		int student_num;	// 학생수
		int[][] student;	// [학생순서] [0=성별, 1=스위치번호]
		
		Scanner sc = new Scanner(System.in);
		
		switch_num = sc.nextInt();
		state = new boolean[switch_num+1];
		
		// 스위치 상태들을 boolean형태로 저장
		for(int i = 1; i <= switch_num; i++) if(sc.nextInt() == 1) state[i] = true;
		
		// student[학생번호][0=성별, 1=스위치번호]
		student_num = sc.nextInt();
		student = new int[student_num][2];
		
		for(int i = 0; i < student_num; i++) {
			student[i][0] = sc.nextInt();	// gender
			student[i][1] = sc.nextInt();	// switch number
		}
		
		
		for(int i = 0; i < student_num; i++) {
			if(student[i][0] == 1) {	// 남학생
				 for(int j = student[i][1]; j <= switch_num; j += student[i][1]) { // 배수로 건너뛰며
					 state[j] = state[j] ? false : true;	// 스위치 상태 스왑
				 }
			}
			
			else { // 여학생
				int middle = student[i][1];
				int start = middle;
				int end = middle;
				 
				// 일단 들고있는 번호 스위치 스왑
				state[middle] = state[middle] ? false : true;
				
				// 스위치 개수의 범위를 벗어나지 않는 선에서
				for(int j = 1; 1 <= (start-1) && (end+1) <= switch_num; j++) {
					start = middle - j;
					end = middle + j;
					if( state[start] == state[end] ) { // 양쪽이 대칭이면 스왑
						state[start] = state[start] ? false : true;
						state[end] = state[end] ? false : true;
					}
					else break;
				}
			}
		} 
		
		for(int i = 1; i <= switch_num; i++) {
			if(state[i]) System.out.print("1");
			else System.out.print("0");
			if(i%20 == 0) System.out.println();
			else if(i < switch_num) System.out.print(" ");
		}
		
		sc.close();
	}
}

