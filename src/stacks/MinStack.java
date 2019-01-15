package stacks;

public class MinStack implements IStack<Integer>{
	
	private Stack<Integer> data;
	private Stack<Integer> min;
	
	public MinStack() {
		data = new Stack<Integer>();
		min = new Stack<Integer>();
	}


	@Override
	public void push(Integer element) {
		data.push(element);
		if (data.size() == 1) {
			min.push(element);
		} else if (element <= min.peek()) {
			min.push(element);
		}		
	}

	@Override
	public Integer peek() {
		return data.peek();
	}

	@Override
	public Integer pop() {
		Integer retval = data.pop();
		if (retval == min.peek()) {
			min.pop();
		}
		
		return retval;
	}

	@Override
	public int size() {
		return data.size();
	}
	
	public int min() {
		return min.peek();
	}
	
	public static void main(String[] args) {
		int [] numbers = new int [] {3,4,5,2,1};
		MinStack minStack = new MinStack();
		for (int i : numbers) {
			minStack.push(i);
			System.out.println("Added " + i + ", min is " + minStack.min());
		}
		
		while(minStack.size() > 1) {
			int popped = minStack.pop();
			System.out.println("Popped " + popped + ", min is " + minStack.min());

		}
	}


}
