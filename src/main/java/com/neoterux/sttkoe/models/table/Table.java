package com.neoterux.sttkoe.models.table;

public class Table<T> {
    private int utility;
    private T table[][] = (T[][]) (new Object[3][3]);

    public Table(T[][] table){
        this.table = table;
    }
    public Table(){}

    //Getting the number of rows available
    private int utilityByRows(T oponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (table[i][0] != oponentSymbol && table[i][1] != oponentSymbol && table[i][2] != oponentSymbol)
                count++;
        }
        return count;
    }

    //Getting the number of columns available
    private int utilityByColumns(T oponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (table[0][i] != oponentSymbol && table[1][i] != oponentSymbol && table[2][i] != oponentSymbol)
                count++;
        }
        return count;
    }

    //Getting the number of diagonals available
    private int utilityByDiagonals(T oponentSymbol){
        if(table[1][1] == oponentSymbol) return 0;
        int count = 0;
        if(table[0][0] != oponentSymbol && table[2][2] != oponentSymbol) count++;
        if(table[2][0] != oponentSymbol && table[0][2] != oponentSymbol) count++;
        return count;
    }

    /**
     * Getting the table utility
     * @return Table utility
     */
    public int getUtility() {
        return utility;
    }

    public T[][] getTable() {
        return table;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void setTable(T[][] table) {
        this.table = table;
    }

    /**
     * Generating the table utility
     * @param computerSymbol The symbol the computer uses
     * @param humanSymbol The symbol the user uses
     */
    public void generateUtility(T computerSymbol, T humanSymbol){
        int computer = utilityByRows(humanSymbol) + utilityByColumns(humanSymbol) + utilityByDiagonals(humanSymbol);
        int human = utilityByRows(computerSymbol) + utilityByColumns(computerSymbol) + utilityByDiagonals(computerSymbol);
        setUtility(computer-human);
    }

    public void insertValue(int positionI, int positionJ, T value){
        table[positionI][positionJ] = value;
    }

    /**
     * Printing the table
     */
    public void printTable(){
        for (T[] row: table) {
            for (T colum: row) {
                System.out.print(colum);
            }
            System.out.println("");
        }
    }
}
