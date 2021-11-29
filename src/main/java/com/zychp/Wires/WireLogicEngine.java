package com.zychp.Wires;


/**
 * Created by zychp_w10 on 14.05.2017.
 */
public class WireLogicEngine {
    /** Silnik logiczny symulacji.
     */
    private int x_size;
    private int y_size;
    private Board before;
    private Board after;
    private Board savedBoard;
    private int counter;

    public WireLogicEngine(int x_size, int y_size) {
        /**
         * Tworzy nowy silnik.
         * @param x_size Wymiar X.
         * @param y_size Wymiar Y.
         */
        this.x_size = x_size;
        this.y_size = y_size;
        this.before = new Board(x_size,y_size);
        this.after = new Board(x_size, y_size);
        this.savedBoard = new Board(x_size,y_size);
        this.counter = 0;
    }

    WireLogicEngine(Board before) {
        /**
         * Tworzy nowy silnik przy pomocy planszy.
         * @param before Plansza poprzednia.
         */
        this.before = before;
        this.x_size = before.getX_size();
        this.y_size = before.getY_size();
        this.after = new Board(x_size, y_size);
        this.savedBoard = new Board(x_size,y_size);
        this.counter = 0;
    }

    public void setBefore(Board before) {
        /**
         * Ustawia planszę poprzednią silnika.
         * @param before Plansza poprzednia.
         */
        this.before = before;
        this.x_size = before.getX_size();
        this.y_size = before.getY_size();
        this.after = new Board(x_size, y_size);
    }

    public Board getBefore() {
        /**
         * Zwraca planszę.
         * @return Zwraca planszę poprzednią.
         */
        return before;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void saveBoard() {
        savedBoard.setBorderBoard(before.getBorderBoard().clone());
    }

    public void revertBoard() {
        before.setBorderBoard(savedBoard.getBorderBoard().clone());
    }

    public void tick() throws Exception {
        /**
         * Wykonuje jeden cykl logiki.
         */
        calculate();
        copy();
        after = new Board(x_size, y_size);
        counter++;
    }

    private void copy(){
        /**
         * Klonuje planszęn następną do poprzedniej.
         */
        before.setBorderBoard(after.getBorderBoard().clone());
    }

    private void calculate(){
        /**
         * Wykonuje podmiany stanu komórek.
         * Oblicza sąsiedztwo w celu zapalania komórki jako Electron Head.
         */
        int x = before.getX_size();
        int y = before.getY_size();

        for(int i =1; i<x+1;i++) {    //Pętle przemierzają tylko środek board
            for(int j = 1; j <y+1; j++) {
                if (before.getBorderBoardCellState(i,j).equals(Cell.State.EMPTY)) {
                    after.setBorderBoardCellState(i,j, Cell.State.EMPTY);
                }
                else if(before.getBorderBoardCellState(i,j).equals(Cell.State.ELEHEAD)) {
                    after.setBorderBoardCellState(i,j, Cell.State.ELETAIL);
                }
                else if(before.getBorderBoardCellState(i,j).equals(Cell.State.ELETAIL)) {
                    after.setBorderBoardCellState(i,j, Cell.State.CONDUCTOR);
                }
                else {
                    int sum=0;
                    for (int k = j-1; k <= j+1; k++)
                    {
                        if (before.getBorderBoardCellState(i-1,k).equals(Cell.State.ELEHEAD))
                            sum ++;
                        if (before.getBorderBoardCellState(i+1,k).equals(Cell.State.ELEHEAD))
                            sum ++;
                                            }
                    if (before.getBorderBoardCellState(i,j-1).equals(Cell.State.ELEHEAD))
                        sum ++;
                    if (before.getBorderBoardCellState(i,j+1).equals(Cell.State.ELEHEAD))
                        sum ++;
                    if (sum == 1 || sum == 2)
                        after.setBorderBoardCellState(i,j, Cell.State.ELEHEAD);
                    else
                        after.setBorderBoardCellState(i,j, Cell.State.CONDUCTOR);
                }
            }
        }
    }
}