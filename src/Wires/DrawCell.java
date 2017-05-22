package Wires;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawCell {
    private static int defaultSize;
    {
        defaultSize = 10;
    }

    public DrawCell(Graphics2D g2D, int x_pos, int y_pos, int scale, Cell.State state) {
        Rectangle2D rect = new Rectangle2D.Double(x_pos * defaultSize * scale ,y_pos * defaultSize * scale ,scale * defaultSize,scale * defaultSize);
        g2D.setPaint(getColor(state));
        g2D.draw(rect);
        g2D.fill(rect);
    }

    private static Color getColor(Cell.State state) {
        if (state.equals(Cell.State.CONDUCTOR))
            return Color.YELLOW;
        else if (state.equals(Cell.State.ELEHEAD))
            return Color.RED;
        else if (state.equals(Cell.State.ELETAIL))
            return Color.BLUE;
        else
            return Color.LIGHT_GRAY;
    }
}
