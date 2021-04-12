package swacademy.no1208;

import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		int[] boxes_by_height; // 박스높이를 index삼아 해당 높이에 있는 박스더미들의 개수를 저장
		int dump, sum, avg, top, bottom, answer, crossover;
		boolean just, answer_founded;
		
		//System.setIn(new FileInputStream("input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 10; i++) {
			boxes_by_height = new int[100];
			dump = Integer.parseInt(br.readLine());
			sum = 0; top = 100; bottom = 0; answer = 1; crossover = 0;
			answer_founded = false;
			
			// 박스더미들을 높이를 index삼아 높이별로 몇개의 박스더미가 있는지 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = st.countTokens();
			for(int j = 0; j < t; j++) {
				int temp = Integer.parseInt(st.nextToken())-1; // 0~99라서
				boxes_by_height[temp]++; // 높이를 index로
				sum += (temp+1);
			}
			
			avg = sum/100; // 박스총량으로 높이 평균구함
			just = (sum%100 == 0) ? true : false; // 박스들의 합이 딱 100에 나눠 떨어질때
			// 위의 just는 dump로 모든 완전평탄화가 되었을때 층차이가 1인지 0인지 구분하기위함
			
			sum = 0;
			crossover = 0;
			for(int j = 99; j >= 0; j--) {
				crossover += boxes_by_height[j]; // 한칸 내려올때마다 이미 지금높이보다 높았던애들은 계속 박스가 있음
				sum += crossover; // 지금위치보다 높거나 있는 박스들을 sum에 더함
				// 평탄화전에 dump가 소진되어 top위치를 찾았을때
				if(sum == dump) { top = j-1; break; } // dump로 옮긴후 맨 위층 완벽하게 평탄할때
				else if(sum > dump) { top = j; break; } // 1개 이상 남았을경우 그 위층이 top임
				// 아래경우는 dump로 충분히 평탄화가 완료되어 더 찾을필요가 없고 그것이 완전평탄인지아닌지
				if(j < avg) { answer_founded = true; answer = just ? 0 : 1; break; } 
			}
			
			// 아래서부터 체크하면서 올라감
			sum = 0;
			crossover = 0;
			if(!answer_founded) {
				for(int j = 1; j <= 100; j++) {
					crossover += boxes_by_height[j-1];
					sum += crossover;
					if(sum == dump) { bottom = j; break; } // dump로 옮겨받은후 맨 밑층이 평탄할때
					if(sum > dump) { bottom = j-1; break; } // 1개이상의 구멍이 있을경우 그 아래층이 bottom임
				}
			}
			
			if(answer_founded) System.out.println("#" + (i+1) + " " + answer);
			else System.out.println("#" + (i+1) + " " + (top-bottom));
		}
		

	}

}
