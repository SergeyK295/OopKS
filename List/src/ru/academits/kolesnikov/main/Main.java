package ru.academits.kolesnikov.main;

import ru.academits.kolesnikov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.addElementAtBeginning(0);
        for (int i = 1; i < 10; i++) {
            list1.addToEnd(i);
        }

        SinglyLinkedList<Integer> list2 = list1.copyList();
        System.out.println("Копия листа №1");

        for (int i = 0; i < list2.size(); i++) {
            System.out.println(list2.getData(i));
        }

        System.out.println();
        System.out.println("Удалим значение элемент со значением \"4\"" + list1.deleteElement(4));

        System.out.println(list1.setData(1, 11));
    }
}