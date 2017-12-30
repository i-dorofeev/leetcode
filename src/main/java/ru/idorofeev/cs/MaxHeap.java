package ru.idorofeev.cs;

public class MaxHeap {

	public static void maxHeapify(int[] heap, int i, int heapSize) {
		do {
			int left = (i << 1) + 1;
			int right = left + 1;

			int largest = i;
			if (left < heapSize && heap[left] > heap[largest])
				largest = left;
			if (right < heapSize && heap[right] > heap[largest])
				largest = right;

			if (largest == i)
				break;

			int tmp = heap[i];
			heap[i] = heap[largest];
			heap[largest] = tmp;

			i = largest;
		} while (true);
	}

	public static void buildMaxHeap(int[] heap) {
		for (int i = (heap.length - 1) / 2; i >= 0; i--) {
			maxHeapify(heap, i, heap.length);
		}
	}

	public static void heapSort(int[] array) {
		buildMaxHeap(array);
		for (int i = array.length - 1; i > 0; i--) {
			int tmp = array[i];
			array[i] = array[0];
			array[0] = tmp;
			maxHeapify(array, 0, i);
		}
	}
}
