package bj_no18352;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int K;
	static int X;
	static Queue<struct> queue;
	static HashMap<Integer, ArrayList<Integer>> hashMap;
	static boolean[] visited;
	static ArrayList<Integer> answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		queue = new LinkedList<struct>();
		hashMap = new HashMap<>();
		visited = new boolean[N+1];
		answer = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			if(hashMap.containsKey(from)) {
				hashMap.get(from).add(to);
			}
			else {
				ArrayList<Integer> temp_arr = new ArrayList<>();
				temp_arr.add(to);
				hashMap.put(from, temp_arr);
			}
		}
		
		if(!hashMap.containsKey(X)) {
			System.out.println("-1");
			return;
		}
		
		visited[X] = true;
		
		// 아래의 코드가 다른점입니다.
		//----------------------------
		
		// 1번 : 이  주석처리된 부분을 사용하면 틀렸습니다가 나옵니다.
//		for(int x: hashMap.get(X)) {
//			queue.add(new struct(x, 1));
//			visited[x] = true;
//		}
		
		// 2번 : 아래 이 한줄을 사용하면 맞았습니다가 나옵니다.
		queue.add(new struct(X, 0));
		
		//----------------------------
		
		while(!queue.isEmpty()) {
			struct str = queue.poll();
			if(str.depth > K) break;
			
			if(str.depth == K) {
				answer.add(str.val);
				continue;
			}
			
			if(hashMap.containsKey(str.val)) {
				ArrayList<Integer> temp_arr = hashMap.get(str.val);
				for(int x: temp_arr) {
					if(!visited[x]) {
						visited[x] = true;
						queue.add(new struct(x, str.depth+1));
					}
				}
			}
		}
		
		if(answer.size() == 0) {
			System.out.println("-1");
		}
		
		else {
			Collections.sort(answer);
			
			for(int x: answer) {
				System.out.println(x);
			}
		}
	}
	
	static class struct {
		public struct(int val, int depth) {
			this.val = val;
			this.depth = depth;
		}
		int val;
		int depth;
	}

}
