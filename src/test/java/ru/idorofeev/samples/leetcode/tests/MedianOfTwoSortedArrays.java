package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
class MedianOfTwoSortedArrays {

	@Test
	void test1() {
		int[] nums1 = new int[] { 1, 3 };
		int[] nums2 = new int[] { 2 };

		double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
		Assertions.assertEquals(2.0, medianSortedArrays);
	}

	@Test
	void test2() {
		int[] nums1 = new int[] { 1, 2 };
		int[] nums2 = new int[] { 3, 4 };

		double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
		Assertions.assertEquals(2.5, medianSortedArrays);
	}

	@Test
	void test3() {
		int[] nums1 = new int[] { 1, 2 };
		int[] nums2 = new int[] { 1, 2 };

		double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
		Assertions.assertEquals(1.5, medianSortedArrays);
	}

	@Test
	void test4() {
		int size = 100000000;
		int[] nums1 = new int[size];
		int[] nums2 = new int[size];

		Random random = new Random();
		for (int i = 0; i < size; i++) {
			nums1[i] = random.nextInt(size);
			nums2[i] = random.nextInt(size);
		}

		double medianSortedArrays = findMedianSortedArrays(nums1, nums2);
		System.out.println(medianSortedArrays);
	}

	private double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalSize = nums1.length + nums2.length;

		int medianIndex = totalSize / 2;
		int[] vals = totalSize % 2 == 0 ? new int[2] : new int[1];

		int i = 0, j = 0;
		while (i + j <= medianIndex) {
			if (i < nums1.length && j < nums2.length && nums1[i] <= nums2[j]) {
				push(vals, nums1[i]);
				i++;
			} else if (i < nums1.length && j < nums2.length && nums1[i] > nums2[j]) {
				push(vals, nums2[j]);
				j++;
			} else if (i < nums1.length) {
				push(vals, nums1[i]);
				i++;
			} else if (j < nums2.length) {
				push(vals, nums2[j]);
				j++;
			}
		}

		return mean(vals);
	}

	private double mean(int[] arr) {
		int sum = 0;
		for (int anArr : arr)
			sum += anArr;

		return (double)sum / arr.length;
	}

	private void push(int[] arr, int val) {
		System.arraycopy(arr, 0, arr, 1, arr.length - 1);
		arr[0] = val;
	}
}
