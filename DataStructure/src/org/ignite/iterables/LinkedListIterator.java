package org.ignite.iterables;

import java.util.Iterator;

public class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> current;

    public LinkedListIterator(Node<T> node) {
        this.current = node;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T value = current.getValue();
        current = current.getNext();
        return value;
    }

}
