package edu.isu.cs.cs3308.structures;

/**
 * Node2 class needed for the List
 * based on code shown by Isaac Griffith in class
 *
 * @author Aaron Harvey
 * @param <E> any type of node
 */
public class Node2<E> {

	// Data stored in the node.
	private E data;

	// Stores what the prev node is.
	private Node2<E> prev;

	// Stores what the next node is.
	private Node2<E> next;

	/**
	 * Constructor with data parameter
	 * @param data Whatever data the Node2 should store
	 */
	public Node2(E data) {
		this.data = data;
	}

	// The following code I created with the IntelliJ Generator
	// I also added comments on them for completeness.

	/**
	 * Get what data is stored in the Node2
	 * @return The data the node currently is storing
	 */
	public E getData() {
		return data;
	}

	/**
	 * Set what data should be stored in the Node2
	 * @param data The data the node should be storing
	 */
	public void setData(E data) {
		this.data = data;
	}


	/**
	 * Get what Node2 is stored as the prev in the List.
	 * @return The Node2 that is currently stored in the prev attribute
	 */
	public Node2<E> getPrev() {
		return prev;
	}

	/**
	 * Set what Node2 should be stored as the prev in the List.
	 * @param prev The Node2 that should be the previous to current node
	 */
	public void setPrev(Node2<E> prev) {
		this.prev = prev;
	}

	/**
	 * Get what Node2 is stored as the next in the List.
	 * @return The Node2 that is currently stored in the next attribute
	 */
	public Node2<E> getNext() {
		return next;
	}

	/**
	 * Set what Node2 should be stored as the next in the List.
	 * @param next The Node2 that should be the next to the current node
	 */
	public void setNext(Node2<E> next) {
		this.next = next;
	}
}
