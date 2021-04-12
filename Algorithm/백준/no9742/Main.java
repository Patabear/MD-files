package baekjoon.no9742;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int COUNT, tgtCOUNT; // 총 건수 (경우의 수), 문제의 카운트
	static char[] src;			// 입력받은 내용들
	static char[] tgt;			// COUNT번째 찾은 permutaion 
	static char[] answer;		// 답
	static boolean[] select;	// permutation 함수 내에서 중복확인용
	static boolean found;		// 찾아서 더이상 할 필요가없음
	static int[] factorial = {1,1,2,6,24,120,720,5040,40320,362880,3628800}; 
	
	// 아오 씨이이이발 좆같네 씨이이발 Scanner의 nextLine은 비면 에러뜸
	// 해당에러는 NoSuchElementException
	// BufferedReader는 그냥 null을 가져옴 개 씨이이발 2시간동안 찾았네 씨발 ㅈ같네 진짜 아오 개 ㅈ같은 씨이이발.. 후우우..
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String problem;
		
		while(true) {
			// input & initialize
			problem = br.readLine();							// 문제를 스트링으로 저장했다 활용
			if(problem == null) break;
			st = new StringTokenizer(problem);
			src = st.nextToken().toCharArray();					// 소스가 될내용과
			tgtCOUNT = Integer.parseInt(st.nextToken());		// 몇번째 퍼뮤를 원하는지 입력
			
			tgt = new char[src.length];							// 찾는중에 필요한 배열
			select = new boolean[src.length];					// 찾는중에 필요한 배열
			found = false;										// 해당 퍼뮤를 찾은경우
			COUNT = 0;											// 몇번째 펴뮤인지 카운트용
			
			// solve
			if(tgtCOUNT <= factorial[src.length]) perm(0);		// 최대퍼뮤카운트를 넘을경우 그냥 No perm
			
			if(found) {
				System.out.print(problem + " = ");
				for(char x: answer) System.out.print(x);
				System.out.println();
			}
			else System.out.println(problem + " = No permutation");
			
			
		}
	}
	
	// 2, 1, 3			// select : 2, 1
	public static void perm(int tgtIdx) {		// tgtIdx는 타겟이 현재 몇개 갖고있는지 ex) 5개소스중 4개길이 순열인데 tgtIdx가 3이면 하나 더붙여야함 
		//if(found) return;						// 찾은경우
		
		// 기저조건
		if(tgtIdx == tgt.length) {
			COUNT++;
			if(COUNT == tgtCOUNT) {				// 해당되는 퍼뮤를 찾은경우
				answer = tgt.clone();			// 답배열에 복사
				found = true;
			}
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(select[i]) continue;
			
			tgt[tgtIdx] = src[i];
			select[i] = true;
			perm(tgtIdx+1);
			select[i] = false;		// 선택하고 다시 취소해야 for문의 다음i에서 다른것도 가능 
		}
	}
}
