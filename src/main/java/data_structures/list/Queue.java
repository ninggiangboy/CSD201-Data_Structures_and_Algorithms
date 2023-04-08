package data_structures.list;

public class Queue<E> extends LinkedList<E> {

    public Queue() {
    }

    public void enqueue(E e) {
        add(e);
    }

    public E dequeue() {
        if (isEmpty())
            return null;
        return removeHead();
    }

    public E first() {
        if (isEmpty())
            return null;
        return getHead();
    }
}
