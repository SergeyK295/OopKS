package ru.academits.kolesnikov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] array;
    private int size;
    private final double fillRatio;
    private int capacity;

    public HashTable() {
        capacity = 11;
        array = new ArrayList[capacity];
        fillRatio = 0.75;
    }

    public HashTable(int size) {
        capacity = size;
        array = new ArrayList[size];
        fillRatio = 0.75;
    }

    public HashTable(int size, double fillRatio) {
        capacity = size;
        array = new ArrayList[size];
        this.fillRatio = fillRatio;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object component) {
        int index = Math.abs(component.hashCode() % array.length);

        if (array[index] == null) {
            return false;
        }

        return array[index].contains(component);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<T> {
        private int viewCounter = -1;
        private int currentIndex = 0;
        private int listIndex = -1;
        private final int modCount = size;

        @Override
        public boolean hasNext() {
            viewCounter++;
            return viewCounter < size;
        }

        @Override
        public T next() {
            if (viewCounter == size) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != size) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            while (array[currentIndex] == null || array[currentIndex].isEmpty()) {
                ++currentIndex;
            }

            ++listIndex;

            if (listIndex == array[currentIndex].size() - 1) {
                int index = currentIndex;
                ++currentIndex;
                listIndex = -1;
                int maxListIndex = array[index].size() - 1;
                return array[index].get(maxListIndex);
            }

            return array[currentIndex].get(listIndex);
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Iterator<T> iterator = iterator();

        for (int i = 0; i < size; i++) {
            result[i] = iterator.next();
        }

        return result;
    }

    @Override
    public boolean add(T component) {
        if ((double) size / capacity > fillRatio) {
            capacity = size * 2;
            ArrayList<T> tamp = new ArrayList<>();

            for (Iterator<T> i = iterator(); i.hasNext(); ) {
                tamp.add(i.next());
            }

            size = 0;
            array = new ArrayList[capacity];
            addAll(tamp);
        }


        int index = Math.abs(component.hashCode() % capacity);

        if (array[index] == null) {
            array[index] = new ArrayList<>();
            array[index].add(component);
            size++;
            return true;
        }

        array[index].add(component);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object component) {
        if (!contains(component)) {
            return false;
        }

        int index = Math.abs(component.hashCode() % capacity);

        if (array[index].size() == 1) {
            array[index] = null;
        }

        array[index].remove(component);
        size--;
        return true;
    }

    @Override
    public boolean addAll(Collection collection) {
        for (int i = 0; i < collection.size(); i++) {
            add((T) collection.toArray()[i]);
        }

        return true;
    }

    @Override
    public void clear() {
        array = new ArrayList[capacity];
        size = 0;
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean hasRemoval = false;

        for (int i = size - 1; i > -1; i--) {
            if (!collection.contains(toArray()[i])) {
                remove(toArray()[i]);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean hasRemoval = false;

        for (int i = 0; i < collection.size(); i++) {
            if (remove(collection.toArray()[i])) {
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean containsAll(Collection collection) {
        boolean hasContains = true;

        for (int i = 0; i < collection.size(); i++) {
            if (!contains(collection.toArray()[i])) {
                hasContains = false;
                break;
            }
        }

        return hasContains;
    }

    @Override
    public Object[] toArray(Object[] array) {
        if (array.length < size) {
            return toArray();
        }

        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            for (int i = size; i < array.length; i++) {
                array[i] = null;
            }
        }

        return array;
    }
}