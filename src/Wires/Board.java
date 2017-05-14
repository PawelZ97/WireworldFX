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
        board = new Cell[x][y];
        for(int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                board[i][j] = new Cell(Cell.State.EMPTY);
            }
        }
    }

    public void setBoardCellState(int x, int y, Cell.State state) {
        board[x][y].setState(state);
    }

    public Cell.State getBoardCellState(int x, int y) {
        return  board[x][y].getState();
    }

    public Cell getBoardCell(int x, int y) { return board[x][y]; }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void printBoardConsole() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(getBoardCell(i,j)+ " ");
            }
            System.out.print("\n");
        }

    }
}
