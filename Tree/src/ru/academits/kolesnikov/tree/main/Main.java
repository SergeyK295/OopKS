package ru.academits.kolesnikov.tree.main;

import ru.academits.kolesnikov.tree.tree_node.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.add(10);
        tree.add(5);
        tree.add(3);
        tree.add(6);
        tree.add(1);
        tree.add(20);
        tree.add(7);
        tree.add(0);
        tree.add(2);
        tree.add(9);
        tree.add(15);
        tree.add(30);
        tree.add(18);

        System.out.println("Проверим есть ли в дереве элемент со значением 9: " + tree.contains(99));
        System.out.println();

        Consumer<Integer> print = x -> System.out.print(x + " ");
        System.out.println("Выведем в консоль дерево с помощью обхода в глубину с рекурсией:");
        tree.bypassInDepthWithRecursion(tree.getHead(), print);
        System.out.println();
        System.out.println();

        System.out.println("Удалим из дерева элемент 5 "+ tree.remove(10));
        System.out.println("Выведем в консоль дерево с помощью обхода в глубину без рекурсии:");
        tree.bypassInDepth(print);
        System.out.println();
        System.out.println(tree.size());

        System.out.println("Выведем в консоль дерево с помощью обхода в ширину:");
        tree.bypassInWidth(print);
    }
}
