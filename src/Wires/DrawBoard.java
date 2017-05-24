package Wires;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawBoard extends JPanel {
    private static int defaultSize;
    private int x_size;
    private int y_size;
    private int scale;
    private Board actual;

    {
        defaultSize = 1;
    }

    public DrawBoard(int x_size, int y_size, int scale) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.scale = scale;
    }

    public void setActual(Board actual) {
        this.actual = actual;
        this.x_size = actual.getX_size();
        this.y_size = actual.getY_size();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < x_size; i++) {
            for (int j= 0; j < y_size; j++){
                DrawCell c = new DrawCell(g2D, i, j, scale, actual.getBoardCellState(i,j));
            }
        }
        DrawNet n = new DrawNet(g2D, x_size, y_size, scale,1);
    }
    public Dimension getPreferredSize() {
        return new Dimension(defaultSize* x_size * scale ,defaultSize * y_size * scale);
    }
}
