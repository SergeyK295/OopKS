package ru.academits.kolesnokov.array_list;

import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.List;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

public class ArrayList<E> implements List<E> {
    private static final int Default_Capacity = 10;
    private E[] items;
    private int size;
    private int modCount;

    public ArrayList(int defaultCapacity) {
        if (defaultCapacity < 0) {
            throw new NegativeArraySizeException("Передано недопустимое значение \"" + defaultCapacity + "\", вместимость списка должна быть не меньше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[defaultCapacity];
    }

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[Default_Capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class ListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
    }

    public void trimToSize() {
        if (size <= modCount) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " некорректный. Допустимый диапазон от 0 до " + (size - 1) + " включительно");
        }
    }

    public boolean add(E item) {
        add(size, item);
        return true;
    }

    public void add(int index, E item) {
        if (index != size) {
            checkIndex(index);
        }

        int capacity = items.length;

        if (size == capacity) {
            if (capacity == 0) {
                items = Arrays.copyOf(items, Default_Capacity);
            } else {
                items = Arrays.copyOf(items, size * 2);
            }
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

        size++;
        modCount++;
    }

    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }


    public E remove(int index) {
        checkIndex(index);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;

        size--;
        modCount++;

        return removedItem;
    }

    public boolean addAll(Collection c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection c) {
        if (index != size) {
            checkIndex(index);
        }

        if (c.isEmpty()) {
            return false;
        }

        int collectionSize = c.size();
        ensureCapacity(collectionSize + size);

        System.arraycopy(items, index, items, index + collectionSize, size - index);

        for (Object item : c) {
            //noinspection unchecked
            items[index] = (E) item;
            index++;
        }

        size += c.size();
        modCount++;
        return true;
    }

    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, 0, size, null);

        modCount++;
        size = 0;
    }

    public E get(int index) {
        checkIndex(index);
        return items[index];
    }

    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }

    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    public boolean retainAll(Collection c) {
        if (size == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    public boolean removeAll(Collection c) {
        if (c.isEmpty() || size == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    public boolean containsAll(Collection c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        stringBuilder.append(items[0]);

        for (int i = 1; i < size; i++) {
            stringBuilder.append(", ").append(items[i]);
        }

        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != getClass()) {
            return false;
        }

        ArrayList<E> list = (ArrayList<E>) o;

        if (size == list.size) {
            for (int i = 0; i <= size; i++) {
                if (!items[i].equals(list.items[i])) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 1;
        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(items);
        return hash;
    }
}
