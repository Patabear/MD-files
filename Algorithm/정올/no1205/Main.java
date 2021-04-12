package jw.no1205;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int Idx;
	static int[] map;
	static int joker;
	static int Max_length;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Idx = 0;
		map = new int[N];
		joker = 0;
		Max_length = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
			if(map[i] == 0) joker++;	// 조커 수 세기
		}
		
		Arrays.sort(map);	// 정렬
		Idx += joker;		// 조커 지나가고 시작
		
		while(Idx < N) {
			Idx = find_straight(Idx);	// 스트레이트 길이세고 마지막index 리턴옴
			Idx++;						// 그 다음 인덱스부터 시작
		}
		
		if(joker == N) System.out.println(N);	// 모든카드가 조커일때
		else System.out.println(Max_length);
		
		

	}
	
	/**
	 * @return 끊어진 index값을 반환
	 * 만약 중간에 끊어져서 조커로 이었을 경우
	 * 최초로 끊어진 부분을 반환함
	 */
	static int find_straight(int start) {
		int before = map[start];
		int joker_used = 0;
		int cut_end = 0;
		int length = 1;
		boolean cut = false;
		int i;
		for(i = start+1; i < N; i++) {
			// 숫자가 같으면 무시
			if(before == map[i]) continue;	
			// 그 다음숫자면 잇고 진행
			if((before+1) == map[i]) {		
				length++;
				before = map[i];
				continue;
			}
			// 중간에 빈숫자가 생기면
			else {							
				if(!cut) {	// 처음 끊어진것이라면 기억 후 리턴
					cut = true;
					cut_end = i-1;
				}
				int between = map[i]-before-1;	// 사이에 빈숫자 개수 확인
				// 빈숫자만큼 조커가 남았다면
				if(between <= (joker-joker_used)) {
					joker_used += between;			// 조커사용
					length += between+1;			// 길이 늘리고
					before = map[i];				// 스트레이트 마지막숫자 적용
					continue;
				}
				// 조커가 충분치 않다면
				else {								
					length += (joker-joker_used);	// 남은조커 다쓰고
					break;							// 탈출
				}
			}
		}
		if(i == N) {	// 마지막 카드를 사용한 것이었다면
			cut = true;
			cut_end = i-1;
			length += (joker-joker_used);	// 남은 조커를 다 붙여줌
		}
		
		Max_length = Math.max(length, Max_length);	// 가장 긴 길이 갱신
		return cut_end;
	}

}
