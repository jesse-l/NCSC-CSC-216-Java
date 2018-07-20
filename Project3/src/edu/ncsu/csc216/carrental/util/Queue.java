package edu.ncsu.csc216.carrental.util;

import java.util.NoSuchElementException;

/**
 * This class is used to create a queue linked list to hold data for the car
 * rental system. The Queue will be handling data of type E.
 * 
 * @author Jesse Liddle - jaliddl2
 *
 * @param <E>
 *            This is the type of data the Queue will be dealing with.
 */
public class Queue<E> implements SimpleQueue<E> {
	/** The first node in the linked list queue. */
	Node front;
	/** The last node in the linked list queue. */
	Node rear;

	/**
	 * Constructor for the Queue class used to create the start of a queue. Sets
	 * both the front and rear nodes to null.
	 */
	public Queue() {
		front = null;
		rear = null;
	}

	/**
	 * This method is used to add an item to the linked list at the very end of
	 * the list.
	 * 
	 * @param item
	 *            This is the item being added to the list.
	 */
	@Override
	public void add(E item) {
		if (front == null)
			front = new Node(item, front);
		else if (rear == null) {
			rear = new Node(item, null);
			front.link = rear;
		} else {
			rear.link = new Node(item, null);
			rear = rear.link;
		}
	}

	/**
	 * This method is used to remove the last element from the linked list.
	 * 
	 * @return Returns the elements data from the end of the linked list.
	 */
	@Override
	public E remove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E removedItem = front.data;
			front = front.link;
			if (front == null)
				rear = null;
			return removedItem;
		}

	}

	/**
	 * This method is used to get a "peek" at the first elements data in the
	 * linked list.
	 * 
	 * @return Returns the data from the first element of the list.
	 */
	@Override
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else
			return front.data;
	}

	/**
	 * This method is used to see if the list is empty or not.
	 * 
	 * @return Returns true if the list is empty or false if it isn't.
	 */
	@Override
	public boolean isEmpty() {
		return front == null;
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
