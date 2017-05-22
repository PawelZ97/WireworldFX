package Wires;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DrawBoard drawboard = new DrawBoard();
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.add(drawboard);
            frame.pack();
            frame.setTitle("Wirewold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });
        Board bef = new Board(10, 10);


        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(4, i, Cell.State.CONDUCTOR);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(6, i, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 1, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 8, Cell.State.CONDUCTOR);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        bef.setBoardCellState(4, 3, Cell.State.ELETAIL);

        Board aft = new Board(10, 10);
        System.out.println("BEFORE:");
        bef.printBoardConsole();
        System.out.println("AFTER:");
        aft.printBoardConsole();
        Engine eng = new Engine(bef, aft);
        for (int i = 0; i < 20; i++) {
            System.out.println("GENERACJA: " + (i + 1));
            eng.calculate();
            eng.copy();
            eng.setAfter(new Board(10, 10));
            eng.getBefore().printBoardConsole();
            drawboard.setActual(eng.getBefore());
            drawboard.repaint();
            sleep(1000);
        }
      System.out.println("END");
    }
}
