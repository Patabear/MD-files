package no2851;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = -100;
		for(int i = 0; i < 10; i++) {
			int temp = sc.nextInt();
			if(Math.abs(sum+temp) <= Math.abs(sum)) sum += temp;
			else break;
		}
		System.out.println(sum+100);
		sc.close();
	}
}
