package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Boolean.TRUE;

// https://leetcode.com/problems/regular-expression-matching/description/
class RegularExpressionMatching {

	@DisplayName("Regular expression matching")
	@ParameterizedTest(name = "{0} / {1} -> {2}")
	@CsvSource({
		"'', c*c*, true",
		"a, ab*a, false",
		"aa, a, false",
		"aa, aa, true",
		"aaa, aa, false",
		"aa, a*, true",
		"aa, .*, true",
		"ab, .*, true",
		"aab, c*a*b, true",
		"aaa, a*a, true",
		"aaaaaaaa, c*aaa*aaac*, true",
		"a, '', false",
		"ab, .*c, false",
		"aaa, aaaa, false",
		"'', '', true",
		"aaaaaaaaaaaaab, a*a*a*a*a*a*a*a*a*a*a*a*b, true",
	})
	void test(String input, String pattern, boolean expectedResult) {

		boolean match = isMatch(input, pattern);
		Assertions.assertEquals(expectedResult, match);
	}

	private boolean isMatch(String input, String pattern) {

		Matcher matcher = parse(pattern);
		if (matcher == null) {
			return input.isEmpty();
		}

		return matcher.match(input, 0);
	}

	private Matcher parse(String pattern) {
		if (pattern.isEmpty())
			return null;

		char symbol = pattern.charAt(0);
		if (pattern.length() > 1) {
			boolean multi = pattern.charAt(1) == '*';

			Matcher next = parse(pattern.substring(multi ? 2 : 1));
			return new Matcher(symbol, multi, next);
		} else {
			return new Matcher(symbol, false, null);
		}
	}

	static class Matcher {

		private static final char ANY = '.';

		private final char symbol;
		private final boolean multi;
		private Matcher next;

		Matcher(char symbol, boolean multi, Matcher next) {
			this.symbol = symbol;
			this.multi = multi;
			this.next = next;
		}

		@Override
		public String toString() {
			return "" + symbol + (multi ? "*" : "") + (next != null ? next.toString() : "");
		}

		boolean match(String input, int start) {
			if (!multi) {
				if (start >= input.length())
					return false;

				Boolean match = match(input, start, 1);
				return TRUE.equals(match);
			} else {
				if (start >= input.length())
					return next == null || next.match(input, start);

				for (int matchSize = 0; matchSize < input.length() - start + 1; matchSize++) {
					Boolean match = match(input, start, matchSize);
					if (match != null)
						return match;
				}

				return false;
			}
		}

		private Boolean match(String input, int start, int size) {
			int i = 0;
			for (; i < size && (start + i) < input.length(); i++) {
				if (!isMatch(input.charAt(start + i)))
					return false;
			}

			if (next != null) {
				boolean isMatch = next.match(input, start + i);
				return isMatch ? true : null;
			} else if (start + i < input.length()) {
				return null;
			} else if (start + i >= input.length()) {
				return true;
			}

			throw new RuntimeException("should not get here");
		}


		boolean isMatch(char input) {
			return (symbol == ANY ? input : symbol) == input;
		}
	}
}
