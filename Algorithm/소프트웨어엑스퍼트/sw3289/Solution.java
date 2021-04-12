package swacademy.sw3289;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int M;
	static int[] parent;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine());
			sb = new StringBuilder("#" + testcase + " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parent = new int[N+1];
			makeSet(); // 서로소 집합 만들기
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int op = Integer.parseInt(st.nextToken());
				int arg1 = Integer.parseInt(st.nextToken());
				int arg2 = Integer.parseInt(st.nextToken());
				if(op == 0) {
					union(arg1, arg2);
				}
				else if(op == 1) {
					if(findSet(arg1) == findSet(arg2)) sb.append("1");
					else sb.append("0");
				}
			}
			
			System.out.println(sb);
		}
	}
	
	static void makeSet() {
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	
	static void union(int x,  int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px < py) parent[py] = px;
		else parent[px] = py;
	}
}

