package swacademy.no1223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static char[] inputcharArr;
	static char[] postfixcharArr;
	static int arr_index = 0;
	static int N;

	// *:74 +:75 0~9:80~89
	public static void main(String[] args) throws IOException {
		Stack<Integer> stack = new Stack<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase = 1; testcase <= 10; testcase++) {							// testcase는 10개
			
			// input
			N = Integer.parseInt(br.readLine());									// 문자 수 입력
			inputcharArr = br.readLine().toCharArray();								// 입력을 위한 char array
			postfixcharArr = new char[inputcharArr.length];							// 후위표기식으로 변환한 char array
			arr_index = 0;															// 입력을 위한 iterator index
			
			// stack에는 char의 아스키코드 기준으로 저장
			// 후위표기식으로 변환하기 위한 반복문
			for(char x: inputcharArr) {
				if('0' <= x) postfixcharArr[arr_index++] = x;						// 피연산자인 경우 그대로 입력
				else {
					if(stack.empty()) stack.push((int)x);							// stack이 비었다면 연산자 push
					else {
						while(x >= stack.peek()) {									// 가장 위에있는연산자 우선순위 확인
							postfixcharArr[arr_index++] = (char)(int)stack.pop();	// 높은 순위 연산자 다뺌  ps. cast는 Integer->int->char
							if(stack.empty()) break;								// stack이 비어버리면 곤란쓰
						}
						stack.push((int)x);											// 연산자를 다 뺐으면 지금연산자 push
					}
				}
			}
			
			while(!stack.empty()) { postfixcharArr[arr_index++] = (char)(int)stack.pop();} // 스택에 남은내용 마저 저장
			
			arr_index = 0;	// initialize
			stack.clear();
			
			// stack에는 int기준으로 저장
			// 계산
			for(char x: postfixcharArr) {
				if('0' <= x) stack.push((int)x-'0');								// 피연산자인경우 push
				else {
					if(x == '*') stack.push(stack.pop()*stack.pop());				// 연산자인경우 stack에서 피연산자 2개꺼내 계산
					else if(x == '+') stack.push(stack.pop()+stack.pop());
				}
			}
			
			System.out.println("#" + testcase + " " + stack.pop());					// 출력
		}
		br.close();
	}
}