package ru.academits.kolesnokov.array_list_main;

import ru.academits.kolesnokov.array_list.ArrayList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList(2);
        list1.add(null);
        list1.add(3);
        list1.add(2);
        list1.add(1, 5);
        list1.set(2, 4);
        System.out.println("Первый список: " + list1);

        ArrayList list2 = new ArrayList();
        list2.add(null);
        list2.add(2);
        System.out.println("Второй список: " + list2);

        System.out.println("Содержит ли в себе один список другой " + list1.containsAll(list2));

        list1.addAll(list2);
        System.out.println("Добавим к первому списку второй: " + list1);

        list1.retainAll(list2);
        System.out.println("Оставим в первом списке только общие элементы обоих списков: " + list1);
    }
}