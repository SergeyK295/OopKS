package ru.academits.kolesnikov.tree.main;

import ru.academits.kolesnikov.tree.treeNode.TreeNode;

public class Main {
    public static void main(String[] args) {
        int data = 10;
        TreeNode<Integer> tree = new TreeNode<>(data);
        data = 20;
        tree.add(data);
        data = 30;
        tree.add(data);
        data = 5;
        tree.add(data);
        data = 40;
        tree.add(data);
        data = 25;
        tree.add(data);
        data = 23;
        tree.add(data);
        data = 24;
        tree.add(data);
        data = 15;
        tree.add(data);

        tree.getRecursionInDepthTraversal();
        tree.getInDepthTraversal();

    }
}
