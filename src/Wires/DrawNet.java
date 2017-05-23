package Wires;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawNet {
    private static int defaultSize;
    {
        defaultSize = 10;
    }

    public DrawNet(Graphics2D g2D, int cols, int rows, int scale, int width) {
        g2D.setColor(Color.DARK_GRAY);
        g2D.setStroke(new BasicStroke(width));
        for (int i = 0; i <= cols ; i++) {
            Line2D line = new Line2D.Double(scale * defaultSize * i, 0, scale * defaultSize * i, scale * defaultSize * rows);
            g2D.draw(line);
        }
        for (int i = 0; i <= rows ; i++) {
            Line2D line = new Line2D.Double(0, scale * defaultSize * i, scale * defaultSize * cols, scale * defaultSize * i);
            g2D.draw(line);
        }
    }
}
