package no18111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 블록제거 2초, 블록쌓기 1초, 인벤에 블록 B개, 높이제한 256까지 가능 음수불가, 답이여러개이면 높이가 높은거
public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int[] blocks_by_height = new int[257];	// 높이별로 나눠서 저장
		int height_sum = 0;
		int height_avg;
		int answer_height = 0;
		int answer_time = 1<<30;				// 충분히 큰수
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int temp = Integer.parseInt(st.nextToken());
				height_sum += temp;
				blocks_by_height[temp]++;
			}
		}
		
		height_avg = height_sum/(N*M);	// 블록들의 평균 높이값은 답이 될 기준선에 비해 항상 낮거나 같음
		
		for(int line = height_avg; line <= 256; line++) {
			int over_blocks = 0;
			int under_blocks = 0;
			
			for(int j = 0; j < 257; j++) {
				if(blocks_by_height[j] == 0) continue;
				
				// 기준선보다 아래의 블록들인 경우와 위의 블록인경우로 나눔
				if(j < line) {
					under_blocks += (line-j)*blocks_by_height[j];
				}
				if(j > line) {
					over_blocks += (j-line)*blocks_by_height[j];
				}
			}
			
			int use_block = under_blocks - over_blocks;
			int current_time = (over_blocks*2) + under_blocks;
			
			if(current_time > answer_time) break;
			else {
				if(use_block > B) break;
				answer_height = line;
				answer_time = current_time;
			}
		}
		
		System.out.println(answer_time + " " + answer_height);
		
		
	}

}

// (기준선 위로 블록개수*2) + (기준선 밑으로 블록개수*1) => 시간
// (기준선위로개수)  + (기준선 밑의 개수 *2) 삐져나온 블록수
