package pg_no72411;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] orders = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = new int[] {2, 3, 4};
		System.out.println(Arrays.toString(solution.solution(orders, course)));
		orders = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
		course = new int[] {2, 3, 5};
		System.out.println("--------------------------");
		System.out.println(Arrays.toString(solution.solution(orders, course)));
		orders = new String[] {"XYZ", "XWY", "WXA"};
		course = new int[] {2, 3, 4};
		System.out.println("--------------------------");
		System.out.println(Arrays.toString(solution.solution(orders, course)));
	}

}
