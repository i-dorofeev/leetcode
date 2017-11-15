package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;

// https://leetcode.com/problems/regular-expression-matching/description/
class RegularExpressionMatching {

	@DisplayName("Regular expression matching")
	@ParameterizedTest(name = "{0} / {1} -> {2}")
	@CsvSource({
		"aaabbb, a[1-3]b[2-5], true",
		"aaaabbb, a[1-3]b[2-5], false",
		"bbaa, a..., false",
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
		if (matcher == null)
			return input.isEmpty();

		return matcher.match(input, 0);
	}

	private Matcher parse(String pattern) {
		if (pattern.isEmpty())
			return null;

		char symbol = pattern.charAt(0);
		if (pattern.length() <= 1)
			return new Matcher(symbol, 1, 1, null);

		Matcher matcher;
		matcher = tryParseMulti(pattern);
		if (matcher != null)
			return matcher;

		matcher = tryParseRange(pattern);
		if (matcher != null)
			return matcher;

		return new Matcher(symbol, 1, 1, parse(pattern.substring(1)));
	}

	private Matcher tryParseMulti(String pattern) {
		if (pattern.charAt(1) != '*')
			return null;

		return new Matcher(pattern.charAt(0), 0, Integer.MAX_VALUE, parse(pattern.substring(2)));
	}

	private Matcher tryParseRange(String pattern) {
		if (pattern.charAt(1) != '[')
			return null;

		int delimiter = pattern.indexOf('-', 1);
		if (delimiter == -1)
			throw new RuntimeException("Invalid range format: - expected");

		int end = pattern.indexOf(']', delimiter + 1);
		if (end == -1)
			throw new RuntimeException("Invalid range format: ] expected");

		int minOccurs = Integer.parseInt(pattern.substring(2, delimiter));
		int maxOccurs = Integer.parseInt(pattern.substring(delimiter + 1, end));

		Matcher next = parse(pattern.substring(end + 1));
		return new Matcher(pattern.charAt(0), minOccurs, maxOccurs, next);
	}

	private static class Matcher {

		private static final char ANY = '.';

		private final char symbol;
		private final int minOccurs;
		private final int maxOccurs;
		private Matcher next;

		private HashMap<Match, Boolean> cache = new HashMap<>();

		Matcher(char symbol, int minOccurs, int maxOccurs, Matcher next) {
			this.symbol = symbol;
			this.minOccurs = minOccurs;
			this.maxOccurs = maxOccurs;
			this.next = next;
		}

		@Override
		public String toString() {
			String quantifier =
				(minOccurs == 0 && maxOccurs == Integer.MAX_VALUE) ? "*" :
				(minOccurs == 1 && maxOccurs == 1)                 ? "" :
				                                                     "[" + minOccurs + "-" + maxOccurs + "]";
			return "" + symbol + quantifier;
		}

		private boolean match(String input, int start) {
			return matchCached(input, start, minOccurs);
		}

		private static class Match {
			private int start;
			private int minOccurs;

			Match(int start, int minOccurs) {
				this.start = start;
				this.minOccurs = minOccurs;
			}

			@Override
			public boolean equals(Object o) {
				Match match = (Match) o;
				return start == match.start && minOccurs == match.minOccurs;
			}

			@Override
			public int hashCode() {
				return start + 31 * minOccurs;
			}
		}


		private boolean matchCached(String input, int start, int minOccurs) {
			Match m = new Match(start, minOccurs);
			return cache.computeIfAbsent(m, match -> match(input, start, minOccurs));
		}

		private boolean match(String input, int start, int minOccurs) {
			if (minOccurs > input.length() - start)
				return false;

			if (minOccurs > maxOccurs)
				return next != null ? next.match(input, start + 1) : start >= input.length();

			return matchNext(input, start, minOccurs) && (
					(next != null ? next.match(input, start + minOccurs) : start + minOccurs >= input.length())
					||
					matchCached(input, start, minOccurs + 1)
				);
		}

		private boolean matchNext(String input, int start, int left) {
			return left == 0 ||
				start < input.length() &&
				isMatch(input.charAt(start)) &&
				matchNext(input, start + 1, left - 1);
		}

		private boolean isMatch(char input) {
			return (symbol == ANY ? input : symbol) == input;
		}
	}
}
