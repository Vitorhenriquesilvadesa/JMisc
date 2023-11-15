package org.vtko.datum.generics;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericTree<T extends Comparable<T>> {
    protected TreeNode<T> root;

    public abstract void insert(T element);

    protected abstract void insert(TreeNode<T> current, T value);

    public abstract void delete(T element);

    protected abstract void delete(TreeNode<T> current, T value);

    protected abstract void removeWithoutChildren(TreeNode<T> parent, TreeNode<T> child);

    protected abstract void removeWithOneChildren(TreeNode<T> parent, TreeNode<T> child);

    protected abstract void removeWithTwoChildren(TreeNode<T> current);

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
