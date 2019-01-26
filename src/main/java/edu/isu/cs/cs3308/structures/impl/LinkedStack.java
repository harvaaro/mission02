package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Stack;

public class LinkedStack<E> implements Stack<E> {

	// create our DLL to use for the stack
	DoublyLinkedList<E> theList = new DoublyLinkedList<>();

	@Override
	public void push(E element) {
		theList.addFirst(element);
	}

	@Override
	public E peek() {
		return theList.first();
	}

	@Override
	public E pop() {
		return theList.removeFirst();
	}

	@Override
	public int size() {
		return theList.size();
	}

	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}

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

	@Override
	public void printStack() {
		theList.printList();
	}
}
