package Wires;

/**
 * Created by zychp_w10 on 27.05.2017.
 */
public class Diode implements WireComponent {
    /**
     * Komponent do rysowania.
     */
    private Cell[][] table;
    private int x_size;
    private int y_size;
    private int x_handler;
    private int y_handler;

    public Diode() {
        /**
         * Tworzy komponent ze z góry narzuconymi parametrami.
         */
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
            if (i != 2)
                table[i][2].setState(Cell.State.CONDUCTOR);
        }
        x_handler = 0;
        y_handler = 2;
    }

    public Cell.State getState(int x, int y){
        return table[x][y].getState();
    }

    public int getX_size() {
        /**
         * @return Wymiar X komponentu.
         */
        return x_size;
    }

    public int getY_size() {
        /**
         * @return Wymiar Y komponentu.
         */
        return y_size;
    }

    public int getX_handler() {
        /**
         * @return Współrzędna X uchwytu komponentu.
         */
        return x_handler;
    }

    public int getY_handler() {
        /**
         * @return Współrzędna Y uchwytu komponentu.
         */
        return y_handler;
    }
}
