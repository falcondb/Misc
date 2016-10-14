public class NextRightPointers {

	/**
	 * Only works for perfect binary tree!!!!
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void connect(TreeLinkNode root) {
	

		if (root == null)
			return;
		if (root.left != null) {
			root.left.next = root.right;
		}
		if (root.right != null) {
			if (root.next == null)
				root.right.next = null;
			else
				root.right.next = root.next.left;
		}
		connect(root.left);
		connect(root.right);
	}

	class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}

}


