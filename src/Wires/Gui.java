package Wires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class Gui {
    private JButton runButton;
    private JPanel drawpanel;
    private JPanel controlpanel;
    private JPanel mainpanel;
    private JButton electronTailButton;
    private JButton electronHeadButton;
    private JButton conductorButton;
    private JFormattedTextField generationsTextField1;
    private JButton stopButton;
    private JFormattedTextField delayTextField1;
    private JLabel gennumLabel;

    private Action saveAction;
    private Action saveAsAction;
    private JCheckBoxMenuItem readonlyItem;
    private JPopupMenu popup;

    private int x_size=10;
    private int y_size=10;
    private boolean run;
    private boolean startflag;
    private int gennum;
    private int delay;


    public Gui() throws Exception {
        DrawBoard drawboard = new DrawBoard(x_size,y_size,30);
        WireLogic logic = new WireLogic(x_size,y_size);
        EventQueue.invokeLater(() ->
        {
            mainpanel = new JPanel();
            mainpanel.setLayout(new GridBagLayout());
            controlpanel = new JPanel();
            controlpanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc;
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(controlpanel, gbc);
            runButton = new JButton();
            runButton.setText("Run");
            runButton.setVerticalAlignment(3);
            runButton.setVerticalTextPosition(3);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.anchor = GridBagConstraints.SOUTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(runButton, gbc);
            electronTailButton = new JButton();
            electronTailButton.setBackground(new Color(-14601039));
            electronTailButton.setForeground(new Color(-1));
            electronTailButton.setText("Electron tail");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(electronTailButton, gbc);
            conductorButton = new JButton();
            conductorButton.setBackground(new Color(-924610));
            conductorButton.setEnabled(true);
            conductorButton.setForeground(new Color(-1));
            conductorButton.setHideActionText(false);
            conductorButton.setText("Conductor");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(conductorButton, gbc);
            final JPanel spacer1 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weighty = 5.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            controlpanel.add(spacer1, gbc);
            generationsTextField1 = new JFormattedTextField();
            generationsTextField1.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(generationsTextField1, gbc);
            final JLabel label1 = new JLabel();
            label1.setText("Generations:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label1, gbc);
            electronHeadButton = new JButton();
            electronHeadButton.setBackground(new Color(-4521972));
            electronHeadButton.setForeground(new Color(-1));
            electronHeadButton.setText("Electron head");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(electronHeadButton, gbc);
            stopButton = new JButton();
            stopButton.setText("Stop");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 11;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(stopButton, gbc);
            delayTextField1 = new JFormattedTextField();
            delayTextField1.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(delayTextField1, gbc);
            final JLabel label2 = new JLabel();
            label2.setText("Delay:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label2, gbc);
            final JLabel label3 = new JLabel();
            label3.setText("Generation no:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label3, gbc);
            gennumLabel = new JLabel();
            gennumLabel.setText("gennum");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.CENTER;
            controlpanel.add(gennumLabel, gbc);
            drawpanel = new JPanel();
            drawpanel.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 20.0;
            gbc.weighty = 20.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(drawpanel, gbc);


            drawboard.setActual(logic.getEng().getBefore());
            drawboard.repaint();
            drawpanel.add(drawboard);

            JMenu fileMenu = new JMenu("File");
            fileMenu.add(new AbstractAction("New")
            {
                public void actionPerformed(ActionEvent event)
                {
                    System.exit(0);
                }
            });
            fileMenu.addSeparator();
            fileMenu.add(new AbstractAction("Open")
            {
                public void actionPerformed(ActionEvent event)
                {
                    try {
                        logic.getEng().getBefore().readBoardFromFile("save.txt",1000,1000);   //DO POPRAWY !!!!!!
                        System.out.println("Board Read:");
                        logic.getEng().getBefore().printBoardToConsole();
                        drawboard.setActual(logic.getEng().getBefore());
                        drawboard.repaint();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            fileMenu.add(new AbstractAction("Save")
            {
                public void actionPerformed(ActionEvent event)
                {
                    try {
                        logic.getEng().getBefore().printBoardToFile("save.txt");
                        System.out.println("Board Saved");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            fileMenu.addSeparator();
            fileMenu.add(new AbstractAction("Close")
            {
                public void actionPerformed(ActionEvent event)
                {
                    System.exit(0);
                }
            });
            JMenuBar menuBar = new JMenuBar();
            menuBar.add(fileMenu);

            JFrame frame = new JFrame();
            frame.add(mainpanel);
            frame.setJMenuBar(menuBar);
            frame.pack();
            frame.setTitle("Wirewold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);


            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gennum = Integer.parseInt(generationsTextField1.getText());
                    delay = Integer.parseInt(delayTextField1.getText());
                    startflag = true;
                    run = true;
                }
            });

            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    run = false;
                }
            });
        });

        while (true) {
            while (true) {
                sleep(100);
                if (startflag) {
                    startflag = false;
                    break;
                }
            }
            for (int i = 1; (i <= gennum) && run ; i++) {
                logic.tick();
                drawboard.setActual(logic.getEng().getBefore());
                drawboard.repaint();
                gennumLabel.setText(Integer.toString(i));
                sleep(delay);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Gui gui = new Gui();
    }
}
