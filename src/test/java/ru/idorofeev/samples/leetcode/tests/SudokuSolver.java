package ru.idorofeev.samples.leetcode.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;

@SuppressWarnings("Convert2Lambda") // Anonymous classes are faster to initialize than lambdas although lambdas are more pretty
class SudokuSolver {

	/*
input:	[[".",".","9","7","4","8",".",".","."],["7",".",".",".",".",".",".",".","."],[".","2",".","1",".","9",".",".","."],[".",".","7",".",".",".","2","4","."],[".","6","4",".","1",".","5","9","."],[".","9","8",".",".",".","3",".","."],[".",".",".","8",".","3",".","2","."],[".",".",".",".",".",".",".",".","6"],[".",".",".","2","7","5","9",".","."]]
output:	[["5","1","9","7","4","8","6","3","2"],["7","8","3","6","5","2","4","1","9"],["4","2","6","1","3","9","8","7","5"],["3","5","7","9","8","6","2","4","1"],["2","6","4","3","1","7","5","9","8"],["1","9","8","5","2","4","3","6","7"],["9","7","5","8","6","3","1","2","4"],["8","3","2","4","9","1","7","5","6"],["6","4","1","2","7","5","9","8","3"]]
	 */
	@Test
	void test1() {

		char[][] board = new char[][] {
			{'.','.','9','7','4','8','.','.','.'},{'7','.','.','.','.','.','.','.','.'},{'.','2','.','1','.','9','.','.','.'},{'.','.','7','.','.','.','2','4','.'},{'.','6','4','.','1','.','5','9','.'},{'.','9','8','.','.','.','3','.','.'},{'.','.','.','8','.','3','.','2','.'},{'.','.','.','.','.','.','.','.','6'},{'.','.','.','2','7','5','9','.','.'}
		};

		char[][] expectedResult = new char[][] {
			{'5','1','9','7','4','8','6','3','2'},{'7','8','3','6','5','2','4','1','9'},{'4','2','6','1','3','9','8','7','5'},{'3','5','7','9','8','6','2','4','1'},{'2','6','4','3','1','7','5','9','8'},{'1','9','8','5','2','4','3','6','7'},{'9','7','5','8','6','3','1','2','4'},{'8','3','2','4','9','1','7','5','6'},{'6','4','1','2','7','5','9','8','3'}
		};

		solveSudoku(board);
		for (int i = 0; i < board.length; i++)
			Assertions.assertArrayEquals(expectedResult[i], board[i], "at row " + i);
	}


	public void solveSudoku(char[][] board) {

		char[][][] data = new char[9][9][10];

		int dotsLeft = 0;
		do {
			int prevDotsLeft = dotsLeft;
			dotsLeft = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					char c = board[i][j];
					if (c == '.'){
						dotsLeft++;
						continue;
					}
					removeCandidateFromColumn(board, data, i, j);
					removeCandidateFromRow(board, data, i, j);
					removeCandidateFromBlock(board, data, i, j);
				}
			}

			if (prevDotsLeft == dotsLeft)
				break;
		} while (dotsLeft > 0);

		backtrack = true;
		if (!backtrack(board, data))
			throw new RuntimeException("Couldn't find solution :(");
	}

	private int[] findLeastUncertainCell(char[][][] data) {
		int[][] searchData = new int[8][2];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				char excluded = data[i][j][0];
				if (excluded > 7)
					continue;
				searchData[excluded][0] = i + 1;
				searchData[excluded][1] = j + 1;
			}
		}

		for (int k = 8 - 1; k >= 0; k--) {
			if (searchData[k][0] != 0)
				return new int[] { searchData[k][0] - 1, searchData[k][1] - 1 };
		}

		return null;
	}

	private boolean backtrack = false;

	private boolean backtrack(char[][] board, char[][][] data) {
		if (!backtrack)
			return true;

		int[] leastUncertainCell = findLeastUncertainCell(data);
		if (leastUncertainCell == null)
			return true;
		int j = leastUncertainCell[0], k = leastUncertainCell[1];

		char[] chars = data[j][k];
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] != 0)
				continue;

			boolean set = set(board, data, j, k, (char) ('1' + i - 1));
			if (set)
				return true;
		}
		return false;
	}

	private void restoreCandidate(char[][] board, char[][][] data, int i, int j, char value) {
		char[] chars = data[i][j];
		if (board[i][j] != '.')
			return;
		if (chars[value - '1' + 1] == 0)
			return;
		chars[value - '1' + 1] = 0;
		chars[0]--;
	}

	private boolean set(char[][] board, char[][][] data, int x, int y, char val) {
		boolean ok;
		board[x][y] = val;
		ok = assertBlock(board, x, y) && assertRow(board, x) && assertColumn(board, y);
		if (ok) {
			char oldValue = data[x][y][0];
			data[x][y][0] = 9;
			ok = removeCandidateFromBlock(board, data, x, y);
			if (ok) {
				ok = removeCandidateFromColumn(board, data, x, y);
				if (ok) {
					ok = removeCandidateFromRow(board, data, x, y);
					if (ok) {
						ok = backtrack(board, data);
					}
					if (!ok)
						restoreCandidateInRow(board, data, x, y);
				}
				if (!ok)
					restoreCandidateInColumn(board, data, x, y);
			}
			if (!ok) {
				restoreCandidateInBlock(board, data, x, y);
				data[x][y][0] = oldValue;
			}
		}
		if (!ok) {
			board[x][y] = '.';
		}
		return ok;
	}

	private boolean traverseColumn(int j, BiPredicate<Integer, Integer> cellPredicate, BiFunction<Integer, Integer, Boolean> action) {
		for (int k = 0; k < 9; k++) {
			if (!cellPredicate.test(k, j))
				continue;

			if (!action.apply(k, j))
				return false;
		}
		return true;
	}

	private void restoreCandidateInColumn(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];

		traverseColumn(j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return x != i;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					SudokuSolver.this.restoreCandidate(board, data, x, y, c);
					return true;
				}
			});
	}

	private boolean removeCandidateFromColumn(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];
		return c != '.' && traverseColumn(j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return x != i;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.removeCandidate(board, data, x, y, c);
				}
			});
	}

	private boolean assertColumn(char[][] board, int j) {
		char[] constraints = new char[9];
		return traverseColumn(j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return board[x][y] != '.';
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.assertCell(board, x, y, constraints);
				}
			});
	}

	private boolean traverseBlock(int i, int j, BiPredicate<Integer, Integer> cellPredicate, BiFunction<Integer, Integer, Boolean> action) {
		int x0 = i / 3 * 3;
		int y0 = j / 3 * 3;

		for (int x = x0; x < x0 + 3; x++) {
			for (int y = y0; y < y0 + 3; y++) {
				if (!cellPredicate.test(x, y))
					continue;

				if (!action.apply(x, y))
					return false;
			}
		}
		return true;
	}

	private void restoreCandidateInBlock(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];
		traverseBlock(i, j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return i != x && j != y;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					SudokuSolver.this.restoreCandidate(board, data, x, y, c);
					return true;
				}
			});
	}

	private boolean assertBlock(char[][] board, int i, int j) {
		char[] constraints = new char[9];
		return traverseBlock(i, j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return board[x][y] != '.';
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.assertCell(board, x, y, constraints);
				}
			});
	}

	private boolean removeCandidateFromBlock(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];
		return c != '.' && traverseBlock(i, j,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return i != x && j != y;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.removeCandidate(board, data, x, y, c);
				}
			});
	}

	private boolean traverseRow(int i, BiPredicate<Integer, Integer> cellPredicate, BiFunction<Integer, Integer, Boolean> action) {
		for (int k = 0; k < 9; k++) {
			if (!cellPredicate.test(i, k))
				continue;
			if (!action.apply(i, k))
				return false;
		}
		return true;
	}

	private void restoreCandidateInRow(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];

		traverseRow(i,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return y != j;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					SudokuSolver.this.restoreCandidate(board, data, x, y, c);
					return true;
				}
			});
	}

	private boolean assertRow(char[][] board, int i) {
		char[] constraints = new char[9];
		return traverseRow(i,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return board[x][y] != '.';
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.assertCell(board, x, y, constraints);
				}
			}
		);
	}

	private boolean removeCandidateFromRow(char[][] board, char[][][] data, int i, int j) {
		char c = board[i][j];

		return c != '.' && traverseRow(i,
			new BiPredicate<Integer, Integer>() {
				@Override
				public boolean test(Integer x, Integer y) {
					return y != j;
				}
			},
			new BiFunction<Integer, Integer, Boolean>() {
				@Override
				public Boolean apply(Integer x, Integer y) {
					return SudokuSolver.this.removeCandidate(board, data, x, y, c);
				}
			}
		);
	}

	private boolean assertCell(char[][] board, int x, int y, char[] constraints) {
		int m = board[x][y] - '1';
		if (constraints[m] != 0)
			return false;
		constraints[m] = board[x][y];
		return true;
	}

	private boolean removeCandidate(char[][] board, char[][][] data, int x, int y, char val) {
		char[] chars = data[x][y];
		if (chars[0] == 9)
			return true;

		if (board[x][y] != '.') {
			chars[0] = 9;
			return true;
		}

		int pos = val - '1' + 1;
		if (chars[pos] != 0)
			return true;

		chars[pos] = val;
		chars[0]++;
		if (chars[0] != 8)
			return true;

		for (int s = 1; s < 10; s++) {
			if (chars[s] != 0)
				continue;

			return set(board, data, x, y, (char)('1' + s - 1));
		}

		throw new RuntimeException("Shouldn't get here :(");
	}
}
