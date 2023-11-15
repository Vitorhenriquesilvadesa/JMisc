package org.vtko.datum.generics;

import java.util.*;

public abstract class TreeIterator {

    public static <T> List<T> inOrderTraversal(List<T> elements, TreeNode<T> root) {
        TreeNode<T> current = root;
        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
        while (current != null || !stack.empty()) {
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            current = stack.pop();
            elements.add(current.getElement());
            current = current.getRight();
        }

        return elements;
    }

    public static <T> void traversePreOrder(List<T> elements, TreeNode<T> node) {
        if (node != null) {
            elements.add(node.getElement());
            traversePreOrder(elements, node.getLeft());
            traversePreOrder(elements, node.getRight());
        }
    }

    public static <T> void traversePosOrder(List<T> elements, TreeNode<T> node) {
        if (node != null) {
            traversePosOrder(elements, node.getLeft());
            traversePosOrder(elements, node.getRight());
            elements.add(node.getElement());
        }
    }

    public static <T> void levelOrderTraversal(List<T> elements, TreeNode<T> root) {
        var queue = new LinkedList<TreeNode<T>>();
        queue.add(root);
        TreeNode<T> current;

        while (!queue.isEmpty()) {
            current = queue.poll();
            elements.add(current.getElement());
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }
}
