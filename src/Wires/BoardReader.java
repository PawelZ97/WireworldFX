package Wires;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by zychp_w10 on 24.05.2017.
 */
public class BoardReader {
    private int x_size;
    private int y_size;
    private int x_size_max;
    private int y_size_max;
    private String filename;

    public BoardReader(int x_size_max, int y_size_max, String filename) {
        this.x_size_max = x_size_max;
        this.y_size_max = y_size_max;
        this.filename = filename;
    }

    public Board readBoardFromFile() throws Exception {
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
        Board board = new Board(x_size,y_size);

        for(int i=0; i< x_read; i++) {
            for(int j=0; j< y_read; j++)
                board.setBoardCellState(i,j,tmpboard[i][j].getState());
        }
        return board;
    }
}
