package ru.academits.kolesnokov.array_list;

import java.util.*;

public class List<E> implements java.util.List<E> {
    private E[] items;
    private int size;

    public List(int capacity) {
        if (capacity < 1) {
            throw new IndexOutOfBoundsException("Передано не допустимое значение \"" + capacity + "\" вместимость списка должна быть больше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    public List() {
        //noinspection unchecked
        items = (E[]) new Object[10];
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

        private final int modCount = Arrays.hashCode(items);

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            if (modCount != Arrays.hashCode(items)) {
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

    public <E> E[] toArray(E[] array) {
        if (array.length < size) {
            return (E[]) toArray();
        }

        for (int i = 0; i < size; i++) {
            array[i] = (E) items[i];
        }

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    public void ensureCapacity(int capacity) {
        while (capacity + size >= items.length) {
            items = Arrays.copyOf(items, items.length * 2);
        }

    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " некорректный. Допустимый диапазон от 0 до " + size);
        }
    }

    @Override
    public boolean add(E o) {
        add(size, o);
        return true;
    }

    @Override
    public void add(int index, Object item) {
        if (index != 0) {
            checkIndex(index - 1);
        }

        ensureCapacity(1);
        System.arraycopy(this.items, index, this.items, index + 1, size - index);

        //noinspection unchecked
        this.items[index] = (E) item;
        size++;
    }

    @Override
    public boolean remove(Object o) {
        remove(indexOf(o));
        return false;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E deletedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size] = null;
        size--;
        return deletedItem;
    }

    @Override
    public boolean addAll(Collection c) {
        addAll(size, c);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if (index != 0) {
            checkIndex(index - 1);
        }

        int collectionSize = c.size();

        ensureCapacity(collectionSize);

        for (int i = size + collectionSize - 1, j = size - 1; j >= index; i--, j--) {
            items[i] = items[j];
        }

        for (int i = 0, j = index; i < collectionSize; i++, j++) {
            //noinspection unchecked
            items[j] = (E) c.toArray()[i];
        }

        size += c.size();
        return true;
    }

    @Override
    public void clear() {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                items[i] = null;
            }

            size = 0;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return items[index];
    }

    @Override
    public E set(int index, Object item) {
        checkIndex(index);

        Object deletedItem = items[index];

        //noinspection unchecked
        items[index] = (E) item;
        return (E) deletedItem;
    }

    @Override
    public int indexOf(Object o) {
        boolean isBothNull;

        for (int i = 0; i < size; i++) {
            E item = items[i];
            isBothNull = (o == null && null == item);

            if (isBothNull || (null != item && item.equals(o))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        boolean isBothNull;

        for (int i = size - 1; i > -1; i--) {
            E item = items[i];
            isBothNull = (o == null && null == item);

            if (isBothNull || (null != item && item.equals(o))) {
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
        boolean hasRemoval = false;
        int collectionSize = c.size();
        boolean hasCoincided;
        boolean isBothNull;

        for (int i = size - 1; i > -1; i--) {
            hasCoincided = false;

            for (int j = 0; j < collectionSize; j++) {
                isBothNull = (c.toArray()[j] == null && null == items[i]);

                if (items[i] != null && items[i].equals(c.toArray()[j]) || isBothNull) {
                    hasCoincided = true;
                    break;
                }
            }

            if (!hasCoincided) {
                remove(i);
                hasRemoval = true;
            }
        }

        return hasRemoval;
    }

    @Override
    public boolean removeAll(Collection c) {
        if (size == 0) {
            return false;
        }

        boolean hasRemoval = false;
        int collectionSize = c.size();
        boolean isBothNull;
        boolean coincided;

        for (int i = size - 1; i > -1; i--) {
            coincided = false;

            for (int j = 0; j < collectionSize; j++) {
                isBothNull = c.toArray()[j] == null && items[i] == null;

                if ((items[i] != null && items[i].equals(c.toArray()[j])) || isBothNull) {
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

        for (Object item : c) {

            hasMatch = contains(item);

            if (!hasMatch) {
                return false;
            }
        }

        return hasMatch;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        stringBuilder.append(items[0]);

        for (int i = 1; i < items.length; i++) {
            stringBuilder.append(", ").append(items[i]);
        }

        stringBuilder.append('}');
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
        List list = (List) o;
        return size == list.size && Arrays.equals(items, list.items);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 3;
        hash = prime * hash + size;
        hash = prime * hash + Arrays.hashCode(items);
        return hash;
    }
}
