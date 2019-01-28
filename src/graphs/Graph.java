package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph<T> {
	
	Map<T, Set<T>> adjacencyMap;
	
	public Graph() {
		adjacencyMap = new HashMap<>();
	}
	
	public void addNode(T data) {
		if (!adjacencyMap.containsKey(data)) {
			adjacencyMap.put(data, new HashSet<>());
		}
	}
	
	public void addEdge(T source, T destination) {
		addNode(source);
		addNode(destination);
		
		adjacencyMap.get(source).add(destination);
	}
	
	public boolean isEmpty() {
		return this.adjacencyMap.isEmpty();
	}
	
	public boolean connected(T source, T destination) {
		if (this.isEmpty()) {
			return false;
		}
		
		if (!adjacencyMap.containsKey(source)) {
			return false;
		}
		
		boolean connected = false;
		T startingPoint = source;
		
		Queue<T> queue = new LinkedList<>();
		Set<T> visited = new HashSet<>();
		
		queue.add(startingPoint);		
		visited.add(startingPoint);

		while (!queue.isEmpty()) {
			T next = queue.remove();
			
			for(T neighbor : this.adjacencyMap.get(next)) {
				if (visited.contains(neighbor)) {
					continue;
				}
				
				if (neighbor.equals(destination)) {
					connected = true;
					break;
				} else {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
		
		return connected;
		
	}
}
