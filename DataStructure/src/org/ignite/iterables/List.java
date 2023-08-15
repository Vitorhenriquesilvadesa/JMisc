package org.ignite.iterables;

public abstract class List<T> {

    public abstract Node<T> getNode(int index);

    public abstract void set(int index, T value);

    public abstract T get(int index);

    public abstract void add(T element);

    public abstract T remove(int index);
}