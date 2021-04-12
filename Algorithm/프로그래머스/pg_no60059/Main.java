package pg_no60059;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
		int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		System.out.println("result " + solution.solution(key, lock));
		
	}

}
