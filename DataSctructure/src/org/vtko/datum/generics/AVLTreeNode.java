package org.vtko.datum.generics;

public class AVLTreeNode<T> extends  TreeNode<T> {

    AVLTreeNode<T> parent;
    int balanceFactor;

    public AVLTreeNode(T value, AVLTreeNode<T> father) {
        super(value);
        this.balanceFactor = 0;
    }

    public AVLTreeNode<T> parent(AVLTreeNode<T> father){
        this.parent = father;
        return this;
    }

    public AVLTreeNode<T> getParent(){
        return this.parent;
    }

    public void incrementBalanceFactor(){
        this.balanceFactor++;
    }

    public void decrementBalanceFactor(){
        this.balanceFactor--;
    }

    public int getBalanceFactor(){
        return this.balanceFactor;
    }
}
