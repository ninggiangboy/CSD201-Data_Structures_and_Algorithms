package data_structures.list;

public class Stack<E> extends LinkedList<E> {

    public Stack() {
    }

    public void push(E item) {
        add(item);
    }

    public E pop() {
        if (isEmpty())
            return null;
        return removeTail();
    }

    public E top() {
        if (isEmpty())
            return null;
        return getTail();
    }
}
