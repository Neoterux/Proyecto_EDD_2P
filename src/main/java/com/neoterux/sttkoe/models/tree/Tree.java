package com.neoterux.sttkoe.models.tree;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Tree<V> {
    private TreeNode<V> root;
    private Comparator<V> cmp;

    public Tree(TreeNode<V> root){
        this.root = root;
    }

    private void initializeChildren(Comparator cmp){
        root.setChildren(new PriorityQueue<>(cmp));
    }

    public void setCmp(Comparator<V> cmp) {
        this.cmp = cmp;
        initializeChildren(cmp);
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
