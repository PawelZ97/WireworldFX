package Wires;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawCell {
    private int defaultSize;
    {
        defaultSize = 10;
    }

    public DrawCell(Graphics2D g2D, double x, double y, int scale, Cell.State state) {
        Rectangle2D rect = new Rectangle2D.Double(x * defaultSize * scale ,y * defaultSize * scale ,x + scale * defaultSize,y + scale * defaultSize);
        g2D.setPaint(getColor(state));
        g2D.draw(rect);
        g2D.fill(rect);
    }

    private Color getColor(Cell.State state) {
        if (state.equals(Cell.State.CONDUCTOR))
            return Color.YELLOW;
        else if (state.equals(Cell.State.ELEHEAD))
            return Color.BLUE;
        else if (state.equals(Cell.State.ELETAIL))
            return Color.RED;
        else
            return Color.LIGHT_GRAY;
    }
}
