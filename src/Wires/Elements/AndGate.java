package Wires.Elements;

import Wires.Cell;
import Wires.Elements.WireComponent;

/**
 * Created by zychp_w10 on 27.05.2017.
 */
public class AndGate implements WireComponent {
    private Cell[][] table;
    private int x_size;
    private int y_size;
    private int x_handler;
    private int y_handler;

    public AndGate() {
        x_size = 13;
        y_size = 10;
        x_handler = 0;
        y_handler = 4;
        table = new Cell[x_size][y_size];
        for(int i = 0; i < x_size; i++){
            for(int j =0; j < y_size; j++) {
                table[i][j]=new Cell(Cell.State.EMPTY);
            }
        }
        table[0][4].setState(Cell.State.CONDUCTOR);
        table[2][8].setState(Cell.State.CONDUCTOR);
        table[4][4].setState(Cell.State.CONDUCTOR);
        table[5][3].setState(Cell.State.CONDUCTOR);
        table[5][4].setState(Cell.State.CONDUCTOR);
        table[5][5].setState(Cell.State.CONDUCTOR);
        table[6][4].setState(Cell.State.CONDUCTOR);
        table[8][6].setState(Cell.State.CONDUCTOR);
        table[9][5].setState(Cell.State.CONDUCTOR);
        table[9][6].setState(Cell.State.CONDUCTOR);
        table[9][7].setState(Cell.State.CONDUCTOR);
        table[10][6].setState(Cell.State.CONDUCTOR);

        table[7][3].setState(Cell.State.CONDUCTOR);
        table[7][5].setState(Cell.State.CONDUCTOR);

        table[11][7].setState(Cell.State.CONDUCTOR);
        table[12][7].setState(Cell.State.CONDUCTOR);

        for(int i = 0; i<3;i++) {
            table[8+i][2].setState(Cell.State.CONDUCTOR);
            table[11][3+i].setState(Cell.State.CONDUCTOR);
            table[1][5+i].setState(Cell.State.CONDUCTOR);
            table[3][5+i].setState(Cell.State.CONDUCTOR);
        }
        for(int i = 0; i<8;i++) {
            table[i][1].setState(Cell.State.CONDUCTOR);
        }
    }

    @Override
    public Cell.State getState(int x, int y) {
        return table[x][y].getState();
    }

    @Override
    public int getX_size() {
        return x_size;
    }

    @Override
    public int getY_size() {
        return y_size;
    }

    @Override
    public int getX_handler() {
        return x_handler;
    }

    @Override
    public int getY_handler() {
        return y_handler;
    }
}
