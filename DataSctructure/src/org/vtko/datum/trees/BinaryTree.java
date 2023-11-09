package org.vtko.datum.trees;

import java.util.*;


public class BinaryTree<T extends Comparable<T>> extends GenericTree<T> {

    @Override
    public T get(T element) {

        TreeNode<T> current = this.root;

        while (current != null) {
            if (current.element().compareTo(element) > 0) {
                current = current.getLeftChild();
            } else if (current.element().compareTo(element) < 0) {
                current = current.getRightChild();
            } else {
                return current.element();
            }
        }

        return null;
    }

    @Override
    protected NodePair<T> getNode(T element) {

        TreeNode<T> current = this.root;
        TreeNode<T> parent = null;

        while (current != null) {
            if (current.element().compareTo(element) > 0) {
                parent = current;
                current = current.getLeftChild();
            } else if (current.element().compareTo(element) < 0) {
                parent = current;
                current = current.getRightChild();
            } else {
                return new NodePair<>(parent, current);
            }
        }

        return null;
    }


    @Override
    public void insert(T element) {
        if (this.size == 0) {
            this.root = new TreeNode<>(element);
        } else {
            insert(this.root, element);
        }
        this.size++;
    }

    @Override
    protected void insert(TreeNode<T> node, T element) {
        TreeNode<T> current = node;

        while (true) {
            if (current.element().compareTo(element) > 0) {
                if (current.getLeftChild() == null) {
                    current.left(new TreeNode<>(element));
                    break;
                }
                current = current.getLeftChild();

            } else if (current.element().compareTo(element) < 0) {
                if (current.getRightChild() == null) {
                    current.right(new TreeNode<>(element));
                    break;
                }
                current = current.getRightChild();
            } else {
                return;
            }
        }

        size++;
    }

    @Override
    public void delete(T element) {

        NodePair<T> pair = getNode(element);
        int childrenCount = 0;

        if (pair == null) {
            return;
        }

        if (pair.getChild().getLeftChild() != null) {
            childrenCount++;
        }
        if (pair.getChild().getRightChild() != null) {
            childrenCount++;
        }

        switch (childrenCount) {
            case 0:
                removeWithoutChildren(pair.getChild());
                break;
            case 1:
                removeWithOneChild(pair);
                break;
            case 2:
                removeWithTwoChildren(pair.getChild());
                break;
        }
    }

    private void removeWithoutChildren(TreeNode<T> node) {
        NodePair<T> pair = getNode(node.element());

        if (pair == null) {
            return;
        }

        TreeNode<T> parent = pair.getParent();
        TreeNode<T> currentNode = pair.getChild();

        if (parent != null) {
            if (parent.getLeftChild() == currentNode) {
                parent.left(null);
            } else {
                parent.right(null);
            }
        } else {
            this.root = null;
        }
    }

    private void removeWithOneChild(NodePair<T> pair) {
        if (pair.getChild().getLeftChild() == null) {
            pair.getParent().right(pair.getChild().getRightChild());
            return;
        }
        pair.getParent().left(pair.getChild().getLeftChild());
    }

    private void removeWithTwoChildren(TreeNode<T> node) {
        TreeNode<T> successor = findLastSuccessor(node).getChild();
        node.setElement(successor.element());
        removeWithoutChildren(successor);
    }

    private NodePair<T> findLastSuccessor(TreeNode<T> node) {
        NodePair<T> pair = getNode(node.element());
        TreeNode<T> current = pair.getChild().getRightChild();
        TreeNode<T> parent = pair.getParent();

        while (current.getLeftChild() != null) {
            parent = current;
            current = current.getLeftChild();
        }

        return new NodePair<>(parent, current);
    }

    private void getElements(List<TreeNode<T>> elements, TreeNode<T> node) {

        if (node == null) {
            return;
        }

        if (elements.size() != this.size) {

            elements.add(node);
            getElements(elements, node.getLeftChild());
            getElements(elements, node.getRightChild());
        }
    }

    @Override
    public void foreach(ForeachCallback<T> callback) {
        List<TreeNode<T>> list = new ArrayList<>();
        getElements(list, root);
        for (TreeNode<T> item : list) {
            callback.consume(item.element());
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator<>(this.root);
    }


    public List<T> traverse(TraverseType traverseType) {

        List<T> elements = new ArrayList<>(this.size);

        switch (traverseType) {
            case IN_ORDER -> traverseInOrder(elements, root);

            case PRE_ORDER -> traversePreOrder(elements, root);

            case POS_ORDER -> traversePosOrder(elements, root);

            case IN_LEVEL -> traverseInLevel(elements, root);
        };

        return elements;
    }

    private void traverseInOrder(List<T> elements, TreeNode<T> node) {
        if (node != null) {
            traverseInOrder(elements, node.getLeftChild());
            elements.add(node.element());
            traverseInOrder(elements, node.getRightChild());
        }
    }

    private void traversePosOrder(List<T> elements, TreeNode<T> node) {
        if (node != null) {
            traversePosOrder(elements, node.getLeftChild());
            traversePosOrder(elements, node.getRightChild());
            elements.add(node.element());
        }
    }

    private void traversePreOrder(List<T> elements, TreeNode<T> node) {
        if (node != null) {
            elements.add(node.element());
            traversePreOrder(elements, node.getLeftChild());
            traversePreOrder(elements, node.getRightChild());
        }
    }

    private void traverseInLevel(List<T> elements, TreeNode<T> node) {
        BinaryTreeNodeIterator iterator = new BinaryTreeNodeIterator(this.root);

        while(iterator.hasNext()){
            elements.add(iterator.next().element());
        }
    }

    private class BinaryTreeNodeIterator implements Iterator<TreeNode<T>> {
        private final Stack<TreeNode<T>> stack;

        public BinaryTreeNodeIterator(TreeNode<T> root) {
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
        public TreeNode<T> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the tree");
            }

            TreeNode<T> node = stack.pop();
            populateStack(node.getRightChild());

            return node;
        }
    }
}
