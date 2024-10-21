package ru.academits.kolesnikov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 20;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Передано недопустимое значение \"" + capacity + "\", вместимость хэш-таблицы должна быть не меньше 1");
        }

        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getListIndex(Object object) {
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
        int listIndex = getListIndex(object);
        return lists[listIndex] != null && lists[listIndex].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<E> {
        private int visitedItemsCount;
        private int arrayIndex;
        private int listIndex;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return visitedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].isEmpty()) {
                arrayIndex++;
            }

            E item = lists[arrayIndex].get(listIndex);

            if (listIndex == lists[arrayIndex].size() - 1) {
                ++arrayIndex;
                listIndex = -1;
            }

            listIndex++;
            visitedItemsCount++;

            return item;
        }
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (E e : list) {
                    result[i] = e;
                    i++;
                }
            }
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int listIndex = getListIndex(item);

        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }

        lists[listIndex].add(item);
        modCount++;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object object) {
        int listIndex = getListIndex(object);

        if (lists[listIndex] != null && lists[listIndex].remove(object)) {
            modCount++;
            size--;
            return true;
        }

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            list.clear();
        }

        modCount++;
        size = 0;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        size = 0;
        boolean isModified = false;

        for (ArrayList<E> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            if (list.retainAll(collection)) {
                isModified = true;
            }

            size += list.size();
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        size = 0;
        boolean isModified = false;

        for (ArrayList<E> list : lists) {
            if (list == null || list.isEmpty()) {
                continue;
            }

            if (list.removeAll(collection)) {
                isModified = true;
            }

            size += list.size();
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
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