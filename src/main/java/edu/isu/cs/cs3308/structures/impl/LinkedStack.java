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

	}

	@Override
	public void reverse() {

	}

	@Override
	public void merge(Stack<E> other) {

	}

	@Override
	public void printStack() {
		theList.printList();
	}
}
