package no11399;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N];
		int sum = 0;

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			P[i] = (Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(P);

		for(int i = 0; i < N; i++) {
			sum += P[i]*(N-i);
		}

		System.out.println(sum);

	}

}
