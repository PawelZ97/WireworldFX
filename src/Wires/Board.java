package Wires;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class Board {
    private int x;
    private int y;
    private Cell[][] board;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = new Cell(Cell.State.EMPTY);
            }
        }
    }

    public Board(int x , int y, Cell.State state) {
        this.x = x;
        this.y = y;
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = new Cell(state);
            }
        }
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void setCell(int x, int y, Cell.State state) {
        board[x][y].setState(state);
    }

    public Cell.State getCell(int x, int y) {
        return  board[x][y].getState();
    }
 }
