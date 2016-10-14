import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ConnectNodesSameLevel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectNodesSameLevel c = new ConnectNodesSameLevel();
		Node h =c.createBinaryTree(4);
		c.linkTree(h);
		System.out.println(h.data);
		
		
	}

	public Node createBinaryTree(int level) {
		Random r = new Random();
		Node head;
		if (level < 1)
			return null;
		if (level == 1) {
			head = new Node(r.nextInt(20), null, null);
			return head;
		}
		Node left = createBinaryTree(level - 1);
		Node right = createBinaryTree(level - 1);
		head = new Node(r.nextInt(20), left, right);
		return head;
	}

	public void linkTree(Node head) {

		Queue<Node> q = new LinkedList<Node>();

		Node markedNode = new Node(-1, null, null);

		if (head == null)
			return;
		q.add(head);
		q.add(markedNode);

		Node temp = null;
		while (!q.isEmpty()) {
			temp = q.poll();
			if (!temp.equals(markedNode)) {
				if (temp.left != null)
					q.add(temp.left);
				if (temp.right != null)
					q.add(temp.right);
				if (q.peek().data == -1) {
					temp.next = null;
					q.add(markedNode);
				} else {
					temp.next = q.peek();
				}
			}
		}

	}

	class Node {

		int data;

		public Node left;
		public Node right;
		public Node next;

		public Node(int data, Node left, Node right) {
			this.left = left;
			this.right = right;
			this.data = data;
		}

		public void setNext(Node n) {
			this.next = n;
		}
	}
}
