package Wires;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class Board {
    private int x_size;
    private int y_size;
    private Cell[][] board;

    public Board(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        board = new Cell[x_size][y_size];
        for(int i = 0; i < x_size; i++) {
            for (int j = 0; j < y_size; j++) {
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

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void setBoard(Cell[][] board) {
        this.board = board;
    }

    public void printBoardToConsole() {
        for (int i = 0; i < y_size; i++) {
            for (int j = 0; j < x_size; j++) {
                System.out.print(getBoardCell(j,i)+ " ");
        }
            System.out.print("\n");
        }

    }

    public void printBoardToFile(String filename) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(filename);
        for (int i = 0; i < y_size; i++) {
            for (int j = 0; j < x_size; j++) {
                out.print(getBoardCell(j,i)+ "-");
            }
            out.print("\n");
        }
        out.close();
    }
}
