package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.Window;

/**
 * Created by zychp_w10 on 21.05.2017.
 */
public class MainGui {
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
            {
                JFrame frame = new MyFrame();
                frame.setTitle("Wirewold");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            });
    }
}

class MyFrame extends JFrame {
    public MyFrame () {
        add(new DrawBoard());
        pack();
    }
}
