package swacademy.no1225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase = 1; testcase <= 10; testcase++) {
			br.readLine();
			// input
			queue.clear();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				queue.offer(Integer.parseInt(st.nextToken()));
			}
			
			int minus_number = 1;
			while(true) {
				int temp = queue.poll() - (minus_number++);		// 숫자를 빼서 저장
				if(temp <= 0) { queue.offer(0); break; }		// 0이하이면 0을 넣고 break
				queue.offer(temp);								// 0초과이면 숫자를 뺀 값을 다시 push
				if(minus_number>5) minus_number = 1;			// 빼는숫자가 5를 넘으면 1로 초기화
			}
			
			System.out.print("#" + testcase + " ");
			for(int x: queue) {
				System.out.print(x + " ");
			}
			System.out.println();
			
			
		}
		
		

	}

}
