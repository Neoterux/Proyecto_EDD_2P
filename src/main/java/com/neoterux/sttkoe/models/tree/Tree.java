package com.neoterux.sttkoe.models.tree;

/**
 * <h1>Tree</h1>
 * This class is a tda that represent a tree data structure, support more than
 * 2 child.
 *
 * @param <V> the type that this tree store.
 */
public class Tree<V> {
    /**
     * The root node of the tree.
     */
    private TreeNode<V> root;

    /**
     * Creates a new Tree with the specified root value.
     *
     * @param rootContent the value of the root.
     */
    public Tree(V rootContent){
        this.root = new TreeNode<>(rootContent);
    }

    /**
     * Creates a new Tree with root content as inserted.
     *
     * @param root the root of the tree
     */
    public Tree(TreeNode<V> root){
        this.root = root;
    }

    /**
     * Creates an empty tree.
     */
    public Tree(){
        this((V) null);
    }

    /**
     * Sets a new value for the root node that this tree holds
     *
     * @param root the new root value
     */
    public void setRoot(TreeNode<V> root) {
        this.root = root;
    }

    public TreeNode<V> getRoot() {
        return root;
    }

    public Tree<V> getChildrenByUtility(){
        return root.getChildren().peek();
    }

}
