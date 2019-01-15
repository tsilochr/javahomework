package stacks;

public interface IStack<T> {
	
	public void push(T element);
	public T peek();
	public T pop();
	public int size();

}
