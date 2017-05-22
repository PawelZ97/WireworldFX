package Gui;

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
    private int scale = 4;

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        double topleftX = 100;
        double topleftY = 100;
        Rectangle2D rect = new Rectangle2D.Double(0,0,scale * 10,scale * 10);
        g2D.setPaint(Color.RED);
        g2D.draw(rect);
        g2D.fill(rect);
        Rectangle2D rect2 = new Rectangle2D.Double(10 * scale,0,scale * 10,scale * 10);
        g2D.setPaint(Color.BLUE);
        g2D.draw(rect2);
        g2D.fill(rect2);
        Rectangle2D rect3 = new Rectangle2D.Double(0,10 * scale,scale * 10,scale * 10);
        g2D.setPaint(Color.YELLOW);
        g2D.draw(rect3);
        g2D.fill(rect3);
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
