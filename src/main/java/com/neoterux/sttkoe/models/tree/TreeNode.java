package com.neoterux.sttkoe.models.tree;

import java.util.List;
import java.util.PriorityQueue;

public class TreeNode<V> {
    private V content;
    private PriorityQueue<Tree<V>> children;

    public TreeNode(V value){
        this.content = value;
    }

    public PriorityQueue<Tree<V>> getChildren() {
        return children;
    }

    public V getContent() {
        return content;
    }

    public void setChildren(PriorityQueue<Tree<V>> children) {
        this.children = children;
    }

    public void setContent(V content) {
        this.content = content;
    }


    public void addChildren(List<V> list){
        for (V value:list) {
            this.children.add(new Tree<>(new TreeNode<>(value)));
        }
    }
}
