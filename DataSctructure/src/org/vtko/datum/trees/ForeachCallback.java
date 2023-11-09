package org.vtko.datum.trees;

import java.lang.FunctionalInterface;
@FunctionalInterface
public interface ForeachCallback<T> {
    public void consume(T element);
}
