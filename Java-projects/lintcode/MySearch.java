
public class MySearch {

	/*
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

	public int[] searchRange(int[] A, int target) {
		// Start typing your Java solution below
		// DO NOT write main() function
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

	/*
	 * Search for a Range
	 */
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
}
