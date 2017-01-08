import java.util.*;

public class MyGraph {

	public void dfs(Node root) {
		// Avoid infinite loops
		if (root == null)
			return;
		System.out.print(root.getVertex() + "\t");
		root.state = State.Visited;
		// for every child
		for (Node n : root.getChild()) {
			// if childs state is not visited then recurse
			if (n.state == State.Unvisited) {
				dfs(n);
			}
		}
	}

	public void bfs(Node root) {
		// Since queue is a interface
		java.util.LinkedList<Node> queue = new java.util.LinkedList<Node>();

		if (root == null)
			return;

		root.state = State.Visited;
		// Adds to end of queue
		queue.add(root);

		while (!queue.isEmpty()) {
			// removes from front of queue
			Node r = queue.remove();
			System.out.print(r.getVertex() + "\t");

			// Visit child first before grandchild
			for (Node n : r.getChild()) {
				if (n.state == State.Unvisited) {
					queue.add(n);
					n.state = State.Visited;
				}
			}
		}
	}

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if (node == null)
			return null;

		java.util.LinkedList<UndirectedGraphNode> queue = new java.util.LinkedList<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);

		queue.add(node);
		map.put(node, newHead);

		while (!queue.isEmpty()) {
			UndirectedGraphNode curr = queue.pop();
			ArrayList<UndirectedGraphNode> currNeighbors = new ArrayList<UndirectedGraphNode>(
					curr.neighbors);

			for (UndirectedGraphNode aNeighbor : currNeighbors) {
				if (!map.containsKey(aNeighbor)) {
					UndirectedGraphNode copy = new UndirectedGraphNode(
							aNeighbor.label);
					map.put(aNeighbor, copy);
					map.get(curr).neighbors.add(copy);
					queue.add(aNeighbor);
				} else {
					map.get(curr).neighbors.add(map.get(aNeighbor));
				}
			}

		}
		return newHead;
	}
}

enum State {
	Unvisited, Visiting, Visited;
}

class Node {
	public Node[] child;
	public int childCount;
	private String vertex;
	public State state;

	public Node(String vertex) {
		this.vertex = vertex;
	}

	public Node(String vertex, int childlen) {
		this.vertex = vertex;
		childCount = 0;
		child = new Node[childlen];
	}

	public void addChildNode(Node adj) {
		adj.state = State.Unvisited;
		if (childCount < 30) {
			this.child[childCount] = adj;
			childCount++;
		}
	}

	public Node[] getChild() {
		return child;
	}

	public String getVertex() {
		return vertex;
	}
}

class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
}