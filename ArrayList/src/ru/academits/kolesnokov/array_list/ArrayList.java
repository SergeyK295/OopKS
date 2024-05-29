package ru.academits.kolesnokov.array_list;

import java.util.*;

public class ArrayList<E> implements java.util.List<E> {
    private E[] items;
    private int size;
    private final int capacity = 10;
    private int modCount;

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("Передано не допустимое значение \"" + capacity + "\" вместимость списка должна быть не меньше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[capacity];
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
        return indexOf(o) != -1;
    }

    private class ListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int modCount = ArrayList.this.modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != ArrayList.this.modCount) {
                throw new ConcurrentModificationException("Коллекция изменилась за время обхода");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    @Override
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

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            items = Arrays.copyOf(items, (minCapacity + items.length) * 2);
        }
    }

    public void trimToSize() {
        if (size < items.length / 2) {
            items = Arrays.copyOf(items, size);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " некорректный. Допустимый диапазон от 0 до " + (size - 1) + " включительно");
        }
    }

    private void checkPreviousIndex(int index) {
        if (index < 0 || index - 1 >= size) {
            throw new IndexOutOfBoundsException("Индекс " + (index - 1) + " некорректный. Допустимый диапазон от 0 до " + (size - 2) + " включительно");
        }
    }

    @Override
    public boolean add(E item) {
        add(size, item);
        return true;
    }

    @Override
    public void add(int index, E item) {
        checkPreviousIndex(index);

        if (size == items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

        size++;
        modCount++;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;

        size--;
        modCount++;

        return removedItem;
    }

    @Override
    public boolean addAll(Collection c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (c.isEmpty() || c.getClass() != getClass()) {
            return false;
        }

        checkPreviousIndex(index);
        int collectionSize = c.size();
        ensureCapacity(collectionSize + size);

        System.arraycopy(items, index, items, index + collectionSize, size - index);

        for (Object item : c) {
            //noinspection unchecked
            items[index] = (E) item;
            index++;
        }

        size += c.size();
        modCount += c.size();
        return true;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(items, null);

        modCount += size;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        modCount++;
        return oldItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            E item = items[i];
            boolean isBothNull = (Objects.equals(o, null) && Objects.equals(item, null));

            if (isBothNull || (!Objects.equals(item, null) && item.equals(o))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i > -1; i--) {
            E item = items[i];
            boolean isBothNull = (Objects.equals(o, null) && Objects.equals(item, null));

            if (isBothNull || (!Objects.equals(item, null) && item.equals(o))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public java.util.ListIterator listIterator() {
        return null;
    }

    @Override
    public java.util.ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public java.util.List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c.isEmpty() || c.getClass() != getClass()) {
            return false;
        }

        boolean hasRemoval = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (c.isEmpty() || c.getClass() != getClass()) {
            return false;
        }

        boolean hasRemoval = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
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

        //noinspection rawtypes
        ArrayList list = (ArrayList) o;
        return size == list.size &&
                Arrays.equals(Arrays.copyOf(items, size), Arrays.copyOf(list.items, list.size));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 3;
        hash = prime * hash + Arrays.hashCode(Arrays.copyOf(items, size));
        return hash;
    }
}
