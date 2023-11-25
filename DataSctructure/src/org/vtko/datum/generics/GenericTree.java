package org.vtko.datum.generics;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericTree<T extends Comparable<T>, N extends TreeNode<T>> {
    protected N root;

    public abstract void insert(T element);

    protected abstract void insert(N current, T value);

    public abstract void delete(T element);

    protected abstract void delete(N current, T value);

    protected abstract void removeWithoutChildren(N parent, N child);

    protected abstract void removeWithOneChildren(N parent, N child);

    protected abstract void removeWithTwoChildren(N current);

    public List<T> traverse(TraverseType type) {

        List<T> elements = new ArrayList<>();

        switch (type) {
            case PRE_ORDER -> TreeIterator.traversePreOrder(elements, root);
            case POS_ORDER -> TreeIterator.traversePosOrder(elements, root);
            case IN_LEVEL -> TreeIterator.levelOrderTraversal(elements, root);
            case IN_ORDER -> TreeIterator.inOrderTraversal(elements, root);
        };

        return elements;
    }
}
