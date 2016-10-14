package yifeima.leetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode r = new TreeNode(1);
		r.left = new TreeNode(2);
		r.right = new TreeNode(3);
		System.out.println(new SymmetricTree().isSymmertic(r));
	}

	public boolean isSymmertic(TreeNode root) {

		Queue<TreeNode> q = new LinkedList<TreeNode>();

		TreeNode markedNode = new TreeNode(-1);
		markedNode.left = null;
		markedNode.right = null;

		ArrayList<String> valuesSampleLevel = new ArrayList<String>();

		if (root == null)
			return true;
		q.add(root);
		q.add(markedNode);

		TreeNode temp = null;
		while (!q.isEmpty()) {
			temp = q.poll();
			if (!temp.equals(markedNode)) {
				if (temp.left != null) {
					q.add(temp.left);
					valuesSampleLevel.add(new Integer(temp.left.val).toString());
				} else {
					valuesSampleLevel.add("#");
				}
				if (temp.right != null) {
					q.add(temp.right);
					valuesSampleLevel.add(new Integer(temp.right.val).toString());
				} else {
					valuesSampleLevel.add("#");
				}
				if (q.peek().equals(markedNode)) {
					if (!isLevelSym(valuesSampleLevel))
						return false;
					valuesSampleLevel.clear();
					q.add(markedNode);
				}
			}
		}
		return true;
	}

	private boolean isLevelSym(ArrayList<String> valuesSampleLevel) {
		if (valuesSampleLevel.size() == 0)
			return true;
		if (valuesSampleLevel.size() % 2 == 1)
			return false;
		for (int i = 0; i < valuesSampleLevel.size() / 2; ++i) {
			if (!(valuesSampleLevel.get(i).equals(valuesSampleLevel.get(valuesSampleLevel.size() - 1 - i))))
				return false;

		}
		return true;
	}
}
