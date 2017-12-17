package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

// https://leetcode.com/problems/longest-valid-parentheses/description/
class LongestValidParenthesis {
	@DisplayName("Longest valid parenthesis")
	@ParameterizedTest(name = "{0} -> {1}")
	@CsvSource({
		")()())()()(, 4",
		"((()))()), 8",
		"((), 2",
		")()()), 4",
		"(, 0",
		"()(()), 6",
		"((((((((), 2",
		"(((((((, 0",
	})
	void test(String input, int expectedResult) {
		Assertions.assertEquals(expectedResult, longestValidParentheses(input));
	}

	public int longestValidParentheses(String s) {

		if (s.length() < 2)
			return 0;

		int level = 0;
		int max = 0;
		int[] mem = new int[s.length() + 1];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			max = mem[level] > max ? mem[level] : max;

			if (c == '(') {
				level++;
				continue;
			}

			if (c == ')') {
				if (level == 0) {
					mem[level] = 0;
					continue;
				}

				level--;
				mem[level] += 2 + mem[level + 1];
				mem[level + 1] = 0;
			}
		}

		return mem[level] > max ? mem[level] : max;
	}
}
