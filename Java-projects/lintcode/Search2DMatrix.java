public class Search2DMatrix {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int v = 1;
		int[][] m = new int[4][6];
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 6; ++j) {
				m[i][j] = v++;
			}
		}
		System.out.println(new Search2DMatrix().searchMatrix(m, 15));
	}

	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix.length <= 0) {
			return false;
		}

		int h = matrix.length;
		int w = matrix[0].length;

		int head = 0;
		int end = h * w - 1;
		int curr = 0;
		while (head <= end) {

			curr = (head + end) / 2;
			if (matrix[curr / w][curr - (curr / w) * w] == target) {
				return true;
			}
			if (matrix[curr / w][curr - (curr / w) * w] > target) {
				end = curr - 1;
			} else {
				head = curr + 1;
			}

		}

		return false;

	}

}
