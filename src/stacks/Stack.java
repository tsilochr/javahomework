package stacks;

import java.util.Arrays;

public class Stack<T> implements IStack<T> {
	private static final int DEFAULT_SIZE = 10;
	
	private T [] data;
	private int index;
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public Stack() {
		data = (T [])new Object[DEFAULT_SIZE];
		index = -1;
		size = 0;
	}
	
	public void push(T element) {
		if (size == data.length) {
			data = Arrays.copyOf(data, data.length*2);
		}
		
		data[++index] = element;
		size++;
	}
	
	public T pop() {
		if (size == 0) {
			throw new IllegalStateException("Empty");
		}
		
		T retVal = this.data[index];
		this.data[index] = null;
		index--;
		size--;
		
		return retVal;
	}
	
	public T peek() {
		if (size == 0) {
			throw new IllegalStateException("Empty");
		}
		
		return this.data[index];
	}
	
	public int size() {
		return this.size;
	}
	
	public static Stack<Integer> sort(Stack<Integer> s) {
		Stack<Integer> retval = new Stack<>();
		while (s.size() > 0) {
			int nextVal = s.pop();
			
			int pushed = 0;
			while (retval.size() > 0 && retval.peek() > nextVal) {
				s.push(retval.pop());
				pushed++;
			}
			
			retval.push(nextVal);
			for (int i = 0; i < pushed; i++) {
				retval.push(s.pop());
			}
		}
		return retval;
	}
	
	public static void main(String[] args) {
		int [] data = new int[] {4,3,1,6,4,7,9,8,3,1,2};
		Stack<Integer> s = new Stack<Integer>();
		for (int i : data) {
			s.push(i);
		}
		
		Stack<Integer> sorted = Stack.sort(s);
		while (sorted.size() > 0) {
			System.out.println(sorted.pop());
		}
		
	}
}
