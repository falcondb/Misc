import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MyLinkedList {

	public static void main(String[] args) {
		new MyLinkedList().testrotateRight(1);
	}

	public void printList(ListNode head) {
		for (ListNode t = head; t != null; t = t.next) {
			System.out.print(t.val + " -> ");
		}
		System.out.println("NULL");
	}

	/*
	 * Merge Two Sorted Lists
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		 
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode fakeHead = new ListNode(0);
        ListNode p = fakeHead;
        while(p1 != null && p2 != null){
          if(p1.val <= p2.val){
              p.next = p1;
              p1 = p1.next;
          }else{
              p.next = p2;
              p2 = p2.next;
          }
          p = p.next;
        }
        if(p1 != null)
            p.next = p1;
        if(p2 != null)
            p.next = p2;
        return fakeHead.next;
    }
	
	/*
	 * Given a list, rotate the list to the right by k places, where k is
	 * non-negative.
	 * 
	 * For example: Given 1->2->3->4->5->NULL and k = 2, return
	 * 4->5->1->2->3->NULL.
	 */
	public ListNode rotateRight(ListNode head, int n) {
		if (head == null || n == 0)
			return head;
		ListNode p = head;
		int len = 1;
		while (p.next != null) {
			len++;
			p = p.next;
		}
		p.next = head; // form a loop
		n = n % len;
		for (int i = 0; i < len - n; i++) {
			p = p.next;
		}
		head = p.next;
		p.next = null;
		return head;
	}

	public ListNode rotateRight2(ListNode head, int n) {
		// walk through the link list and get the last node and the last k+1 th
		// node
		int k = 0;
		ListNode k1Node = null, last = null;
		if (head == null)
			return null;
		if (n == 0)
			return head;

		for (last = head; last.next != null; last = last.next) {
			if (++k == n)
				k1Node = head;
			else if (k > n)
				k1Node = k1Node.next;
		}
		if (k1Node == null)
			return null;
		last.next = head;
		head = k1Node.next;
		k1Node.next = null;
		printList(head);
		return head;
	}

	public ListNode testrotateRight(int n) {
		ListNode head = createAnSortedLinkedList(n);
		rotateRight(head, n);
		return head;
	}

	public ListNode testReorderList(int length) {
		ListNode head = createAnSortedLinkedList(length);
		reorderList(head);
		return head;
	}

	private ListNode createAnSortedLinkedList(int length) {
		ListNode head = null;
		ListNode tmp = null;
		for (int i = 1; i <= length; ++i) {
			tmp = head;
			head = new ListNode(i);
			head.next = tmp;
		}
		printList(head);
		return head;
	}

	/* a->b->c->d->e => a->e->b->d->c */
	public void reorderList(ListNode head) {

		if (head == null)
			return;
		int length = 0;
		ListNode mid = head;
		for (ListNode tmp = head; tmp.next != null; tmp = tmp.next) {
			length++;
			if (length % 2 == 0)
				mid = mid.next;
		}
		if (length % 2 == 1)
			mid = mid.next;
		ListNode reverseHead = null, tmp = null;
		for (ListNode cur = mid; cur != null;) {
			tmp = cur.next;
			cur.next = reverseHead;
			reverseHead = cur;
			cur = tmp;
		}
		ListNode fcur = head, ftmp = null, stmp = null;
		ListNode scur = reverseHead;
		while (scur != null) {
			ftmp = fcur.next;
			stmp = scur.next;
			fcur.next = scur;
			scur.next = ftmp;
			fcur = ftmp;
			scur = stmp;
		}
		printList(head);
	}

	/*
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * If the number of nodes is not a multiple of k then left-out nodes in the
	 * end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 */

	public ListNode reverseKGroup(ListNode head, int k) {
		// Start typing your Java solution below
		// DO NOT write main() function
		if (head == null || k == 1) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode pre = dummy;

		int i = 0;
		while (head != null) {
			i++;

			if (i % k == 0) {
				pre = reverse(pre, head.next);
				head = pre.next;
			} else {
				head = head.next;

			}
		}
		return dummy.next;

	}

	/**
	 * Reverse a link list between pre and next exclusively an example: a linked
	 * list: 0->1->2->3->4->5->6 | | pre next after call pre = reverse(pre,
	 * next)
	 * 
	 * 0->3->2->1->4->5->6 | | pre next
	 * 
	 * @param pre
	 * @param next
	 * @return the reversed list's last node, which is the precedence of
	 *         parameter next
	 */
	public ListNode reverse(ListNode pre, ListNode next) {

		ListNode last = pre.next;
		ListNode cur = last.next;

		while (cur != next) {

			last.next = cur.next;
			cur.next = pre.next;
			pre.next = cur;

			cur = last.next;
		}
		return last;
	}

	/*
	 * Swap Nodes in Pairs
	 */

	public ListNode swapPairs(ListNode head) {
		// Start typing your Java solution below
		// DO NOT write main() function
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode n1 = helper, n2 = head;

		while (n2 != null && n2.next != null) {
			ListNode temp = n2.next.next;
			n2.next.next = n1.next;
			n1.next = n2.next;
			n2.next = temp;
			n1 = n2;
			n2 = n1.next;
		}
		return helper.next;
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
	 * Sort List: Sort a linked list in O(n log n) time using constant space
	 * complexity
	 */
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

	// insertion sort for ArrayList
	public ArrayList insertSort(ArrayList source) {

		int index = 1;

		while (index < source.size()) {
			insertSorted((String) (source.get(index)), source, index);
			index = index + 1;
		}
		return source;
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

	/*
	 * Given a linked list, determine if it has a cycle in it
	 */
	public boolean hasCycle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;

		if (head == null)
			return false;

		if (head.next == null)
			return false;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				return true;
		}

		return false;
	}

	public ListNode detectCycle(ListNode head) {
		if (head == null || head.next == null) {
			return null;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null && slow != null) {
			slow = slow.next;
			fast = fast.next.next;
			if (slow == fast) {
				break;
			}
		}
		if (slow == null || fast == null || fast.next == null) {
			return null;
		}
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	/*
	 * Copy List with Random Pointer
	 * 
	 * A linked list is given such that each node contains an additional random
	 * pointer which could point to any node in the list or null.
	 * 
	 * Return a deep copy of the list.
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode newHead = new RandomListNode(head.label);

		RandomListNode p = head;
		RandomListNode q = newHead;
		map.put(head, newHead);

		p = p.next;
		while (p != null) {
			RandomListNode temp = new RandomListNode(p.label);
			map.put(p, temp);
			q.next = temp;
			q = temp;
			p = p.next;
		}
		p = head;
		q = newHead;
		while (p != null) {
			if (p.random != null)
				q.random = map.get(p.random);
			else
				q.random = null;
			p = p.next;
			q = q.next;
		}
		return newHead;
	}

	/*
	 * Reverse Linked List II
	 * 
	 * Reverse a linked list from position m to n. Do it in-place and in
	 * one-pass.
	 * 
	 * For example: Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	 * 
	 * return 1->4->3->2->5->NULL.
	 */

	ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy, cur = head;
		for (int i = 1; i < m; i++) {
			pre = cur;
			cur = cur.next;
		}
		ListNode p1 = null, p2 = null;
		if (cur != null)
			p1 = cur.next;
		if (p1 != null)
			p2 = p1.next;
		for (int j = m; j < n; j++) {
			p1.next = cur;
			cur = p1;
			p1 = p2;
			if (p2 != null)
				p2 = p2.next;
		}
		pre.next.next = p1;
		pre.next = cur;
		return dummy.next;
	}

	public ListNode Mypartition(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode fh = null, sh = null, tmp = null;
		for (ListNode cur = head; cur != null;) {
			tmp = cur;
			cur = cur.next;
			if (tmp.val < x) {
				if (fh == null)
					fh = tmp;
				else {
					fh.next = tmp;
					fh = fh.next;
				}
			} else {
				if (sh == null)
					sh = tmp;
				else {
					sh.next = tmp;
					sh = sh.next;
					sh.next = null;
				}
			}
		}
		if (fh == null)
			return sh;
		if (sh == null)
			return fh;
		else {
			fh.next = sh;
			return fh;
		}
	}

	public ListNode partition(ListNode head, int x) {
		if (head == null)
			return null;

		ListNode fakeHead1 = new ListNode(0);
		ListNode fakeHead2 = new ListNode(0);
		fakeHead1.next = head;

		ListNode p = head;
		ListNode prev = fakeHead1;
		ListNode p2 = fakeHead2;

		while (p != null) {
			if (p.val < x) {
				p = p.next;
				prev = prev.next;
			} else {
				p2.next = p;
				prev.next = p.next;
				p = prev.next;
				p2 = p2.next;
			}
		}
		// close the list
		p2.next = null;
		prev.next = fakeHead2.next;
		return fakeHead1.next;
	}

	/*
	 * Remove Nth Node From End of List
	 * 
	 * Given a linked list, remove the nth node from the end of list and return
	 * its head. For example, Given linked list: 1->2->3->4->5, and n = 2.
	 * 
	 * After removing the second node from the end, the linked list becomes
	 * 1->2->3->5.
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (n <= 0)
			return head;
		ListNode prev = new ListNode(0);
		prev.next = head;
		head = prev;
		ListNode n1 = head.next, n2 = head.next;
		int k = n;
		while (n2 != null && k > 0) {
			n2 = n2.next;
			k--;
		}
		if (k > 0)
			return n1;
		while (n2 != null) {
			n2 = n2.next;
			n1 = n1.next;
			prev = prev.next;
		}
		prev.next = n1.next;
		return head.next;
	}

	/*
	 * Add Two Numbers
	 * 
	 * You are given two linked lists representing two non-negative numbers. The
	 * digits are stored in reverse order and each of their nodes contain a
	 * single digit. Add the two numbers and return it as a linked list. Input:
	 * (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 */
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode res = new ListNode(0);
		ListNode cur1 = l1, cur2 = l2, head = res;
		while (cur1 != null || cur2 != null) {
			if (cur1 != null) {
				carry += cur1.val;
				cur1 = cur1.next;
			}
			if (cur2 != null) {
				carry += cur2.val;
				cur2 = cur2.next;
			}
			head.next = new ListNode(carry % 10);
			head = head.next;
			carry /= 10;
		}
		if (carry == 1)
			head.next = new ListNode(1);
		return res.next;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}

class RandomListNode {
	int label;
	RandomListNode next, random;

	RandomListNode(int x) {
		this.label = x;
	}
}
