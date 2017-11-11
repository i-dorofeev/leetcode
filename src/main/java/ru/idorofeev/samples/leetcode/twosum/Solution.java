package ru.idorofeev.samples.leetcode.twosum;

public class Solution {

	public int[] twoSum(int[] num, int target) {
		for (int i = 0; i < num.length; i++)
			for (int j = 0; j < num.length; j++) {
				if (i == j)
					continue;

				if (num[i] + num[j] == target)
					return new int[] { i, j };
			}

		return null;
	}
}
