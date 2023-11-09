package org.vtko.datum.trees;

public class TreeNode<T extends Comparable<T>>{
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;
    private T element;

    public TreeNode(T element){
        this.element = element;
    }

    public T element(){
        return this.element;
    }

    public TreeNode<T> left(TreeNode<T> leftChild){
        this.leftChild = leftChild;
        return this;
    }

    public TreeNode<T> right(TreeNode<T> rightChild){
        this.rightChild = rightChild;
        return this;
    }

    public TreeNode<T> getLeftChild(){
        return this.leftChild;
    }

    public TreeNode<T> getRightChild(){
        return this.rightChild;
    }

    public void setElement(T element) {
        this.element = element;
    }
}
