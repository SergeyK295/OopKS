package ru.academits.kolesnikov.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count = -1;

    public SinglyLinkedList() {
    }

    public int size() {
        return count + 1;
    }

    public T getHeadData() {
        return head.getData();
    }

    public T getData(int index) {
        ListItem<T> result = head;

        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }

        return result.getData();
    }

    public T setData(int index, T data) {
        ListItem<T> result = head;

        for (int i = 0; i < index; i++) {
            result = result.getNext();
        }

        ListItem<T> tamp = new ListItem<>(result.getData(), result.getNext());
        result.setData(data);
        result.setNext(tamp.getNext());
        return tamp.getData();
    }

    public T deleteElementByIndex(int index) {
        if (index == 0) {
            return deleteFirstElement();
        }

        count--;
        ListItem<T> tamp = head;
        int priorIndex = index - 1;


        for (int i = 0; i < priorIndex; i++) {
            tamp = tamp.getNext();
        }

        if (tamp.getNext().getNext() == null) {
            tamp.setNext(null);
            return null;
        }

        ListItem<T> result = tamp.getNext().getNext();
        tamp.setNext(result);
        return result.getData();
    }

    public void addElementAtBeginning(T data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addElement(int index, T data) {
        count++;

        ListItem<T> tamp = head;
        int priorIndex = index - 1;

        for (int i = 0; i < priorIndex; i++) {
            tamp = tamp.getNext();
        }

        ListItem<T> result = new ListItem<>(data, tamp.getNext());
        tamp.setNext(result);
    }

    public boolean deleteElement(T data) {
        int index = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            if (p.getData().equals(data)) {
                deleteElementByIndex(index);
                return true;
            }

            index++;
        }

        return false;
    }

    public T deleteFirstElement() {
        count--;
        return head.getNext().getData();
    }

    public void reverse() {
        int index = count;

        addElementAtBeginning(getData(index));
        deleteElementByIndex(count);

        for (int i = 1; i < index; i++) {
            addElement(i, getData(index));
            deleteElementByIndex(count);
        }
    }

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();

        for (int i = 0; i <= count; i++) {
            result.addElementAtBeginning(getData(i));
        }

        result.reverse();
        return result;
    }

    public void addToEnd(T data) {
        ListItem<T> result = head;

        for (int i = 0; i < count; i++) {
            result = result.getNext();
        }

        result.setNext(new ListItem<>(data));
        count++;
    }
}