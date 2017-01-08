import java.util.*;

public class Matrix {

	public static void main(String[] args) {
		new Matrix().generateMatrix(5);
	}

	/*
	 * Rotate Image
	 * 
	 * You are given an n x n 2D matrix representing an image.
	 * 
	 * Rotate the image by 90 degrees (clockwise).
	 */
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}

	public int[][] generateMatrix2(int n) {
		int[][] res = new int[n][n];
		int k = 1;
		int top = 0, bottom = n - 1, left = 0, right = n - 1;
		while (left < right && top < bottom) {
			for (int j = left; j < right; j++) {
				res[top][j] = k++;
			}
			for (int i = top; i < bottom; i++) {
				res[i][right] = k++;
			}
			for (int j = right; j > left; j--) {
				res[bottom][j] = k++;
			}
			for (int i = bottom; i > top; i--) {
				res[i][left] = k++;
			}
			left++;
			right--;
			top++;
			bottom--;
		}
		if (n % 2 != 0)
			res[n / 2][n / 2] = k;
		return res;
	}

	/*
	 * Valid Sudoku
	 */
	public boolean isValidSudoku(char[][] board) {
		HashSet<Character> set = new HashSet<Character>();
		// Check for each row
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.')
					continue;
				if (set.contains(board[i][j]))
					return false;
				set.add(board[i][j]);
			}
			set.clear();
		}

		// Check for each column
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (board[i][j] == '.')
					continue;
				if (set.contains(board[i][j]))
					return false;
				set.add(board[i][j]);
			}
			set.clear();
		}

		// Check for each sub-grid
		for (int k = 0; k < 9; k++) {
			for (int i = k / 3 * 3; i < k / 3 * 3 + 3; i++) {
				for (int j = (k % 3) * 3; j < (k % 3) * 3 + 3; j++) {
					if (board[i][j] == '.')
						continue;
					if (set.contains(board[i][j]))
						return false;
					set.add(board[i][j]);
				}
			}
			set.clear();
		}

		return true;
	}

	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0) {
			return;
		}
		solved(board);
	}

	private boolean solved(char[][] board) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					for (char num = '1'; num <= '9'; num++) {

						if (isValid(board, i, j, num)) {
							// no conflict
							board[i][j] = num;

							if (solved(board)) {
								return true;
							} else {
								board[i][j] = '.';
							}

						}

					}
					// if no proper number found, return false
					return false;
				}
			}
		}

		return true;
	}

	private boolean isValid(char[][] board, int i, int j, char c) {

		// check column
		for (int row = 0; row < 9; row++) {
			if (board[row][j] == c) {
				return false;
			}

		}

		// check row
		for (int col = 0; col < 9; col++) {
			if (board[i][col] == c) {
				return false;
			}

		}

		// check block
		for (int row = i / 3 * 3; row < i / 3 * 3 + 3; row++) {
			for (int col = j / 3 * 3; col < j / 3 * 3 + 3; col++) {
				if (board[row][col] == c) {
					return false;
				}

			}
		}

		return true;

	}

	public static void testSpiralOrder() {
		int[][] data = new int[4][8];
		java.util.Random rd = new java.util.Random();
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < data[0].length; ++j) {
				data[i][j] = rd.nextInt(50);
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("====>");
		new Matrix().spiralOrder(data);
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		// Given a matrix of m x n elements (m rows, n columns), return all
		// elements of the matrix in spiral order.
		//
		// For example,
		// Given the following matrix:
		//
		// [
		// [ 1, 2, 3 ],
		// [ 4, 5, 6 ],
		// [ 7, 8, 9 ]
		// ]
		// You should return [1,2,3,6,9,8,7,4,5].

		List<Integer> res = new ArrayList<Integer>();
		if (matrix == null)
			return res;
		int left = 0;
		int right = matrix[0].length - 1;
		int upper = 0;
		int bottom = matrix.length - 1;
		while (true) {
			for (int y = left; y <= right; ++y)
				res.add(matrix[upper][y]);
			if (++upper > bottom)
				break;
			for (int x = upper; x <= bottom; ++x)
				res.add(matrix[x][right]);
			if (--right < left)
				break;
			for (int y = right; y >= left; --y)
				res.add(matrix[bottom][y]);
			if (--bottom < upper)
				break;
			for (int x = bottom; x >= upper; --x)
				res.add(matrix[x][left]);
			if (++left > right)
				break;
		}
		return res;
	}

	public int[][] generateMatrix(int n) {
		int d = 1;
		int[][] res = new int[n][n];
		for (int w = 0; w < n / 2; ++w) {
			for (int i = w; i <= n - w - 1; ++i)
				res[w][i] = d++;
			for (int i = w + 1; i <= n - 1 - w; ++i)
				res[i][n - w - 1] = d++;
			for (int i = n - w - 2; i >= w; --i)
				res[n - w - 1][i] = d++;
			for (int i = n - w - 2; i > w; --i)
				res[i][w] = d++;
		}
		if (n % 2 == 1)
			res[n / 2][n / 2] = d++;
		return res;
	}

	public static void testPrintDiagonal() {
		int[][] data = new int[3][8];
		java.util.Random rd = new java.util.Random();
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < data[0].length; ++j) {
				data[i][j] = rd.nextInt(50);
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}

		System.out.println("====>");

		new Matrix().PrintDiagonal(data);
	}

	public void PrintDiagonal(int[][] data) {
		for (int i = 0; i < data.length; ++i)
			printLine(data, i, 0);
		for (int i = 1; i < data[0].length; ++i)
			printLine(data, data.length - 1, i);
	}

	private void printLine(int[][] data, int x, int y) {
		int i = 0;
		int j = 0;
		if (y == 0) {
			i = x;
			j = 0;
		} else if (x == data.length - 1) {
			i = x;
			j = y;
		} else {
			return;
		}
		while (i >= 0 && j < data[0].length) {
			System.out.print(data[i][j] + " -> ");
			--i;
			++j;
		}
		System.out.println();
	}

	/*
	 * Maximal Rectangle Given a 2D binary matrix filled with 0's and 1's, find
	 * the largest rectangle containing all ones and return its area.
	 */
	public static int maximalRectangle(char[][] matrix) {
		int rows = matrix.length;
		if (rows == 0) {
			return 0;
		}
		int cols = matrix[0].length;
		int[][] hOnes = new int[rows][cols]; // horizontal ones

		int max = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (matrix[i][j] == '1') {
					if (j == 0) {
						hOnes[i][j] = 1;
					} else {
						hOnes[i][j] = hOnes[i][j - 1] + 1;
					}
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (hOnes[i][j] != 0) {
					int minI = i;
					int minRowWidth = hOnes[i][j];
					while (minI >= 0) {
						minRowWidth = Math.min(minRowWidth, hOnes[minI][j]);
						int area = minRowWidth * (i - minI + 1);
						max = Math.max(max, area);
						minI--;
					}
				}
			}
		}

		return max;
	}

	// Largest Rectangle in Histogram
	// Given n non-negative integers representing the histogram's bar
	// height where the width of each bar is 1, find the area of largest
	// rectangle in the histogram.
	// The main function to find the maximum rectangular area under given
	// histogram with n bars
	// int getMaxArea(int hist[], int n)
	// {
	// // Create an empty stack. The stack holds indexes of hist[] array
	// // The bars stored in stack are always in increasing order of their
	// // heights.
	// stack<int> s;
	//
	// int max_area = 0; // Initalize max area
	// int tp; // To store top of stack
	// int area_with_top; // To store area with top bar as the smallest bar
	//
	// // Run through all bars of given histogram
	// int i = 0;
	// while (i < n)
	// {
	// // If this bar is higher than the bar on top stack, push it to stack
	// if (s.empty() || hist[s.top()] <= hist[i])
	// s.push(i++);
	//
	// // If this bar is lower than top of stack, then calculate area of
	// rectangle
	// // with stack top as the smallest (or minimum height) bar. 'i' is
	// // 'right index' for the top and element before top in stack is 'left
	// index'
	// else
	// {
	// tp = s.top(); // store the top index
	// s.pop(); // pop the top
	//
	// // Calculate the area with hist[tp] stack as smallest bar
	// area_with_top = hist[tp] * (s.empty() ? i : i - s.top() - 1);
	//
	// // update max area, if needed
	// if (max_area < area_with_top)
	// max_area = area_with_top;
	// }
	// }
	//
	// // Now pop the remaining bars from stack and calculate area with every
	// // popped bar as the smallest bar
	// while (s.empty() == false)
	// {
	// tp = s.top();
	// s.pop();
	// area_with_top = hist[tp] * (s.empty() ? i : i - s.top() - 1);
	//
	// if (max_area < area_with_top)
	// max_area = area_with_top;
	// }
	//
	// return max_area;
	// }

	public int largestRectangleArea(int[] height) {
		int area = 0;
		java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			if (stack.empty() || height[stack.peek()] < height[i]) {
				stack.push(i);
			} else {
				int start = stack.pop();
				int width = stack.empty() ? i : i - stack.peek() - 1;
				area = Math.max(area, height[start] * width);
				i--;
			}
		}
		while (!stack.empty()) {
			int start = stack.pop();
			int width = stack.empty() ? height.length : height.length
					- stack.peek() - 1;
			area = Math.max(area, height[start] * width);
		}
		return area;
	}

	/*
	 * Search a 2D Matrix Write an efficient algorithm that searches for a value
	 * in an m x n matrix. This matrix has the following properties: Integers in
	 * each row are sorted from left to right. The first integer of each row is
	 * greater than the last integer of the previous row.
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int start = 0, end = matrix.length * matrix[0].length - 1;

		while (start <= end) {
			int mid = (start + end) / 2, midX = mid / matrix[0].length, midY = mid
					% matrix[0].length;
			if (matrix[midX][midY] == target)
				return true;
			if (matrix[midX][midY] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return false;
	}

	/*
	 * Set Matrix Zeroes, constant space Given a m x n matrix, if an element is
	 * 0, set its entire row and column to 0. Do it in place.
	 */
	public void setZeroes(int[][] matrix) {

		boolean firstRow = false, firstColumn = false;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][0] == 0) {
				firstColumn = true;
				break;
			}
		}

		for (int i = 0; i < matrix[0].length; i++) {
			if (matrix[0][i] == 0) {
				firstRow = true;
				break;
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		if (firstRow) {
			for (int i = 0; i < matrix[0].length; i++)
				matrix[0][i] = 0;
		}

		if (firstColumn) {
			for (int i = 0; i < matrix.length; i++)
				matrix[i][0] = 0;
		}
	}

	/*
	 * Minimum Path Sum Given a m x n grid filled with non-negative numbers,
	 * find a path from top left to bottom right which minimizes the sum of all
	 * numbers along its path. Note: You can only move either down or right at
	 * any point in time.
	 */
	public int minPathSum(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[] res = new int[col];
		// init
		Arrays.fill(res, Integer.MAX_VALUE);
		res[0] = 0;

		// rest elements
		for (int i = 0; i < row; i++) {
			// init the 0th sum = old 0th element + the new 0th element
			// just init the 0th column every time dynamically
			res[0] = res[0] + grid[i][0];

			// loop through each element of each row
			for (int j = 1; j < col; j++) {
				res[j] = grid[i][j] + Math.min(res[j], res[j - 1]);
			}
		}

		return res[col - 1];
	}


	/*
	 * Unique Paths 
	 * 
	 * Unique Paths A robot is located at the top-left corner of a m x n grid
	 * (marked 'Start' in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. The
	 * robot is trying to reach the bottom-right corner of the grid (marked
	 * 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 */
	
	int count;
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
			return 0;

		count = 0;
		helper(obstacleGrid, 0, 0);
		return count;
	}
	public void helper(int[][] obstacleGrid, int row, int column) {
		// encounter block
		if (obstacleGrid[row][column] == 1)
			return;

		// right bottom reached
		if (row == obstacleGrid.length - 1
				&& column == obstacleGrid[0].length - 1) {
			count++;
			return;
		}

		// bottom reached
		if (row == obstacleGrid.length - 1)
			helper(obstacleGrid, row, column + 1);
		// right most reached
		else if (column == obstacleGrid[0].length - 1)
			helper(obstacleGrid, row + 1, column);
		else {
			helper(obstacleGrid, row + 1, column);
			helper(obstacleGrid, row, column + 1);
		}
	}

	/*
	 * Surrounded Regions Given a 2D board containing 'X' and 'O', capture all
	 * regions surrounded by 'X'.
	 * 
	 * A region is captured by flipping all 'O's into 'X's in that surrounded
	 * region.
	 */

	public void solve(char[][] board) {
		int row = board.length;
		if (row < 2)
			return;
		int col = board[0].length;
		if (col < 2)
			return;

		// ?????????dfs
		// top??
		for (int i = 0; i < col; i++) {
			if (board[0][i] == 'O') {
				board[0][i] = '#';
				dfs(board, 0, i, row, col);
			}
		}
		// bottom??
		for (int i = 0; i < col; i++) {
			if (board[row - 1][i] == 'O') {
				board[row - 1][i] = '#';
				dfs(board, row - 1, i, row, col);
			}
		}
		// left??
		for (int i = 1; i < row - 1; i++) {
			if (board[i][0] == 'O') {
				board[i][0] = '#';
				dfs(board, i, 0, row, col);
			}
		}
		// right??
		for (int i = 1; i < row - 1; i++) {
			if (board[i][col - 1] == 'O') {
				board[i][col - 1] = '#';
				dfs(board, i, col - 1, row, col);
			}
		}

		// ?'O'??'X',?'#'???'O'
		for (int i = 0; i < row; i++)
			for (int j = 0; j < col; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				else if (board[i][j] == '#')
					board[i][j] = 'O';
			}
	}

	void dfs(char[][] board, int i, int j, int row, int col) {
		if (i > 1 && board[i - 1][j] == 'O') {
			board[i - 1][j] = '#';
			dfs(board, i - 1, j, row, col);
		}
		if (i < row - 1 && board[i + 1][j] == 'O') {
			board[i + 1][j] = '#';
			dfs(board, i + 1, j, row, col);
		}
		if (j > 1 && board[i][j - 1] == 'O') {
			board[i][j - 1] = '#';
			dfs(board, i, j - 1, row, col);
		}
		if (j < col - 1 && board[i][j + 1] == 'O') {
			board[i][j + 1] = '#';
			dfs(board, i, j + 1, row, col);
		}
	}
}
