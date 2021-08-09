package com.neoterux.sttkoe.models.tree;

public class Tree<V> {
    private TreeNode<V> root;
    private boolean isUsed;

    public Tree(TreeNode<V> root){
        this.root = root;
    }
    public Tree(){}

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

    public Tree<V> getChildrenByUtility(){
        return root.getChildren().peek();
    }

}
