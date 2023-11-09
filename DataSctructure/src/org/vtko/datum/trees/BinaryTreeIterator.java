package org.vtko.datum.trees;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Stack<TreeNode<T>> stack;

    public BinaryTreeIterator(TreeNode<T> root) {
        this.stack = new Stack<>();
        populateStack(root);
    }

    private void populateStack(TreeNode<T> node) {
        while (node != null) {
            stack.push(node);
            node = node.getLeftChild();
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the tree");
        }

        TreeNode<T> node = stack.pop();
        populateStack(node.getRightChild());

        return node.element();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation is not supported.");
    }
}
