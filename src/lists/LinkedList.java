package lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LinkedListNode<T> {
	T data;
	LinkedListNode<T> next;
	
	public LinkedListNode(T data, LinkedListNode<T> next) {
		this.data = data;
		this.next = next;
	}
	
	public LinkedListNode(T data) {
		this(data, null);
	}
	
}

public class LinkedList<T> {
	private LinkedListNode<T> head;
	private LinkedListNode<T> tail;
	
	private int size;
	 
	public LinkedList() {
		this.head = null;
		this.tail = null;
		
		this.size = 0;
	}
	
	public void addRear(T element) {
		LinkedListNode<T> newNode = new LinkedListNode<T>(element);
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			this.tail.next = newNode;
			this.tail = newNode;
		}
		
		this.size++;
	}
	
	public void addFront(T element) {
		LinkedListNode<T> newNode = new LinkedListNode<T>(element);
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		} else {
			newNode.next = this.head;
			this.head = newNode;
		}
		
		this.size++;
	}
	
	public int size() {
		return this.size;
	}
	
	public void print() {
		LinkedListNode<T> current = this.head;
		while (current != null) {
			System.out.print(current.data);
			if (current.next != null) {
				System.out.print(", ");
			}
			
			current = current.next;
		}
		System.out.println();
	}
	
	public void removeDups() {
		if (this.head == null) {
			return;
		}
		
		Set<T> items = new HashSet<>();
		LinkedListNode<T> current = this.head;
		items.add(current.data);
		
		while (current.next != null) {
			if (!items.add(current.next.data)) {
				current.next = current.next.next;
			} else {
				current = current.next;
			}
		}
	}
	
	public T returnKToLastWithSize(int k) {
		int hops = this.size() - k;
		LinkedListNode<T> current = this.head;
		for (int i = 0; i < hops; i++) {
			current = current.next;
		}
		
		return current.data;
	}
	
	public void merge(LinkedList<T> rhs) {
		if (rhs.size() > 0) {
			this.tail.next = rhs.head;
			this.tail = rhs.tail;
		}
	}
	
	public LinkedList<T> reverse() {
		LinkedList<T> reversed = new LinkedList<>();
		LinkedListNode<T> current = this.head;
		while (current != null) {
			reversed.addFront(current.data);
			current = current.next;
		}
		
		return reversed;
	}
	
	public String serialize() {
		StringBuilder builder = new StringBuilder();
		
		LinkedListNode<T> current = this.head;
		while (current != null) {
			builder.append(current.data.toString());

			current = current.next;
		}
		
		return builder.toString();
	}
	
	public static LinkedList<Character> fromString(String str) {
		LinkedList<Character> list = new LinkedList<Character>();
		for (Character ch : str.toCharArray()) {
			list.addRear(ch);
		}
		
		return list;
	}
	
	public static LinkedList<Integer> partition(LinkedList<Integer> list, int pivot) {
		LinkedList<Integer> smaller = new LinkedList<>();
		LinkedList<Integer> bigger = new LinkedList<>();
		
		LinkedListNode<Integer> current = list.head;
		
		while(current != null) {
			int diff  = current.data - pivot;
			if (diff < 0) {
				smaller.addRear(current.data);
			} else if (diff == 0){
				bigger.addFront(current.data);
			} else {
				bigger.addRear(current.data);
			}
			
			current = current.next;
		}
		
		smaller.merge(bigger);
		return smaller;
		
	}
	
	public static LinkedList<Integer> sum(LinkedList<Integer> lhs, LinkedList<Integer> rhs) {
		LinkedList<Integer> sum = new LinkedList<>();
		
		boolean hasSupplement = false;
		
		LinkedListNode<Integer> rhsCurrent = rhs.head;
		LinkedListNode<Integer> lhsCurrent = lhs.head;
		while (rhsCurrent != null || lhsCurrent != null) {
			
			int lhsData = lhsCurrent != null ? lhsCurrent.data : 0;
			int rhsData = rhsCurrent != null ? rhsCurrent.data : 0;
						
			int digitSum = lhsData + rhsData;
			if (hasSupplement) {
				digitSum++;
			}
			
			int nodeData = digitSum % 10;
			sum.addRear(nodeData);
			
			hasSupplement = digitSum >= 10;

			lhsCurrent = lhsCurrent != null ? lhsCurrent.next : null;
			rhsCurrent = rhsCurrent != null ? rhsCurrent.next : null;
		}
		
		if (hasSupplement) {
			sum.addRear(1);
		}
		return sum;
		
	}
	
	public static<T> boolean isPalindrome(LinkedList<T> list) {
		LinkedList<T> reverse = list.reverse();
		
		LinkedListNode<T> runner1 = list.head;
		LinkedListNode<T> runner2 = reverse.head;
		
		while (runner1 != null) {
			if (!runner1.data.equals(runner2.data)) {
				return false;
			}
			
			runner1 = runner1.next;
			runner2 = runner2.next;
		}
		
		return true;
	}
	 
	
	public static void task2_1() {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i : new int [] {1,2,2, 1, 3, 3, 4,5,}) {
			list.addRear(i);
		}
		System.out.println("Remove dups. Before:");
		list.print();
		list.removeDups();
		System.out.println("After: ");
		list.print();
		System.out.println("Remove dups Finish:");
	}
	
	public static void task2_2() {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 24; i++) {
			list.addRear(i+1);
		}		
		
		System.out.println("Find kth to last (3). List is");
		list.print();
		int kth = list.returnKToLastWithSize(3);
		System.out.println(kth);
		System.out.println("Find kth to last (3). Finish");
	}
	
	public static void task2_4() {
		Integer [] data = new Integer [] {1,2,3,4,5,6,7,8,9,10};
		List<Integer> list = Arrays.asList(data);
		Collections.shuffle(list);
		LinkedList<Integer> linkedList = new LinkedList<>();
		for (Integer integer : list) {
			linkedList.addRear(integer);
		}
		
		System.out.println("Partition. List is");
		linkedList.print();
		LinkedList<Integer> partition = LinkedList.partition(linkedList, 5);
		partition.print();
		System.out.println("Partition. Finished");
	}
	
	public static void task2_5() {
		System.out.println("Sum lists ");
		int num1 = 123456;
		int num2 = 34572;
		System.out.println(num1 + " + " + num2 + " = " + (num1 + num2));
		
		LinkedList<Integer> list1 = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		
		for (char ch : String.valueOf(num1).toCharArray()) {
			list1.addFront(Integer.valueOf(String.valueOf(ch)));
		}
		
		for (char ch : String.valueOf(num2).toCharArray()) {
			list2.addFront(Integer.valueOf(String.valueOf(ch)));
		}
		
		System.out.println("Print " + list1.serialize() + " with " + list2.serialize());
		
		LinkedList<Integer> sum = LinkedList.sum(list1, list2);
		System.out.println(sum.reverse().serialize());
		System.out.println("Sum lists finished");
	}

	public static void task2_6() {
		System.out.println("Palindrome ");
		LinkedList<Character> list1 = LinkedList.fromString("helloolleh");
		System.out.println(list1.serialize() + "is palindrome? " + LinkedList.isPalindrome(list1));
		
		list1 = LinkedList.fromString("asfgkjdlskb");
		System.out.println(list1.serialize() + "is palindrome? " + LinkedList.isPalindrome(list1));
		
		System.out.println("Palindrome finished");

	}
	public static void main(String[] args) {
		//task2_1();
		//task2_2();
		//task2_4();
		//task2_5();
		task2_6();
	}
}
