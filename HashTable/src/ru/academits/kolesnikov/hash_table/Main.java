package ru.academits.kolesnikov.hash_table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(16);
        hashTable.add(1);
        hashTable.add(6);
        hashTable.add(26);
        hashTable.add(36);
        hashTable.add(46);
        hashTable.add(48);
        hashTable.add(21);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(9);
        arrayList.add(5);

        hashTable.addAll(arrayList);

        for (Iterator<Integer> i = hashTable.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }
    }
}