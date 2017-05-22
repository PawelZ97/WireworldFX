package Wires;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class WireworldGen {
    private Board bef;
    private Board aft;

    public WireworldGen() throws Exception {
        bef = new Board(10, 10);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(4, i, Cell.State.CONDUCTOR);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(6, i, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 1, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 8, Cell.State.CONDUCTOR);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        bef.setBoardCellState(4, 3, Cell.State.ELETAIL);
        aft = new Board(10, 10);
        System.out.println("BEFORE:");
        bef.printBoardConsole();
        System.out.println("AFTER:");
        aft.printBoardConsole();
    }

    public void tick() throws Exception {
        Engine eng = new Engine(bef, aft);
        for (int i = 0; i < 20; i++) {
            System.out.println("GENERACJA: " + (i + 1));
            eng.calculate();
            eng.copy();
            eng.setAfter(new Board(10, 10));
            //System.out.println("BEFORE:");
            eng.getBefore().printBoardConsole();
            //System.out.println("AFTER:");
            //eng.getAfter().printBoardConsole();
            sleep(2000);
        }
        System.out.println("END");
    }
}


