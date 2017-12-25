package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class P051_NQueens {

	private static Stream<Arguments> testSuite() {
		return Stream.of(
			Arguments.of(4, new String[][] {
				{	".Q..",
					"...Q",
					"Q...",
					"..Q." },

				{	"..Q.",
					"Q...",
					"...Q",
					".Q.."} })
		);
	}

	@DisplayName("N Queens")
	@ParameterizedTest(name = "{0} -> {1}")
	@MethodSource("testSuite")
	void test(int n, String[][] expectedResult) {
		List<List<String>> result = solveNQueens(n);
		System.out.println(result);
		for (int i = 0; i < expectedResult.length; i++)
			Assertions.assertArrayEquals(expectedResult[i], result.get(i).toArray());
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();

		char[][] board = new char[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				board[i][j] = '.';

		backtrack(board, n, result);

		return result;
	}

	private void backtrack(char[][] board, int n, List<List<String>> result) {
		if (n == 0) {
			List<String> res = new ArrayList<>();
			for (char[] row : board)
				res.add(new String(row));
			result.add(res);
			return;
		}
		for (int i = 0; i < board.length; i++) {
			board[board.length - n][i] = 'Q';
			if (check(board, board.length - n, i)) {
				backtrack(board, n - 1, result);
			}
			board[board.length - n][i] = '.';
		}
	}

	private boolean check(char[][] board, int n, int i) {
		for (int j = 0; j < board.length; j++) {
			int dr = i - j + n;
			int dl = i + j - n;
			if (j == n)
				for (int k = 0; k < board.length; k++) {
					if (k != i && board[j][k] == 'Q')
						return false;
				}
			else if (dl >= 0 && dl < board.length && board[j][dl] == 'Q')
				return false;
			else if (board[j][i] == 'Q')
				return false;
			else if (dr >= 0 && dr < board.length && board[j][dr] == 'Q')
				return false;
		}
		return true;
	}
}
