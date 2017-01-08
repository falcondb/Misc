import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST {
	TreeNode root = null;

	public void insertNode(TreeNode n) {
		if (root == null) {
			root = n;
			return;
		}
		insertRec(root, n);
	}

	public static void main(String[] args) {
		BST tree = new BST();
		java.util.Random rd = new java.util.Random();
		for (int i = 0; i < 15; ++i) {
			tree.insertNode(new TreeNode(rd.nextInt(1000)));
		}
	}

	/**
	 * Given a singly linked list where elements are sorted in ascending order,
	 * convert it to a height balanced BST. Definition for singly-linked list.
	 * public class ListNode { int val; ListNode next; ListNode(int x) { val =
	 * x; next = null; } }
	 */
	/**
	 * Definition for binary tree public class TreeNode { int val; TreeNode
	 * left; TreeNode right; TreeNode(int x) { val = x; } }
	 */
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return null;
		TreeNode root = new TreeNode(1);
		return root;
	}

	public void inorderRec(TreeNode r) {
		if (r == null)
			return;
		inorderRec(r.left);
		System.out.print(r.value + ", ");
		inorderRec(r.right);
	}

	public void inorderIter(TreeNode r) {
		if (r == null)
			return;
		java.util.Stack<TreeNode> st = new java.util.Stack<TreeNode>();

		TreeNode temp = r;
		while (st.size() > 0 || temp != null) {
			if (temp != null) {
				st.push(temp);
				temp = temp.left;
			} else {
				temp = st.pop();
				System.out.print(temp.value + ", ");
				temp = temp.right;
			}
		}
	}

	public TreeNode getImmediateNext(TreeNode node, int x) {
		if (node == null)
			return null;
		if (node.value < x)
			return getImmediateNext(node.right, x);
		else {
			TreeNode n = getImmediateNext(node.left, x);
			if (n == null)
				return node;
			else
				return n;
		}
	}

	public int getNth(int order, int ranking) {
		java.util.Stack<TreeNode> st = new java.util.Stack<TreeNode>();
		TreeNode temp = root;
		int ct = 0;
		while (st.size() > 0 || temp != null) {
			if (temp != null) {
				st.push(temp);
				if (order == 0) // smallest
					temp = temp.left;
				else
					temp = temp.right;
			} else {
				temp = st.pop();
				if (++ct == ranking)
					return temp.value;
				if (order == 0) // smallest
					temp = temp.right;
				else
					temp = temp.left;
			}
		}
		return Integer.MAX_VALUE;
	}

	public void insertRec(TreeNode lr, TreeNode n) {
		if (lr.value >= n.value) {
			if (lr.left == null)
				lr.left = n;
			else
				insertRec(lr.left, n);
		} else {
			if (lr.right == null)
				lr.right = n;
			else
				insertRec(lr.right, n);
		}
	}

	/*
	 * 
	 * Sum Root to Leaf Numbers
	 * 
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf
	 * path could represent a number.
	 * 
	 * An example is the root-to-leaf path 1->2->3 which represents the number
	 * 123.
	 * 
	 * Find the total sum of all root-to-leaf numbers.
	 * 
	 * For example,
	 * 
	 * 1 / \ 2 3 The root-to-leaf path 1->2 represents the number 12. The
	 * root-to-leaf path 1->3 represents the number 13.
	 * 
	 * Return the sum = 12 + 13 = 25.
	 */

	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int[] sum = { 0 };
		int current = 0;
		getSum(root, current, sum);
		return sum[0];
	}

	public void getSum(TreeNode root, int current, int[] sum) {
		if (root == null) {
			return;
		}
		current = current * 10 + root.value;
		if (root.left == null && root.right == null) {
			sum[0] = sum[0] + current;
			return;
		}
		getSum(root.left, current, sum);
		getSum(root.right, current, sum);
	}

	/*
	 * Populating Next Right Pointers in Each Node
	 * 
	 * Populate each next pointer to point to its next right node. If there is
	 * no next right node, the next pointer should be set to NULL.
	 */

	public void connect2(TreeLinkNode root) {
		if (root == null) // Empty tree
			return;
		TreeLinkNode headOfNextLevel = root;
		// Between levels
		while (headOfNextLevel != null) {
			// Initialize tailOfNextLevel and current
			TreeLinkNode tailOfNextLevel = null, current = headOfNextLevel;
			headOfNextLevel = null;
			// Within a level
			while (current != null) {
				// Update headOfNextLevel if we find the first node in the next
				// level
				if (headOfNextLevel == null) {
					if (current.left != null)
						headOfNextLevel = current.left;
					else if (current.right != null)
						headOfNextLevel = current.right;
				}
				// Link its two subtrees if both exist
				if (current.left != null && current.right != null)
					current.left.next = current.right;
				if (tailOfNextLevel != null) {
					// Link the currently last node in the next level to a
					// subtree (if any) of current node
					if (current.left != null && current.right != null) {
						tailOfNextLevel.next = current.left;
						tailOfNextLevel = current.right;
					} else if (current.left != null) {
						tailOfNextLevel.next = current.left;
						tailOfNextLevel = current.left;
					} else if (current.right != null) {
						tailOfNextLevel.next = current.right;
						tailOfNextLevel = current.right;
					}
				} else if (headOfNextLevel != null) {
					// The first node in the next level has been found
					if (headOfNextLevel.next != null)
						tailOfNextLevel = headOfNextLevel.next;
					else
						tailOfNextLevel = headOfNextLevel;
				}
				// Move to the next node in the same level
				current = current.next;
			}
		}
	}

	public void connect(TreeLinkNode root) {
		if (root == null) // Empty tree
			return;
		TreeLinkNode headOfNextLevel = root;
		// Between levels
		while (headOfNextLevel != null) {
			// Initialize tailOfNextLevel and current, and update
			// headOfNextLevel to null
			TreeLinkNode tailOfNextLevel = null, current = headOfNextLevel;
			headOfNextLevel = null;

			// Within a level
			while (current != null) {
				// Update headOfNextLevel if we find the first node in the next
				// level
				if (headOfNextLevel == null && current.left != null)
					headOfNextLevel = current.left;
				// Not a leaf; link its children
				if (current.left != null)
					current.left.next = current.right;
				// Link the currently last node in the next level to the left
				// subtree of current node
				if (tailOfNextLevel != null)
					tailOfNextLevel.next = current.left;
				// Update tailOfNextLevel to the right subtree, and
				// move to the next node in the same level
				tailOfNextLevel = current.right;
				current = current.next;
			}
		}
	}

	/*
	 * Flatten Binary Tree to Linked List
	 * 
	 * (1) store the right child (we call R)(2) find the right-most node of left
	 * child(3) set R as the right-most node's right child.(4) set left child as
	 * the right child(5) set the left child NULL(6) set current node to current
	 * node's right child.(7) iterate these steps until all node are flattened.
	 */

	public void flatten(TreeNode root) {
		TreeNode pre;
		while (root != null) {
			if (root.left != null) {
				pre = root.left;
				while (pre.right != null) {
					pre = pre.right;
				}
				pre.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			root = root.right;
		}
	}

	/*
	 * Path sum Given a binary tree and a sum, determine if the tree has a
	 * root-to-leaf path such that adding up all the values along the path
	 * equals the given sum.
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;

		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
		LinkedList<Integer> values = new LinkedList<Integer>();

		nodes.add(root);
		values.add(root.value);

		while (!nodes.isEmpty()) {
			TreeNode curr = nodes.poll();
			int sumValue = values.poll();

			if (curr.left == null && curr.right == null && sumValue == sum) {
				return true;
			}

			if (curr.left != null) {
				nodes.add(curr.left);
				values.add(sumValue + curr.left.value);
			}

			if (curr.right != null) {
				nodes.add(curr.right);
				values.add(sumValue + curr.right.value);
			}
		}

		return false;
	}

	/*
	 * Minimum Depth of Binary Tree Given a binary tree, find its minimum depth.
	 * The minimum depth is the number of nodes along the shortest path from the
	 * root node down to the nearest leaf node.
	 */

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;

		if (root.left == null && root.right == null)
			return 1;

		if (root.left == null) {
			return 1 + minDepth(root.right);
		} else if (root.right == null) {
			return 1 + minDepth(root.left);
		} else
			return 1 + Math.min(minDepth(root.left), minDepth(root.right));
	}

	/*
	 * 
	 * Balanced Binary Tree Solution
	 * 
	 * Given a binary tree, determine if it is height-balanced. For this
	 * problem, a height-balanced binary tree is defined as a binary tree in
	 * which the depth of the two subtrees of everynode never differ by more
	 * than 1.
	 */

	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (Math.abs(depth(root.left) - depth(root.right)) > 1) {
			return false;
		}
		return isBalanced(root.left) && isBalanced(root.right);
	}

	public int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(depth(root.left), depth(root.right));
	}

	
	/*
	 * Sorted Array To BST
	 */
	
	public TreeNode sortedArrayToBST(int[] num) {
		if (num.length == 0)
			return null;

		return sortedArrayToBST(num, 0, num.length - 1);
	}

	public TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBST(num, start, mid - 1);
		root.right = sortedArrayToBST(num, mid + 1, end);

		return root;
	}

	/*
	 * Binary Tree Level Order Traversal II Given a binary tree, return the
	 * bottom-up level order traversal of its nodes' values. (ie, from left to
	 * right, level by level from leaf to root).
	 */

	public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (root == null) {
			return ret;
		}
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int curLevCnt = 1;
		int nextLevCnt = 0;
		ArrayList<Integer> al = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			TreeNode cur = queue.remove();
			curLevCnt--;
			al.add(cur.value);
			if (cur.left != null) {
				queue.add(cur.left);
				nextLevCnt++;
			}
			if (cur.right != null) {
				queue.add(cur.right);
				nextLevCnt++;
			}

			if (curLevCnt == 0) {
				curLevCnt = nextLevCnt;
				nextLevCnt = 0;
				ret.add(al);
				al = new ArrayList<Integer>();
			}
		}
		ArrayList<ArrayList<Integer>> ret2 = new ArrayList<ArrayList<Integer>>();
		for (int i = ret.size() - 1; i >= 0; i--) {
			ret2.add(ret.get(i));
		}
		return ret2;
	}

	/*
	 * 
	 * Construct Binary Tree from Inorder and Postorder Traversal
	 */

	public TreeNode buildTreeip(int[] inorder, int[] postorder) {
		int inStart = 0;
		int inEnd = inorder.length - 1;
		int postStart = 0;
		int postEnd = postorder.length - 1;

		return buildTree(inorder, inStart, inEnd, postorder, postStart, postEnd);
	}

	public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
			int[] postorder, int postStart, int postEnd) {
		if (inStart > inEnd || postStart > postEnd)
			return null;

		int rootValue = postorder[postEnd];
		TreeNode root = new TreeNode(rootValue);

		int k = 0;
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == rootValue) {
				k = i;
				break;
			}
		}

		root.left = buildTree(inorder, inStart, k - 1, postorder, postStart,
				postStart + k - (inStart + 1));
		// Becuase k is not the length, it it need to -(inStart+1) to get the
		// length
		root.right = buildTree(inorder, k + 1, inEnd, postorder, postStart + k
				- inStart, postEnd - 1);
		// postStart+k-inStart = postStart+k-(inStart+1) +1

		return root;
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null
				|| inorder.length != preorder.length)
			return null;
		int n = inorder.length;
		return buildTree(preorder, inorder, 0, n - 1, 0, n - 1);
	}

	public TreeNode buildTree(int[] preorder, int[] inorder, int s1, int e1,
			int s2, int e2) {
		if (s1 >= inorder.length || s2 >= inorder.length)
			return null;
		if (s1 == e1)
			return new TreeNode(preorder[s1]);
		if (s1 > e1 || s2 > e2)
			return null;
		int rootval = preorder[s1];
		TreeNode root = new TreeNode(rootval);
		int i;
		for (i = s2; i <= e2; i++) {
			if (inorder[i] == rootval)
				break;
		}
		int leftlength = i - s2;
		root.left = buildTree(preorder, inorder, s1 + 1, s1 + leftlength, s2,
				i - 1);
		root.right = buildTree(preorder, inorder, s1 + leftlength + 1, e1,
				i + 1, e2);
		return root;
	}

	/*
	 * Given a binary tree, find its maximum depth.
	 * 
	 * The maximum depth is the number of nodes along the longest path from the
	 * root node down to the farthest leaf node.
	 */

	int maxDepth(TreeNode r) {
		int depth = 0;
		Stack<TreeNode> wq = new Stack<TreeNode>();
		Stack<TreeNode> path = new Stack<TreeNode>();
		wq.push(r);
		while (!wq.empty()) {
			r = wq.peek();
			if (!path.empty() && r == path.peek()) {
				if (path.size() > depth)
					depth = path.size();
				path.pop();
				wq.pop();
			} else {
				path.push(r);
				if (r.right != null)
					wq.push(r.right);
				if (r.left != null)
					wq.push(r.left);
			}
		}
		return depth;
	}

	public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if (root == null)
			return list;

		Queue<TreeNode> queue = new LinkedList<TreeNode>();

		queue.offer(root);
		while (!queue.isEmpty()) {
			Queue<TreeNode> nextQueue = new LinkedList<TreeNode>();
			ArrayList<Integer> newList = new ArrayList<Integer>();
			while (!queue.isEmpty()) {
				TreeNode node = queue.poll();
				newList.add(node.value);

				if (node.left != null)
					nextQueue.offer(node.left);
				if (node.right != null)
					nextQueue.offer(node.right);
			}
			list.add(newList);
			queue.addAll(nextQueue);
			nextQueue = new LinkedList<TreeNode>();
		}
		return list;
	}

	/*
	 * 
	 * Symmetric Tree
	 * 
	 * Given a binary tree, check whether it is a mirror of itself (ie,
	 * symmetric around its center).
	 */

	public boolean isSymmetric(TreeNode root) {
		if (root == null)
			return true;
		LinkedList<TreeNode> l = new LinkedList<TreeNode>(), r = new LinkedList<TreeNode>();
		l.add(root.left);
		r.add(root.right);
		while (!l.isEmpty() && !r.isEmpty()) {
			TreeNode temp1 = l.poll(), temp2 = r.poll();
			if (temp1 == null && temp2 != null || temp1 != null	&& temp2 == null)
				return false;
			if (temp1 != null) {
				if (temp1.value != temp2.value)
					return false;
				l.add(temp1.left);
				l.add(temp1.right);
				r.add(temp2.right);
				r.add(temp2.left);
			}
		}
		return true;
	}

	/*
	 * 
	 * Binary Tree Zigzag Level Order Traversal Given a binary tree, return the
	 * zigzag level order traversal of its nodes' values. (ie, from left to
	 * right, then right to left for the next level and alternate between).
	 */

	public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		if (root != null)
			queue.add(root);
		int nodesLeft = 0;
		ArrayList<Integer> row = null;
		boolean reverse = true;
		while (!queue.isEmpty()) {
			if (nodesLeft == 0) {
				nodesLeft = queue.size();
				row = new ArrayList<Integer>();
				result.add(row);
				reverse = !reverse;
			}
			TreeNode node = null;
			if (!reverse)
				node = queue.pollFirst();
			else
				node = queue.pollLast();
			nodesLeft--;
			row.add(node.value);
			if (!reverse) {
				if (node.left != null)
					queue.addLast(node.left);
				if (node.right != null)
					queue.addLast(node.right);
			} else {
				if (node.right != null)
					queue.addFirst(node.right);
				if (node.left != null)
					queue.addFirst(node.left);
			}
		}
		return result;
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if (p != null && q != null) {
			if (p.value == q.value) {
				return isSameTree(p.left, q.left)
						&& isSameTree(q.right, p.right);
			}
		} else {
			return false;
		}
		return false;
	}

	public static boolean isValidBST(TreeNode root) {
		return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public static boolean validate(TreeNode root, int min, int max) {
		if (root == null) {
			return true;
		}

		// not in range
		if (root.value <= min || root.value >= max) {
			return false;
		}

		// left subtree must be < root.val && right subtree must be > root.val
		return validate(root.left, min, root.value)
				&& validate(root.right, root.value, max);
	}

	/*
	 * Recover Binary Search Tree Two elements of a binary search tree (BST) are
	 * swapped by mistake. Recover the tree without changing its structure.
	 */

	public void recoverTree(TreeNode root) {
		TreeNode node1 = null, node2 = null; // The two nodes to be swapped
		TreeNode p = root, pre = null; // pre points to in-order predecessor of
										// p when accessing p's value
		// Morris Traversal
		while (p != null) {
			// A reverse order is detected
			if (pre != null && pre.value > p.value) {
				if (node1 == null)
					node1 = pre;
				node2 = p; // Updated if the second reverse order is detected
			}

			// Use in-order predecessor's right child to cache the root if left
			// subtree is not empty
			// Go to left subtree first, recover the tree structure when back to
			// the root,
			// and then go to right subtree
			if (p.left == null) { // Empty left subtree
				pre = p;
				p = p.right;
			} else {
				// Find in-order predecessor of current node
				// Note: do not use pre since we don't know whether we will
				// access p's value at this stage
				TreeNode q = p.left;
				while (q.right != null && q.right != p)
					q = q.right;

				if (q.right == null) { // Its left subtree has not been
										// traversed; link it to its predecessor
					q.right = p;
					// p's value is not accessed at this stage; no need to
					// update pre
					p = p.left;
				} else { // Its left subtree has been traversed; recover tree
							// structure
					q.right = null;
					pre = p;
					p = p.right;
				}
			}
		}
		// Swapped nodes are found; swap them back
		if (node1 != null && node2 != null) {
			int temp = node1.value;
			node1.value = node2.value;
			node2.value = temp;
		}
	}

	/*
	 * LeetCode - Unique Binary Search Trees
	 * 
	 * Given n, how many structurally unique BST's (binary search trees) that
	 * store values
	 */
	public int numTrees(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;

		// table[i]: number of trees that stores 1...i
		int[] table = new int[n + 1];
		table[0] = table[1] = 1;

		// table[i] = sum{table[j]*table[i-j-1]}, 0<=j<i
		for (int i = 2; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				table[i] += table[j] * table[i - j - 1];
			}
		}

		return table[n];
	}

	/*
	 * LeetCode - Unique Binary Search Trees II
	 * 
	 * Given n, generate all structurally unique BST's (binary search trees)
	 * that store values 1...n.
	 */

	public ArrayList<TreeNode> recursiveGenerateTrees(int lower, int upper) {
		ArrayList<TreeNode> result = new ArrayList<TreeNode>();
		// Empty tree
		if (lower > upper) {
			result.add(null);
			return result;
		}

		// Take each value within the range as the root, generate all possible
		// left subtrees
		// and right subtrees, and make each combination of them
		for (int i = lower; i <= upper; i++) {
			ArrayList<TreeNode> leftSubtrees = recursiveGenerateTrees(lower,
					i - 1); // Left subtrees
			ArrayList<TreeNode> rightSubtrees = recursiveGenerateTrees(i + 1,
					upper); // right subtrees
			// Make each combination of the left subtrees, the right subtrees
			// and the root
			for (TreeNode leftSubtree : leftSubtrees) {
				for (TreeNode rightSubtree : rightSubtrees) {
					TreeNode root = new TreeNode(i);
					root.left = leftSubtree;
					root.right = rightSubtree;
					result.add(root);
				}
			}
		}

		return result;
	}

	/**
	 * Binary Tree Inorder Traversal
	 * 
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 * 
	 * For example: Given binary tree {1,#,2,3}, 1 \ 2 / 3 return [1,3,2].
	 * 
	 * Note: Recursive solution is trivial, could you do it iteratively?
	 * 
	 * confused what "{1,#,2,3}" means? > read more on how binary tree is
	 * serialized on OJ.
	 * 
	 * 
	 * OJ's Binary Tree Serialization: The serialization of a binary tree
	 * follows a level order traversal, where '#' signifies a path terminator
	 * where no node exists below.
	 * 
	 * Here's an example: 1 / \ 2 3 / 4 \ 5 The above binary tree is serialized
	 * as "{1,2,3,#,#,4,#,#,5}".
	 * 
	 */

	public ArrayList<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		rec(root, ret);
		return ret;
	}

	public void rec(TreeNode root, ArrayList<Integer> ret) {
		if (root == null)
			return;
		rec(root.left, ret);
		ret.add(root.value);
		rec(root.right, ret);
	}

}

final class TreeNode {
	public int value;
	public TreeNode left, right;

	public TreeNode(int v) {
		this.value = v;
	}
}

final class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}
