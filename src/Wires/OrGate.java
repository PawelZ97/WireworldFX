package Wires;

/**
 * Created by zychp_w10 on 28.05.2017.
 */
public class OrGate implements WireComponent {
    private Cell[][] table;
    private int x_size;
    private int y_size;
    private int x_handler;
    private int y_handler;

    public OrGate() {
        x_size = 7;
        y_size = 7;
        x_handler = 0;
        y_handler = 2;
        table = new Cell[x_size][y_size];
        for(int i = 0; i < x_size; i++){
            for(int j =0; j < y_size; j++) {
                table[i][j]=new Cell(Cell.State.EMPTY);
            }
        }
        table[0][2].setState(Cell.State.CONDUCTOR);
        table[0][4].setState(Cell.State.CONDUCTOR);
        table[1][2].setState(Cell.State.CONDUCTOR);
        table[1][4].setState(Cell.State.CONDUCTOR);
        table[2][1].setState(Cell.State.CONDUCTOR);
        table[2][5].setState(Cell.State.CONDUCTOR);
        table[3][1].setState(Cell.State.CONDUCTOR);
        table[3][5].setState(Cell.State.CONDUCTOR);

        table[4][2].setState(Cell.State.CONDUCTOR);
        table[4][4].setState(Cell.State.CONDUCTOR);

        for(int i = 0; i<4;i++) {
            table[3+i][3].setState(Cell.State.CONDUCTOR);
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
