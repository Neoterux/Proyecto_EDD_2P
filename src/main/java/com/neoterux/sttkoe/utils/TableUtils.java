package com.neoterux.sttkoe.utils;

import com.neoterux.sttkoe.custom.controls.GridButton;
import com.neoterux.sttkoe.models.table.Table;
import com.neoterux.sttkoe.models.tree.Tree;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public final class TableUtils {
    
    private TableUtils(){}
    
    @Nullable
    public static GridButton[][] copyTable (@NotNull GridPane table){
        GridButton[][] copy = new GridButton[3][3];
        
        return null;
    }
    
    /**
     * Creates a Matrix of GridButton with a superficial copy of the button.
     *
     * @param table the table that would be copied.
     * @return a new matrix with a copy of available buttons.
     */
    public static GridButton[][] copyTable(@NotNull GridButton[][] table){
        int row = table.length;
        int col = table[0].length;
        GridButton[][] copyMatrix = new GridButton[row][col];
        matrixIter(table,(item, r, c) -> copyMatrix[r][c] = (item == null)? null: item.copy());
        return copyMatrix;
    }
    
    
    /**
     * A helper method to reduce the duplicated code for iterate a matrix.
     *
     * @param matrix the matrix that would be iterated
     * @param <T> the content of the matrix.
     * @param action the action that would be realized in each iteration, the order is Content, row, column
     */
    public static <T> void matrixIter(T[][] matrix, @NotNull TriConsumer<T, Integer, Integer> action){
        int row = 0;
        for(T[] rowArr : matrix){
            int col = 0;
            for (T colItem : rowArr){
                action.accept(colItem, row, col);
                col++;
            }
            row++;
        }
    }
    
    /**
     * This method help to fill a matrix, with a value returned by the {@literal fillAction}.
     *
     * @param matrix the matrix that would be filled.
     * @param fillAction the method that would return the value to be stored, receives the row and col
     * @param <T> the Type that the matrix store.
     */
    public static <T> void matrixIterFiller(final T[][] matrix, @NotNull BiFunction<Integer, Integer, T> fillAction){
        int rowCount = matrix.length;
        for (int i = 0; i < rowCount; i++){
            int colCount = matrix[i].length; // this may not be necessary to get in every loop but :p
            for (int j = 0; j < colCount; j++) {
                // the apply method receives the row and the column respectively
                matrix[i][j] = fillAction.apply(i,j);
            }
        }
    }

    public static Tree<Table> childenByUtility(Tree<Table> tree){
        return tree.getRoot().getChildren().peek();
    }
}
