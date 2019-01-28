package graphs;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class BinaryTree<T> {
	private BinaryTreeNode<T> root;
	
	public BinaryTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public Map<Integer, LinkedList<T>> depthNodes() {
		Map<Integer, LinkedList<T>> depthMap = new HashMap<>();
		if(isEmpty()) {
			return depthMap;
		}
		
		
		Queue<Map.Entry<Integer, BinaryTreeNode<T>>> queue = new LinkedList<>();
		
		queue.add(new AbstractMap.SimpleEntry<Integer, BinaryTreeNode<T>>(0, root));
		
		while (!queue.isEmpty()) {
			Entry<Integer, BinaryTreeNode<T>> nextEntry = queue.remove();
			int depth = nextEntry.getKey();
			BinaryTreeNode<T> node = nextEntry.getValue();
			addToDepthMap(depthMap, depth, node.data);
			
			queue.add(new AbstractMap.SimpleEntry<Integer, BinaryTreeNode<T>>(depth+1, node.left));
			queue.add(new AbstractMap.SimpleEntry<Integer, BinaryTreeNode<T>>(depth+1, node.right));
			
		}
		
		return depthMap;
	}

	private void addToDepthMap(Map<Integer, LinkedList<T>> depthMap, int i, T data) {
		if (!depthMap.containsKey(i)) {
			depthMap.put(i, new LinkedList<T>());
		}
		
		depthMap.get(i).add(data);
	}
}
