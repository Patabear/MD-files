package bj_no1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int sum = 0;
		PriorityQueue<Integer> PQ = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		for (int i = 0; i < N; i++) {
			PQ.offer(Integer.parseInt(br.readLine()));
		}
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		int next_length = N;
		while(next_length > 1) {
			int temp = PQ.poll() + PQ.poll();
			sum += temp;
			PQ.offer(temp);
			next_length--;
		}
		
		System.out.println(sum);
	}
}
