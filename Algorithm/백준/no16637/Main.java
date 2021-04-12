package no16637;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static char[] fomula;
	static boolean[] selected;
	static int Max_answer;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());		// 수식길이 입력
		fomula = new char[N];						// 수식을 받을 배열
		fomula = br.readLine().toCharArray();		// 수식 입력
		selected = new boolean[N];					// 수식 길이만큼 comb때 사용할 bool배열
		Max_answer = (int) (0-Math.pow(2,31));		// 최소값이 -2의 31제곱
		
		if(N==1) System.out.println(fomula[0]-'0');	// 수식길이가 1인 경우
		else {
			comb(0);
			System.out.println(Max_answer);
		}
	}
	
	static void comb(int srcIdx) {
		// src는 두칸씩 뜁니다.
		// 묶으면 그 뒤의 숫자는 같이 묶을수 없으므로 4칸뜁니다.
		
		if(srcIdx > N) {
			int prev_operand = 0;		// 앞의 피연산자
			int next_operand = 0;		// 뒤의 피연산자
			int operator_cond = 0;		// 사이에 낀 연산자 0:+ 1:- 2:*
			// System.out.println(Arrays.toString(selected));
			for(int i = 0; i < N; i++) {
				
				if(selected[i]) {		// 괄호를 만난경우
					// 괄호안을 미리 계산
					int temp_prev = fomula[i]-'0';
					int temp_next = fomula[i+2]-'0';
					char temp_oper = fomula[i+1];
					if(temp_oper == '+') temp_prev += temp_next;
					else if(temp_oper == '-') temp_prev -= temp_next;
					else if(temp_oper == '*') temp_prev *= temp_next;
					next_operand = temp_prev;	// 괄호 안 계산완료한 값
					
					// 아래는 일반 피연산자를 만났을때와 동일
					if(i != 0) {
						if(operator_cond == 0)	prev_operand += next_operand;
						else if(operator_cond == 1)	prev_operand -= next_operand;
						else if(operator_cond == 2)	prev_operand *= next_operand;
					}
					else prev_operand = next_operand;
					
					i += 2;	// 괄호때문에 2칸 더뜀
					continue;
				}
				
				// 괄호를 만나지 않고
				
				if(fomula[i] >= '0') {	// 숫자라면
					next_operand = fomula[i] - '0';
					if(i != 0) {
						if(operator_cond == 0)	prev_operand += next_operand;
						else if(operator_cond == 1)	prev_operand -= next_operand;
						else if(operator_cond == 2)	prev_operand *= next_operand;
					}
					else prev_operand = next_operand;
				}
				
				else {	// 연산자라면
					if(fomula[i] == '+') operator_cond = 0;
					else if(fomula[i] == '-') operator_cond = 1;
					else if(fomula[i] == '*') operator_cond = 2;
				}
			}
			
			Max_answer = Math.max(Max_answer, prev_operand);
			return;
		}
		
		if(srcIdx+2 < N) {	// 뒤에 비연산자가 더있으면
			selected[srcIdx] = true;
			selected[srcIdx+2] = true;
			comb(srcIdx+4);	// 체크하고 4칸뛰기
			selected[srcIdx] = false;
			selected[srcIdx+2] = false;
			comb(srcIdx+2);	// 체크 풀고 2칸뛰기
		}
		else {	// 지금이 마지막 비연산자인 경우
			comb(srcIdx+2);
		}
	}

}
