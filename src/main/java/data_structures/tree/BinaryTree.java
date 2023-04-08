package data_structures.tree;

import data_structures.list.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTree<E extends Comparable<E>> {

    protected static class Node<E> {
        E data;
        Node<E> left, right;

        Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.right = right;
            this.left = left;
        }

        Node(E data) {
            this(data, null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    protected Node<E> root;

    public BinaryTree() {
        root = null;
    }

    public Node<E> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void visit(Node<E> p) {
        System.out.print(p.data + " ");
    }

    public void preOrder(Node<E> p) {
        if (p == null) {
            return;
        }

        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    public void postOrder(Node<E> p) {
        if (p == null) {
            return;
        }

        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    public void inOrder(Node<E> p) {
        if (p == null) {
            return;
        }

        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    public void breadth(Node<E> p) {
        if (p == null) {
            return;
        }

        Queue<Node<E>> queue = new Queue<>();
        queue.enqueue(p);
        Node<E> currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.dequeue();
            visit(currentNode);
            if (currentNode.left != null)
                queue.enqueue(currentNode.left);
            if (currentNode.right != null)
                queue.enqueue(currentNode.right);
        }
    }

    public int height() {
        return height(root);
    }

    protected int height(Node<E> current) {
        if (current == null) {
            return 0;
        }
        int leftHeight = height(current.left);
        int rightHeight = height(current.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int count() {
        return count(root);
    }

    protected int count(Node<E> current) {
        if (current == null) {
            return 0;
        }
        int leftCount = count(current.left);
        int rightCount = count(current.right);
        return leftCount + rightCount + 1;
    }

    protected int level(Node<E> current) {
        return level(root, current, 1);
    }

    protected int level(Node<E> rootNode, Node<E> current, int level) {
        if (rootNode == null)
            return 0;

        if (rootNode == current)
            return level;

        int leftLevel = level(rootNode.left, current, level + 1);
        if (leftLevel != 0)
            return leftLevel;

        return level(rootNode.right, current, level + 1);
    }

    protected int balanceFactor(Node<E> current) {
        if (current == null) {
            return 0;
        }
        int leftHeight = height(current.left);
        int rightHeight = height(current.right);
        return rightHeight - leftHeight;
    }

    protected int numOfChildren(Node<E> p) {
        if (p.left == null && p.right == null) {
            return 0;
        } else if (p.left != null && p.right == null) {
            return -1;
        } else if (p.left == null && p.right != null) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean isAVL() {
        return isAVL(root);
    }

    protected boolean isAVL(Node<E> node) {
        if (node == null) {
            return true;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        return isAVL(node.left) && isAVL(node.right);
    }

    public boolean isMaxHeap() {
        return isMaxHeap(root);
    }

    protected boolean isMaxHeap(Node<E> node) {
        if (node == null)
            return true;

        if (node.left != null && node.left.data.compareTo(node.data) > 0)
            return false;

        if (node.right != null && node.right.data.compareTo(node.data) > 0)
            return false;

        return isMaxHeap(node.left) && isMaxHeap(node.right);
    }

    // ----------------------------------------------------------------------------------

    public void printDiagram(Node<E> root) {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printNodeInternal(List<Node<E>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhiteSpaces(firstSpaces);

        List<Node<E>> newNodes = new ArrayList<>();
        for (Node<E> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhiteSpaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhiteSpaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhiteSpaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    printWhiteSpaces(1);

                printWhiteSpaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    printWhiteSpaces(1);

                printWhiteSpaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private void printWhiteSpaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private int maxLevel(Node<E> node) {
        if (node == null)
            return 0;

        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private boolean isAllElementsNull(List<Node<E>> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }
        return true;
    }
}
