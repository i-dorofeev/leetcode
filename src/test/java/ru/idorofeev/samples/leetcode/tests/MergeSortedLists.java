package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortedLists {

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return MergeSortedLists.toString(this);
		}
	}

	private ListNode listNode(int val, ListNode next) {
		ListNode node = new ListNode(val);
		node.next = next;
		return node;
	}

	private ListNode buildList(int start, int[] values) {
		if (start >= values.length)
			return null;
		return listNode(values[start], buildList(start + 1, values));
	}

	@Test
	void test() {
		ListNode[] lists = new ListNode[] {
			buildList(0, new int[] { 1, 4, 7 }),
			buildList(0, new int[] { 2, 5, 8, 10 }),
			buildList(0, new int[] { 3, 6, 9 })
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, toArray(result));
	}

	@Test
	void test1() {
		ListNode[] lists = new ListNode[] {
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] {}, toArray(result));
	}

	@Test
	void test2() {
		ListNode[] lists = new ListNode[] {
			buildList(0, new int[] { 1, 2, 2 }),
			buildList(0, new int[] { 1, 1, 2 })
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] { 1, 1, 1, 2, 2, 2 }, toArray(result));
	}

	@Test
	void test4() {
		ListNode[] lists = new ListNode[] {
			null
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] {}, toArray(result));
	}

	@Test
	void test5() {
		ListNode[] lists = new ListNode[] {
			null,
			buildList(0, new int[] { 1, 4, 7 }),
			buildList(0, new int[] { 2, 5, 8, 10 }),
			null,
			buildList(0, new int[] { 3, 6, 9 })
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, toArray(result));
	}

	@Test
	void test6() {
		ListNode[] lists = new ListNode[] {
			buildList(0, new int[] { -8, -7, -7, -5, 1, 1, 3, 4 }),
			buildList(0, new int[] { -2 }),
			buildList(0, new int[] { -10, -10, -7, 0, 1, 3 }),
			buildList(0, new int[] { 2 })
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] { -10, -10, -8, -7, -7, -7, -5, -2, 0, 1, 1, 1, 2, 3, 3, 4 }, toArray(result));
	}

	@Test
	void test7() {
		ListNode[] lists = new ListNode[] {
			buildList(0, new int[] { -2, 1, 4, 5 }),
			buildList(0, new int[] { -2, 5, 6 }),
			buildList(0, new int[] { -2, 0 }),
		};

		ListNode result = mergeKLists(lists);
		assertArrayEquals(new Integer[] { -2, -2, -2, 0, 1, 4, 5, 5, 6 }, toArray(result));
	}

	private Integer[] toArray(ListNode result) {
		ArrayList<Integer> arr = new ArrayList<>();
		ListNode node = result;
		while (node != null) {
			arr.add(node.val);
			node = node.next;
		}
		return arr.toArray(new Integer[arr.size()]);
	}

	static class SortedArray {

		private final ListNode[] array;

		private int start;
		private int length;

		SortedArray(ListNode[] array) {
			this.array = array;

			Arrays.sort(array, this::compare);

			start = 0;
			length = array.length;
		}

		private int compare(ListNode o1, ListNode o2) {
			return o1 != null && o2 != null ? o1.val - o2.val : o1 == null ? 1 : -1;
		}

		final int length() {
			return length;
		}

		final ListNode pullHead() {
			ListNode head = array[start];

			start++;
			if (start >= array.length)
				start = 0;

			length--;

			return head;
		}

		final void insert(ListNode item) {
			int insertionIndex = findInsertionIndex(item);
			if (insertionIndex >= (length - 1) / 2)
				start = shiftRight(insertionIndex);
			else
				start = shiftLeft(insertionIndex - 1);
			insert(insertionIndex, item);
			length++;
		}

		private void insert(int insertionIndex, ListNode item) {
			int physicalIndex = getPhysicalIndex(insertionIndex);
			array[physicalIndex] = item;
		}

		private int shiftLeft(int to) {
			for (int i = 0; i <= to; i++) {
				int physicalIndex = getPhysicalIndex(i);
				if (physicalIndex > 0)
					array[physicalIndex - 1] = array[physicalIndex];
				else
					array[array.length - 1] = array[physicalIndex];
			}
			return start > 0 ? start - 1 : array.length - 1;
		}

		private int shiftRight(int from) {
			for (int i = length - 1; i >= from; i--) {
				int physicalIndex = getPhysicalIndex(i);
				if (physicalIndex < array.length - 1)
					array[physicalIndex + 1] = array[physicalIndex];
				else
					array[0] = array[physicalIndex];
			}
			return start;
		}

		private int findInsertionIndex(ListNode item) {
			int index = find(item, 0, length);
			return index == -1 ? 0 : index;
		}

		private int find(ListNode item, int from, int to) {
			if (from == to)
				return from;

			int physicalIndex = getPhysicalIndex(from);
			ListNode element = array[physicalIndex];
			if (compare(element, item) > 0)
				return -1;

			int middle = from + (to - from) / 2;
			middle = middle == from ? middle + 1 : middle;
			int right = find(item, middle, to);
			return right == -1 ? find(item, from, middle) : right;
		}

		private int getPhysicalIndex(int logicalIndex) {
			int index = start + logicalIndex;
			return index >= array.length ? index - array.length : index;
		}
	}

	private ListNode mergeKLists(ListNode[] lists) {

		SortedArray array = new SortedArray(lists);

		ListNode root = null;
		ListNode prev = null;
		while (array.length() > 0) {

			ListNode head = array.pullHead();
			if (head == null)
				continue;

			if (root == null)
				root = head;

			ListNode next = head.next;
			head.next = null;

			if (prev != null)
				prev.next = head;

			prev = head;
			if (next != null)
				array.insert(next);
		}

		return root;
	}

	static String toString(ListNode node) {
		return node == null ? "null" : Integer.toString(node.val) + (node.next != null ? " -> " + toString(node.next) : "");
	}

}
