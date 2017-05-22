package Wires;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawBoard extends JPanel {
    private static final int DEFAULT_WIDTH = 100;
    private static final int DEFAULT_HEIGHT = 100;
    private int scale = 5;

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        DrawCell c1 = new DrawCell(g2D, 0,0, scale, Cell.State.CONDUCTOR);
        DrawCell c2 = new DrawCell(g2D, 1,0, scale, Cell.State.ELEHEAD);
        DrawCell c3 = new DrawCell(g2D, 0,1, scale, Cell.State.ELETAIL);
        DrawCell c4 = new DrawCell(g2D, 0,2, scale, Cell.State.EMPTY);
        DrawCell c5 = new DrawCell(g2D, 5,5, scale, Cell.State.ELETAIL);
        DrawNet n = new DrawNet(g2D,10,12, scale,2);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH * scale ,DEFAULT_HEIGHT* scale);
    }
}
