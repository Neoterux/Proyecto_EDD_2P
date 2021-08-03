package com.neoterux.sttkoe.models.tree;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Tree<V> {
    private TreeNode<V> root;
    private Comparator<Tree<V>> cmp;

    public Tree(TreeNode<V> root){
        this.root = root;
    }

    private void initializeChildren(){
        root.setChildren(new PriorityQueue<>(this.cmp));
    }

    public void setCmp(Comparator<Tree<V>> cmp) {
        this.cmp = cmp;
        initializeChildren();
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
