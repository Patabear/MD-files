package baekjoon.no20304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 모든 pasword 후보 N 에 대해, 모든 leak passwrord M 에 대해  bit 연산을 수행하면 연산수가 초과
// N 안에 M 을 포함해서 생각
//  leak password 로부터 bit 1 거리의 숫자를 찾아서 별도의 배열에 표시
//  해당 배열은 visit select 역할을 수행
public class Main {

	static int N, M, result = 0;
	static int[] distance; // index ( 패스워드 후보 숫자 ) 별 안전거리
	static Queue<Pwd> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine()); // 패스워드 최댓값
		M = Integer.parseInt(br.readLine()); // 해커가 사용한 패스워드 갯수

		distance = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			distance[i] = -1; // 미방문. 방문되는 순간 거리정보로 갱신함.
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int leak = Integer.parseInt(st.nextToken()); // 공격한 비밀번호
			queue.add(new Pwd(leak, 0)); // 큐에 모아놓고
			distance[leak] = 0; // 얘네들은 안전거리(보안척도) 0 얘네들로부터 1비트씩 멀어지는걸 기록하기
		}
		
		// bfs탐색해서 해커가 공격한 비밀번호로부터 1비트씩 멀어지면서 거리 기록할 예정.
		while (!queue.isEmpty()) {
			Pwd n = queue.poll();
			for (int temp = 1; temp <= N; temp <<= 1) {// 1-2-4-8-16-31...<N 을 옆으로 한칸씩 쉬프트하면서 기존 숫자와 매칭해서
				
				// 기존 숫자 n.num이랑 1비트가 다른 숫자 만들기.
				// 음수가 안되도록 더하거나, 빼거나
				int next = (n.num & temp) > 0 ? (n.num - temp) : (n.num + temp); 
				// 유효하고 안전거리 카운트 한적 없으면
				if(next<=N && distance[next]==-1) { 
					distance[next] = n.count + 1; // 현재 n.num이랑 1비트 차이나는 숫자니까 거리+1 해서 큐에 넣기.
					queue.add(new Pwd(next, n.count + 1)); // 큐에 넣어서 그 다음 숫자들 찾으러 다니기
				}
			}
		}
		
		for(int i=0; i<=N; i++) {
			result = Math.max(result, distance[i]); // 방금 발견한 숫자의 안전거리가 max 인지 갱신확인.
		}
		
		System.out.println(result);
	}

	static class Pwd {
		int num, count;

		Pwd(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}
}











//		내가 제출한 코드
//		public class Algo3_서울_13_이희정 {
//			static int N, M;				// 관리자 패스워드의 최댓값, 해커가 사용한 패스워드 개수
//			static int[] P;					// 해커가 사용한 패스워드들
//			static int MAX_secure_score;	// 최종 답안
//			
//			public static void main(String[] args) throws NumberFormatException, IOException {
//				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	// 입력에 버퍼드리더 이용
//				
//				N = Integer.parseInt(br.readLine());								// 관리자 패스워드 최댓값
//				M = Integer.parseInt(br.readLine());								// 해커 패스워드 개수
//				P = new int [M];													// 해커 패스워드를 입력받을 배열
//				MAX_secure_score = 0;												// 최종 답안 최대 보안성값 초기화
//				
//				StringTokenizer st = new StringTokenizer(br.readLine());
//				for(int i = 0; i < M; i++) P[i] = Integer.parseInt(st.nextToken());
//				
//				
//				// solve
//				for(int admin_pass = 0; admin_pass <= N; admin_pass++) {			// 관리자 패스워드 최댓값만큼 반복
//					int secure_score = 100;											// 적당히 큰 수 2의 100제곱 수는 나올수 없으므로
//					
//					for(int j = 0; j < M; j++) {									// 해커 패스워드 개수만큼 반복
//						int temp_secure_score = 0;									// 지금 해커패스워드와 비교해서 나온 보안척도
//						int comp = admin_pass ^ P[j];								// 관리자 패스워드와 해커패스워드 비트비교값 저장
//						while(comp!=0) {											// 비교 하는 값이 0이 되지않으면
//							if(comp%2 == 1) temp_secure_score++;					// 끝비트 확인 보안척도+1
//							comp = comp>>1;											// 나누기 2
//						}
//						secure_score = Math.min(temp_secure_score, secure_score);	// 이번 보안성을 현재 관리자패스워드의 보안성 중 가장 낮은값 저장
//					}
//					
//					MAX_secure_score = Math.max(secure_score, MAX_secure_score);	// 구한 보안척도들 중 가장 높은 보안척도를 구함
//				}
//				
//				System.out.println(MAX_secure_score);
//			}
//
//		}



		
		
		
//		MK - 3
//		if(N < 1) { length = 0; }
//		if(N < 2) { length = 1; }
//		else if (N < 4) { length = 2; }
//		else if (N < 8) { length = 3; }
//		else if (N < 16) { length = 4; }
//		else if (N < 32) { length = 5; }
//		else if (N < 64) { length = 6; }
//		else if (N < 128) { length = 7; }
//		else if (N < 256) { length = 8; }
//		else if (N < 512) { length = 9; }
//		else { length = 10; }
//		P = new int [M];													// 해커 패스워드를 입력받을 배열
//		inverted_P = new int[length][M];
//		
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		for(int i = 0; i < M; i++) {
//			P[i] = Integer.parseInt(st.nextToken());
//			int temp = P[i];
//			while(comp!=0) {											// 비교 하는 값이 0이 되지않으면
//				if(comp%2 == 1) temp_secure_score++;					// 끝비트 확인 보안척도+1
//				comp = comp>>1;											// 나누기 2
//			}
//		}
//		
//		select = new boolean[length];
//		CNT_P = new int[M];
//		
//		recursionQ(0);
//		
//		System.out.println(Max_score);
//		
//	}
//	
//	static void recursionQ(int srcIdx) {
//		if(srcIdx == length) {
//			int temp_sum = 0;
//			
//			for(int i = 0; i < length; i++) {
//				if(select[i]) temp_sum += Math.pow(2, i);
//			}
//			
//			if(temp_sum > N) {
//				return;
//			}
//			
//			int secure_score = 100;		// 적당히 큰 수
//			for(int i = 0; i < M; i++) {
//				int temp_secure_score = 0;
//				int comp = temp_sum ^ P[i];
//				while(comp!=0) {											// 비교 하는 값이 0이 되지않으면
//					if(comp%2 == 1) temp_secure_score++;					// 끝비트 확인 보안척도+1
//					comp = comp>>1;											// 나누기 2
//				}
//				secure_score = temp_secure_score < secure_score ? temp_secure_score : secure_score;
//			}
//			
//			Max_score = Max_score < secure_score ? secure_score : Max_score;
//			//System.out.println(temp_sum);
//			return;
//		}
//		
//		for(int i = 0; i < M; i++) {
//			int temp = P[i];
//			temp = temp>>srcIdx;
//			for(int j = 0; j < M; j++) {
//				
//			}
//		}
//		
//		select[srcIdx] = true;
//		recursionQ(srcIdx+1);
//		select[srcIdx] = false;
//		recursionQ(srcIdx+1);
		

//		MK - 2
//		int mid = M/2+1;
//		if(M <= 2) mid = 1;
//		int answer = 1023;
//		
//		if(N < 2) { answer = 1; }
//		else if (N < 4) { answer = 3; }
//		else if (N < 8) { answer = 7; }
//		else if (N < 16) { answer = 15; }
//		else if (N < 32) { answer = 31; }
//		else if (N < 64) { answer = 63; }
//		else if (N < 128) { answer = 127; }
//		else if (N < 256) { answer = 255; }
//		else if (N < 512) { answer = 511; }
//		
//		for(int i = 0; i < 10; i++) {
//			if(count[i] >= mid) answer -= Math.pow(2,i);
//		}
//		
//		System.out.println(answer);
//		
//		if(answer > N) {
//			mid = M/2;
//			if(M <= 2) mid = 1;
//			answer = 1023;
//			
//			if(N < 2) { answer = 1; }
//			else if (N < 4) { answer = 3; }
//			else if (N < 8) { answer = 7; }
//			else if (N < 16) { answer = 15; }
//			else if (N < 32) { answer = 31; }
//			else if (N < 64) { answer = 63; }
//			else if (N < 128) { answer = 127; }
//			else if (N < 256) { answer = 255; }
//			else if (N < 512) { answer = 511; }
//			
//			for(int i = 0; i < 10; i++) {
//				if(count[i] >= mid) answer -= Math.pow(2,i);
//			}
//		}
//		
//		System.out.println(answer);
//		
//		int temp = answer;
//		for(int j = 0; j < 10; j++) {
//			if(answer <= N) break;
//			if((temp & 1) == 1)
//				answer -= Math.pow(2,j);
//			temp = temp>>1;
//		}
//		
//		System.out.println(answer);
//
//		for(int j = 0; j < M; j++) {									// 해커 패스워드 개수만큼 반복
//			int temp_secure_score = 0;									// 지금 해커패스워드와 비교해서 나온 보안척도
//			int comp = answer ^ P[j];								// 관리자 패스워드와 해커패스워드 비트비교값 저장
//			while(comp > 0) {
//				comp &= comp - 1;
//				temp_secure_score++;
//			}
//			MAX_secure_score = Math.min(MAX_secure_score, temp_secure_score);
//		}
//		for(int x: count) System.out.print(x + " ");
//		System.out.println(MAX_secure_score);
//		
//	}
//	
//
//}
		
		
		
//		MK - 1
//		int bit_off = 1023;
//		int length = 10;
//		if(N < 2) { bit_off = 1; length = 1; }
//		else if (N < 4) { bit_off = 3; length = 2; }
//		else if (N < 8) { bit_off = 7; length = 3; }
//		else if (N < 16) { bit_off = 15; length = 4; }
//		else if (N < 32) { bit_off = 31; length = 5; }
//		else if (N < 64) { bit_off = 63; length = 6; }
//		else if (N < 128) { bit_off = 127; length = 7; }
//		else if (N < 256) { bit_off = 255; length = 8; }
//		else if (N < 512) { bit_off = 511; length = 9; }
		
		
		
//		for(int i = 0; i < M; i++) {
//			bit_on = bit_on & P[i];			// 1이 다 같은애들인지
//			bit_off = bit_off & ~P[i];		// 0이 다 같은애들인지
//		}
//		
//		// bit on은 1같은개수를 찾으러갑니다.
//		while(bit_on > 0) {
//			System.out.println(bit_on + "on");
//			bit_on &= bit_on - 1;
//			is_same_hackpass++;
//		}
//		// bit off는 0 같은개수를 찾으러 갑니다. 세는것은 1개수 세면 됩니다 뒤집어서 계산했으므로
//		while(bit_off > 0) {
//			System.out.println(bit_off + "off");
//			bit_off &= bit_off - 1;
//			is_same_hackpass++;
//		}
		
		
//		for(int admin_pass = 0; admin_pass <= N; admin_pass++) {			// 관리자 패스워드 최댓값만큼 반복
//			int secure_score = 100;											// 적당히 큰 수 2의 100제곱 수는 나올수 없으므로
//			
//			for(int j = 0; j < M; j++) {									// 해커 패스워드 개수만큼 반복
//				int temp_secure_score = 0;									// 지금 해커패스워드와 비교해서 나온 보안척도
//				int comp = admin_pass ^ P[j];								// 관리자 패스워드와 해커패스워드 비트비교값 저장
//				while(comp > 0) {
//					comp &= comp - 1;
//					temp_secure_score++;
//				}
//				if(temp_secure_score < secure_score) secure_score = temp_secure_score; 	// 이번 보안성을 현재 관리자패스워드의 보안성 중 가장 낮은값 저장
//			}
//			if(MAX_secure_score < secure_score) MAX_secure_score = secure_score;		// 구한 보안척도들 중 가장 높은 보안척도를 구함
//		}
//		if(length == is_same_hackpass) System.out.println(is_same_hackpass);
//		else System.out.println(is_same_hackpass+1);

