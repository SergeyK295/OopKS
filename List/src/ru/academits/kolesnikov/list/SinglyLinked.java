package ru.academits.kolesnikov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinked<E> {
    private ListItem<E> head;
    private int size;

    public SinglyLinked() {
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    public E get(int index) {
        checkIndex(index);
        return getItem(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);
        ListItem<E> item = getItem(index);
        E previousData = item.getData();
        item.setData(data);
        return previousData;
    }

    public E deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<E> previousItem = getItem(index - 1);
        ListItem<E> deletedItem = previousItem.getNext();
        previousItem.setNext(deletedItem.getNext());
        size--;
        return deletedItem.getData();
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        size++;
    }

    public void add(int index, E data) {
        size++;
        checkIndex(index);
        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
    }

    public boolean deleteByData(E data) {
        isEmpty();

        if (Objects.equals(data, head.getData())) {
            deleteFirst();
            return true;
        }

        for (ListItem<E> previousItem = head; previousItem != null; previousItem = previousItem.getNext()) {
            if (Objects.equals(data, previousItem.getNext().getData())) {
                previousItem.setNext(previousItem.getNext().getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        isEmpty();

        ListItem<E> deletedItem = head;
        head = deletedItem.getNext();
        size--;
        return deletedItem.getData();
    }

    public void reverse() {
        for (int i = 1; i < size; i++) {
            addFirst(deleteByIndex(i));
        }
    }

    public SinglyLinked<E> copy() {
        isEmpty();

        SinglyLinked<E> copy = new SinglyLinked<>();
        ListItem<E> item = head;

        copy.head = new ListItem<>(item.getData());
        ListItem<E> copiedItem = copy.head;

        for (int i = 1; i < size; i++) {
            item = item.getNext();
            copiedItem.setNext(new ListItem<>(item.getData()));
            copiedItem = copiedItem.getNext();
        }

        copy.size = size;
        return copy;
    }

    private ListItem<E> getItem(int index) {
        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        ListItem<E> item = head;
        stringBuilder.append(item.getData());

        while (item.getNext() != null) {
            item = item.getNext();
            stringBuilder.append(", ").append(item.getData());
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Нет индекса со значением " + index + ". Допустимое значение от 0 до " + size + " включительно.");
        }
    }

    private void isEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }
    }
}