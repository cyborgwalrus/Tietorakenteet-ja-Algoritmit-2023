package oy.interact.tira.student;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {
    private static final int DEFAULT_STACK_SIZE = 10;
    private Object[] itemArray;
    private int capacity;
    private int top;

    public StackImplementation() {
        this.itemArray = new Object[DEFAULT_STACK_SIZE];
        this.top = -1;
        this.capacity = DEFAULT_STACK_SIZE;
    }

    public StackImplementation(int capacity) {
        this.itemArray = new Object[capacity];
        this.top = -1;
        this.capacity = capacity;
    }

    @Override
    public int capacity() {
        return  this.capacity;
    }

    private void reallocate(int newSize) {
        Object[] newArray = new Object[newSize];
        for (int i = 0; i <= this.top; i++) {
            newArray[i] = this.itemArray[i];
        }
        this.capacity = newSize;
        this.itemArray = newArray;
    }

    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        if(element == null)
            throw new NullPointerException();
        if (top == capacity - 1)
            reallocate(capacity * 2);
        top++;
        itemArray[top] = element;
    }

    @Override
    public E pop() throws IllegalStateException{
        if(this.top == -1 || this.itemArray[top] == null)
            throw new IllegalStateException();
        Object element = this.itemArray[top];
        this.itemArray[top] = null;
        top--;
        return (E)element;
    }

    @Override
    public E peek() throws IllegalStateException{
        if(this.top == -1)
            throw new IllegalStateException();
        return (E)this.itemArray[top];
    }

    @Override
    public int size(){
        if(this.top == -1)
            return 0;
        return top + 1;
    }

    @Override
    public boolean isEmpty(){
        if(this.top == -1)
            return true;
        return false;
    }

    @Override
    public void clear(){
        this.itemArray = new Object[capacity];
        this.top = -1;
    }

    @Override
    public String toString(){
        if(this.top == -1)
            return "[]";
        StringBuilder str = new StringBuilder();

        str.append("[");
        for(int i = 0; i <= this.top; i++){
            str.append(this.itemArray[i].toString());
            str.append(", ");
        }
        str.delete(str.length()-2, str.length());
        str.append("]");

        return str.toString();
    }

}
