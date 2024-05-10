package ru.academits.kolesnikov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private ArrayList<E>[] lists;
    private int size;
    private final double fillRatio;


    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[20];
        fillRatio = 0.75;
    }

    public HashTable(int capacity) {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
        fillRatio = 0.75;
    }

    public HashTable(int capacity, double fillRatio) {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
        this.fillRatio = fillRatio;
    }

    //  todo пункт 8
    public int indexOf(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
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
    public boolean contains(Object object) {
        if (lists[indexOf(object)] == null || lists[indexOf(object)].isEmpty()) {
            return false;
        }

        return lists[indexOf(object)].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        private int visitedItemsCounter;
        private int arrayIndex;
        private int listIndex;
        private final int modCount = Arrays.hashCode(lists);

        @Override
        public boolean hasNext() {
            return visitedItemsCounter < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != Arrays.hashCode(lists)) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].isEmpty()) {
                arrayIndex++;
            }

            E result = lists[arrayIndex].get(listIndex);

            if (listIndex == lists[arrayIndex].size() - 1) {
                ++arrayIndex;
                listIndex = -1;
            }

            listIndex++;
            visitedItemsCounter++;

            return result;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[lists.length];
        int itemIndex = 0;

        for (Object item : lists) {
            result[itemIndex] = item;
            itemIndex++;
        }

        return result;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        if (array.length < lists.length) {
            //noinspection unchecked
            return (E[]) toArray();
        }

        System.arraycopy(toArray(), 0, array, 0, lists.length);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public void ensureCapacity(int capacity) {
        if ((double) size / lists.length > fillRatio) {
            while (capacity + size >= lists.length) {
                lists = Arrays.copyOf(lists, lists.length * 2);
            }
        }
    }

    @Override
    public boolean add(E item) {
        ensureCapacity(1);
        int index = indexOf(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (lists[index] != null && lists[index].contains(object)) {
            lists[index].remove(object);
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection collection) {
        for (Object item : collection) {
            //noinspection unchecked
            add((E) item);
        }

        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            Arrays.fill(lists, null);

            size = 0;
        }
    }

    @Override
    public boolean retainAll(Collection collection) {
        size = 0;

        for (int i = lists.length - 1; i > 0; i--) {
            while (lists[i] == null || lists[i].isEmpty()) {
                i--;
            }

            lists[i].retainAll(collection);
            size += lists[i].size();
        }

        return size != 0;
    }

    @Override
    public boolean removeAll(Collection collection) {
        size = 0;

        for (int i = lists.length - 1; i > 0; i--) {
            while (lists[i] == null || lists[i].isEmpty()) {
                i--;
            }

            lists[i].removeAll(collection);
            size += lists[i].size();
        }

        return size != 0;
    }

    @Override
    public boolean containsAll(Collection collection) {
        if (size == 0) {
            return false;
        }

        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(lists[0]);

        for (int i = 1; i < lists.length; i++) {
            stringBuilder.append(", ").append(lists[i]);
        }

        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}