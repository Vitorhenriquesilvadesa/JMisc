package com.swlo.tree;

import java.util.List;

public class BinaryTree<T extends Comparable<T>> extends GenericTree<T> {

    @Override
    public void add(T element) {
        if (getRoot() == null) {
            setRoot(new TreeNode<>(element));
            return;
        }

        TreeNode<T> current = getRoot();

        while (true) {
            if (element.compareTo(current.getElement()) < 0) {
                if (current.getLeftChild() == null) {
                    current.setLeftChild(new TreeNode<>(element, current));
                    break;
                } else {
                    current = current.getLeftChild();
                }
            }

            if (element.compareTo(current.getElement()) > 0) {
                if (current.getRightChild() == null) {
                    current.setRightChild(new TreeNode<>(element, current));
                    break;
                } else {
                    current = current.getRightChild();
                }
            }

            if (element.compareTo(current.getElement()) == 0) {
                break;
            }
        }
    }

    @Override
    public void remove(T element) {

        if (getRoot() == null) {
            return;
        }


        TreeNode<T> current = search(element);
        TreeNode<T> auxiliary = null;


        if (current.getLeftChild() == null && current.getRightChild() == null) {
            auxiliary = current.getParent();
            if (auxiliary.getLeftChild() == current) {
                auxiliary.setLeftChild(null);
            } else {
                auxiliary.setRightChild(null);
            }
        }

        if (current.getLeftChild() != null && current.getRightChild() == null) {
            auxiliary = current.getParent();
            if (auxiliary.getLeftChild() == current) {
                auxiliary.setLeftChild(current.getLeftChild());
                auxiliary.getLeftChild().setParent(auxiliary);
            } else {
                auxiliary.setRightChild(current.getLeftChild());
                auxiliary.getRightChild().setParent(auxiliary);
            }
        }

        if (current.getLeftChild() == null && current.getRightChild() != null) {
            auxiliary = current.getParent();
            if (auxiliary.getLeftChild() == current) {
                auxiliary.setLeftChild(current.getRightChild());
                auxiliary.getLeftChild().setParent(auxiliary);
            } else {
                auxiliary.setRightChild(current.getRightChild());
                auxiliary.getRightChild().setParent(auxiliary);
            }
        }

        if (current.getLeftChild() != null && current.getRightChild() != null) {
            auxiliary = current.getRightChild();
            while (auxiliary.getLeftChild() != null) {
                auxiliary = auxiliary.getLeftChild();
            }
            current.setElement(auxiliary.getElement());
            if (auxiliary.getRightChild() != null) {
                auxiliary.setElement(auxiliary.getRightChild().getElement());
                auxiliary.setRightChild(null);
            } else {
                auxiliary.setParent(auxiliary.getParent());
                auxiliary.getParent().setLeftChild(null);
            }

        }

    }

    @Override
    public TreeNode<T> search(T element) {

        if (getRoot() == null) {
            return null;
        }

        TreeNode<T> current = getRoot();

        while (true) {
            if (element.compareTo(current.getElement()) < 0) {
                if (current.getLeftChild() == null) {
                    return null;
                } else {
                    current = current.getLeftChild();
                }
            }
            if (element.compareTo(current.getElement()) > 0) {
                if (current.getRightChild() == null) {
                    return null;
                } else {
                    current = current.getRightChild();
                }
            }

            if (element.compareTo(current.getElement()) == 0) {
                return current;
            }
        }

    }

    @SuppressWarnings("unchecked")
    public List<String> getOrdered(PrintEnum type) {

        return type.getFunction().apply(getRoot());


    }
}