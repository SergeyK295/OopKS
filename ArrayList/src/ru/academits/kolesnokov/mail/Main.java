package ru.academits.kolesnokov.mail;


import ru.academits.kolesnokov.array_list.MyList;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyList list = new MyList(2);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(5);

        MyList list1 = new MyList(2);
        list1.add(3);
        list1.add(2);

        System.out.println(list.containsAll(list1));
        System.out.println();

        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}