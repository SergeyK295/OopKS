package ru.academits.kolesnikov.tree.tree_node;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> head;
    private int size;
    private Comparator<? super E> comparator;

    public BinaryTree() {
    }

    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public TreeNode<E> getHead() {
        return head;
    }

    public void setHead(TreeNode<E> data) {
        head = data;
    }

    public void add(E data) {
        if (size == 0) {
            head = new TreeNode<>(data);
        } else {
            TreeNode<E> item = head;

            while (true) {
                if (compare(item.getData(), data) > 0) {
                    if (item.getLeft() == null) {
                        item.setLeft(new TreeNode<>(data));
                        break;
                    }

                    item = item.getLeft();
                } else {
                    if (item.getRight() == null) {
                        item.setRight(new TreeNode<>(data));
                        break;
                    }

                    item = item.getRight();
                }
            }
        }

        size++;
    }

    public boolean contains(E data) {
        if (size == 0) {
            return false;
        }

        TreeNode<E> item = head;
        int comparisonResult;

        while (true) {
            comparisonResult = compare(item.getData(), data);

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult > 0) {
                item = item.getLeft();
            } else {
                item = item.getRight();
            }

            if (item == null) {
                return false;
            }
        }
    }

    public boolean remove(E data) {
        TreeNode<E> parentItem = head;
        TreeNode<E> currentItem;
        int isLeftChild = 0;
        int comparisonResult = compare(parentItem.getData(), data);

        if (comparisonResult == 0) {
            if (head.getLeft() == null && head.getRight() == null) {
                head = new TreeNode<>();
                size = 0;
                return true;
            }

            if (head.getLeft() == null) {
                head = head.getRight();
                size--;
                return true;
            }

            receiveHeir(isLeftChild, head, head);
            return true;
        }

        while (parentItem.getLeft() != null || parentItem.getRight() != null) {
            comparisonResult = compare(parentItem.getData(), data);

            if (comparisonResult > 0) {
                currentItem = parentItem.getLeft();

                if (compare(currentItem.getData(), data) != 0) {
                    parentItem = currentItem;
                } else {
                    if (currentItem.getLeft() == null) {
                        if (currentItem.getRight() == null) {
                            parentItem.setLeft(null);
                            size--;
                            return true;
                        }

                        parentItem.setLeft(currentItem.getRight());
                        size--;
                        return true;
                    }

                    isLeftChild = -1;
                    receiveHeir(isLeftChild, parentItem, currentItem);
                    return true;
                }
            } else {
                currentItem = parentItem.getRight();

                if (compare(currentItem.getData(), data) != 0) {
                    parentItem = currentItem;
                } else {
                    if (currentItem.getLeft() == null) {
                        if (currentItem.getRight() == null) {
                            parentItem.setRight(null);
                            size--;
                            return true;
                        }

                        parentItem.setRight(currentItem.getRight());
                        size--;
                        return true;
                    }

                    isLeftChild = 1;
                    receiveHeir(isLeftChild, parentItem, currentItem);
                    return true;
                }
            }
        }

        return false;
    }

    private void receiveHeir(int removalSite, TreeNode<E> parentItem, TreeNode<E> currentItem) {
        if (currentItem.getRight() == null) {
            if (removalSite == 1) {
                parentItem.setRight(currentItem.getLeft());
            } else if (removalSite == -1) {
                parentItem.setLeft(currentItem.getLeft());
            } else {
                head = currentItem.getLeft();
            }

            size--;
            return;
        }

        TreeNode<E> replaceableItemParent = currentItem.getRight();

        if (replaceableItemParent.getLeft() == null) {
            replaceableItemParent.setLeft(currentItem.getLeft());

            if (removalSite == 1) {
                parentItem.setRight(replaceableItemParent);//
            } else if (removalSite == -1) {
                parentItem.setLeft(replaceableItemParent);
            } else {
                head = replaceableItemParent;
            }

            size--;
            return;
        }

        TreeNode<E> replaceableCurrentItem = replaceableItemParent.getLeft();

        while (replaceableCurrentItem.getLeft() != null) {
            replaceableItemParent = replaceableItemParent.getLeft();
            replaceableCurrentItem = replaceableItemParent.getLeft();
        }

        if (replaceableCurrentItem.getRight() == null) {
            replaceableItemParent.setLeft(null);
        } else {
            replaceableItemParent.setLeft(replaceableCurrentItem.getRight());
        }

        replaceableCurrentItem.setLeft(currentItem.getLeft());
        replaceableCurrentItem.setRight(currentItem.getRight());
        size--;

        if (removalSite == 1) {
            parentItem.setRight(replaceableCurrentItem);
        } else if (removalSite == -1) {
            parentItem.setLeft(replaceableCurrentItem);
        } else {
            head = replaceableCurrentItem;
        }
    }

    public int size() {
        return size;
    }

    public void bypassInWidth(Consumer<E> consumer) {
        consumer.accept(head.getData());

        LinkedList<TreeNode<E>> list = new LinkedList<>();

        if (head.getLeft() != null) {
            list.addFirst(head.getLeft());
        }

        if (head.getRight() != null) {
            list.addFirst(head.getRight());
        }

        TreeNode<E> item;

        for (int i = 1; i < size; ) {
            item = list.removeLast();
            consumer.accept(item.getData());
            i++;

            if (item.getLeft() != null) {
                list.addFirst(item.getLeft());
            }

            if (item.getRight() != null) {
                list.addFirst(item.getRight());
            }
        }
    }

    public void bypassInDepthWithRecursion(TreeNode<E> node, Consumer<E> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            BinaryTree<E> n = new BinaryTree<>();
            n.setHead(node.getLeft());
            n.bypassInDepthWithRecursion(n.getHead(), consumer);
        }

        if (node.getRight() != null) {
            BinaryTree<E> n = new BinaryTree<>();
            n.setHead(node.getRight());
            n.bypassInDepthWithRecursion(n.getHead(), consumer);
        }
    }

    public void bypassInDepth(Consumer<E> consumer) {
        consumer.accept(head.getData());
        LinkedList<TreeNode<E>> list = new LinkedList<>();

        if (head.getRight() != null) {
            list.addFirst(head.getRight());
        }

        if (head.getLeft() != null) {
            list.addFirst(head.getLeft());
        }

        TreeNode<E> item;

        while (!list.isEmpty()) {
            item = list.remove();

            consumer.accept(item.getData());

            if (item.getRight() != null) {
                list.addFirst(item.getRight());
            }

            if (item.getLeft() != null) {
                list.addFirst(item.getLeft());
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