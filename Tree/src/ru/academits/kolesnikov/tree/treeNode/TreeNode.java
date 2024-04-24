package ru.academits.kolesnikov.tree.treeNode;

import java.util.LinkedList;

public class TreeNode<T extends Comparable<? super T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;
    private int size = 1;

    public TreeNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    public void add(T data) {
        if (this.data.compareTo(data) > 0) {
            if (left == null) {
                left = new TreeNode<>(data);
            } else {
                left.add(data);
            }
        } else {
            if (right == null) {
                right = new TreeNode<>(data);
            } else {
                right.add(data);
            }
        }

        size++;
    }

    public boolean valueRetrieval(T data) {
        if (this.data.compareTo(data) == 0) {
            return true;
        }

        if (this.data.compareTo(data) > 0) {
            if (left == null) {
                return false;
            } else {
                return left.valueRetrieval(data);

            }
        } else {
            if (right == null) {
                return false;
            } else {
                return right.valueRetrieval(data);

            }
        }
    }

    public void remove(T data) {
        if (this.data.compareTo(data) == 0) {
            if (right == null) {
                this.data = left.data;
            } else {
                this.data = right.data;
            }
        } else if (this.data.compareTo(data) > 0) {
            if (left.data.compareTo(data) == 0) {
                if (left.left == null && left.right == null) {
                    left = null;
                } else if (left.left == null) {
                    left = left.right;
                } else if (left.right == null) {
                    left = left.left;
                } else {
                    TreeNode<T> del = left.right;

                    while (del.left.left != null) {
                        del = del.left;
                    }

                    T substitute = del.left.data;

                    if (del.left.right != null) {
                        del.left = del.left.right;
                    } else {
                        del.left = null;
                    }

                    left.data = substitute;
                }
            } else {
                left.remove(data);
            }
        } else {
            if (right.data.compareTo(data) == 0) {
                if (right.left == null && right.right == null) {
                    right = null;
                } else if (right.left == null) {
                    right = right.right;
                } else if (right.right == null) {
                    right = right.left;
                } else {
                    TreeNode<T> del = right.right;
                    while (del.left.left != null) {
                        del = del.left;
                    }

                    T substitute = del.left.data;

                    if (del.left.right != null) {
                        del.left = del.left.right;
                    } else {
                        del.left = null;
                    }

                    right.data = substitute;
                }
            } else {
                right.remove(data);
            }
        }

        size--;
    }

    public int size() {
        return size;
    }

    public void bypassInWidth() {
        System.out.println(data);
        LinkedList<TreeNode> queue = new LinkedList<>();

        if (left != null) {
            queue.addFirst(left);
        }

        if (right != null) {
            queue.addFirst(right);
        }

        TreeNode result;

        for (int i = 1; i < size; ) {
            result = queue.removeLast();
            System.out.println(result.data);
            i++;

            if (result.left != null) {
                queue.addFirst(result.left);
            }

            if (result.right != null) {
                queue.addFirst(result.right);
            }
        }
    }

    public void getRecursionInDepthTraversal() {
        System.out.println(data);

        if (left != null) {
            left.getRecursionInDepthTraversal();
        }

        if (right != null) {
            right.getRecursionInDepthTraversal();
        }
    }

    public void getInDepthTraversal() {
        System.out.println(data);
        LinkedList<TreeNode> queue = new LinkedList<>();

        if (right != null) {
            queue.addFirst(right);
        }

        if (left != null) {
            queue.addFirst(left);
        }

        TreeNode result;

        while (!queue.isEmpty()) {
            result = queue.remove();

            System.out.println(result.data);

            if (result.right != null) {
                queue.addFirst(result.right);
            }

            if (result.left != null) {
                queue.addFirst(result.left);
            }
        }
    }
}
