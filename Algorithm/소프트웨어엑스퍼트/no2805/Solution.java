package sw.no2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   // BufferedReader 사용
         
        int[][] map;                                        // 농장
        int T, N, sum;                                      // T테스트케이스, N농장의크기 ,sum수익합
         
         
        // input
        T = Integer.parseInt(br.readLine());                // 테스트케이스 개수 입력
         
        for(int testcase = 1; testcase <= T; testcase++) {   // 테스트케이스 개수만큼 반복
            // input
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
             
            for(int i = 0; i < N; i++) {
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j < N; j++) {
                    map[i][j] = temp[j]-48;
                }
            }
             
             
            // solve
            // 계산에 필요한 변수들, 마름모꼴을 만들기위한 배열index에 맞는 변수
            int n = N-1;
            int mid = n/2;
            sum = 0;
             
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(j >= Math.abs(mid-i) && j <= n-Math.abs(mid-i))
                    { sum += map[i][j]; }
                }
            }
             
            System.out.println("#" + testcase + " " + sum);
        }
        br.close();
    }
}