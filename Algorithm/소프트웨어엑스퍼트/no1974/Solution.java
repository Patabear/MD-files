package sw.no1974;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testcase = 1; testcase <= T; testcase++) {
			boolean answer = true;
			int[][] map = new int[9][9];
			boolean[][] vertical_check = new boolean[9][9];
			boolean[][] rect_check = new boolean[9][9];
			
			for(int i = 0; i < 9; i++) {
				st = new StringTokenizer(br.readLine());
				boolean[] check = new boolean[9];
				for(int j = 0; j < 9; j++) {
					if(answer == false) continue;
					int temp = Integer.parseInt(st.nextToken())-1;
					map[i][j] = temp;
					if(check[temp]) answer = false;
					else check[temp] = true;
					
					if(vertical_check[j][temp]) answer = false;
					else vertical_check[j][temp] = true;
					
					int rect_Idx;
					if(i<3) {
						if(j<3) rect_Idx = 0;
						else if(j<6) rect_Idx = 1;
						else rect_Idx = 2;
					}
					else if(i<6) {
						if(j<3) rect_Idx = 3;
						else if(j<6) rect_Idx = 4;
						else rect_Idx = 5;
					}
					else {
						if(j<3) rect_Idx = 6;
						else if(j<6) rect_Idx = 7;
						else rect_Idx = 8;
					}
					
					if(rect_check[rect_Idx][temp]) answer = false;
					else rect_check[rect_Idx][temp] = true;
				}
			}
			
			if(answer) System.out.println("#" + testcase + " 1");
			else System.out.println("#" + testcase + " 0");
		}

	}

}
