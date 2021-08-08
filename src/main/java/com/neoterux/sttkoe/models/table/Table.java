package com.neoterux.sttkoe.models.table;

public class Table<T> {
    private int utility;
    private T table[][] =(T[][]) (new Object[3][3]);

    public Table(T[][] table){
        this.table = table;
    }

    private int utilityByRows(String oponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (table[i][0] != oponentSymbol && table[i][1] != oponentSymbol && table[i][2] != oponentSymbol)
                count++;
        }
        return count;
    }

    private int utilityByColumns(String oponentSymbol){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (table[0][i] != oponentSymbol && table[1][i] != oponentSymbol && table[2][i] != oponentSymbol)
                count++;
        }
        return count;
    }

    private int utilityByDiagonals(String oponentSymbol){
        if(table[1][1] == oponentSymbol) return 0;
        int count = 0;
        if(table[0][0] != oponentSymbol && table[2][2] != oponentSymbol) count++;
        if(table[2][0] != oponentSymbol && table[0][2] != oponentSymbol) count++;
        return count;
    }

    public int getUtility() {
        return utility;
    }

    public T[][] getTable() {
        return table;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }

    public void generateUtility(String computerSymbol, String humanSymbol){
        int computer = utilityByRows(humanSymbol) + utilityByColumns(humanSymbol) + utilityByDiagonals(humanSymbol);
        int human = utilityByRows(computerSymbol) + utilityByColumns(computerSymbol) + utilityByDiagonals(computerSymbol);
        setUtility(computer-human);
    }

    public void printTable(){
        for (T[] row: table) {
            for (T colum: row) {
                System.out.print(colum);
            }
            System.out.println("");
        }
    }
}
