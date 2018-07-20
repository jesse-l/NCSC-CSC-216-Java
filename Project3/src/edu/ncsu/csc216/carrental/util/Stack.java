package edu.ncsu.csc216.carrental.util;

import java.util.EmptyStackException;

/**
 * This class is used to create a stacked linked list. This means the list will
 * be a first in last out list.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 * @param <E>
 *            This is the type of data the stack will be handling.
 */
public class Stack<E> implements SimpleStack<E> {
	/** This is the node at the top of the stack. */
	private Node top;

	/**
	 * This is the constructor for the linked list. It sets the top node to
	 * null.
	 */
	public Stack() {
		top = null;
	}

	/**
	 * This method is used to see if the stack is empty.
	 * 
	 * @return True if the list is empty otherwise returns false.
	 */
	@Override
	public boolean isEmpty() {
		return top == null;
	}

	/**
	 * This method is used to "peek" at the top elements data.
	 * 
	 * @return Returns the data from the top node without removing it.
	 */
	@Override
	public E peek() {
		if (isEmpty())
			throw new EmptyStackException();
		else
			return top.data;
	}

	/**
	 * Removes the top of the stack and returns it.
	 * 
	 * @return Returns the top item off the stack.
	 */
	@Override
	public E pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			E data = top.data;
			if (top.link != null)
				top = top.link;
			else
				top = null;

			return data;
		}
	}

	/**
	 * Pushes new data to the top of the stack.
	 * 
	 * @param item
	 *            This is the item being added to the top of the stack.
	 */
	@Override
	public void push(E item) {
		if (top == null)
			top = new Node(item, top);
		else
			top = new Node(item, top);
	}

	/**
	 * This is a private inner class used to assist in the creation of the
	 * linked list. This class creates the nodes for the list.
	 * 
	 * @author Jesse Liddle - jaliddl2
	 *
	 */
	private class Node {
		/** This is the data that the node is storing. */
		public E data;
		/** This is the link between the two nodes. */
		public Node link;

		/**
		 * This is the constructor for the Node object.
		 * 
		 * @param data
		 *            This is the object being stored inside the node.
		 * @param link
		 *            This is the link to the next node.
		 */
		public Node(E data, Node link) {
			this.data = data;
			this.link = link;
		}
	}

}
