package swacademy.sw1251;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
   static int N;
   static int[] x;
   static int[] y;
   static double E;
   static Double[] vertex;
   static boolean[] visit;
   static Double INF;
   static Double total_dist;
   static PriorityQueue<str> pq;

   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      int T = Integer.parseInt(br.readLine());
      
      for(int testcase = 1; testcase <= T; testcase++) {
         N = Integer.parseInt(br.readLine());
         x = new int[N];
         y = new int[N];
         vertex = new Double[N];
         visit = new boolean[N];
         total_dist = 0.0;
         pq = new PriorityQueue<str>();
         
         st = new StringTokenizer(br.readLine());
         for(int i = 0; i < N; i++) {
        	 x[i] = Integer.parseInt(st.nextToken());
         }
         st = new StringTokenizer(br.readLine());
         for(int i = 0; i < N; i++) {
        	 y[i] = Integer.parseInt(st.nextToken());
         }

         E = Double.parseDouble(br.readLine());
         INF = Math.pow(2, 63);
//         System.out.println(INF);
         
         for(int i = 1; i < N; i++) {
        	 vertex[i] = INF;
         }
         
         vertex[0] = (double) 0;
         pq.add(new str(0, 0.0));
         
         while(!pq.isEmpty()) {
        	 str temp = pq.poll();
        	 int Idx = temp.idx;
        	 
        	 if(visit[Idx]) continue;
        	 visit[Idx] = true;
        	 total_dist += temp.value;
        	 
        	 for(int j = 0; j < N; j++) {
        		 if(visit[j]) continue;
        		 long a = Math.abs(x[Idx]-x[j]);
        		 long b = Math.abs(y[Idx]-y[j]);
        		 Double dist = (a*a + b*b) * E;
        		 if(dist < vertex[j]) {
        			 vertex[j] = dist;
        			 pq.add(new str(j, dist));
        		 }
        	 }
        	 
         }
         
         System.out.println("#" + testcase + " " + Math.round(total_dist));
      }
   }
   
   static class str implements Comparable<str>{
	   int idx;
	   Double value;
	   public str(int idx, Double value) {
		this.idx = idx;
		this.value = value;
	   }
		@Override
		public int compareTo(str o) {
			return this.value < o.value ? -1 : 1;
		}
   }

}

