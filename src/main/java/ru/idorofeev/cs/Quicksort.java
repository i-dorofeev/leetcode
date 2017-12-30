package ru.idorofeev.cs;

public class Quicksort {

	public static void quicksort(int[] array) {
		quicksort(array, 0, array.length - 1);
	}

	private static void quicksort(int[] array, int p, int r) {
		int q = partition(array, p, r);
		if (q >= 0) {
			quicksort(array, p, q - 1);
			quicksort(array, q + 1, r);
		}
	}

	private static int partition(int[] array, int p, int r) {
		if (p >= r)
			return -1;
		int i = p - 1;
		int j = p;
		while (j < r) {
			if (array[j] < array[r]) {
				i++;
				if (i != j) {
					int tmp = array[j];
					array[j] = array[i];
					array[i] = tmp;
				}
			}
			j++;
		}

		i++;
		if (i != r) {
			int tmp = array[r];
			array[r] = array[i];
			array[i] = tmp;
		}
		return i;
	}
}
