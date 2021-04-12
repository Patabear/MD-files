package pg_no60057;

// 1 <= s <= 1000 소문자
class Solution {
	static int cut;
	static int max_cut;
	static int s_len;
	static String str;
	static int min_length;
	
	
    public int solution(String s) {
    	int answer = 0;
    	max_cut = s.length()/2;
    	s_len = s.length();
    	min_length = s_len;
    	
    	for(cut = 1; cut <= max_cut; cut++) {
			str = s.substring(0, cut);
			int count = 1;
			int temp_len = s_len;
			for(int j = cut; j <= s_len-cut; j+=cut) {
    			if(str.equals(s.substring(j, j+cut))) {
    				count++;
    			}
    			else {
    				if(count > 1) {
    					temp_len = (temp_len - ((count-1)*cut) + ((int) Math.log10(count) + 1));
    				}
    				str = s.substring(j, j+cut);
    				count = 1;
    			}
    		}
			if(count > 1) {
				temp_len = (temp_len - ((count-1)*cut) + ((int) Math.log10(count) + 1));
			}
			min_length = Math.min(min_length, temp_len);
    	}
    	
    	answer = min_length;
        return answer;
    }
}