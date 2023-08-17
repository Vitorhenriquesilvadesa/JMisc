package org.ignite.iterables;

public interface Sort<T> {
    public abstract List<T> sort(List<T> source);
}
