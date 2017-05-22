package Wires;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        DrawBoard drawboard = new DrawBoard();
        WireLogic logic = new WireLogic();
        EventQueue.invokeLater(() ->
        {
            JFrame frame = new JFrame();
            frame.add(drawboard);
            frame.pack();
            frame.setTitle("Wirewold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        });


        for (int i = 0; i < 100; i++) {
            logic.tick();
            drawboard.setActual(logic.getEng().getBefore());
            drawboard.repaint();
            sleep(200);
        }
      System.out.println("END");
    }
}
