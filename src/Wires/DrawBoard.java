package Wires;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class DrawBoard extends JPanel {
    /**
     * Pozwala rysować planszę na panelu.
     */
    private static int defaultSize;
    private int x_size;
    private int y_size;
    private int scale;
    private Board actual;

    {
        defaultSize = 1;
    }

    public DrawBoard(int x_size, int y_size, int scale) {
        /**
         * Tworzy obiekt rysowania planszy.
         * @param x_size Wymiar X planszy.
         * @param y_size Wymiar Y planszy.
         * @param scale Skala rysowania planszy.
         *
         */
        this.x_size = x_size;
        this.y_size = y_size;
        this.scale = scale;
    }

    public void setActual(Board actual) {
        /**
         * Ustawia planszę do rysowania.
         * @param actual Plansza do rysowania.
         *
         */
        this.actual = actual;
        this.x_size = actual.getX_size();
        this.y_size = actual.getY_size();
    }

    public void paintComponent(Graphics g) {
        /**
         * Rysuję planszę na panelu za pomocą klas DrawCell i DrawNet
         */
        Graphics2D g2D = (Graphics2D) g;
        for (int i = 0; i < x_size; i++) {
            for (int j= 0; j < y_size; j++){
                DrawCell c = new DrawCell(g2D, i, j, scale, actual.getBoardCellState(i,j));
            }
        }
        DrawNet n = new DrawNet(g2D, x_size, y_size, scale,1);
    }
    public Dimension getPreferredSize() {
        /**
         * Zwraca preferowany rozmiar planszy
         */
        return new Dimension(defaultSize* x_size * scale ,defaultSize * y_size * scale);
    }

    public void setScale(int scale) {
        /**
         * Ustawia skalę.
         * @param scale Skala.
         */
        this.scale = scale;
    }
}
