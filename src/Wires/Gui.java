package Wires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
    private JFormattedTextField generationsTextField1;
    private JButton stopButton;
    private JFormattedTextField delayTextField1;
    private JLabel infoLabel;
    private JButton stepButton;
    private JButton electronTailButton;
    private JButton conductorButton;
    private JButton electronHeadButton;
    private JButton emptyButton;
    private JPanel drawtoolspanel;

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

        Board bef = new Board(x_size, y_size);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(4, i, Cell.State.CONDUCTOR);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(6, i, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 1, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 8, Cell.State.CONDUCTOR);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        bef.setBoardCellState(4, 3, Cell.State.ELETAIL);

        DrawBoard drawboard = new DrawBoard(x_size,y_size,30);
        WireLogicEngine logic = new WireLogicEngine(bef);

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
            runButton.setBackground(new Color(-15139070));
            runButton.setForeground(new Color(-16777216));
            runButton.setText("Run");
            runButton.setVerticalAlignment(3);
            runButton.setVerticalTextPosition(3);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(runButton, gbc);
            final JPanel spacer1 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weighty = 5.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            controlpanel.add(spacer1, gbc);
            generationsTextField1 = new JFormattedTextField();
            generationsTextField1.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(generationsTextField1, gbc);
            final JLabel label1 = new JLabel();
            label1.setText("Generations:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label1, gbc);
            stopButton = new JButton();
            stopButton.setBackground(new Color(-60928));
            stopButton.setForeground(new Color(-16777216));
            stopButton.setText("Stop");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(stopButton, gbc);
            delayTextField1 = new JFormattedTextField();
            delayTextField1.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(delayTextField1, gbc);
            final JLabel label2 = new JLabel();
            label2.setText("Delay:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label2, gbc);
            final JLabel label3 = new JLabel();
            label3.setText("Generation no:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label3, gbc);
            infoLabel = new JLabel();
            infoLabel.setHorizontalAlignment(0);
            infoLabel.setHorizontalTextPosition(0);
            infoLabel.setText("gennum");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            controlpanel.add(infoLabel, gbc);
            stepButton = new JButton();
            stepButton.setBackground(new Color(-924610));
            stepButton.setForeground(new Color(-16777216));
            stepButton.setText("Step");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(stepButton, gbc);
            drawpanel = new JPanel();
            drawpanel.setLayout(new GridBagLayout());
            drawpanel.setMinimumSize(new Dimension(0, 0));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 20.0;
            gbc.weighty = 20.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(drawpanel, gbc);
            drawtoolspanel = new JPanel();
            drawtoolspanel.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(drawtoolspanel, gbc);
            conductorButton = new JButton();
            conductorButton.setBackground(new Color(-924610));
            conductorButton.setEnabled(true);
            conductorButton.setForeground(new Color(-16777216));
            conductorButton.setHideActionText(false);
            conductorButton.setText("Conductor");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            drawtoolspanel.add(conductorButton, gbc);
            electronHeadButton = new JButton();
            electronHeadButton.setBackground(new Color(-4521972));
            electronHeadButton.setForeground(new Color(-16777216));
            electronHeadButton.setText("Electron head");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            drawtoolspanel.add(electronHeadButton, gbc);
            electronTailButton = new JButton();
            electronTailButton.setBackground(new Color(-14601039));
            electronTailButton.setForeground(new Color(-16777216));
            electronTailButton.setText("Electron tail");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            drawtoolspanel.add(electronTailButton, gbc);
            emptyButton = new JButton();
            emptyButton.setBackground(new Color(-8290180));
            emptyButton.setForeground(new Color(-16777216));
            emptyButton.setText("Empty");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            drawtoolspanel.add(emptyButton, gbc);
            final JPanel spacer2 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weighty = 2.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            drawtoolspanel.add(spacer2, gbc);
            final JLabel label4 = new JLabel();
            label4.setText("Components:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            drawtoolspanel.add(label4, gbc);
            final JScrollPane scrollPane1 = new JScrollPane();
            scrollPane1.setVerticalScrollBarPolicy(22);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            drawtoolspanel.add(scrollPane1, gbc);


            drawboard.setActual(logic.getBefore());
            drawboard.repaint();
            drawpanel.add(drawboard);


            JFrame frame = new JFrame();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            JMenuBar menuBar = new JMenuBar();


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
                    chooser.showOpenDialog(frame);
                    BoardReader reader = new BoardReader(200,200, chooser.getSelectedFile());
                    Board boardread = null;
                    try {
                        boardread = reader.readBoardFromFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    infoLabel.setText("Board Read " + Integer.toString(boardread.getX_size()) + " x " + Integer.toString(boardread.getY_size()));
                    boardread.printBoardToConsole();
                    logic.setBefore(boardread);
                    drawboard.setActual(boardread);
                    drawboard.repaint();
                    frame.pack();

                }
            });
            fileMenu.add(new AbstractAction("Save")
            {
                public void actionPerformed(ActionEvent event)
                {
                    chooser.showSaveDialog(frame);
                    try {
                        logic.getBefore().printBoardToFile(chooser.getSelectedFile().getPath());
                        infoLabel.setText("Board Saved");
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
            menuBar.add(fileMenu);
            JMenu editMenu = new JMenu("Tools");
            editMenu.add(new AbstractAction("Run")
            {
                public void actionPerformed(ActionEvent event)
                {
                   controlpanel.setVisible(true);
                   drawtoolspanel.setVisible(false);
                    frame.pack();
                }
            });
            editMenu.add(new AbstractAction("Draw")
            {
                public void actionPerformed(ActionEvent event)
                {
                    drawtoolspanel.setVisible(true);
                    controlpanel.setVisible(false);
                    frame.pack();
                }
            });
            editMenu.add(new AbstractAction("Booth")
            {
                public void actionPerformed(ActionEvent event)
                {
                    drawtoolspanel.setVisible(true);
                    controlpanel.setVisible(true);
                    frame.pack();
                }
            });
            menuBar.add(editMenu);


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

            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startflag = true;
                    run = true;
                    gennum=1;
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
                drawboard.setActual(logic.getBefore());
                drawboard.repaint();
                infoLabel.setText(Integer.toString(i));
                sleep(delay);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Gui gui = new Gui();
    }
}
