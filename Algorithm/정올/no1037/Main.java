package jw.no1037;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] sum_I = new int[N];
		int[] sum_J = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) {
					sum_I[i]++;
					sum_J[j]++;
				}
			}
		}
		
		int i_odd = 0;
		int[] i_odd_Idx = new int[N];
		int i_odd_Idx_cnt = 0;
		int j_odd = 0;
		int[] j_odd_Idx = new int[N];
		int j_odd_Idx_cnt = 0;
		
		for(int i = 0; i < N; i++) {
			if(sum_I[i]%2 == 1) {
				i_odd += sum_I[i]%2;
				i_odd_Idx[i_odd_Idx_cnt++] = i;
			}
			if(sum_J[i]%2 == 1) {
				j_odd += sum_J[i]%2;
				j_odd_Idx[j_odd_Idx_cnt++] = i;
			}
				
		}
		
		if(i_odd == 0 && j_odd == 0) System.out.println("OK");
		else {
			if(i_odd == j_odd) {
				for(int i = 0; i < i_odd; i ++) {
					System.out.println("Change bit (" + (i_odd_Idx[i]+1) + "," + (j_odd_Idx[i]+1) + ")");
				}
			}
			else {
				System.out.println("Corrupt");
			}
		}
		
		

	}

}
