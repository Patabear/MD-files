package pg_no60057;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "aabbaccc";
		System.out.println("result " + solution.solution(s));
		s = "ababcdcdababcdcd";
		System.out.println("result " + solution.solution(s));
		s = "abcabcdede";
		System.out.println("result " + solution.solution(s));
		s = "abcabcabcabcdededededede";
		System.out.println("result " + solution.solution(s));
		s = "xababcdcdababcdcd";
		System.out.println("result " + solution.solution(s));
	}

}
