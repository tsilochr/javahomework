package graphs;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T> {
	
	private BinaryTreeNode<T> root = null;
	
	
	public List<List<T>> possibleArrays() {
		return possibleArrays(root);
	}

	private List<List<T>> possibleArrays(BinaryTreeNode<T> node) {
		List<List<T>> list = new ArrayList<>();
		if (node == null) {
			return list;
		}
		
		List<List<T>> subtreeArrays = possibleArrays(node.left);
		subtreeArrays.addAll(possibleArrays(node.right));
		
		for (List<T> subtreeArray : subtreeArrays) {
			ArrayList<T> thisNodeList = new ArrayList<T>();
			thisNodeList.add(node.data);
			thisNodeList.addAll(subtreeArray);
			list.add(thisNodeList);
		}		
		
		return list;
	}

}
