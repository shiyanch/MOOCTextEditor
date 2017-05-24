package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode(null);
		tail = new LLNode(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		add(size, element);		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> temp = head.next;
		while(index-->0)
			temp = temp.next;
		
		return temp.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null)
			throw new NullPointerException();
		
		if(index > size || (index < 0 && size > 0))
			throw new IndexOutOfBoundsException();
		
		LLNode<E> previous = head;
		LLNode<E> newNode = new LLNode(element);
		
		while(index-- > 0) {
			previous = previous.next;
		}
		
		LLNode<E> nextNode = previous.next;
		
		newNode.next = nextNode;
		newNode.prev = previous;
		previous.next = newNode;
		nextNode.prev = newNode;
		
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(size == 0 || index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> previous = head;
		while(index-- > 0) {
			previous = previous.next;
		}
		
		LLNode<E> temp = previous.next;
		LLNode<E> nextNode = previous.next.next;
		previous.next = nextNode;
		nextNode.prev = previous;
		temp.prev = temp.next = null;
		
		size--;
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null)
			throw new NullPointerException();
		
		if(size == 0 || index < 0 || index > size-1)
			throw new IndexOutOfBoundsException();
		
		LLNode<E> previous = head;
		while(index-- > 0) {
			previous = previous.next;
		}
		
		E last = previous.next.data;
		previous.next.data = element;
		
		return last;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
