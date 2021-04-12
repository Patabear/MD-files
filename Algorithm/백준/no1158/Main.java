package baekjoon.no1158;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int N, K;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		
		for(int i = 1; i <= N; i++){
			queue.add(i);
		}
		
		System.out.print("<");
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < K-1; j++) {
				queue.add(queue.poll());
			}
			System.out.print(queue.poll() + ", ");
		}
		System.out.println(queue.poll() + ">");
		
		sc.close();
	}

}
		
//MK-3
//System.out.print("<");
//for(int i = 0; i <= N-2; i++) {
//	for(int j = 0; j < K-1; j++) {
//		boolean added = false;
//		while(!added) {
//			head += 1;
//			if( head >= N ) head %= N;
//			if(check[head] != true) added = true;
//		}
//		added = false;
//		while(!added) {
//			tail += 1;
//			if( tail >= N ) tail %= N;
//			if(check[tail] != true)
//				added = true;
//		}
//	}
//	
//	check[head] = true;
//	System.out.print(arr[head] + ", ");
//	boolean added = false;
//	while(!added) {
//		head += 1;
//		if( head >= N ) head %= N;
//		if(check[head] != true) added = true;
//	}
//}
//System.out.print(arr[head] + ">");
//	


//		MK-2
//		for(int i = 1; i <= N; i++){
//			queue.add(i);
//		}
//		
//		System.out.print("<");
//		for(int i = 0; i < N-1; i++) {
//			for(int j = 0; j < K-1; j++) {
//				queue.add(queue.poll());
//			}
//			System.out.print(queue.poll() + ", ");
//		}
//		System.out.println(queue.poll() + ">");
//		
		
//		MK - 1
//		for(int i = 1; i <= N; i++){
//			queue.add(i);
//		}
//		int lotaion = 0;
//		boolean first_time = true;
//		while(!queue.isEmpty()) {
//			if(lotaion == (K-1)) {
//				lotaion = 0;
//				if(first_time) {
//					System.out.print("<" + queue.poll());
//					first_time = false;
//				}
//				else System.out.print(", " + queue.poll());
//			}
//			else {
//				queue.add(queue.peek());
//				queue.poll();
//				lotaion++;
//			}
//		}
//		System.out.print(">");

