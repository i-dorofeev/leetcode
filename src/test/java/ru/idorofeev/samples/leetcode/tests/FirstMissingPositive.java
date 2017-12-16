package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FirstMissingPositive {

	@DisplayName("First missing positive")
	@ParameterizedTest(name = "{0} -> {2}")
	@CsvSource({
		"'1,2,0', 3",
		"'3,4,-1,1', 2",
		"'', 1",
		"'1', 2",
		"'1,1', 2"
	})
	void test(String inputArray, int expectedResult) {
		String[] split = inputArray.equals("") ? new String[0] : inputArray.split(",");
		int[] intArray = new int[split.length];
		for (int i = 0; i < intArray.length; i++)
			intArray[i] = Integer.parseInt(split[i]);

		int result = firstMissingPositive(intArray);
		Assertions.assertEquals(expectedResult, result);
	}

	public int firstMissingPositive(int[] nums) {
		int i = 0;
		while (i < nums.length) {
			int val1 = nums[i];
			int val2;
			if (val1 == i + 1 || val1 > nums.length || val1 <= 0 || (val2 = nums[val1 - 1]) == val1) {
				i++;
				continue;
			}

			nums[val1 - 1] = val1;
			nums[i] = val2;
		}

		int j = 0;
		while (++j <= nums.length) {
			if (nums[j - 1] != j)
				return j;
		}
		return j;
	}
}
