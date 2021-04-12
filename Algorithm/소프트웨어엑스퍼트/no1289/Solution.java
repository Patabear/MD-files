package swacademy.no1289;

//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) throws FileNotFoundException {
		
		//System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T;
		String[] memory;
		int[] sum;
		boolean state;
		char[] testcase;
		
		// input TestCase Number
		T = sc.nextInt();
		memory = new String[T];
		sum = new int[T];
		
		// input prev memory state
		for(int i = 0; i < T; i++) {
			memory[i] = sc.next();
		}
		
		sc.close();
		
		// count swapping memory state
		for(int i = 0; i < T; i++) {
			int len = memory[i].length();
			testcase = memory[i].toCharArray();
			state = false;
			
			for(int j = 0; j < len; j++) {
				if(testcase[j] == '0') {
					if(state) { sum[i]++; state = false; } 	// 바로전 1 이번꺼 0
					else continue;	}						// 바로전 0 이번꺼 0
				else {
					if(state) continue;						// 바로전 1 이번꺼 1
					else { sum[i]++; state = true; } }		// 바로전 0 이번꺼 1
			}
			
			System.out.println("#" + (i+1) + " " + sum[i]);
		}
	}
}

/*
2
0011
100
*/
