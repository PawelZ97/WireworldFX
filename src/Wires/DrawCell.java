package Wires;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawCell {
    /**
     * Pozwala rysować komórki na panelu jako kwadraty.
     */
    private static int defaultSize;
    {
        defaultSize = 1;
    }

    public DrawCell(Graphics2D g2D, int x_pos, int y_pos, int scale, Cell.State state) {
        /**
         * Rysyje komórkę.
         * @param g2D Obiekt używany do rysowania.
         * @param x_pos Pozycja X rysowanej komórki.
         * @param y_pos Pozycja Y rysowanej komórki.
         * @param scale Skala w jakiej jest rysowana komórka.
         * @param state Stan rysowanej komórki.
         */
        Rectangle2D rect = new Rectangle2D.Double(x_pos * defaultSize * scale ,y_pos * defaultSize * scale ,scale * defaultSize,scale * defaultSize);
        g2D.setPaint(getColor(state));
        g2D.draw(rect);
        g2D.fill(rect);
    }

    private static Color getColor(Cell.State state) {
        /**
         * Tłumaczy stan komórki na kolor.
         * @param state Stan rysowanej komórki.
         * @return Kolor komórki.
         */
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
