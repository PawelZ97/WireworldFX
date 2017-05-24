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

    public void readBoardFromFile(String filename, int x_size_max, int y_size_max) throws FileNotFoundException {
        File file = new File(filename);
        Scanner in = new Scanner(file);
        int x_read = 0;
        int y_read = 0;
        Cell[][] tmpboard = new Cell[x_size_max][y_size_max];
        while (in.hasNextLine()) {

            String line = in.nextLine();
            x_read = (line.length() / 2);
            String[] code = line.split("-");
            for (int i = 0; i < x_read; i++) {
                if (code[i].equals("C"))
                    tmpboard[i][y_read] = new Cell(Cell.State.CONDUCTOR);
                else if (code[i].equals("H"))
                    tmpboard[i][y_read] = new Cell(Cell.State.ELEHEAD);
                else if (code[i].equals("T"))
                    tmpboard[i][y_read] = new Cell(Cell.State.ELETAIL);
                else
                    tmpboard[i][y_read] = new Cell(Cell.State.EMPTY);
            }
            y_read++;
        }
        x_size = x_read;
        y_size = y_read;
        for(int i=0; i< x_read; i++) {
            for(int j=0; j< y_read; j++)
               board[i][j]= tmpboard[i][j];
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
