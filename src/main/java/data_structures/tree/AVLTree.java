package data_structures.tree;

import data_structures.list.LinkedList;

public class AVLTree<E extends Comparable<E>> extends BSTree<E> {

    public AVLTree() {
    }

    @Override
    public void insert(E x) {
        if (isEmpty()) {
            root = new Node<E>(x);
            return;
        }

        Node<E> newNode = new Node<E>(x);
        Node<E> p = root, parent = root;

        while (p != null) {
            parent = p;
            if (p.data.compareTo(x) > 0) {
                p = p.left;
            } else if (p.data.compareTo(x) < 0) {
                p = p.right;
            } else {
                return;
            }
        }

        if (x.compareTo(parent.data) < 0) {
            parent.left = newNode;

        } else {
            parent.right = newNode;
        }
        balance(x);
    }

    void balance(E x) {
        LinkedList<Node<E>> lt = new LinkedList<>();
        Node<E> p = root;
        while (p != null && p.data.compareTo(x) != 0) {
            lt.add(p);
            if (p.data.compareTo(x) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }

            int balFactor;
            for (int i = lt.size() - 1; i >= 0; i--) {
                Node<E> q = lt.get(i);
                balFactor = balanceFactor(q);
                if (balFactor == 2) {
                    balFactor = balanceFactor(q.right);
                    if (balFactor == 1) {
                        leftRotate(q);
                    } else if (balFactor == -1) {
                        rightLeftRotate(q);
                    }
                    break;
                } else if (balFactor == -2) {
                    balFactor = balanceFactor(q.left);
                    if (balFactor == -1) {
                        rightRotate(q);
                    } else if (balFactor == 1) {
                        leftRightRotate(q);
                    }
                    break;
                }
            }
        }
    }
}
