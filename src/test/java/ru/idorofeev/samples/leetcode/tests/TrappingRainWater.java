package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class TrappingRainWater {

	private static Stream<Arguments> testSuite() {
		return Stream.of(
			Arguments.of(new int [] { }, 0),
			Arguments.of(new int [] { 0 }, 0),
			Arguments.of(new int [] { 0, 0 }, 0),
			Arguments.of(new int [] { 0,1,0,2,1,0,1,3,2,1,2,1 }, 6)
		);
	}

	@DisplayName("Trapping rain water")
	@ParameterizedTest(name = "{0} -> {1}")
	@MethodSource("testSuite")
	void test(int[] source, int expectedResult) {
		Assertions.assertEquals(expectedResult, trap(source));
	}

	public int trap(int[] height) {

		int water = 0;
		int maxHeightLeft = 0;
		for (int i = 0, heightLength = height.length; i < heightLength; i++) {
			int h = height[i];
			int curHeight = maxHeightLeft - h;
			curHeight = curHeight > 0 ? curHeight : 0;
			water += curHeight;
			maxHeightLeft = maxHeightLeft > h ? maxHeightLeft : h;
		}

		int maxHeightRight = 0;
		for (int j = height.length - 1; j >= 0; j--) {
			if (height[j] == maxHeightLeft)
				break;

			maxHeightRight = maxHeightRight > height[j] ? maxHeightRight : height[j];
			water -= maxHeightLeft - maxHeightRight;
		}
		return water;
	}
}
