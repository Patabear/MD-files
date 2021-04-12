package bj_no1389;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
	static int N;
	static int M;
	static HashMap<Integer, ArrayList<Integer>> hashMap;
	static boolean[] visited;
	static int min_answer;
	static Queue<element> queue;
	static int[] arr;
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		hashMap = new HashMap<>();
		visited = new boolean[N+1];
		min_answer = 200000;
		queue = new LinkedList<element>();
		answer = 0;
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(hashMap.containsKey(a)) {
				hashMap.get(a).add(b);
			}
			else {
				ArrayList<Integer> temp_arr = new ArrayList<>();
				temp_arr.add(b);
				hashMap.put(a, temp_arr);
			}
			
			if(hashMap.containsKey(b)) {
				hashMap.get(b).add(a);
			}
			else {
				ArrayList<Integer> temp_arr = new ArrayList<>();
				temp_arr.add(a);
				hashMap.put(b, temp_arr);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			queue.clear();
			arr = new int[N+1];
			visited = new boolean[N+1];
			bfs(i);
			int sum = 0;
			for(int j = 1; j <= N; j++) {
				sum += arr[j];
			}
			if(sum < min_answer) {
				min_answer = sum;
				answer = i;
			}
		}
		System.out.println(answer);
	}
	
	static void bfs(int startIdx) {
		visited[startIdx] = true;
		queue.add(new element(startIdx, 0));
		
		while(!queue.isEmpty()) {
			element temp = queue.poll();
			
			if(temp.dist < arr[temp.idx] || arr[temp.idx] == 0) {
				arr[temp.idx] = temp.dist;
			}
			
			
			for(int x: hashMap.get(temp.idx)) {
				if(!visited[x]) {
					visited[x] = true;
					queue.add(new element(x, temp.dist+1));
				}
			}
		}
		
		visited[startIdx] = false;
	}
	
	static class element {
		int idx;
		int dist;
		public element(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
	}

}
