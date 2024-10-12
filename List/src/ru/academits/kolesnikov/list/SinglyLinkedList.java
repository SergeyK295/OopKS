package ru.academits.kolesnikov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        checkIfEmptyList();

        return head.getData();
    }

    public E get(int index) {
        checkIndex(index);
        return getItem(index).getData();
    }

    public E set(int index, E data) {
        checkIndex(index);

        ListItem<E> item = getItem(index);
        E oldData = item.getData();
        item.setData(data);

        return oldData;
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
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Нет индекса со значением " + index + ". Допустимое значение от 0 до " + size + " включительно.");
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        size++;
    }

    public boolean deleteByData(E data) {
        if (size == 0) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            deleteFirst();
            return true;
        }

        for (ListItem<E> previousItem = head, item = previousItem.getNext(); item != null; previousItem = item, item = item.getNext()) {
            if (Objects.equals(data, item.getData())) {
                previousItem.setNext(item.getNext());
                size--;
                return true;
            }
        }

        return false;
    }

    public E deleteFirst() {
        checkIfEmptyList();

        E deletedItem = head.getData();
        head = head.getNext();
        size--;

        return deletedItem;
    }

    public void reverse() {
        ListItem<E> item = head;
        ListItem<E> previousItem = null;

        while (item != null) {
            ListItem<E> nextItem = item.getNext();
            item.setNext(previousItem);
            previousItem = item;
            item = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<E> copy() {
        if (size == 0) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<E> listCopy = new SinglyLinkedList<>();
        listCopy.addFirst(head.getData());
        ListItem<E> copiedItem = listCopy.head;

        for (ListItem<E> item = head.getNext(); item != null; item = item.getNext()) {
            copiedItem.setNext(new ListItem<>(item.getData()));
            copiedItem = copiedItem.getNext();
        }

        listCopy.size = size;
        return listCopy;
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

        while (item != null) {
            stringBuilder.append(item.getData()).append(", ");
            item = item.getNext();
        }

        int stringLength = stringBuilder.length();
        stringBuilder.delete(stringLength - 2, stringLength);

        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Нет индекса со значением " + index + ". Допустимое значение от 0 до " + (size - 1) + " включительно");
        }
    }

    private void checkIfEmptyList() {
        if (size == 0) {
            throw new NoSuchElementException("Список пуст.");
        }
    }
}