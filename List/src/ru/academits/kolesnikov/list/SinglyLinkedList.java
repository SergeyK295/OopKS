package ru.academits.kolesnikov.list;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public E getFirst() {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }

        return head.getData();
    }

    public E get(int index) {
        return getItem(index).getData();
    }

    public E set(int index, E data) {
        ListItem<E> item = getItem(index);
        ListItem<E> itemDeleted = new ListItem<>(item.getData());
        item.setData(data);
        return itemDeleted.getData();
    }

    public E deleteByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Нет индекса со значением " + index + ". Допустимое значение от 0 до " + size + " включительно.");
        }

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
        ListItem<E> previousItem = getItem(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));
    }

    public boolean deleteByData(E data) {
        if (head.getData().equals(data)) {
            deleteFirst();
            return true;
        }

        int index = 0;

        for (ListItem<E> previousItem = head; index < size; previousItem = previousItem.getNext()) {
            if ((data == null && previousItem.getNext().getData() == null) || (previousItem.getNext().getData() != null && previousItem.getNext().getData().equals(data))) {
                previousItem.setNext(previousItem.getNext().getNext());
                size--;
                return true;
            }

            index++;
        }

        return false;
    }

    public E deleteFirst() {
        if (size == 0) {
            throw new NullPointerException("Список пуст.");
        }

        ListItem<E> deletedItem = head;
        head = deletedItem.getNext();
        size--;
        return deletedItem.getData();
    }

    public SinglyLinkedList<E> reverse() {
        SinglyLinkedList<E> result = new SinglyLinkedList<>();
        ListItem<E> item = head;

        for (int i = 0; i < size; i++) {
            result.addFirst(item.getData());
            item = item.getNext();
        }

        return result;
    }

    public SinglyLinkedList<E> copyList() {
        SinglyLinkedList<E> result = new SinglyLinkedList<>();
        ListItem<E> exportItem = head;

        result.head = new ListItem<>(exportItem.getData());
        ListItem<E> importItem = result.head;

        exportItem = exportItem.getNext();
        importItem.setNext(new ListItem<>(exportItem.getData()));

        for (int i = 2; i < size; i++) {
            importItem = importItem.getNext();
            exportItem = exportItem.getNext();
            importItem.setNext(new ListItem<>(exportItem.getData()));
        }

        result.size = size;
        return result;
    }

    private ListItem<E> getItem(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Нет индекса со значением " + index + ". Допустимое значение от 0 до " + size + " включительно.");
        }

        ListItem<E> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListItem<E> item = head;
        stringBuilder.append(item.getData());

        for (int i = 1; i < size; i++) {
            item = item.getNext();
            stringBuilder.append(", ").append(item.getData());
        }

        return stringBuilder.toString();
    }
}