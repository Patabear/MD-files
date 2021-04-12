package swacademy.no1233;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static Node[] tree;
	static boolean state;			// false 피연산자, true 연산자  => 피연산자뒤에 연산자 연산자뒤에 피연산자
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int testcase = 1; testcase <= 10; testcase++) {
			N = Integer.parseInt(br.readLine());
			tree = new Node[255];
			for(int i = 0; i < 255; i++) { tree[i] = new Node(); }
			tree[0].value = '0';
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int temp = Integer.parseInt(st.nextToken());
				tree[temp].value = st.nextToken().charAt(0);
				
				if(st.hasMoreTokens()) tree[temp].left_index = Integer.parseInt(st.nextToken());
				else continue;
				
				if(st.hasMoreTokens()) tree[temp].right_index = Integer.parseInt(st.nextToken());
			}
			
			answer = 1;			// 처음엔 계산이 가능하다고 표시
			state = true;		// 처음에 피연산자가 나와야하는데 그 전이 연산자라는 표시를 해주어야 반복가능
			check_validity(1);
			
			System.out.println("#" + testcase + " " + answer);
			
			
		}

	}
	static void check_validity(int index) {
		if( tree[index].left_index != 0 )check_validity(tree[index].left_index);
		if('0' <= tree[index].value) {					// 만난 노드가 피연산자일 경우
			if(!state) { answer = 0; return; }			// 전 노드가 피연산자이면 유효X
			else state = false;							// 상태 변경
		}
		else {											// 만난 노드가 연산자일 경우
			if(state) { answer = 0; return; }			// 전 노드가 연산자이면 유효X
			else state = true;							// 상태변경
		}
		if( tree[index].right_index != 0 ) check_validity(tree[index].right_index);
	}
}
class Node {
	int left_index;
	int right_index;
	char value;
}
