package com.neoterux.sttkoe.models.tree;

import java.util.Comparator;

/**
 * A comparator for tree to compare its types
 * @param <T>
 */
@FunctionalInterface
public interface TreeComparator <T> extends Comparator<Tree<T>> {
    
    int compareContent(T o1, T o2);
    
    @Override
    default int compare(Tree<T> t1, Tree<T> t2){
        return compareContent(t1.getRoot().getContent(), t2.getRoot().getContent());
    }
   
}
