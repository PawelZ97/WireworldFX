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
        g2D.setPaint(Color.BLACK);

        for (int i = 0; i <= 10 ; i++) {
            Line2D line = new Line2D.Double(scale * 10 * i, 0, scale * 10 * i, scale * 100);
            g2D.setStroke(new BasicStroke(2));
            g2D.draw(line);
        }
        for (int i = 0; i <= 10 ; i++) {
            Line2D line = new Line2D.Double(0, scale * 10 * i, scale * 100, scale * 10 * i);
            g2D.draw(line);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH * scale ,DEFAULT_HEIGHT* scale);
    }
}
