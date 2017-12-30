package ru.idorofeev.cs.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.idorofeev.cs.MaxHeap;
import ru.idorofeev.cs.Quicksort;

import java.util.Random;
import java.util.stream.Stream;

class MaxHeapTests {

	private static Stream<Arguments> testSuite() {
		return Stream.of(
			Arguments.of(new int[]{2,1}, 0, new int[]{2,1}),
			Arguments.of(new int[]{1,2}, 0, new int[]{2,1}),
			Arguments.of(new int[]{2}, 0, new int[]{2}),
			Arguments.of(new int[]{2, 1, 5}, 0, new int[]{5, 1, 2}),
			Arguments.of(new int[]{1, 10, 11, 9, 8, 7, 6}, 0, new int[] {11, 10, 7, 9, 8, 1, 6})
		);
	}

	@DisplayName("maxHeapify tests")
	@ParameterizedTest(name = "{0} / {1} -> {2}")
	@MethodSource("testSuite")
	void maxHeapifyTest(int[] inputHeap, int start, int[] outputHeap) {
		MaxHeap.maxHeapify(inputHeap, start, inputHeap.length);
		Assertions.assertArrayEquals(outputHeap, inputHeap);
	}

	@Test
	void buildMaxHeap() {
		int[] heap = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		MaxHeap.buildMaxHeap(heap);
	}

	@Test
	void heapSortTest() {

		int heapSize = 10000000;
		//int heapSize = 10;
		int[] heap = new int[heapSize];
		Random rnd = new Random();
		for (int i = 0; i < heapSize; i++)
			heap[i] = rnd.nextInt(heapSize);

		long start = System.currentTimeMillis();
		//MaxHeap.heapSort(heap);
		//Arrays.sort(heap);
		Quicksort.quicksort(heap);
		long end = System.currentTimeMillis();
		System.out.println(end - start + " ms");

		for (int i = 1, prev = heap[0]; i < heapSize; i++) {
			Assertions.assertTrue(heap[i] >= prev);
			prev = heap[i];
		}
	}
}
