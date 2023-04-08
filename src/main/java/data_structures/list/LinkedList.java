package data_structures.list;

import java.util.*;

public class LinkedList<E> {

    private static class Node<E> {
        E data;
        Node<E> next, prev;

        Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head, tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            arr[i++] = node.data;
        }
        return arr;
    }

    public boolean add(E e) {
        linkTail(e);
        return true;
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> prevNode, nextNode;
        if (index == size) {
            nextNode = null;
            prevNode = tail;
        } else {
            nextNode = search(index);
            prevNode = nextNode.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked")
            E e = (E) o;
            Node<E> newNode = new Node<>(e, prevNode, null);
            if (prevNode == null)
                head = newNode;
            else
                prevNode.next = newNode;
            prevNode = newNode;
        }

        if (nextNode == null) {
            tail = prevNode;
        } else {
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        size += numNew;
        return true;
    }

    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }

    public LinkedList<E> clone() {
        LinkedList<E> clone = new LinkedList<>();

        for (Node<E> x = head; x != null; x = x.next)
            clone.add(x.data);

        return clone;
    }

    public E get(int index) {
        checkElementIndex(index);
        return search(index).data;
    }

    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = search(index);
        E oldVal = x.data;
        x.data = element;
        return oldVal;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkTail(element);
        else
            linkBefore(element, search(index));
    }

    public E remove(int index) {
        checkElementIndex(index);
        return unlink(search(index));
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.data == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (o.equals(x.data))
                    return index;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (x.data == null)
                    return index;
            }
        } else {
            for (Node<E> x = tail; x != null; x = x.prev) {
                index--;
                if (o.equals(x.data))
                    return index;
            }
        }
        return -1;
    }

    public E getHead() {
        final Node<E> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return head.data;
    }

    public E getTail() {
        final Node<E> t = tail;
        if (t == null)
            throw new NoSuchElementException();
        return tail.data;
    }

    public void addHead(E e) {
        linkHead(e);
    }

    public void addTail(E e) {
        linkTail(e);
    }

    public E removeHead() {
        final Node<E> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return unlinkFirst(h);
    }

    public E removeTail() {
        final Node<E> t = tail;
        if (t == null)
            throw new NoSuchElementException();
        return unlinkTail(t);
    }

    private Node<E> search(int index) {
        // assert isElementIndex(index)
        Node<E> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private void linkHead(E e) {
        final Node<E> h = head;
        final Node<E> newNode = new Node<>(e, null, h);
        head = newNode;
        if (h == null)
            tail = newNode;
        else
            h.prev = newNode;
        size++;
    }

    private void linkTail(E e) {
        final Node<E> t = tail;
        final Node<E> newNode = new Node<>(e, t, null);
        tail = newNode;
        if (t == null)
            head = newNode;
        else
            t.next = newNode;
        size++;
    }

    private void linkBefore(E e, Node<E> nextNode) {
        // assert nextNode != null;
        final Node<E> prev = nextNode.prev;
        final Node<E> newNode = new Node<>(e, prev, nextNode);
        nextNode.prev = newNode;
        if (prev == null)
            head = newNode;
        else
            prev.next = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> h) {
        // assert h == head && l != null;
        final E element = h.data;
        final Node<E> nextNode = h.next;
        h.data = null;
        h.next = null;
        head = nextNode;
        if (nextNode == null)
            tail = null;
        else
            nextNode.prev = null;
        size--;
        return element;
    }

    private E unlinkTail(Node<E> t) {
        // assert t == tail && t != null;
        final E element = t.data;
        final Node<E> prevNode = t.prev;
        t.data = null;
        t.prev = null;
        tail = prevNode;
        if (prevNode == null)
            head = null;
        else
            prevNode.next = null;
        size--;
        return element;
    }

    private E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.data;
        final Node<E> nextNode = x.next;
        final Node<E> prevNode = x.prev;

        if (prevNode == null)
            head = nextNode;
        else {
            prevNode.next = nextNode;
            x.prev = null;
        }

        if (nextNode == null)
            tail = prevNode;
        else {
            nextNode.prev = prevNode;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
}
