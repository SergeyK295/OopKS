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

        SinglyLinkedList<Integer> list2 = list1.copyList();
        System.out.println("Копия писка №1");
        System.out.println(list2);

        int delete = 4;
        System.out.println("Удалим элемент со значением: \"4\" " + list1.deleteByData(list1.get(delete)));
        System.out.println(list1);
    }
}