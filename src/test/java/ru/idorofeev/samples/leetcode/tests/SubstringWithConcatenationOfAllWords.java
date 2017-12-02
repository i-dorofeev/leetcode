package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class SubstringWithConcatenationOfAllWords {

	static final int REPEAT = 10000;

	@Test
	void test() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
			Assertions.assertArrayEquals(new Integer[]{0, 9}, result.toArray());
		}
	}

	@Test
	void test1() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("mississippi", new String[]{"is"});
			Assertions.assertArrayEquals(new Integer[]{4, 1}, result.toArray());
		}
	}

	/*
	"barfoofoobarthefoobarman"
["bar","foo","the"]
	 */
	@Test
	void test2() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("barfoofoobarthefoobarman", new String[]{"bar", "foo", "the"});
			Assertions.assertArrayEquals(new Integer[]{6, 9, 12}, result.toArray());
		}
	}

	/*
	"abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab"
["ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"]
	 */
	@Test
	void test3() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab",
				new String[]{"ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba", "ab", "ba"});

			Assertions.assertArrayEquals(new Integer[]{}, result.toArray());
		}
	}

	/*
	"wordgoodgoodgoodbestword"
["word","good","best","good"]
	 */
	@Test
	void test4() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("wordgoodgoodgoodbestword",
				new String[]{"word", "good", "best", "good"});

			Assertions.assertArrayEquals(new Integer[]{8}, result.toArray());
		}
	}

	/*
	"aabbaabbaabb"
["bb","aa","bb","aa","bb"]
	 */
	@Test
	void test5() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("aabbaabbaabb",
				new String[]{"bb", "aa", "bb", "aa", "bb"});

			Assertions.assertArrayEquals(new Integer[]{2}, result.toArray());
		}
	}

	/*
	"aaaaaaaa"
["aa","aa","aa"]
	 */
	@Test
	void test6() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("aaaaaaaa",
				new String[]{"aa", "aa", "aa"});

			Assertions.assertArrayEquals(new Integer[]{0, 2, 1}, result.toArray());
		}
	}

	/*
	"ababaab"
["ab","ba","ba"]
	 */
	@Test
	void test7() {
		for (int i = 0; i < REPEAT; i++) {
			List<Integer> result = findSubstring("ababaab",
				new String[]{"ab","ba","ba"});

			Assertions.assertArrayEquals(new Integer[]{ 1 }, result.toArray());
		}
	}

	public List<Integer> findSubstring(String s, String[] words) {
		int pa = 0, pb = 0, pc = 0;
		ArrayList<Integer> result = new ArrayList<>(10);
		int wordsCount = words.length;
		HashMap<String, Integer> map = new HashMap<>(wordsCount);

		int wIdx = 0;
		int[][] table = new int[2][wordsCount];
		for (String w: words) {
			int mappingIndex = map.getOrDefault(w, -1);
			if (mappingIndex == -1) {
				mappingIndex = wIdx++;
				map.put(w, mappingIndex);
			}
			table[0][mappingIndex]++;
		}
		int[] ORIG = table[0];
		int[] OPER = table[1];

		int uniqueWords = map.size();
		int wordLength = words[0].length();
		int maxIndex = s.length() - wordLength;
		boolean clear = false;
		int[] matched = new int[s.length() / wordLength];
		for (int shift = 0; shift < wordLength; shift++) {
			int i = shift;
			int iw = 0;
			int wordIndex = 0;
			int mappingIndex = -1;
			while (i <= maxIndex) {
				if (clear) {
					Arrays.fill(OPER, 0, uniqueWords, 0);
					clear = false;
				}
				if (mappingIndex == -1) {
					String word = s.substring(i, i + wordLength);
					mappingIndex = map.getOrDefault(word, -1);
					matched[iw] = mappingIndex;
				}
				if (mappingIndex != -1 && OPER[mappingIndex] < ORIG[mappingIndex]) {
					pa++;
					OPER[mappingIndex]++;
					i += wordLength;
					iw++;
					if (wordIndex == wordsCount - 1) {
						result.add(i - wordLength * wordsCount);
						OPER[matched[iw - wordIndex - 1]]--;
					} else {
						wordIndex++;
					}
					mappingIndex = -1;
				} else if (wordIndex > 0) {
					pb++;
					wordIndex--;
					int k = matched[iw - wordIndex - 1];
					OPER[k] = OPER[k] - 1;
				} else {
					pc++;
					wordIndex = 0;
					i += wordLength;
					iw++;
					clear = true;
					mappingIndex = -1;
				}
			}
			clear = true;
		}
		System.out.println(Arrays.toString(new int[] { pa, pb, pc }));
		return result;
	}

	//	public List<Integer> findSubstring(String s, String[] words) {
//		ArrayList<Integer> result = new ArrayList<>(10);
//		int wordLength = words[0].length();
//		int maxIndex = s.length() - wordLength;
//		int iter = 0;
//		for (int shift = 0; shift < wordLength; shift++) {
//			int i = 0;
//			int wordIndex = 0;
//			int wordsStart = 0;
//			while (i <= maxIndex - shift) {
//				iter++;
//				String word = s.substring(shift + i, shift + i + wordLength);
//				int matchedIndex = testSubstring(word, words, wordsStart, wordIndex);
//				if (matchedIndex != -1) {
//					int physicalIndex = wordsStart + wordIndex;
//					while (physicalIndex >= words.length)
//						physicalIndex -= words.length;
//					String temp = words[physicalIndex];
//					words[physicalIndex] = words[matchedIndex];
//					words[matchedIndex] = temp;
//
//					i += wordLength;
//					if (wordIndex == words.length - 1) {
//						result.add(shift + i - wordLength * words.length);
//						wordsStart++;
//					} else {
//						wordIndex++;
//					}
//				} else if (wordIndex > 0) {
//					wordsStart++;
//					wordIndex--;
//				} else {
//					i += wordLength;
//					wordsStart = 0;
//					wordIndex = 0;
//				}
//			}
//		}
//		System.out.println(iter);
//		return result;
//	}
//
//	private int testSubstring(String word, String[] words, int wordsStart, int wordsIndex) {
//		for (int i = wordsIndex; i < words.length; i++) {
//			int physicalIndex = wordsStart + i;
//			if (physicalIndex >= words.length)
//				physicalIndex = physicalIndex % words.length;
//
//			if (word.equals(words[physicalIndex]))
//				return physicalIndex;
//		}
//
//		return -1;
//	}

	public List<Integer> findSubstring0(String s, String[] words) {
		int N = s.length();
		List<Integer> indexes = new ArrayList<Integer>(s.length());
		if (words.length == 0) {
			return indexes;
		}
		int M = words[0].length();
		if (N < M * words.length) {
			return indexes;
		}
		int last = N - M + 1;

		//map each string in words array to some index and compute target counters
		Map<String, Integer> mapping = new HashMap<String, Integer>(words.length);
		int [][] table = new int[2][words.length];
		int failures = 0, index = 0;
		for (int i = 0; i < words.length; ++i) {
			Integer mapped = mapping.get(words[i]);
			if (mapped == null) {
				++failures;
				mapping.put(words[i], index);
				mapped = index++;
			}
			++table[0][mapped];
		}

		//find all occurrences at string S and map them to their current integer, -1 means no such string is in words array
		int [] smapping = new int[last];
		for (int i = 0; i < last; ++i) {
			String section = s.substring(i, i + M);
			Integer mapped = mapping.get(section);
			if (mapped == null) {
				smapping[i] = -1;
			} else {
				smapping[i] = mapped;
			}
		}

		//fix the number of linear scans
		for (int i = 0; i < M; ++i) {
			//reset scan variables
			int currentFailures = failures; //number of current mismatches
			int left = i, right = i;
			Arrays.fill(table[1], 0);
			//here, simple solve the minimum-window-substring problem
			while (right < last) {
				while (currentFailures > 0 && right < last) {
					int target = smapping[right];
					if (target != -1 && ++table[1][target] == table[0][target]) {
						--currentFailures;
					}
					right += M;
				}
				while (currentFailures == 0 && left < right) {
					int target = smapping[left];
					if (target != -1 && --table[1][target] == table[0][target] - 1) {
						int length = right - left;
						//instead of checking every window, we know exactly the length we want
						if ((length / M) ==  words.length) {
							indexes.add(left);
						}
						++currentFailures;
					}
					left += M;
				}
			}

		}
		return indexes;
	}



}
