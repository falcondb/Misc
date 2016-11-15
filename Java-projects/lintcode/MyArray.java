public class MyArray {

	public static void main(String[] args) {
		int[] d = { -11, 12, 3, 17, 5 };
		System.out.println(new MyArray().firstMissingPositive(d, d.length));

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
	 */
	public static int removeDuplicatesNaive(int[] A) {
		if (A.length < 2)
			return A.length;
		int j = 0;
		int i = 1;
		while (i < A.length) {
			if (A[i] == A[j]) {
				i++;
			} else {
				j++;
				A[j] = A[i];
				i++;
			}
		}
		return j + 1;
	}

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
	
	/*
	 * Merge Sorted Array
	 * 
	 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
	 * 
	 */
	
	public void merge(int A[], int m, int B[], int n) {
		 
        while(m > 0 && n > 0){
            if(A[m-1] > B[n-1]){
                A[m+n-1] = A[m-1];
                m--;
            }else{
                A[m+n-1] = B[n-1];
                n--;
            }
        }
 
        while(n > 0){
            A[m+n-1] = B[n-1];
            n--;
        }
    }
}
