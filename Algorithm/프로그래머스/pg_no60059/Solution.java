package pg_no60059;

import java.util.ArrayList;

class Solution {
    public boolean solution(int[][] key, int[][] lock) {
    	int M = key.length;
    	int N = lock.length;
    	ArrayList<pos> key_bump1 = new ArrayList<pos>();
    	ArrayList<pos> key_bump2 = new ArrayList<pos>();
    	ArrayList<pos> key_bump3 = new ArrayList<pos>();
    	ArrayList<pos> key_bump4 = new ArrayList<pos>();
    	ArrayList<pos> lock_holes = new ArrayList<pos>();
    	int key_bump_cnt;
    	int lock_hole_cnt;
    	pos std_hole = null;
    	boolean sucess = false;
    	
    	// lock 구멍하나 잡고 거기에 모든 키의 경우수를 대조
    	// 기준이 되는 구멍 제외하고 add
    	boolean isfind = false;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(lock[i][j] == 0) {
    				if(!isfind) {
	    				std_hole = new pos(i, j); // 기준이 될 구멍
	    				isfind = true;
    				}
    				else {
    					lock_holes.add(new pos(i,j));
    				}
    			}
    		}
    	}
    	if(!isfind) return true; // 자물쇠가 이미 열려있음
    	lock_hole_cnt = lock_holes.size();
    	
    	// key 돌기 저장
    	isfind = false;
    	for(int i = 0; i < M; i++) {
    		for(int j = 0; j < M; j++) {
    			if(key[i][j] == 1) {
    				key_bump1.add(new pos(i, j));
    				isfind = true;
    			}
    		}
    	}
    	if(!isfind) return false; // 열쇠가 음슴, 여기왔다는건 자물쇠가 잠겨있다는 뜻
    	key_bump_cnt = key_bump1.size();
    	if(lock_hole_cnt == 0) return true; // 열쇠가 있는데 자물쇠 빈칸이 하나
    	if(lock_hole_cnt+1 > key_bump_cnt) return false; // 열쇠돌기보다 자물쇠 구멍이 많음
    	
    	
    	// 키 돌리기 저장
    	for(pos temp: key_bump1) {
    		key_bump2.add(new pos(temp.j, M-1-temp.i));
    		key_bump3.add(new pos(M-1-temp.i, M-1-temp.j));
    		key_bump4.add(new pos(M-1-temp.j, temp.i));
    	}
    	
    	for(int i = 0; i < key_bump_cnt; i++) {
    		// 자물쇠에 맞는 수 체크
    		// 기준구멍에 열쇠돌기 하나 끼우고 시작하므로
    		// lock arraylist개수만큼만 끼워지면 성공
    		int unlock_cnt1 = 0;
    		int unlock_cnt2 = 0;
    		int unlock_cnt3 = 0;
    		int unlock_cnt4 = 0;
    		
    		// 기준이 되는 구멍에 열쇠돌기 하나끼운뒤 변경된 열쇠돌기위치가 저장될 list
    		ArrayList<pos> temp_key1 = new ArrayList<>();
    		ArrayList<pos> temp_key2 = new ArrayList<>();
    		ArrayList<pos> temp_key3 = new ArrayList<>();
    		ArrayList<pos> temp_key4 = new ArrayList<>();
    		
    		// 키 하나를 맞추고 시작
    		pos key_std1 = key_bump1.get(i);
    		pos key_std2 = key_bump2.get(i);
    		pos key_std3 = key_bump3.get(i);
    		pos key_std4 = key_bump4.get(i);
    		
    		// 자물쇠를 기준으로 키를 움직였을때 움직인 변화값
    		int di1 = std_hole.i - key_std1.i;
			int dj1 = std_hole.j - key_std1.j;
			int di2 = std_hole.i - key_std2.i;
			int dj2 = std_hole.j - key_std2.j;
			int di3 = std_hole.i - key_std3.i;
			int dj3 = std_hole.j - key_std3.j;
			int di4 = std_hole.i - key_std4.i;
			int dj4 = std_hole.j - key_std4.j;
			
			
			// 움직인 키들의 위치를 기억
			for(int j = 0; j < key_bump_cnt; j++) {
				if(i == j) continue; // 기준키 위치 제외 (이미 맞춰진 것)
				pos temp1 = key_bump1.get(j);
				temp_key1.add(new pos(temp1.i+di1, temp1.j+dj1));
				pos temp2 = key_bump2.get(j);
				temp_key2.add(new pos(temp2.i+di2, temp2.j+dj2));
				pos temp3 = key_bump3.get(j);
				temp_key3.add(new pos(temp3.i+di3, temp3.j+dj3));
				pos temp4 = key_bump4.get(j);
				temp_key4.add(new pos(temp4.i+di4, temp4.j+dj4));
			}
			
			// 열쇠돌기가 자물쇠 돌기에 부딪히진 않았나요? 갓뎀.. 문제조건 헬파티
			boolean temp1_success = true;
			boolean temp2_success = true;
			boolean temp3_success = true;
			boolean temp4_success = true;
			
			for(pos temp_hole: lock_holes) {
				for(pos x: temp_key1) {
					boolean find = false;
					if(x.i == temp_hole.i && x.j == temp_hole.j) { // 열쇠가 맞으면
						unlock_cnt1++;
						find = true;
					}
					if(!find) { // 열쇠가 안맞는데
						if(x.i < 0 || x.j < 0 || x.i > N-1 || x.j > N-1) continue;
						else {
							// 심지어 열쇠가 자물쇠의 돌기와 부딪혀 버린다면
							if(lock[x.i][x.j] == 1) {
								temp1_success = false; // X망..
								break;
							}
						}
					}
				}
				
				for(pos x: temp_key2) {
					boolean find = false;
					if(x.i == temp_hole.i && x.j == temp_hole.j) {
						unlock_cnt2++;
						find = true;
					}
					if(!find) {
						if(x.i < 0 || x.j < 0 || x.i > N-1 || x.j > N-1) continue;
						else {
							if(lock[x.i][x.j] == 1) {
								temp2_success = false;
								break;
							}
						}
					}
				}
				
				for(pos x: temp_key3) {
					boolean find = false;
					if(x.i == temp_hole.i && x.j == temp_hole.j) {
						unlock_cnt3++;
						find = true;
					}
					if(!find) {
						if(x.i < 0 || x.j < 0 || x.i > N-1 || x.j > N-1) continue;
						else {
							if(lock[x.i][x.j] == 1) {
								temp3_success = false;
								break;
							}
						}
					}
				}
				for(pos x: temp_key4) {
					boolean find = false;
					if(x.i == temp_hole.i && x.j == temp_hole.j) {
						unlock_cnt4++;
						find = true;
					}
					if(!find) {
						if(x.i < 0 || x.j < 0 || x.i > N-1 || x.j > N-1) continue;
						else {
							if(lock[x.i][x.j] == 1) {
								temp4_success = false;
								break;
							}
						}
					}
				}
				
			}
			
			if(temp1_success && unlock_cnt1 == lock_hole_cnt) sucess = true;
			if(temp2_success && unlock_cnt2 == lock_hole_cnt) sucess = true;
			if(temp3_success && unlock_cnt3 == lock_hole_cnt) sucess = true;
			if(temp4_success && unlock_cnt4 == lock_hole_cnt) sucess = true;
    	}
    	
        boolean answer = sucess;
        return answer;
    }
    
    static class pos {
    	int i;
    	int j;
		public pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
    }
}