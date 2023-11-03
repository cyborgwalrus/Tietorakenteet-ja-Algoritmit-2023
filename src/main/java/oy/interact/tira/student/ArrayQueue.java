package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class ArrayQueue<E> implements QueueInterface<E> {
    private static final int DEFAULT_QUEUE_SIZE = 10;

    private Object[] array;
    private int head;
    private int tail;
    private int capacity;
    private int count;

    public ArrayQueue() {
        this.array = new Object[DEFAULT_QUEUE_SIZE];
        this.head = 0;
        this.tail = 0;
        this.capacity = DEFAULT_QUEUE_SIZE;
        this.count = 0;
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.capacity = capacity;
        this.count = 0;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    private void reallocate(int newSize) {
        Object[] newArray = new Object[newSize];
        int newHead = 0;
        int oldCount = this.count;
        while(this.count > 0){
            newArray[newHead] = this.dequeue();
            newHead++;
        }
        this.capacity = newSize;
        this.array = newArray;
        this.head = 0;
        this.tail = oldCount;
        this.count = oldCount;
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
        if (this.count == this.capacity)
            reallocate(this.capacity * 2);
        if (tail >= this.capacity && head > 0)
            tail = 0;
        this.array[tail++] = element;
        this.count++;

    }

    /**
     * Removes an element from the queue.
     * 
     * @return The element from the head of the queue.
     * @throws QueueIsEmptyException If the queue is empty.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() throws IllegalStateException {
        if (this.count == 0)
            throw new IllegalStateException();

        E x = (E)this.array[this.head];
        this.head++;
        this.count--;
        if (this.head >= this.capacity)
            this.head = 0;
        return x;

    }

    /**
     * Returns the element at the head of the queue, not removing it from the queue.
     * 
     * @return The element in the head of the queue.
     * @throws QueueIsEmptyException If the queue is empty.
     */
    @Override
    @SuppressWarnings("unchecked")
    public E element() throws IllegalStateException {
        if (this.count == 0)
            throw new IllegalStateException();
        return (E) this.array[this.head];
    }

    /**
     * Returns the count of elements currently in the queue.
     * 
     * @return Count of elements in the queue.
     */
    @Override
    public int size(){
        return this.count;
    }

    /**
     * Can be used to check if the queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    @Override
    public boolean isEmpty(){
        if(this.count == 0)
            return true;
        return false;
    }

    /**
     * Resets the queue to empty state, removing the objects.
     */
    @Override
    public void clear(){
        this.array = new Object[DEFAULT_QUEUE_SIZE];
        this.capacity = DEFAULT_QUEUE_SIZE;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
    }

    public String toString(){
        if(this.count == 0)
            return "[]";
        StringBuilder str = new StringBuilder();

        str.append("[");
        int headPtr = this.head;
        while(headPtr != this.tail){
            if(headPtr >= capacity)
                headPtr = 0;
            if(this.array[headPtr] == null){
                headPtr++;
                continue;
            }
            str.append(this.array[headPtr].toString());
            str.append(", ");
            headPtr++;
        }
        str.delete(str.length()-2, str.length());
        str.append("]");

        return str.toString();
    }
}
