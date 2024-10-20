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

    public int size() {
        return size;
    }

    public E getRoot() {
        if (size == 0) {
            throw new NullPointerException("Ориентированный граф пуст.");
        }

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

        TreeNode<E> deleteNode = root;
        TreeNode<E> deleteNodeParent = root;
        int comparisonResult = compare(deleteNodeParent.getData(), data);
        boolean isLeftChild = false;

        if (comparisonResult == 0) {
            root = getSuccessor(deleteNodeParent);
            size--;
            return true;
        }

        while (deleteNodeParent.getLeft() != null || deleteNodeParent.getRight() != null) {
            if (comparisonResult > 0) {
                deleteNode = deleteNodeParent.getLeft();

                if (deleteNode == null) {
                    return false;
                }

                comparisonResult = compare(deleteNode.getData(), data);

                if (comparisonResult == 0) {
                    isLeftChild = true;
                    break;
                }
            } else {
                deleteNode = deleteNodeParent.getRight();

                if (deleteNode == null) {
                    return false;
                }

                comparisonResult = compare(deleteNode.getData(), data);

                if (comparisonResult == 0) {
                    break;
                }
            }

            deleteNodeParent = deleteNode;
        }

        if (isLeftChild) {
            deleteNodeParent.setLeft(getSuccessor(deleteNode));
            size--;
            return true;
        }

        deleteNodeParent.setRight(getSuccessor(deleteNode));
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

        replaceableNodeParent.setLeft(replaceableNode.getRight());

        replaceableNode.setRight(deleteNode.getRight());
        replaceableNode.setLeft(deleteNode.getLeft());

        return replaceableNode;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Queue<TreeNode<E>> queueList = new LinkedList<>();
        queueList.add(root);

        while (!queueList.isEmpty()) {
            TreeNode<E> node = queueList.remove();

            consumer.accept(node.getData());

            if (node.getLeft() != null) {
                queueList.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queueList.add(node.getRight());
            }
        }
    }

    public void bypassInDepthWithRecursion(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        bypassInDepthWithRecursion(consumer, root);
    }

    private void bypassInDepthWithRecursion(Consumer<E> consumer, TreeNode<E> treeNode) {
        consumer.accept(treeNode.getData());

        if (treeNode.getLeft() != null) {
            bypassInDepthWithRecursion(consumer, treeNode.getLeft());
        }

        if (treeNode.getRight() != null) {
            bypassInDepthWithRecursion(consumer, treeNode.getRight());
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        if (size == 0) {
            return;
        }

        Deque<TreeNode<E>> dequeList = new LinkedList<>();
        dequeList.push(root);

        while (!dequeList.isEmpty()) {
            TreeNode<E> node = dequeList.pop();

            consumer.accept(node.getData());

            if (node.getRight() != null) {
                dequeList.push(node.getRight());
            }

            if (node.getLeft() != null) {
                dequeList.push(node.getLeft());
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Consumer<E> consumer = x -> stringBuilder.append(", ").append(x);
        bypassInWidth(consumer);
        stringBuilder.append(']');
        stringBuilder.delete(1, 3);
        return stringBuilder.toString();
    }
}