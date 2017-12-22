package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class P044_WildcardMatching {

	@DisplayName("Regular expression matching")
	@ParameterizedTest(name = "{0} / {1} -> {2}")
	@CsvSource({
		"a, ******a, true",
		"aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba, a*b, false",
		"aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba, a*******b, false",
		"abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb,**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb,false",
		"'', *, true",
		"zacabz, *a?b*, false",
		"aa, aa, true",
		"aa, a, false",
		"aaa, aa, false",
		"aa, *, true",
		"aa, a*, true",
		"ab, ?*, true",
		"aab, c*a*b, false"
	})
	void test(String s, String p, boolean expectedResult) {
		for (int i = 0; i < 1; i++)
			Assertions.assertEquals(expectedResult, isMatch(s, p));
	}

	public boolean isMatch(String s, String p) {
		return isMatch(s, 0, p, 0) == -1;
	}

	private int isMatch(String s, int si, String p, int pi) {
		boolean soverflow = si >= s.length();
		if (soverflow && pi == p.length())
			return -1;

		if (!soverflow && pi >= p.length())
			return 0;

		char pchar = p.charAt(pi);
		if (pchar == '?') {
			return soverflow ? 0 : isMatch(s, si + 1, p, pi + 1);
		} else if (pchar == '*') {
			int match = 0;
			for (int i = 0, max = s.length() - si + 1; i < max && match == 0; i++)
				match = isMatch(s, si + i, p, pi + 1);
			return match == -1 ? -1 : match + 1;
		} else {
			return soverflow || pchar != s.charAt(si) ? 0 : isMatch(s, si + 1, p, pi + 1);
		}
	}

	boolean comparison(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
           // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
           //current pattern pointer is not star, last patter pointer was not *
          //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
}
}
