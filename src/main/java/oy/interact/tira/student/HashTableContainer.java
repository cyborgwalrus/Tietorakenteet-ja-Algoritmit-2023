package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private Pair<K, V>[] array;
    private int arraySize;
    private int elementsInArray;
    private final int DEFAULT_SIZE = 100;
    private final double REALLOCATION_THRESHOLD = 0.75;

    public HashTableContainer() {
        this.array = (Pair<K, V>[]) new Pair[DEFAULT_SIZE];
        this.arraySize = DEFAULT_SIZE;
        this.elementsInArray = 0;
    }

    public HashTableContainer(int arraySize) {
        this.array = (Pair<K, V>[]) new Pair[arraySize];
        this.arraySize = arraySize;
        this.elementsInArray = 0;
    }

    private int calculateIndex(K key, int i) {
        i++;
        return ((key.hashCode() + 29 * i + 11 * i * i) & 0x7fffffff) % arraySize;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null)
            throw new IllegalArgumentException();
        
        if(key.equals("61496b6a-46c1-4918-9fc0-53db57633b6d"))
            key = key;

        // If the new add pushes the array size over the reallocation threshold,
        // increase capacity
        if (size() + 1 > capacity() * REALLOCATION_THRESHOLD)
            ensureCapacity(2 * capacity());

        int i = 0;
        int index = calculateIndex(key, i);
        while (this.array[index] != null) {
            // If the same key is already in the array, replace it
            if (this.array[index].getKey().compareTo(key) == 0) {
                this.array[index] = new Pair<K, V>(key, value);
                return;
            }

            i++;
            index = calculateIndex(key, i);
        }
        // Found a free slot in the array
        this.array[index] = new Pair<K, V>(key, value);
        elementsInArray++;

    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if (key == null)
            throw new IllegalArgumentException();
        if(key.equals("61496b6a-46c1-4918-9fc0-53db57633b6d"))
            key = key;

        int i, index;
        for (i = 0; i < capacity() - 1; i++) {
            index = calculateIndex(key, i);
            if (this.array[index] != null)
                if (this.array[index].getKey().compareTo(key) == 0)
                    return this.array[index].getValue();
        }

        index = calculateIndex(key, i);
        if (this.array[index] != null) {
            if (this.array[index].getKey().compareTo(key) == 0)
                return this.array[index].getValue();
        }
        return null;
    }

    @Override
    public V remove(K key) throws IllegalArgumentException {
        if (key == null)
            throw new IllegalArgumentException();

        V returnValue;
        int i = 0;
        int index = calculateIndex(key, i);
        while (this.array[index] != null) {

            if (this.array[index].getKey().compareTo(key) == 0) {
                returnValue = this.array[index].getValue();
                this.array[index] = null;
                elementsInArray--;
                return returnValue;
            }
            i++;
            index = calculateIndex(key, i);
        }

        return null;
    }

    @Override
    public V find(Predicate<V> searcher) {
        // Predicate is based on value and not key, therefore the array must be searched
        // manually
        for (int i = 0; i < arraySize; i++) {
            if (array[i] == null)
                continue;

            if (searcher.test(array[i].getValue()))
                return array[i].getValue();
        }
        return null;
    }

    @Override
    public int size() {
        return elementsInArray;
    }

    @Override
    public int capacity() {
        return arraySize;
    }

    @Override
    public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        if (capacity <= 0 || capacity <= size())
            throw new IllegalArgumentException();

        Pair<K, V>[] oldArray = array;
        int oldArraySize = capacity();
        array = (Pair<K, V>[]) new Pair[capacity];
        elementsInArray = 0;
        arraySize = capacity;
        for (int i = 0; i < oldArraySize; i++) {
            if (oldArray[i] != null)
                add(oldArray[i].getKey(), oldArray[i].getValue());
        }
        arraySize = capacity;
        oldArray = null;
    }

    @Override
    public void clear() {
        array = (Pair<K, V>[]) new Pair[arraySize];
        elementsInArray = 0;
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] newArray = (Pair<K, V>[]) new Pair[size()];

        int newIndex = 0;
        for (int oldIndex = 0; oldIndex < capacity(); oldIndex++) {
            if (array[oldIndex] != null) {
                newArray[newIndex] = array[oldIndex];
                newIndex++;
            }

        }
        return newArray;
    }

}
