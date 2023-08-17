package org.ignite.iterables;

public abstract class List<T> implements Iterable<T> {

    protected int size;

    protected abstract Node<T> getNode(int index);

    public abstract void set(int index, T value);

    public abstract T get(int index);

    public abstract void add(T element);

    public abstract void add(int index, T element);

    public abstract T remove(int index);

    public abstract List<T> sort(Sort<T> sortMethod);

    public abstract List<T> sort();

    public abstract int size();
}