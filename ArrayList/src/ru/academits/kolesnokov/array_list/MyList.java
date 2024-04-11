package ru.academits.kolesnokov.array_list;

import java.util.*;
import java.util.List;

public class MyList<T> implements List {
    private int capacity;
    private Object[] items;
    private int size;

    public MyList(int capacity) {
        this.capacity = capacity;
        items = new Object[capacity];
    }

    public MyList() {
        capacity = 10;
        items = new Object[capacity];
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
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int modCount = size;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (currentIndex == size) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != size) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            ++currentIndex;
            return (T) items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(items, 0, result, 0, size);

        return result;
    }

    public void ensureCapacity(int minCapacity) {
        Object[] tamp = new Object[size];
        System.arraycopy(items, 0, tamp, 0, size);

        capacity = minCapacity;
        items = new Object[capacity];
        System.arraycopy(tamp, 0, items, 0, size);
    }

    public void trimToSize() {
        capacity = size;
        System.arraycopy(items, 0, items, 0, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Нет элемента под индексом " + index);
        }
    }

    private void fastRemove(int index) {
        size--;

        for (int i = index; i < size; i++) {
            items[i] = items[i + 1];
        }

        items[size] = null;
    }

    @Override
    public boolean add(Object o) {
        if (size >= capacity) {
            ensureCapacity(size + 1);
        }

        items[size] = o;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                fastRemove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        int minCapacity = size + c.size();

        if (size >= capacity) {
            ensureCapacity(minCapacity);
        }

        for (int i = size, j = 0; i < minCapacity; i++, j++) {
            items[i] = c.toArray()[j];
        }

        size = minCapacity;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        checkIndex(index);

        int minCapacity = size + c.size();

        if (size >= capacity) {
            ensureCapacity(minCapacity);
        }

        for (int i = minCapacity - 1, j = size - 1; j >= index; i--, j--) {
            items[i] = items[j];
        }

        for (int i = 0, j = index; i < c.size(); i++, j++) {
            items[j] = c.toArray()[i];
        }

        size = minCapacity;
        return true;
    }

    @Override
    public void clear() {
        items = new Object[capacity];
        size = 0;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public Object set(int index, Object element) {
        checkIndex(index);

        Object tamp = items[index];
        items[index] = element;
        return tamp;
    }

    @Override
    public void add(int index, Object element) {
        checkIndex(index);

        if (size >= capacity) {
            ensureCapacity(size + 1);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        size++;
    }

    @Override
    public Object remove(int index) {
        checkIndex(index);

        if (index < size) {
            Object result = items[index];
            fastRemove(index);
            return result;
        }

        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > -1; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean hasRemoval = false;

        for (int i = size - 1; i > -1; i--) {
            boolean coincided = false;

            for (int j = 0; j < c.size(); j++) {
                if (items[i].equals(c.toArray()[j])) {
                    coincided = true;
                    break;
                }
            }

            if (!coincided) {
                remove(i);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean hasRemoval = false;

        for (int i = size - 1; i > -1; i--) {
            boolean coincided = false;

            for (int j = 0; j < c.size(); j++) {
                if (items[i].equals(c.toArray()[j])) {
                    coincided = true;
                    break;
                }
            }

            if (coincided) {
                remove(i);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean hasMatch = false;

        for (int i = 0; i < c.size(); i++) {
            for (int j = 0; j < size; j++) {
                if (contains(c.toArray()[i])) {
                    hasMatch = true;
                    break;
                }

                hasMatch = false;
            }

            if (!hasMatch) {
                return hasMatch;
            }
        }

        return hasMatch;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Object[] result = new Object[a.length];
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }
}
