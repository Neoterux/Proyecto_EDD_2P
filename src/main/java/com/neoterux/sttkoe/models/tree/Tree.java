package com.neoterux.sttkoe.models.tree;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Tree<V> {
    private TreeNode<V> root;
    private boolean isUsed;
    private Comparator<Tree<V>> cmp;

    public Tree(TreeNode<V> root){
        this.root = root;
    }
    public Tree(){}

    private void initializeChildren(){
        root.setChildren(new PriorityQueue<>(this.cmp));
    }

    public void setComparator(Comparator<Tree<V>> cmp) {
        this.cmp = cmp;
        initializeChildren();
    }

    public void setRoot(TreeNode<V> root) {
        this.root = root;
    }

    public void setIsUsed(boolean used) {
        isUsed = used;
    }

    public boolean getIsUsed(){
        return isUsed;
    }

    public TreeNode<V> getRoot() {
        return root;
    }

    public void setChildren(List<V> list){
        root.addChildren(list);
    }

    public Tree<V> getChildrenByUtility(){
        return root.getChildren().peek();
    }
}
