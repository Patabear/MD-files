package baekjoon.no2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<struct> stack = new Stack<>();						// 스택
		
		int N = Integer.parseInt(br.readLine());					// 타워의 개수
		StringTokenizer st = new StringTokenizer(br.readLine());	// 타워들을 입력
		int[] towers = new int[N];									// 타워의 높이들을 받는 배열
		int[] receive = new int[N];									// 답안을 채우는 배열
		
		for(int i = 0; i < N; i++) {
			towers[i] = Integer.parseInt(st.nextToken());			// 타워 입력
		}
		
		
		for(int i = N-1; i >=0; i--) {
			if(stack.empty()) {
				stack.push(new struct(towers[i], i));				// 스택이 비었으면 채움
				continue;
			}
			if(stack.peek().value > towers[i]) {				// 스택최근값과 비교해서 더 작으면
				stack.push(new struct(towers[i], i));				// 너도 스택에 드가라~
				continue;
			}
			while(stack.peek().value <= towers[i]) {			// 아~ 스택에 들어간애보다 큰애를 발견
				receive[stack.pop().index] = i+1;				// 지금 수신한타워의 index+1을 내마음속에 저!장!
				if(stack.empty()) break;
			}
			stack.push(new struct(towers[i], i));
		}
		
		while(!stack.empty()) {
			receive[stack.pop().index] = 0;
		}
		
		for(int x: receive) {
			System.out.print(x + " ");
		}
	}
}

class struct {
	public struct(int value, int index) {
		this.value = value;
		this.index = index;
	}
	int value;
	int index;
}
