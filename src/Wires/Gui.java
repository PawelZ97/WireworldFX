package Wires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JLabel statusLabel;

    private int x_size_max=100;
    private int y_size_max=100;
    private int scale;
    private boolean run;
    private boolean startflag;
    private int gennum;
    private int delay;
    enum ColorState {EMPTY, ELEHEAD, ELETAIL, CONDUCTOR};
    private ColorState drawcell = ColorState.EMPTY;


    public Gui() throws Exception {
        int def_x_size = 10;
        int def_y_size = 10;
        scale = 40;

        Board bef = new Board(def_x_size, def_y_size);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(4, i, Cell.State.CONDUCTOR);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(6, i, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 1, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 8, Cell.State.CONDUCTOR);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        bef.setBoardCellState(4, 3, Cell.State.ELETAIL);

        DrawBoard drawboard = new DrawBoard(def_x_size,def_y_size,scale);
        WireLogicEngine logic = new WireLogicEngine(bef);

        EventQueue.invokeLater(() ->
        {
            // GENERATED CODE
            mainpanel = new JPanel();
            mainpanel.setLayout(new GridBagLayout());
            controlpanel = new JPanel();
            controlpanel.setLayout(new GridBagLayout());
            controlpanel.setMaximumSize(new Dimension(120, 415));
            controlpanel.setMinimumSize(new Dimension(110, 415));
            controlpanel.setPreferredSize(new Dimension(120, 415));
            GridBagConstraints gbc;
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(controlpanel, gbc);
            controlpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
            final JPanel spacer1 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weighty = 5.0;
            gbc.fill = GridBagConstraints.BOTH;
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
            final JLabel label2 = new JLabel();
            label2.setText("Delay:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label2, gbc);
            final JLabel label3 = new JLabel();
            label3.setText("Status:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            controlpanel.add(label3, gbc);
            infoLabel = new JLabel();
            infoLabel.setHorizontalAlignment(0);
            infoLabel.setHorizontalTextPosition(0);
            infoLabel.setText(" ");
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
            delayTextField1 = new JFormattedTextField();
            delayTextField1.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(delayTextField1, gbc);
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
            drawpanel = new JPanel();
            drawpanel.setLayout(new GridBagLayout());
            drawpanel.setMinimumSize(new Dimension(0, 0));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.weightx = 20.0;
            gbc.weighty = 20.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            mainpanel.add(drawpanel, gbc);
            drawpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
            drawtoolspanel = new JPanel();
            drawtoolspanel.setLayout(new GridBagLayout());
            drawtoolspanel.setMaximumSize(new Dimension(150, 415));
            drawtoolspanel.setMinimumSize(new Dimension(110, 415));
            drawtoolspanel.setOpaque(true);
            drawtoolspanel.setPreferredSize(new Dimension(120, 415));
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(drawtoolspanel, gbc);
            drawtoolspanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
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
            final JLabel label4 = new JLabel();
            label4.setText("Components:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.anchor = GridBagConstraints.WEST;
            drawtoolspanel.add(label4, gbc);
            final JScrollPane scrollPane1 = new JScrollPane();
            scrollPane1.setVerticalScrollBarPolicy(22);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            drawtoolspanel.add(scrollPane1, gbc);
            final JLabel label5 = new JLabel();
            label5.setText("Status:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            drawtoolspanel.add(label5, gbc);
            statusLabel = new JLabel();
            statusLabel.setText("");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.anchor = GridBagConstraints.WEST;
            drawtoolspanel.add(statusLabel, gbc);
            final JPanel spacer2 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            drawtoolspanel.add(spacer2, gbc);
            // END OF GENERATED CODE

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
                    NewDialog dialog = new NewDialog(100, 100);
                    dialog.pack();
                    dialog.setTitle("New");
                    dialog.setLocationByPlatform(true);
                    dialog.setResizable(false);
                    dialog.setVisible(true);
                    if (dialog.isReadsucccesful()) {
                        Board newboard = new Board(dialog.getX_size(), dialog.getY_size());
                        infoLabel.setText("New Board " + dialog.getX_size() + "x" + dialog.getY_size());
                        logic.setBefore(newboard);
                        drawboard.setActual(newboard);
                        drawboard.revalidate();
                        drawboard.repaint();
                        frame.pack();
                    }
                }
            });
            fileMenu.addSeparator();
            fileMenu.add(new AbstractAction("Open")
            {
                public void actionPerformed(ActionEvent event)
                {
                    chooser.showOpenDialog(frame);
                    BoardReader reader = new BoardReader(x_size_max,y_size_max, chooser.getSelectedFile());
                    Board boardread = null;
                    try {
                        boardread = reader.readBoardFromFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    infoLabel.setText("Board Read " + boardread.getX_size() + " x " + boardread.getY_size());
                    boardread.printBorderBoardToConsole();
                    logic.setBefore(boardread);
                    drawboard.setActual(boardread);
                    drawboard.revalidate();
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
            frame.setTitle("Wirewold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.pack();

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

            drawpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int x_size = logic.getBefore().getX_size();
                    int y_size = logic.getBefore().getY_size();
                    int x = e.getX()-2;
                    int y = e.getY()-2;
                    int x_cell = x/scale;
                    if (x_cell >= x_size)
                       x_cell = x_size-1;
                    int y_cell = y/scale;
                    if (y_cell >= y_size)
                        y_cell = y_size-1;
                    infoLabel.setText("MC: X:" + x +" Y:"+ y);
                    statusLabel.setText("MC: X:" + x/scale +" Y:"+ y/scale);
                    if (drawcell==ColorState.CONDUCTOR)
                        logic.getBefore().setBoardCellState(x_cell,y_cell, Cell.State.CONDUCTOR);
                    else if (drawcell==ColorState.ELEHEAD)
                        logic.getBefore().setBoardCellState(x_cell,y_cell, Cell.State.ELEHEAD);
                    else if (drawcell==ColorState.ELETAIL)
                        logic.getBefore().setBoardCellState(x_cell,y_cell, Cell.State.ELETAIL);
                    else
                        logic.getBefore().setBoardCellState(x_cell,y_cell, Cell.State.EMPTY);
                    logic.getBefore().printBorderBoardToConsole();
                    drawboard.setActual(logic.getBefore());
                    drawboard.repaint();
                    frame.pack();
                }
            });

            conductorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell =  ColorState.CONDUCTOR;
                    statusLabel.setText("Draw: CONDUCTOR");
                }
            });
            electronHeadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell =  ColorState.ELEHEAD;
                    statusLabel.setText("Draw: ELEHEAD");
                }
            });
            electronTailButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell =  ColorState.ELETAIL;
                    statusLabel.setText("Draw: ELETAIL");
                }
            });
            emptyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell =  ColorState.EMPTY;
                    statusLabel.setText("Draw: EMPTY");
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
                drawboard.revalidate();
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
