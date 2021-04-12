package no11047;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		List<Integer> A = new ArrayList<>();
		int coinCnt = 0;

		for(int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			if(temp > K) {
				N = i;
				break;
			}
			else A.add(temp);
		}

		for(int i = N-1; i >= 0; i--) {
			coinCnt += K/(A.get(i));
			K = K%(A.get(i));
		}

		System.out.println(coinCnt);
	}
}
