package ru.academits.kolesnikov.list_main;

import ru.academits.kolesnikov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();

        list1.addFirst(0);

        for (int i = 1; i < 10; i++) {
            list1.add(list1.size(), i);
        }

        list1.add(list1.size(), null);
        list1.add(list1.size(), 11);

        SinglyLinkedList<Integer> list2 = list1.copy();
        System.out.println("Копия списка №1");
        System.out.println(list2);

        int deleteItem = 4;
        System.out.println("Удалим элемент со значением: \"4\" " + list1.deleteByData(deleteItem));
        System.out.println(list1);
    }
}