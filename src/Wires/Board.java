package Wires;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class Board {
    /**
     * Klasa Board pozwala tworzyć plansze automatu komórkowego.
     */
    private int x_size = 20;
    private int y_size = 15;
    private Cell[][] board;

    public Board(){
        board = new Cell[x_size+2][y_size+2];
        for (int i = 0; i < x_size + 2; i++) {
            for (int j = 0; j < y_size + 2; j++) {
                board[i][j] = new Cell(Cell.State.EMPTY);
            }
        }
    }

    public Board(int x_size, int y_size) {
        /**
         * Tworzy nową planszę.
         * Rzeczywiste wymiary są o dwa większe od podanych.
         * @param x_size Rozmiar X planszy.
         * @param y_size Rozmiar Y planszy.
         */
        this.x_size = x_size;
        this.y_size = y_size;
        board = new Cell[x_size + 2][y_size + 2];
        for (int i = 0; i < x_size + 2; i++) {
            for (int j = 0; j < y_size + 2; j++) {
                board[i][j] = new Cell(Cell.State.EMPTY);
            }
        }
    }

    public void setAllBoard(Cell.State state) {
        for (int i=0; i<x_size; i++) {
            for (int j=0; j<y_size; j++) {
                setBoardCellState(i,j,state);
            }
        }
    }

    public void setBoardCellState(int x, int y, Cell.State state) {
        /**
         * Ustawia stan danej komórki w planszy.
         * Współrzędne liczone bez ramki.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @param state Ustawiany stan komórki.
         */
        board[x + 1][y + 1].setState(state);
    }

    public void setBorderBoardCellState(int x, int y, Cell.State state) {
        /**
         * Ustawia stan danej komórki w planszy.
         * Współrzędne liczone wraz z ramką.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @param state Ustawiany stan komórki.
         */

        board[x][y].setState(state);
    }

    public Cell.State getBoardCellState(int x, int y) {
        /**
         * Pobiera stan komórki z planszy.
         * Współrzędne liczone bez ramki.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @return Stan komórki w planszy.
         */
        return board[x + 1][y + 1].getState();
    }

    public Cell.State getBorderBoardCellState(int x, int y) {
        /**
         * Pobiera stan komórki z planszy.
         * Współrzędne liczone wraz z ramką.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @return Stan komórki w planszy.
         */
        return board[x][y].getState();
    }

    public Cell getBoardCell(int x, int y) {
        /**
         * Pobiera komórkę z planszy.
         * Współrzędne liczone wraz z ramką.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @return Komórka planszy.
         */
        return board[x + 1][y + 1];
    }

    public Cell getBorderBoardCell(int x, int y) {
        /**
         * Pobiera komórkę z planszy.
         * Współrzędne liczone bez ramki.
         * @param x Współrzędna X.
         * @param y Współrzędna Y.
         * @return Komórka panszy.
         */
        return board[x][y];
    }

    public int getX_size() {
        /**
         * Zwraca wymiar X planszy.
         * @return Wymiar X.
         */
        return x_size;
    }

    public int getY_size() {
        /**
         * Zwraca wymiar Y planszy.
         * @return Wymiar Y.
         */
        return y_size;
    }

    public Cell[][] getBorderBoard() {
        /**
         * Zwraca całą planszę.
         * @return Plaszna.
         */

        return board;
    }

    public void setBorderBoard(Cell[][] board) {
        /**
         * Ustawia całą planszę.
         * @param board Plansza.
         */

        this.board = board;
    }

    public void printBorderBoardToConsole() {
        /**
         * Drukuję planszę do konsoli tekstowej.
         * Przydatne przy debugowaniu.
         */
        for (int i = 0; i < x_size + 2; i++) {
            for (int j = 0; j < y_size + 2; j++) {
                System.out.print(getBorderBoardCell(i, j) + " ");
            }
            System.out.print("\n");
        }

    }

    public void printBoardToFile(String filename) throws FileNotFoundException {
        /**
         * Drukuję planszę do pliku w celu jej zapisania.
         * @param filename Nazwa pliku.
         */

        PrintWriter out = new PrintWriter(filename);
        for (int i = 0; i < x_size; i++) {
            for (int j = 0; j < y_size; j++) {
                out.print(getBoardCell(i, j) + "-");
            }
            out.print("\n");
        }
        out.close();
    }
}
