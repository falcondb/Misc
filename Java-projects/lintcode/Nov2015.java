package leetCode;

public class Nov2015 {

	public static void main(String[] args) {
		Nov2015 c = new Nov2015();
		int[][] m = new int[][]{{5, 2, 8, 2, 6, 3},
		                        {0, 0, 1, 0, 0, 4},
		                        {4, 0, 3, 4, 2, 0},
		                        {4, 3, 3, 2, 4, 0},
		                        {4, 6, 6, 0, 3, 9},
		                        {3, 1, 0, 6, 5, 8}};
				
			System.out.println(c.eatCarrots(m));

	}

	public int eatCarrots (int[][] matrix){
		// Sanity check on input
		if(matrix == null || matrix.length ==0 || matrix[0].length == 0)
			return 0;
		
		int res = 0;

		// Search for the start point
		int max = Integer.MIN_VALUE;
		int si = 0, sj=0;
		for(int i = (matrix.length-1)/2; i <= matrix.length/2; ++i)
			for(int j = (matrix[0].length-1)/2; j <= matrix[0].length/2; ++j)
				if(matrix[i][j] > max){
					max = matrix[i][j];
					si = i;
					sj = j;
				}

		int ni = -1, nj = -1;
		max = -1;
		while(max != 0){
			max = 0;
			// eat the carrots on the cell
			res += matrix[si][sj];
			matrix[si][sj]=0;
			
			// search for the next cell
			ni = -1; nj = -1;
			// up cell
			if( si > 0 && matrix[si-1][sj] > max){
				max = matrix[si-1][sj];
				ni = si -1; nj = sj;
			}
			// down cell
			if( si < matrix.length -1 && matrix[si+1][sj] > max){
				max = matrix[si+1][sj];
				ni = si + 1; nj = sj;
			}
			// right cell
			if( sj > 0 && matrix[si][sj -1] >max){
				max = matrix[si][sj-1];
				ni = si; nj = sj-1;
			}
			// left cell
			if( sj < matrix[0].length -1 && matrix[si][sj+1] > max){
				max = matrix[si][sj+1];
				ni = si; nj = sj+1;
			}
			// find the next cell
			si = ni;
			sj = nj;
		}
		return res;
	}
	
	public void moveZeroes(int[] nums) {
		int cur = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[cur++] = nums[i];
			}
		}
		for (int i = cur; i < nums.length; nums[i++] = 0)
			;
	}

	public int numSquares(int n) {
		if (n <= 0)
			return -1;
		if (n == 1)
			return 1;
		int[] mins = new int[n + 1];
		mins[0] = 0;
		mins[1] = 1;
		int cmin = Integer.MAX_VALUE;
		int base = 2;
		for (int i = 2; i <= n; i++) {
			if (i == base * base) {
				mins[i] = 1;
				base++;
			} else {
				cmin = Integer.MAX_VALUE;
				for (int j = 1; j < i; j++) {
					cmin = cmin < mins[j] + mins[i - j] ? cmin : mins[j] + mins[i - j];
				}
				mins[i] = cmin;
			}
		}
		return mins[n];
	}
}
