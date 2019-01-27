package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Stack;

/**
 * A linked stack that is based off of a Doubly Linked List
 *
 * @author Aaron Harvey
 * @param <E> any type of stack
 */
public class LinkedStack<E> implements Stack<E> {

	// create our DLL to use for the stack
	DoublyLinkedList<E> theList = new DoublyLinkedList<>();

	/**
	 * Add a new element onto the stack at the beginning
	 * @param element data to add to top, unless it is null
	 */
	@Override
	public void push(E element) {
		theList.addFirst(element);
	}

	/**
	 * See the value at the beginning without removing it
	 * @return The value at the beginning of the stack
	 */
	@Override
	public E peek() {
		return theList.first();
	}

	/**
	 * Remove the value at the beginning of the stack
	 * @return The value at the beginning of the stack
	 */
	@Override
	public E pop() {
		return theList.removeFirst();
	}

	/**
	 * Get how many elements are in the stack
	 * @return Number of elements in stack
	 */
	@Override
	public int size() {
		return theList.size();
	}

	/**
	 * Determine if the stack list is empty or not
	 * @return True if the stack list is empty, else is false
	 */
	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}

	/**
	 * Transfer all the data from one stack to another,
	 * this also reverses the order of the elements
	 * @param to The stack to transfer to unless it is null
	 */
	@Override
	public void transfer(Stack<E> to) {
		// if the stack is not null or empty
		if (to != null && this.size() > 0) {
			// loop until all elements are transferred
			while(this.size() > 0) {
				to.push(this.pop());
			}
		}
	}

	/**
	 *Reverse the order of the elements of the current stack
	 */
	@Override
	public void reverse() {
		// create 2 temporary stacks to use for reversing
		LinkedStack<E> temp1 = new LinkedStack<>();
		LinkedStack<E> temp2 = new LinkedStack<>();

		// make reverse order in temp1
		this.transfer(temp1);

		// make normal order in temp2
		temp1.transfer(temp2);

		// send back to original as reversed
		temp2.transfer(this);
	}

	/**
	 * Copy the other stack to the end of the this stack.
	 * @param other Stack whose contents are to be merged
	 * onto the bottom of this stack.
	 */
	@Override
	public void merge(Stack<E> other) {
		if (other != null) {
			// temporary stacks used for copying from
			LinkedStack<E> origCopy = new LinkedStack<>();
			LinkedStack<E> otherCopy = new LinkedStack<>();

			// transfer the 2 stacks for copying from
			this.transfer(origCopy);
			other.transfer(otherCopy);

			// copy the stack to merge to other and this
			while (otherCopy.size() > 0) {
				// get the element to merge
				E tempElem = otherCopy.pop();

				// push that element to each stack
				other.push(tempElem);
				this.push(tempElem);
			}

			// now transfer the rest of the original stack
			origCopy.transfer(this);
		}
	}

	/**
	 * Prints out the content of the stack list
	 */
	@Override
	public void printStack() {
		theList.printList();
	}
}
