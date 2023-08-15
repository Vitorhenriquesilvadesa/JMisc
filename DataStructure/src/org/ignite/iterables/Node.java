package org.ignite.iterables;

public class Node<T> {

    private Node<T> next;
    private Node<T> prev;
    private T value;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

    public Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
        this.prev = null;
    }

    public Node(T value, Node<T> next, Node<T> prev) {
        this.next = next;
        this.prev = prev;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
