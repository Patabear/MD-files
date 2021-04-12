package swacademy.no1218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
	static Stack<Integer> stack = new Stack<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase = 1; testcase <= 10; testcase++) {
			boolean validation = true;
			stack.clear();
			
			// input
			br.readLine();
			char[] problem = br.readLine().toCharArray();
			
			for(char x: problem) { // stack and pop
				if(x == '(') { stack.push(1); continue; }
				else if(x == '[') { stack.push(2); continue; } 
				else if(x == '{') { stack.push(3); continue; }
				else if(x == '<') { stack.push(4); continue; }
				if(!validation) break; // stack할 경우 확인할 필요없어서 코드 위치 아래
				else validation = check_pop(x); // pop시 유효성검사
			}
			
			if(!stack.empty()) validation = false;
			
			if(validation) System.out.println("#" + testcase + " 1");
			else System.out.println("#" + testcase + " 0");
		}
		

	}
	
	static boolean check_pop (char arg) {
		int x = 0;
		int last_pushed_item = stack.pop();
		
		if(arg == ')') x = 1;
		else if(arg == ']') x = 2;
		else if(arg == '}') x = 3;
		else if(arg == '>') x = 4;
		
		// pop후 닫은 괄호와 가장 최근에 열린 괄호의 짝 확인
		if(x == last_pushed_item) return true;
		else return false;
	}

}
