package Wires;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zychp_w10 on 21.05.2017.
 */
public class MainGui {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
            {
                JFrame frame = new JFrame();
                frame.add(new DrawBoard());
                frame.pack();
                frame.setTitle("Wirewold");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            });
    }
}