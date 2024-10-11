package ru.academits.kolesnikov.tree;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class BinarySearchTree<E> {
    private TreeNode<E> root;
    private int size;
    private final Comparator<? super E> comparator;

    public BinarySearchTree() {
        comparator = null;
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public E getRoot() {
        return root.getData();
    }

    public void add(E data) {
        if (size == 0) {
            root = new TreeNode<>(data);
            size++;
            return;
        }

        TreeNode<E> node = root;

        while (true) {
            if (compare(node.getData(), data) > 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new TreeNode<>(data));
                    break;
                }

                node = node.getLeft();
            } else {
                if (node.getRight() == null) {
                    node.setRight(new TreeNode<>(data));
                    break;
                }

                node = node.getRight();
            }
        }

        size++;
    }

    public boolean contains(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> node = root;

        while (true) {
            int comparisonResult = compare(node.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node == null) {
                return false;
            }
        }
    }

    public boolean remove(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> parentDeleteNode = root;
        TreeNode<E> deleteNode = root;
        int comparisonResult = compare(deleteNode.getData(), data);
        boolean isLeftChild = false;

        if (comparisonResult == 0) {
            if (root.getLeft() == null && root.getRight() == null) {
                root = new TreeNode<>(null);
                size = 0;
                return true;
            }

            if (root.getLeft() == null) {
                root = root.getRight();
                size--;
                return true;
            }

            root = getSuccessor(deleteNode);
            root.setLeft(parentDeleteNode.getLeft());
            size--;
            return true;
        }

        while (parentDeleteNode.getLeft() != null || parentDeleteNode.getRight() != null) {
            comparisonResult = compare(parentDeleteNode.getData(), data);

            if (comparisonResult > 0) {
                if (parentDeleteNode.getLeft() == null) {
                    return false;
                }

                if (compare(parentDeleteNode.getLeft().getData(), data) == 0) {
                    deleteNode = parentDeleteNode.getLeft();
                    isLeftChild = true;
                    break;
                }

                parentDeleteNode = parentDeleteNode.getLeft();
            } else {
                if (parentDeleteNode.getRight() == null) {
                    return false;
                }

                if (compare(parentDeleteNode.getRight().getData(), data) == 0) {
                    deleteNode = parentDeleteNode.getRight();
                    break;
                }

                parentDeleteNode = parentDeleteNode.getRight();
            }
        }

        if (parentDeleteNode.getLeft() == null && parentDeleteNode.getRight() == null) {
            return false;
        }


        if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
            if (isLeftChild) {
                parentDeleteNode.setLeft(null);
                size--;
                return true;
            }

            parentDeleteNode.setRight(null);
            size--;
            return true;
        }

        if (isLeftChild) {
            parentDeleteNode.setLeft(getSuccessor(deleteNode));
            size--;
            return true;
        }

        parentDeleteNode.setRight(getSuccessor(deleteNode));
        size--;
        return true;
    }

    private TreeNode<E> getSuccessor(TreeNode<E> deleteNode) {
        if (deleteNode.getLeft() == null) {
            return deleteNode.getRight();
        }

        if (deleteNode.getRight() == null) {
            return deleteNode.getLeft();
        }

        TreeNode<E> replaceableNodeParent = deleteNode;
        TreeNode<E> replaceableNode = deleteNode.getRight();

        if (replaceableNode.getLeft() == null) {
            replaceableNode.setLeft(deleteNode.getLeft());
            return replaceableNode;
        }

        while (replaceableNode.getLeft() != null) {
            replaceableNodeParent = replaceableNode;
            replaceableNode = replaceableNode.getLeft();
        }

        if (replaceableNode.getRight() != null) {
            replaceableNodeParent.setLeft(replaceableNode.getRight());
        } else {
            replaceableNodeParent.setLeft(null);
        }

        replaceableNode.setRight(deleteNode.getRight());
        replaceableNode.setLeft(deleteNode.getLeft());

        return replaceableNode;
    }

    public int size() {
        return size;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<E>> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            TreeNode<E> node = list.remove();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                list.add(node.getLeft());
            }

            if (node.getRight() != null) {
                list.add(node.getRight());
            }
        }
    }

    public void bypassInDepthWithRecursion(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        consumer.accept(root.getData());
        TreeNode<E> temp = root;

        if (root.getLeft() != null) {
            root = root.getLeft();
            bypassInDepthWithRecursion(consumer);
            root = temp;
        }

        if (root.getRight() != null) {
            root = root.getRight();
            bypassInDepthWithRecursion(consumer);
            root = temp;
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<E>> list = new LinkedList<>();
        list.add(root);

        while (!list.isEmpty()) {
            TreeNode<E> node = list.remove();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                list.addFirst(node.getRight());
            }

            if (node.getLeft() != null) {
                list.addFirst(node.getLeft());
            }
        }
    }

    private int compare(E data1, E data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<E> comparableData1 = (Comparable<E>) data1;
        return comparableData1.compareTo(data2);
    }
}