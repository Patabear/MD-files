package bj.no10163;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][]map = new int[101][101];
		int[] answer = new int[N+1];
		
		for(int Idx = 1; Idx < N+1; Idx++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken()); // i방향 값
			int height = Integer.parseInt(st.nextToken());// j방향 값
			
			for(int x = i; x < i+width; x++) {
				for(int y = j; y < j+height; y++) {
					map[x][y] = Idx;
				}
			}
		}
		
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(map[i][j] != 0) answer[map[i][j]]++;
			}
		}
		
		for(int i = 1; i < N+1; i++) System.out.println(answer[i]);
		
	}

}
