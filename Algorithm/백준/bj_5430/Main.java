package bj_5430;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[] P;
	static int N;
	static int[] arr;
	static boolean forward;
	static int head, tail;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= T; testcase++) {

			// input
			P = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			forward = true;
			head = 0;
			tail = N - 1;

			st = new StringTokenizer(br.readLine(), "[,]");
			for (int i = 0; i < N; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			// solve
			boolean error_occured = false;

			for (char x : P) {
				if (x == 'R')
					swap_forward();
				else if (x == 'D') {
					if (!del_head()) {
						error_occured = true;
						break;
					}
				}
			}

			
			// output
			if (error_occured)
				System.out.println("error");

			else {
				StringBuilder sb = new StringBuilder();

				if (forward) {
					for (int i = head; i <= tail; i++) {
						sb.append(arr[i]);
						if (i != tail)
							sb.append(",");
					}
				} else {
					for (int i = tail; i >= head; i--) {
						sb.append(arr[i]);
						if (i != head)
							sb.append(",");
					}
				}
				System.out.println("[" + sb.toString() + "]");
			}

		}
	}

	static void swap_forward() {
		forward = forward ? false : true;
	}

	static boolean del_head() {
		if (head > tail)
			return false;

		if (forward)
			head++;
		else
			tail--;
		return true;
	}

}
