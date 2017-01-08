import java.util.*;

public class MySort {

	public static void mergeSort(Comparable[] a) {
		Comparable[] tmp = new Comparable[a.length];
		mergeSort(a, tmp, 0, a.length - 1);
	}

	private static void mergeSort(Comparable[] a, Comparable[] tmp, int left,
			int right) {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}

	private static void merge(Comparable[] a, Comparable[] tmp, int left,
			int right, int rightEnd) {
		int leftEnd = right - 1;
		int k = left;
		int num = rightEnd - left + 1;

		while (left <= leftEnd && right <= rightEnd)
			if (a[left].compareTo(a[right]) <= 0)
				tmp[k++] = a[left++];
			else
				tmp[k++] = a[right++];

		while (left <= leftEnd)
			// Copy rest of first half
			tmp[k++] = a[left++];

		while (right <= rightEnd)
			// Copy rest of right half
			tmp[k++] = a[right++];

		// Copy tmp back
		for (int i = 0; i < num; i++, rightEnd--)
			a[rightEnd] = tmp[rightEnd];
	}

	public ListNode sortList(ListNode head) {
		// recursively calling, without using header
		if (head == null || head.next == null)
			return head;

		ListNode slow = head, fast = head.next;
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}
		fast = slow.next; // use fast as the head of 2nd list
		slow.next = null; // point last to null
		head = sortList(head);
		fast = sortList(fast);
		head = mergeList(head, fast);
		return head;
	}

	/*
	 * Sort List: Sort a linked list in O(n log n) time using constant space
	 * complexity
	 */
	private ListNode mergeList(ListNode h1, ListNode h2) {
		ListNode tmp;
		if (h2.val < h1.val) {// make sure, h2.val < h1.val
			tmp = h2;
			h2 = tmp.next;
			tmp.next = h1;
			h1 = tmp;
		}

		ListNode preNode = h1;
		while (h2 != null) {
			tmp = h2;
			h2 = tmp.next;
			while (preNode.next != null && preNode.next.val < tmp.val)
				preNode = preNode.next;
			tmp.next = preNode.next;
			preNode.next = tmp;
		}
		return h1;
	}

	/*
	 * Insertion Sort List Sort a linked list using insertion sort.
	 */
	public ArrayList<String> insertSorted(String s, ArrayList<String> source,
			int index) {
		int loc = index - 1;
		while ((loc >= 0) &&
		// c is smaller that the next highest element
				(s.compareTo(source.get(loc)) <= 0)) {
			// move element to the right
			source.set(loc + 1, source.get(loc));
			loc = loc - 1;
		}
		source.set(loc + 1, s);
		return source;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}