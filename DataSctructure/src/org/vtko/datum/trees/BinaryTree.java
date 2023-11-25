package org.vtko.datum.trees;

import org.vtko.datum.generics.GenericTree;
import org.vtko.datum.generics.TraverseType;
import org.vtko.datum.generics.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class BinaryTree<T extends Comparable<T>> extends GenericTree<T, BinaryTreeNode<T>> {

    @Override
    public void insert(T element) {

        if (root == null) {
            root = new BinaryTreeNode<T>(element);
        } else {
            insert(root, element);
        }
    }

    @Override
    protected void insert(BinaryTreeNode<T> current, T value) {
        BinaryTreeNode<T> node = new BinaryTreeNode<>(value);
        BinaryTreeNode<T> parent;

        while (true) {
            parent = current;
            if (value.compareTo(current.getElement()) < 0) {
                current = (BinaryTreeNode<T>) current.getLeft();
                if (current == null) {
                    parent.setLeft(node);
                    break;
                }
            } else if (value.compareTo(current.getElement()) > 0) {
                current = (BinaryTreeNode<T>) current.getRight();
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
    protected void delete(BinaryTreeNode<T> child, T value) {
        BinaryTreeNode<T> parent = child;

        while (child != null && value.compareTo(child.getElement()) != 0) {
            parent = child;

            if (value.compareTo(child.getElement()) < 0) {
                child = (BinaryTreeNode<T>) child.getLeft();
            } else if (value.compareTo(child.getElement()) > 0) {
                child = (BinaryTreeNode<T>) child.getRight();
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

    protected void removeWithoutChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> child) {

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


    protected void removeWithOneChildren(BinaryTreeNode<T> parent, BinaryTreeNode<T> child) {
        TreeNode<T> newChild = child.getLeft() != null ? child.getLeft() : child.getRight();

        if (parent.getLeft() == child) {
            parent.setLeft(newChild);
        } else {
            parent.setRight(newChild);
        }
    }

    protected void removeWithTwoChildren(BinaryTreeNode<T> current) {

        TreeNode<T> successor = findSuccessor(current);
        current.setElement(successor.getElement());
        delete((BinaryTreeNode<T>) current.getRight(), successor.getElement());
    }

    public void balance() {

        try {
            List<T> orderElements = this.traverse(TraverseType.IN_ORDER);
            List<T> balancedElements = new ArrayList<>();

            @SuppressWarnings("unchecked")
            BinaryTree<T> balancedTree = getClass().getDeclaredConstructor().newInstance();

            while (!orderElements.isEmpty()) {
                T firstElement = orderElements.remove(0);

                if (!orderElements.isEmpty()) {
                    T nextElement = orderElements.remove(0);
                    balancedElements.add(nextElement);
                }

                balancedElements.add(firstElement);
            }

            while (!balancedElements.isEmpty()) {
                balancedTree.insert(balancedElements.remove(0));
            }

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            System.err.println(e.getMessage());
        }
    }
}

