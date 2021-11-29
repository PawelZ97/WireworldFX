package com.zychp.Wires.Elements;

import com.zychp.Wires.Cell;

/**
 * Created by zychp_w10 on 27.05.2017.
 */
public class DiodeRev implements WireComponent {
    private final Cell[][] table;
    private final int x_size;
    private final int y_size;
    private final int x_handler;
    private final int y_handler;

    public DiodeRev() {
        x_size = 6;
        y_size = 5;
        table = new Cell[x_size][y_size];
        for(int i = 0; i < x_size; i++){
            for(int j =0; j < y_size; j++) {
                table[i][j]=new Cell(Cell.State.EMPTY);
            }
        }
        table[2][1].setState(Cell.State.CONDUCTOR);
        table[3][1].setState(Cell.State.CONDUCTOR);
        table[2][3].setState(Cell.State.CONDUCTOR);
        table[3][3].setState(Cell.State.CONDUCTOR);
        for(int i = 0; i<x_size;i++) {
            if (i != 3)
                table[i][2].setState(Cell.State.CONDUCTOR);
        }
        x_handler = 0;
        y_handler = 2;
    }

    public Cell.State getState(int x, int y){
        return table[x][y].getState();
    }

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }

    public int getX_handler() {
        return x_handler;
    }

    public int getY_handler() {
        return y_handler;
    }
}