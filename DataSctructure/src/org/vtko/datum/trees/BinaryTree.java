
package org.vtko.datum.trees;

import org.vtko.datum.generics.GenericTree;
import org.vtko.datum.generics.TreeNode;


public class BinaryTree<T extends Comparable<T>> extends GenericTree<T> {

    @Override
    public void insert(T element) {

        if (root == null) {
            root = new BinaryTreeNode<T>(element);
        } else {
            insert(root, element);
        }
    }

    @Override
    protected void insert(TreeNode<T> current, T value) {
        TreeNode<T> node = new BinaryTreeNode<>(value);
        TreeNode<T> parent;

        while (true) {
            parent = current;
            if (value.compareTo(current.getElement()) < 0) {
                current = current.getLeft();
                if (current == null) {
                    parent.setLeft(node);
                    break;
                }
            } else if (value.compareTo(current.getElement()) > 0) {
                current = current.getRight();
                if (current == null) {
                    parent.setRight(node);
                    break;
                }
            } else {
                break;
            }
        }
    }

    @Override
    public void delete(T value) {
        delete(root, value);
    }

    @Override
    protected void delete(TreeNode<T> child, T value) {
        TreeNode<T> parent = child;

        while (child != null && value.compareTo(child.getElement()) != 0) {
            parent = child;

            if (value.compareTo(child.getElement()) < 0) {
                child = child.getLeft();
            } else if (value.compareTo(child.getElement()) > 0) {
                child = child.getRight();
            }
        }

        if (child == null) {
            return;
        }

        int countChildren = 0;

        if (child.getLeft() != null) {
            countChildren++;
        }
        if (child.getRight() != null) {
            countChildren++;
        }
        switch (countChildren) {
            case 0:
                removeWithoutChildren(parent, child);
                break;
            case 1:
                removeWithOneChildren(parent, child);
                break;
            case 2:
                removeWithTwoChildren(child);
                break;
        }
    }

    private TreeNode<T> findSuccessor(TreeNode<T> genericTreeNode) {

        TreeNode<T> successor = genericTreeNode.getRight();
        while (successor.getLeft() != null) {
            successor = successor.getLeft();
        }
        return successor;
    }

    protected void removeWithoutChildren(TreeNode<T> parent, TreeNode<T> child) {

        if (child == root) {
            root = null;
        } else {
            if (parent.getLeft() == child) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
    }


    protected void removeWithOneChildren(TreeNode<T> parent, TreeNode<T> child) {
        TreeNode<T> newChild = child.getLeft() != null ? child.getLeft() : child.getRight();

        if (parent.getLeft() == child) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }

    }

    protected void removeWithTwoChildren(TreeNode<T> current) {

        TreeNode<T> successor = findSuccessor(current);
        current.setElement(successor.getElement());
        delete(current.getRight(), successor.getElement());
    }
}

