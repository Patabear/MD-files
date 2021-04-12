package jungol.no1828;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static Temp[] temp, Refri_temp;
	static int RefriCnt = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		temp = new Temp[N];
		Refri_temp = new Temp[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			temp[i] = new Temp(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));   
		}
		
		Arrays.sort(temp);		// 최저온도 내림차순 정렬
		Refri_temp[RefriCnt] = new Temp(temp[0].low, temp[0].high);
		for(int i = 1; i < N; i++) {
			if(temp[i].high < Refri_temp[RefriCnt].low) {
				RefriCnt += 1;
				Refri_temp[RefriCnt] = new Temp(temp[i].low, temp[i].high);
			}
		}
		System.out.println(RefriCnt+1);

	}
	
	static class Temp implements Comparable<Temp> {
		public int low;
		public int high;
		
		public Temp() {
			this.low = 10000;
			this.high = -270;
		}
		
		public Temp(int low, int high) {
			this.low = low;
			this.high = high;
		}
		
		@Override
		public int compareTo(Temp temp) {
			if (this.low < temp.low) {
				return 1;
			}
			else if (this.low == temp.low) {
				return 0;
			}
			else {
				return -1;
			}
		}
		
	}

}
