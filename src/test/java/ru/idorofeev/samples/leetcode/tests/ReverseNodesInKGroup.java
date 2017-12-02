package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ReverseNodesInKGroup {

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			return ReverseNodesInKGroup.toString(this);
		}
	}

	static String toString(ListNode node) {
		return node == null ? "null" : Integer.toString(node.val) + (node.next != null ? " -> " + toString(node.next) : "");
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

	private Integer[] toArray(ListNode result) {
		ArrayList<Integer> arr = new ArrayList<>();
		ListNode node = result;
		while (node != null) {
			arr.add(node.val);
			node = node.next;
		}
		return arr.toArray(new Integer[arr.size()]);
	}

	@Test
	void test() {
		ListNode list = buildList(0, new int[]{1, 2, 3, 4, 5});

		ListNode result = reverseKGroup(list, 2);
		assertArrayEquals(new Integer[] { 2, 1, 4, 3, 5 }, toArray(result));
	}

	@Test
	void test1() {
		ListNode list = buildList(0, new int[]{1, 2, 3, 4, 5});

		ListNode result = reverseKGroup(list, 3);
		assertArrayEquals(new Integer[] { 3, 2, 1, 4, 5 }, toArray(result));
	}

	@Test
	void test2() {
		ListNode list = buildList(0, new int[]{1, 2, 3, 4, 5});

		ListNode result = reverseKGroup(list, 5);
		assertArrayEquals(new Integer[] { 5, 4, 3, 2, 1 }, toArray(result));
	}

	@Test
	void test3() {
		ListNode list = buildList(0, new int[]{ 1, 2 });

		ListNode result = reverseKGroup(list, 1);
		assertArrayEquals(new Integer[] { 1, 2 }, toArray(result));
	}

	@Test
	void test4() {
		ListNode list = buildList(0, new int[]{ 1, 2 });

		ListNode result = reverseKGroup(list, 3);
		assertArrayEquals(new Integer[] { 1, 2 }, toArray(result));
	}

	@Test
	void test5() {
		ListNode list = buildList(0, new int[]{ 1, 2, 3 });

		ListNode result = reverseKGroup(list, 1);
		assertArrayEquals(new Integer[] { 1, 2, 3 }, toArray(result));
	}

	@Test
	void test6() {
		ListNode list = buildList(0, new int[]{ 1, 2, 3 });

		ListNode result = reverseKGroup(list, 4);
		assertArrayEquals(new Integer[] { 1, 2, 3 }, toArray(result));
	}

	@Test
	void test7() {
		ListNode list = buildList(0, new int[]{ 1, 2, 3, 4 });

		ListNode result = reverseKGroup(list, 2);
		assertArrayEquals(new Integer[] { 2, 1, 4, 3 }, toArray(result));
	}

	@Test
	void test8() {
		ListNode list = buildList(0, new int[]{ });

		ListNode result = reverseKGroup(list, 1);
		assertArrayEquals(new Integer[] {}, toArray(result));
	}

	public ListNode reverseKGroup(ListNode head, int k) {

		ListNode reversed = reverseKGroup(head, k, k - 1);
		if (reverseGroup)
			head.next = groupStart;
		return reversed;
	}

	private ListNode groupStart;
	private boolean reverseGroup;

	private ListNode reverseKGroup(ListNode node, int groupSize, int k) {
		if (node == null)
			return null;

		if (k > 0 && node.next == null) {
			return node;
		}

		if (k == 0 && node.next == null) {
			reverseGroup = true;
			return node;
		}

		if (k == 0 && node.next != null) {
			ListNode reversed = reverseKGroup(node.next, groupSize, groupSize - 1);
			if (reverseGroup)
				node.next.next = groupStart;
			groupStart = reversed;
			reverseGroup = true;
			return node;
		}

		ListNode next = node.next;
		ListNode reversed = reverseKGroup(next, groupSize, k - 1);
		if (reverseGroup) {
			next.next = node;
			return reversed;
		}
		return node;
	}

}
