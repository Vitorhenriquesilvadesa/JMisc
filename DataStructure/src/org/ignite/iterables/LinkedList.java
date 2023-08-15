package org.ignite.iterables;

import java.util.Iterator;

public class LinkedList<T> extends List<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this.first);
    }

    @Override
    public Node<T> getNode(int index) {

        Node<T> current = this.first;

        if (index < this.size && index >= 0) {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            return current;
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public void set(int index, T value) {
        Node<T> node = getNode(index);
        node.setValue(value);
    }

    @Override
    public T get(int index) {

        Node<T> current = this.first;

        if (index < this.size && index >= 0) {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            return current.getValue();
        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

    @Override
    public void add(T element) {

        if (this.size == 0) {
            Node<T> node = new Node<T>(element);
            this.first = node;
            this.first.setNext(this.last);
            this.last = node;
            this.size++;
        } else {

            Node<T> node = new Node<T>(element);
            this.last.setNext(node);
            this.last = node;
            this.size++;
        }
    }

    @Override
    public T remove(int index) {

        if (index < this.size && index >= 0) {
            Node<T> node = getNode(index);
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());

            T value = node.getValue();
            node = null;
            this.size--;

            return value;

        } else {
            throw new IndexOutOfBoundsException(index);
        }
    }

}