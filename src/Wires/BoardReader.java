package Wires;

import java.io.File;
import java.util.Scanner;

/**
 * Created by zychp_w10 on 24.05.2017.
 */
public class BoardReader {
    /**
     * Wczytuje planszę z pliku.
     */
    private int x_size;
    private int y_size;
    private int x_size_max;
    private int y_size_max;
    private File file;

    public BoardReader(int x_size_max, int y_size_max, File file) {
        /**
         * Tworzy nowy czytnik plansz.
         * @param x_size_max Maksymalny akceptowalny wymiar X odczytywanej planszy.
         * @param y_size_max Maksymalny akceptowalny wymiar Y odczytywanej planszy.
         * @param file Plik z którego plansza ma być czytana.
         */
        this.x_size_max = x_size_max;
        this.y_size_max = y_size_max;
        this.file = file;
    }

    public Board readBoardFromFile() throws Exception {
        /**
         * Właściwe czytanie planszy.
         * Sprawdza poprawność formatu i maksymalne rozmiary.
         * @return Przeczytana plansza.
         */
        Scanner in = new Scanner(file);
        Cell[][] tmpboard = new Cell[x_size_max][y_size_max];
        int x_read;
        int y_read = 0;
        x_read = (in.nextLine().length() / 2);
        in = new Scanner(file);
        if (x_read > x_size_max)
            throw new Exception("Read x_size is too big " + Integer.toString(x_read));
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if ((line.length() / 2) != x_read)
                throw new Exception("Board file bad structure. Not the same line lenght.");
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
            if (y_read > y_size_max)
                throw new Exception("Read y_size is too big " + Integer.toString(y_read));
        }
        x_size = x_read;
        y_size = y_read;
        Board board = new Board(x_size,y_size);

        for(int i=0; i< x_read; i++) {
            for(int j=0; j< y_read; j++)
                board.setBoardCellState(i,j,tmpboard[i][j].getState());
        }
        return board;
    }
}
