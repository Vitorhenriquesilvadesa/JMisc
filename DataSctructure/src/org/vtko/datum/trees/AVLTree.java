package org.vtko.datum.trees;

import org.vtko.datum.generics.AVLTreeNode;
import org.vtko.datum.generics.GenericTree;
import org.vtko.datum.generics.TreeNode;

public class AVLTree<T extends Comparable<T>> extends GenericTree<T, AVLTreeNode<T>> {


    @Override
    public void insert(T element) {

    }

    @Override
    protected void insert(AVLTreeNode<T> current, T value) {

    }

    @Override
    public void delete(T element) {

    }

    @Override
    protected void delete(AVLTreeNode<T> current, T value) {

    }

    @Override
    protected void removeWithoutChildren(AVLTreeNode<T> parent, AVLTreeNode<T> child) {

    }

    @Override
    protected void removeWithOneChildren(AVLTreeNode<T> parent, AVLTreeNode<T> child) {

    }

    @Override
    protected void removeWithTwoChildren(AVLTreeNode<T> current) {

    }

    private enum BalanceType {
        LEFT,
        RIGHT
    }
}
