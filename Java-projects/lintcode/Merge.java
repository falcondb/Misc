import java.util.*;

public class Merge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity.
	 */
	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		ListNode result = null;
		ListNode curr = result;
		Comparator<ListNode> comp = new Comparator<ListNode>() {
			public int compare(ListNode a, ListNode b) {
				return a.val - b.val;
			}
		};
		PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(
				lists.size(), comp);
		for (int i = 0; i < lists.size(); i++) {
			ListNode currHead = lists.get(i);
			if (currHead != null)
				minHeap.add(currHead);
		}
		while (!minHeap.isEmpty()) {
			ListNode smallest = minHeap.poll();
			if (result == null) {
				result = smallest;
				curr = result;
			} else {
				curr.next = smallest;
				curr = curr.next;
			}
			ListNode nextEnqueue = smallest.next;
			if (nextEnqueue != null)
				minHeap.add(nextEnqueue);
		}
		return result;
	}

	/*
	 * Median of Two Sorted Arrays
	 * 
	 * There are two sorted arrays A and B of size m and n respectively. Find
	 * the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 */

	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;

		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) + findKth(A,
					B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}

	public static int findKth(int A[], int B[], int k, int aStart, int aEnd,
			int bStart, int bEnd) {

		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;

		// Handle special cases
		if (aLen == 0)
			return B[bStart + k];
		if (bLen == 0)
			return A[aStart + k];
		if (k == 0)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];

		int aMid = aLen * k / (aLen + bLen); // a's middle count
		int bMid = k - aMid - 1; // b's middle count

		// make aMid and bMid to be array index
		aMid = aMid + aStart;
		bMid = bMid + bStart;

		if (A[aMid] > B[bMid]) {
			k = k - (bMid - bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}

		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
