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
    private Board actual;
    private int scale = 5;


    public void setActual(Board actual) {
        this.actual = actual;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < 10; i++) {
            for (int j= 0; j < 10; j++){
                DrawCell c = new DrawCell(g2D, i, j, scale, actual.getBoardCellState(i,j));
            }
        }
        DrawNet n = new DrawNet(g2D,10,10, scale,3);
    }
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH * scale ,DEFAULT_HEIGHT* scale);
    }
}
