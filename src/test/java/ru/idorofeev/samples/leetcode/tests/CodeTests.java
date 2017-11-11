package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Test;
import ru.idorofeev.samples.leetcode.twosum.Solution;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CodeTests {

	@Test
	void twoSum() {
		int[] nums = new int[] { 2, 7, 11, 15 };
		int target = 9;

		Solution solution = new Solution();
		int[] result = solution.twoSum(nums, target);

		assertArrayEquals(new int[] { 0, 1 }, result);
	}
}
