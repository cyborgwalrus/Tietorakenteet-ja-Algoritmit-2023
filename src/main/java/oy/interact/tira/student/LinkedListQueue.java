package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class LinkedListQueue<E> implements QueueInterface<E> {

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;

    private int count;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    @Override
    public int capacity() {
        return this.count;
    }

    /**
     * Add an element to the queue.
     * 
     * @param element The element to add, must not be null.
     * @throws QueueAllocationException If the reallocation for the queue failed in
     *                                  case queue needs reallocation.
     * @throws NullPointerException     If the element is null.
     */
    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null)
            throw new NullPointerException();
        if (this.count == 0) {
            this.head = new Node<E>(element);
            this.tail = this.head;
        }
        this.tail.next = new Node<E>(element);
        this.tail.previous = this.tail;
        this.tail = this.tail.next;
        this.count++;

    }

    /**
     * Removes an element from the queue.
     * 
     * @return The element from the head of the queue.
     * @throws QueueIsEmptyException If the queue is empty.
     */
    @Override
    public E dequeue() throws IllegalStateException {
        if (this.count == 0)
            throw new IllegalStateException();

        E x = (E) this.head.data;
        this.head = this.head.next;
        this.count--;
        return x;

    }

    /**
     * Returns the element at the head of the queue, not removing it from the queue.
     * 
     * @return The element in the head of the queue.
     * @throws QueueIsEmptyException If the queue is empty.
     */
    @Override
    public E element() throws IllegalStateException {
        if (this.count == 0)
            throw new IllegalStateException();
        return (E) this.head.data;
    }

    /**
     * Returns the count of elements currently in the queue.
     * 
     * @return Count of elements in the queue.
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Can be used to check if the queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (this.count == 0)
            return true;
        return false;
    }

    /**
     * Resets the queue to empty state, removing the objects.
     */
    @Override
    public void clear() {
        this.tail = null;
        this.head = null;
        this.count = 0;
    }

    public String toString() {
        if (this.count == 0)
            return "[]";
        StringBuilder str = new StringBuilder();

        str.append("[");
        Node<E> headPtr = this.head;
        while (headPtr != null) {
            str.append(headPtr.data.toString());
            str.append(", ");
        }
        str.delete(str.length() - 2, str.length());
        str.append("]");

        return str.toString();
    }
}
