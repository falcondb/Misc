public class QArray {

	public static void main(String[] args) {
		int[] d = { -11, 12, 3, 17, 5 };
		System.out.println(new QArray().firstMissingPositive(d, d.length));

	}

	/*
	 * Jump Game
	 * 
	 * Given an array of non-negative integers, you are initially positioned at
	 * the first index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that
	 * position.
	 * 
	 * Determine if you are able to reach the last index.
	 * 
	 * For example: A = [2,3,1,1,4], return true.
	 * 
	 * A = [3,2,1,0,4], return false.
	 */
	public boolean canJump(int[] A) {

		if (A.length <= 1)
			return true;
		if (A[0] >= (A.length - 1))
			return true;
		int maxlength = A[0];
		if (maxlength == 0)
			return false;
		for (int i = 1; i < A.length - 1; i++) {
			if (maxlength >= i && (i + A[i]) >= A.length - 1)
				return true;
			if (maxlength <= i && A[i] == 0)
				return false;
			if ((i + A[i]) > maxlength)
				maxlength = i + A[i];
		}
		return false;
	}

	/*
	 * Maximum Subarray Find the contiguous subarray within an array (containing
	 * at least one number) which has the largest sum.
	 * 
	 * More practice: If you have figured out the O(n) solution, try coding
	 * another solution using the divide and conquer approach, which is more
	 * subtle.
	 */
	public int maxSubArray(int[] A) {
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			maxSum = Math.max(maxSum, sum);
			// re-set sum when < 0 (no need to keep neg value)
			if (sum < 0)
				sum = 0;
		}
		return maxSum;
	}

	/*
	 * Solution #2, Divide and conquer, O(n) also? The max range can be in the
	 * left half, or in the right half, or across the mid of the array, so we
	 * just divide it to tree parts and recursive until we get the max value of
	 * each part, and then get the largest value.
	 */
	public int maxSubArrayDC(int[] A) {
		int maxSum = Integer.MIN_VALUE;
		return findMaxSub(A, 0, A.length - 1, maxSum);
	}

	public int findMaxSub(int[] A, int left, int right, int maxSum) {
		if (left > right)
			return Integer.MIN_VALUE;

		// get max sub sum from both left and right cases
		int mid = (left + right) / 2;
		int leftMax = findMaxSub(A, left, mid - 1, maxSum);
		int rightMax = findMaxSub(A, mid + 1, right, maxSum);
		maxSum = Math.max(maxSum, Math.max(leftMax, rightMax));

		// get max sum of this range (case: across mid)
		// so need to expend to both left and right using mid as center
		// mid -> left
		int sum = 0, midLeftMax = 0;
		for (int i = mid - 1; i >= left; i--) {
			sum += A[i];
			if (sum > midLeftMax)
				midLeftMax = sum;
		}
		// mid -> right
		int midRightMax = 0;
		sum = 0;
		for (int i = mid + 1; i <= right; i++) {
			sum += A[i];
			if (sum > midRightMax)
				midRightMax = sum;
		}

		// get the max value from the left, right and across mid
		maxSum = Math.max(maxSum, midLeftMax + midRightMax + A[mid]);
		return maxSum;
	}

	/*
	 * Search in Rotated Sorted Array
	 * 
	 * 
	 * Suppose a sorted array is rotated at some pivot unknown to you
	 * beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * You are given a target value to search. If found in the array return its
	 * index, otherwise return -1.
	 * 
	 * You may assume no duplicate exists in the array.
	 */
	public int search(int[] A, int target) {
		return RotatedBinarySearch(A, 0, A.length - 1, target);
	}

	public int BinarySearch(int[] A, int a, int b, int target) {
		int middle = (a + b) / 2;
		if (A[middle] == target) {
			return middle;
		} else if ((A[middle] < target) && (middle + 1 <= b)) {
			return BinarySearch(A, middle + 1, b, target);
		} else if ((A[middle] > target) && (middle - 1 >= a)) {
			return BinarySearch(A, a, middle - 1, target);
		} else {
			return -1;
		}
	}

	public int RotatedBinarySearch(int[] A, int a, int b, int target) {
		int middle = (a + b) / 2;
		if (A[middle] == target) {
			return middle;
		}
		// the second half is sorted completely
		else if (middle + 1 <= b && A[middle + 1] <= A[b]) {
			if ((A[middle + 1] <= target) && (target <= A[b])) {
				return BinarySearch(A, middle + 1, b, target);
			} else if (middle - 1 >= a) {
				return RotatedBinarySearch(A, a, middle - 1, target);
			} else {
				return -1;
			}
		}
		// the first half is sorted completely
		else if (middle - 1 >= a && A[middle - 1] >= A[a]) {
			if ((A[middle - 1] >= target) && (target >= A[a])) {
				return BinarySearch(A, a, middle - 1, target);
			} else if (middle + 1 <= b) {
				return RotatedBinarySearch(A, middle + 1, b, target);
			} else {
				return -1;
			}
		} else {
			return -1;
		}
	}

	/*
	 * Given an array and a value, remove all instances of that value in place
	 * and return the new length.
	 * 
	 * The order of elements can be changed. It doesn't matter what you leave
	 * beyond the new length.
	 */
	public int removeElement(int[] A, int elem) {
		int i = 0;
		for (int j = 0; j < A.length; j++) {
			if (A[j] != elem) {
				A[i] = A[j];
				i++;
			}
		}
		return i;
	}

	/*
	 * Remove Duplicates from Sorted Array
	 * 
	 * Given a sorted array, remove the duplicates in place such that each
	 * element appear only once and return the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this in place
	 * with constant memory.
	 * 
	 * For example, Given input array A = [1,1,2],
	 * 
	 * Your function should return length = 2, and A is now [1,2].
	 */
	public static int removeDuplicatesNaive(int[] A) {
		if (A.length < 2)
			return A.length;
		int j = 0, i = 1;
		while (i < A.length) {
			if (A[i] == A[j])
				i++;
			else {
				j++;
				A[j] = A[i];
				i++;
			}
		}
		return j + 1;
	}

	/*
	 * First Missing Positive
	 * 
	 * First Missing Positive Given an unsorted integer array, find the first
	 * missing positive integer.
	 */
	public int firstMissingPositive(int A[], int n) {
		int i = 0;
		int temp = 0;
		while (i < n) {
			if (A[i] != (i + 1) && A[i] >= 1 && A[i] <= n
					&& A[A[i] - 1] != A[i]) {
				temp = A[i];
				A[i] = A[A[i] - 1];
				A[A[i] - 1] = temp;
			} else
				i++;
		}
		for (i = 0; i < n; ++i)
			if (A[i] != (i + 1))
				return i + 1;
		return n + 1;
	}

	/*
	 * Search Insert Position
	 * 
	 * Search Insert Position Given a sorted array and a target value, return
	 * the index if the target is found. If not, return the index where it would
	 * be if it were inserted in order. You may assume no duplicates in the
	 * array.
	 */
	public int searchInsert(int[] A, int target) {
		if (A == null || A.length == 0)
			return 0;
		return searchInsert(A, target, 0, A.length - 1);
	}

	public int searchInsert(int[] A, int target, int start, int end) {
		int mid = (start + end) / 2;
		if (target == A[mid])
			return mid;
		else if (target < A[mid])
			return start < mid ? searchInsert(A, target, start, mid - 1)
					: start;
		else
			return end > mid ? searchInsert(A, target, mid + 1, end)
					: (end + 1);
	}

	/*
	 * Search for a Range
	 * 
	 * Given a sorted array of integers, find the starting and ending position
	 * of a given target value.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * 
	 * If the target is not found in the array, return [-1, -1].
	 * 
	 * For example, Given [5, 7, 7, 8, 8, 10] and target value 8, return [3, 4].
	 */
	public int[] searchRange(int[] A, int target) {
		if (A == null || A.length == 0)
			return null;
		int[] res = new int[2];
		res[0] = searchMaxLessThan(A, target, 0, A.length - 1);
		res[1] = searchMaxLessThan(A, target + 1, 0, A.length - 1);
		if (res[0] == res[1]) {
			res[0] = -1;
			res[1] = -1;
		} else {
			res[0]++;
		}
		return res;
	}

	public int searchMaxLessThan(int[] A, int target, int start, int end) {

		if (start == end)
			return A[start] < target ? start : start - 1;
		if (start == end - 1)
			return A[end] < target ? end : (A[start] < target ? start
					: start - 1);
		int mid = (start + end) / 2;
		if (A[mid] >= target) {
			end = mid - 1;
		} else {
			start = mid;
		}
		return searchMaxLessThan(A, target, start, end);
	}
	/* End of searching range */
}
