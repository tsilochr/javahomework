package dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class PossibleSteps {
	public static void main(String[] args) {
		int ladderLength = 10;
		int possibleHops = possibleHops(ladderLength);
		System.out.println(possibleHops);
		
		possibleHops = possibleHopsRecursiveMemory(ladderLength, new HashMap<Integer, Integer>());
		System.out.println(possibleHops);
	}

	private static int possibleHops(int ladderLength) {
		return possibleHopsRecursive(ladderLength);
	}

	private static int possibleHopsRecursive(int ladderLength) {
		int possibleHops = 0;
		if (ladderLength >= 3) {
			possibleHops += 1 + possibleHopsRecursive(ladderLength - 3);
		}
		
		if (ladderLength >= 2) {
			possibleHops += 1 + possibleHopsRecursive(ladderLength - 1);
		}
		
		if (ladderLength >= 1) {
			possibleHops += 1 + possibleHopsRecursive(ladderLength - 1);
		}
		
		return possibleHops;
	}
	
	private static int possibleHopsRecursiveMemory(int ladderLength, Map<Integer, Integer> cache) {
		if (cache.containsKey(ladderLength)) {
			return cache.get(ladderLength);
		}
		
		int possibleHops = 0;
		if (ladderLength >= 3) {
			possibleHops += 1 + possibleHopsRecursiveMemory(ladderLength - 3, cache);
		}
		
		if (ladderLength >= 2) {
			possibleHops += 1 + possibleHopsRecursiveMemory(ladderLength - 1, cache);
		}
		
		if (ladderLength >= 1) {
			possibleHops += 1 + possibleHopsRecursiveMemory(ladderLength - 1, cache);
		}
		
		cache.put(ladderLength, possibleHops);
		return possibleHops;
	}

}
