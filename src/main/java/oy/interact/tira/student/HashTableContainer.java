package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>, V> implements TIRAKeyedContainer<K, V> {

    private Pair<K, V>[] array;
    private int arraySize;
    private int elementsInArray;

    public HashTableContainer(int arraySize) {
        this.array = (Pair<K, V>[]) new Pair[arraySize];
        this.arraySize = arraySize;
        this.elementsInArray = 0;
    }

    private int calculateIndex(K key, int i) {
        return ((key.hashCode() + i) & 0x7fffffff) % arraySize;
    }

    @Override
    public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null)
            throw new IllegalArgumentException();

        int i = 0;
        int index = calculateIndex(key, i);
        while (this.array[index] != null) {
            // If the same key is already in the array, replace it
            if (this.array[index].getKey().compareTo(key) == 0)
                this.array[index] = new Pair<K, V>(key, value);
                
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

        int i = 0;
        int index = calculateIndex(key, i);
        while (this.array[index] != null) {

            if (this.array[index].getKey().compareTo(key) == 0)
                return this.array[index].getValue();

            i++;
            index = calculateIndex(key, i);
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
        // Predicate is based on value and not key, therefore the array must be searched manually
        for(int i = 0; i < arraySize;i++){
            if(searcher.test(array[i].getValue()))
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
        if(capacity <= 0 || capacity <= size())
            throw new IllegalArgumentException();
        
        Pair<K, V>[] oldArray = array;
        int oldArraySize = capacity();
        array = (Pair<K, V>[]) new Pair[capacity];

        for(int i = 0; i < oldArraySize;i++){
            if(oldArray[i] != null)
                add(oldArray[i].getKey(), oldArray[i].getValue());
        }
        oldArray = null;
    }

    @Override
    public void clear() {
        array = (Pair<K, V>[]) new Pair[arraySize];
        elementsInArray = 0;
    }

    @Override
    public Pair<K, V>[] toArray() throws Exception {
        Pair<K, V>[] newArray = array;
        return newArray;
    }

}
