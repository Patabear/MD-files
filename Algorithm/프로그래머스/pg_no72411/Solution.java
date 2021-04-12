package pg_no72411;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

class Solution {
	static int course_num;
	static int order_cnt;
	static char[] arr;
	static boolean[] selected;
	static HashMap<String, Integer> map;
	
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        ArrayList<String> answerList = new ArrayList<>();
        
        order_cnt = orders.length;
        
        for(int x: course) {
        	map = new HashMap<String, Integer>();
        	course_num = x;
        	for(int i = 0; i < order_cnt; i++) {
        		comb_func(orders[i]);
        	}
        	Set<String> keys = map.keySet();
        	ArrayList<String> arrayList = new ArrayList<>();
        	int max = 0;
        	for(String key: keys) {
        		if( max == map.get(key) ) {
        			if(max >= 2) {
        			arrayList.add(key);
        			}
        		}
        		else if(max < map.get(key)){
        			arrayList.clear();
        			max = map.get(key);
        			if(max >= 2) {
        			arrayList.add(key);
        			}
        		}
        	}
        	
        	for(String str: arrayList) {
        		answerList.add(str);
        	}
        }
        
        answer = new String[answerList.size()];
        int temp_cnt = 0;
        for(String str: answerList) {
        	answer[temp_cnt++] = str;
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    static void comb_func(String str) {
    	int leng = str.length();
    	arr = new char[leng];
    	arr = str.toCharArray();
    	selected = new boolean[leng];
    	comb(0, 0, leng);
    }
    
    static void comb(int tgtIdx, int srcIdx, int leng) {
    	if(tgtIdx == course_num) {
    		char[] temp_arr = new char[course_num];
    		int cnt = 0;
    		StringBuilder sb = new StringBuilder();
    		for(int i = 0; i < leng; i++) {
    			if(selected[i] == true) {
    				temp_arr[cnt++] = arr[i];
    			}
    		}
    		
    		Arrays.sort(temp_arr);
    		for(char ch: temp_arr) {
    			sb.append(ch);
    		}
    		
    		int temp_cnt = 0;
    		if(map.get(sb.toString()) != null) {
    			temp_cnt = map.get(sb.toString());
    		}
    		map.put(sb.toString(), temp_cnt+1);
    		return;
    	}
    	
    	if(srcIdx == leng) {
    		return;
    	}
    	
    	selected[srcIdx] = true;
    	comb(tgtIdx+1, srcIdx+1, leng);
    	selected[srcIdx] = false;
    	comb(tgtIdx, srcIdx+1, leng);
    }
}