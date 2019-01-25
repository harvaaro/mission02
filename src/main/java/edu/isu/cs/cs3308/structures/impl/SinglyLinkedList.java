package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;
import edu.isu.cs.cs3308.structures.Node;

/**
 * A class to implement a singly linked list based off the List class
 *
 * @author Aaron Harvey
 * @param <E> any type of list
 */
public class SinglyLinkedList<E> implements List<E> {

	// Head node for the List
	protected Node<E> head = null;

	// Tail node for the List
	protected Node<E> tail = null;

	// Count of the nodes in the List
	protected int size = 0;

	/**
	 * Checks to see if a given element is null or not
	 * @param element the element to check
	 * @return true if not null, and false if null
	 */
	private boolean checkElement(E element) {
	    // may eventually throw an error or some message
        // but currently am just doing an error check
	    return (element != null) ? true : false;
    }

	/**
	 * Checks to see if a given index is within 0 and size
	 * @param index the index to check
	 * @return true if valid index, and false if invalid
	 */
	private boolean checkIndex(int index) {
	    return (index < size && index >= 0) ? true : false;
    }

	/**
	 * Used to fix the head and tail of the list
	 * If a single node remains in the list, then head and tail are set to it
	 * If the size of the list is 0 then set head and tail to null
	 * @param theNode The node to set if its the only one in the list
	 */
	private void singleHeadTail(Node<E> theNode) {
		// if theNode is the only one in the list
		if (size == 1) {
			head = theNode;
			tail = theNode;
		}
		// else there are no nodes in the list
		else if (size == 0) {
			head = null;
			tail = null;
		}
	}

	/**
	 * Get a node from the list given a specific index
	 * @param index The index within the list
	 * @return The node retrieved from the list
	 */
    private Node<E> getNode(int index) {
		// get current head node to start from
		Node<E> seekNode = head;

		// seek through the list starting from the head
		for (int i = 0; i < index; i++) {
			seekNode = seekNode.getNext();
		}

		// return the desired Node from the list index
		return seekNode;
	}

	/**
	 * Adds 1 to the size value.
	 * I put this as a function if I ever needed to do other checks with it
	 */
	private void addSize() {
		size++;
	}

	/**
	 * Subtracts 1 from the size value, and ensure it cannot go below 0
	 */
	private void subSize() {
		size--;

		if (size < 0) {
			size = 0;
		}
	}

	/**
	 * To get the data from the first node in the list
	 * @return The data within the head node, if none then null
	 */
	@Override
	public E first() {
	    return (head != null) ? head.getData() : null;
	}

	/**
	 * To get the data from the last node in the list
	 * @return The data within the tail node, if none then null
	 */
	@Override
	public E last() {
		return (tail != null) ? tail.getData() : null;
	}

	/**
	 * Creates a node with the given element data to the end of the list
	 * @param element Data to store in the last node
	 */
	@Override
	public void addLast(E element) {
	    // check if the element is not null
        if (checkElement(element)) {
        	// check if the size is at least 1
        	if (!isEmpty()) {
				// create the new node
				Node<E> lastNode = new Node<>(element);

				// set its next to be null
				lastNode.setNext(null);

				// make the original tail next to the tail to be
				tail.setNext(lastNode);

				// change the tail to the new node
				tail = lastNode;

				// increment size
				addSize();
			}
        	// else if size is 0 just add first
        	else {
        		addFirst(element);
			}
        }
	}

	/**
	 * Creates a node with the given element data to the beginning of the list
	 * @param element Data to store in the first node
	 */
	@Override
	public void addFirst(E element) {
        // check if the element is not null
	    if (checkElement(element)) {
			// create the new node
			Node<E> firstNode = new Node<>(element);

			// if there is more than one node in the list
	    	if (size > 0) {
				// make the next of the new node to the original head
				firstNode.setNext(head);
			}
	    	// else this is the only node in the list
	    	else {
	    		// there are no other nodes so just have it as null
	    		firstNode.setNext(null);
			}

			// change the head to the new node
			head = firstNode;

			// increment size
			addSize();

			// fix the head and tail if single node in list
			singleHeadTail(firstNode);
        }
	}

	/**
	 * Removes the first node in the list
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E removeFirst() {
		// if the head Node is not null
		if (head != null) {
			// if there are more than 1 nodes in the list
			if (size > 1) {

				// keep track of the original head
				Node<E> removeNode = head;

				// set the new head to next of original
				head = removeNode.getNext();

				// remove next of original head
				removeNode.setNext(null);

				// decrement size
				subSize();

				// fix the head and tail if single node in list
				singleHeadTail(head);

				// return the original head data
				return removeNode.getData();
			}
			// else there is only one node in the list to remove
			else {
				// make a temp of the current head node
				Node<E> removeNode = head;

				// set the list head and tail to null
				head.setNext(null);
				head = null;
				tail.setNext(null);
				tail = null;

				// return the data
				return removeNode.getData();
			}
		}

		// else there is no head Node
		else {
			return null;
		}
	}

	/**
	 * Removes the last node in the list
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E removeLast() {
		// because of how remove works, check if there are at least 2 nodes
		if (size > 1) {
			return remove(size - 1);
		}

		// else just remove the first node
		else {
			return removeFirst();
		}
	}

	/**
	 * Create a node with the given element data, and insert that node
	 * into the list at the given index
	 * @param element Data to store in the node
	 * @param index Where in the list the node should be inserted
	 */
	@Override
	public void insert(E element, int index) {
	    // if the element is not null
	    if (checkElement(element)) {
			// if the index is the head
			if (index == 0) {
				addFirst(element);
			}

			// else if the index is the tail
			else if (index >= size) {
				addLast(element);
			}

			// else the index is some other node
			else {
				// check if the index is a usable value
				if (checkIndex(index)) {
					// get the node before the one to be added
					Node<E> prevNode = getNode(index-1);

					// create the node to be inserted
					Node<E> insertNode = new Node<>(element);

					// set the correct next for the new node
					insertNode.setNext(prevNode.getNext());

					// set the correct next for the previous node
					prevNode.setNext(insertNode);

					// increment size
					addSize();
				}
			}
        }
	}

	/**
	 * Removes a given node from the list based on a given index
	 * @param index The index of the node in the list to remove
	 * @return Data stored within the node that is to be removed
	 */
	@Override
	public E remove(int index) {
		// check if the index is a usable value
		if (checkIndex(index)) {
			// if the index is the head
			if (index == 0) {
				return removeFirst();
			}

			// else the index is some other node
			else {
				// get the node before the one to be removed
				Node<E> prevNode = getNode(index-1);

				// get the node that will be removed
				Node<E> removeNode = prevNode.getNext();

				// set the new connection with the node removed
				prevNode.setNext(removeNode.getNext());

				// null out the next of the removeNode
				removeNode.setNext(null);

				// decrement size
				subSize();

				// fix the head and tail if single node in list
				singleHeadTail(prevNode);

				// return the removed Node data
				return removeNode.getData();
			}
		}

		// if the index is invalid
		else {
			return null;
		}
	}

	/**
	 * Get the data within a node from the list at the given index
	 * @param index The index of the node in the list to retrieve
	 * @return Data stored within the node that is to be retrieved
	 */
	@Override
	public E get(int index) {
		// check if the index is a usable value
		if (checkIndex(index)) {
			// if the index is the head
			if (index == 0) {
				return head.getData();
			}

			// else if the index is the tail
			else if (index == size-1) {
				return tail.getData();
			}

			// else the index is some other node
			else {
				return getNode(index).getData();
			}
		}

		// if the index is invalid
		else {
			return null;
		}
	}

	/**
	 * The stored value which contains the number of nodes within the list
	 * @return The count of nodes in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Determines if the list is empty or not
	 * @return True if the list is empty, and false if size does not equal 0
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0) ? true : false;
	}

	/**
	 * For each node in the list, print out its index on a new line
	 */
	@Override
	public void printList() {
		// if there are nodes in the list
		if (!isEmpty()) {
			// get the head as the starting point
			Node<E> printNode = head;

			// iterate though the list, until we reach the end
			for (int i = 0; i < size; i++) {
				// print the current nodes data on a new line
				System.out.println(printNode.getData());

				// then get the next node to get data from
				printNode = printNode.getNext();
			}
		}
		// else there are no nodes in the list
		else {
			System.out.println("There is nothing in this list.");
		}
	}
}
