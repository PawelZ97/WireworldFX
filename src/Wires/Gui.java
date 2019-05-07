package Wires;

import Wires.Elements.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.Thread.sleep;

/**
 * Created by zychp_w10 on 22.05.2017.
 */
public class Gui {
    /**
     * Główna klasa programu. Cały interfejs graficzny.
     * Zawiera kod wygenerowany przez kreator GUI.
     */
    private JPanel mainpanel;
    private JPanel controlpanel;
    private JPanel drawpanel;
    private JPanel drawtoolspanel;
    private JPanel scalesetpanel;
    private JFormattedTextField generationsTextField1;
    private JButton stopButton;
    private JLabel infoLabel;
    private JButton stepButton;
    private JFormattedTextField delayTextField1;
    private JButton runButton;
    private JButton conductorButton;
    private JButton electronHeadButton;
    private JButton electronTailButton;
    private JButton emptyButton;
    private JLabel statusLabel;
    private JList componentslist1;
    private JSlider scaleslider1;
    private JLabel scaleLabel;

    private JCheckBoxMenuItem scaleCheckBoxMenu;

    private int x_size_max = 100;
    private int y_size_max = 100;
    private int scale;
    private boolean run;
    private boolean startflag;
    private int gennum;
    private int delay;

    enum ColorState {EMPTY, ELEHEAD, ELETAIL, CONDUCTOR}
    ;
    private ColorState drawcell = ColorState.EMPTY;


    public Gui() throws Exception {
        /**
         * Tworzy Gui.
         * Tutaj znajdują się wszelkie nasłuchwacze przycisków, myszki itd
         * Tutaj znajduje
         */
        int def_x_size = 15;
        int def_y_size = 15;
        scale = 40;

        Board bef = new Board(def_x_size, def_y_size);
        DrawBoard drawboard = new DrawBoard(def_x_size, def_y_size, scale);
        WireLogicEngine logic = new WireLogicEngine(bef);

        EventQueue.invokeLater(() ->
        {
            // GENERATED CODE
            mainpanel = new JPanel();
            mainpanel.setLayout(new GridBagLayout());
            mainpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
            controlpanel = new JPanel();
            controlpanel.setLayout(new GridBagLayout());
            controlpanel.setMaximumSize(new Dimension(120, 415));
            controlpanel.setMinimumSize(new Dimension(120, 415));
            controlpanel.setPreferredSize(new Dimension(120, 415));
            GridBagConstraints gbc;
            gbc = new GridBagConstraints();
            gbc.gridx = 1;
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
            infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
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
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            controlpanel.add(runButton, gbc);
            drawpanel = new JPanel();
            drawpanel.setLayout(new GridBagLayout());
            drawpanel.setMinimumSize(new Dimension(0, 0));
            gbc = new GridBagConstraints();
            gbc.gridx = 3;
            gbc.gridy = 0;
            gbc.weightx = 20.0;
            gbc.weighty = 20.0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            mainpanel.add(drawpanel, gbc);
            drawpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
            drawtoolspanel = new JPanel();
            drawtoolspanel.setLayout(new GridBagLayout());
            drawtoolspanel.setMaximumSize(new Dimension(150, 415));
            drawtoolspanel.setMinimumSize(new Dimension(120, 415));
            drawtoolspanel.setOpaque(true);
            drawtoolspanel.setPreferredSize(new Dimension(120, 415));
            gbc = new GridBagConstraints();
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(drawtoolspanel, gbc);
            drawtoolspanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
            conductorButton = new JButton();
            conductorButton.setBackground(new Color(-924610));
            conductorButton.setEnabled(true);
            conductorButton.setForeground(new Color(-16777216));
            conductorButton.setHideActionText(false);
            conductorButton.setText("Conductor");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
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
            final JLabel label5 = new JLabel();
            label5.setText("Status:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.anchor = GridBagConstraints.WEST;
            drawtoolspanel.add(label5, gbc);
            statusLabel = new JLabel();
            statusLabel.setText(" ");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 5;
            drawtoolspanel.add(statusLabel, gbc);
            final JPanel spacer2 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            drawtoolspanel.add(spacer2, gbc);
            final JScrollPane scrollPane1 = new JScrollPane();
            scrollPane1.setVerticalScrollBarPolicy(22);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.BOTH;
            drawtoolspanel.add(scrollPane1, gbc);
            componentslist1 = new JList();
            final DefaultListModel defaultListModel1 = new DefaultListModel();
            defaultListModel1.addElement("None");
            defaultListModel1.addElement("Diode");
            defaultListModel1.addElement("Diode Rev");
            defaultListModel1.addElement("AND");
            defaultListModel1.addElement("OR");
            componentslist1.setModel(defaultListModel1);
            scrollPane1.setViewportView(componentslist1);
            scalesetpanel = new JPanel();
            scalesetpanel.setLayout(new GridBagLayout());
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            mainpanel.add(scalesetpanel, gbc);
            scalesetpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), null));
            scaleslider1 = new JSlider();
            scaleslider1.setInverted(false);
            scaleslider1.setMinimum(1);
            scaleslider1.setOrientation(1);
            scaleslider1.setPaintLabels(false);
            scaleslider1.setPaintTicks(false);
            scaleslider1.setPaintTrack(true);
            scaleslider1.setValue(40);
            scaleslider1.setValueIsAdjusting(false);
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.anchor = GridBagConstraints.NORTH;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            scalesetpanel.add(scaleslider1, gbc);
            final JLabel label6 = new JLabel();
            label6.setText("Scale:");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            scalesetpanel.add(label6, gbc);
            scaleLabel = new JLabel();
            scaleLabel.setText(" ");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.anchor = GridBagConstraints.NORTH;
            scalesetpanel.add(scaleLabel, gbc);
            final JPanel spacer3 = new JPanel();
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.VERTICAL;
            scalesetpanel.add(spacer3, gbc);
            // END OF GENERATED CODE

            drawboard.setActual(logic.getBefore());
            drawboard.repaint();
            drawpanel.add(drawboard);

            JFrame frame = new JFrame();
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            JMenuBar menuBar = new JMenuBar();


            JMenu fileMenu = new JMenu("File");
            fileMenu.add(new AbstractAction("New") {
                public void actionPerformed(ActionEvent event) {
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
            fileMenu.add(new AbstractAction("Open") {
                public void actionPerformed(ActionEvent event) {
                    chooser.showOpenDialog(frame);
                    BoardReader reader = new BoardReader(x_size_max, y_size_max, chooser.getSelectedFile());
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
            fileMenu.add(new AbstractAction("Save") {
                public void actionPerformed(ActionEvent event) {
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
            fileMenu.add(new AbstractAction("Close") {
                public void actionPerformed(ActionEvent event) {
                    System.exit(0);
                }
            });
            menuBar.add(fileMenu);
            JMenu editMenu = new JMenu("Toolbars");
            scaleCheckBoxMenu = new JCheckBoxMenuItem("Scale set");
            scaleCheckBoxMenu.setSelected(true);
            scaleLabel.setText("" + scale);
            scaleCheckBoxMenu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if (scaleCheckBoxMenu.isSelected()) {
                        scalesetpanel.setVisible(true);
                        frame.pack();
                    } else {
                        scalesetpanel.setVisible(false);
                        frame.pack();
                    }
                }
            });
            editMenu.add(scaleCheckBoxMenu);
            editMenu.add(new AbstractAction("Run") {
                public void actionPerformed(ActionEvent event) {
                    controlpanel.setVisible(true);
                    drawtoolspanel.setVisible(false);
                    frame.pack();
                }
            });
            editMenu.add(new AbstractAction("Draw") {
                public void actionPerformed(ActionEvent event) {
                    drawtoolspanel.setVisible(true);
                    controlpanel.setVisible(false);
                    frame.pack();
                }
            });
            editMenu.add(new AbstractAction("Both") {
                public void actionPerformed(ActionEvent event) {
                    drawtoolspanel.setVisible(true);
                    controlpanel.setVisible(true);
                    frame.pack();
                }
            });
            menuBar.add(editMenu);


            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startflag = true;
                    run = true;
                    try {
                        gennum = Integer.parseInt(generationsTextField1.getText());
                        if (gennum < 1) {
                            infoLabel.setText("<html>Generations must<br>be bigger than 0</html>");
                            startflag = false;
                            run = false;
                        }
                    }
                    catch (NumberFormatException e1) {
                        infoLabel.setText("<html>Generations must<br>be a number</html>");
                        startflag = false;
                        run = false;
                    }
                    try {
                        delay = Integer.parseInt(delayTextField1.getText());
                        if (delay < 1) {
                            infoLabel.setText("<html>Delay must<br>be bigger than 0</html>");
                            startflag = false;
                            run = false;
                        }
                    }
                    catch (NumberFormatException e1) {
                        infoLabel.setText("<html>Delay must<br>be a number</html>");
                        startflag = false;
                        run = false;
                    }

                }
            });

            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startflag = true;
                    run = true;
                    gennum = 1;
                }
            });

            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    run = false;
                }
            });

            scaleslider1.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    scale = scaleslider1.getValue();
                    scaleLabel.setText("" + scale);
                    drawboard.setScale(scale);
                    drawboard.revalidate();
                    drawboard.repaint();
                    frame.pack();
                }
            });

            componentslist1.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting() == false) {
                        int index = componentslist1.getSelectedIndex();
                        if (index != 0) {
                            if (index == 1) {
                                statusLabel.setText("Draw: Diode");
                            } else if (index == 2) {
                                statusLabel.setText("Draw: DiodeRev");
                            } else if (index == 3) {
                                statusLabel.setText("Draw: ANDGate");
                            } else if (index == 4) {
                                statusLabel.setText("Draw: ORGate");
                            }
                        }
                    }
                }
            });

            conductorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell = ColorState.CONDUCTOR;
                    statusLabel.setText("Draw: CONDUCTOR");
                }
            });
            electronHeadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell = ColorState.ELEHEAD;
                    statusLabel.setText("Draw: ELEHEAD");
                }
            });
            electronTailButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell = ColorState.ELETAIL;
                    statusLabel.setText("Draw: ELETAIL");
                }
            });
            emptyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    drawcell = ColorState.EMPTY;
                    statusLabel.setText("Draw: EMPTY");
                }
            });

            componentslist1.setSelectedIndex(0);

            drawpanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    int x_size = logic.getBefore().getX_size();
                    int y_size = logic.getBefore().getY_size();
                    int x = e.getX() - 2;
                    int y = e.getY() - 2;
                    int x_cell = x / scale;
                    if (x_cell >= x_size)
                        x_cell = x_size - 1;
                    if (x_cell < 0)
                        x_cell = 0;
                    int y_cell = y / scale;
                    if (y_cell >= y_size)
                        y_cell = y_size - 1;
                    if (y_cell < 0)
                        y_cell = 0;

                    WireComponent element = null;
                    int index = componentslist1.getSelectedIndex();

                    if (index != 0) {
                        if (index == 1) {
                            element = new Diode();
                        } else if (index == 2) {
                            element = new DiodeRev();
                        } else if (index == 3) {
                            element = new AndGate();
                        } else if (index == 4) {
                            element = new OrGate();
                        }
                        componentslist1.setSelectedIndex(0);
                        x_cell -= element.getX_handler();
                        y_cell -= element.getY_handler();
                        boolean okflag = true;
                        for (int i = 0; i < element.getX_size(); i++) {
                            for (int j = 0; j < element.getY_size(); j++) {
                                if (i + x_cell >= 0 && i + x_cell <= x_size - 1) {
                                    if (j + y_cell >= 0 && j + y_cell <= y_size - 1) {
                                        logic.getBefore().setBoardCellState(i + x_cell, j + y_cell, element.getState(i, j));
                                        //logic.getBefore().printBorderBoardToConsole();
                                    } else {
                                        statusLabel.setText("<html>Element too big!<br>Y off border</html>");
                                        okflag = false;
                                    }

                                } else {
                                    statusLabel.setText("<html>Element too big!<br>X off border</html>");
                                    okflag = false;
                                }
                            }
                        }
                        if (okflag) {
                            statusLabel.setText("<html>Element draw<br>successfully</html>");
                        }
                    } else {
                        if (drawcell == ColorState.CONDUCTOR)
                            logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.CONDUCTOR);
                        else if (drawcell == ColorState.ELEHEAD)
                            logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.ELEHEAD);
                        else if (drawcell == ColorState.ELETAIL)
                            logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.ELETAIL);
                        else
                            logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.EMPTY);
                    }
                    drawboard.setActual(logic.getBefore());
                    drawboard.repaint();
                    frame.pack();
                }
            });

            drawpanel.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e);
                    int x_size = logic.getBefore().getX_size();
                    int y_size = logic.getBefore().getY_size();
                    int x = e.getX() - 2;
                    int y = e.getY() - 2;
                    int x_cell = x / scale;
                    if (x_cell >= x_size)
                        x_cell = x_size - 1;
                    if (x_cell < 0)
                        x_cell = 0;
                    int y_cell = y / scale;
                    if (y_cell >= y_size)
                        y_cell = y_size - 1;
                    if (y_cell < 0)
                        y_cell = 0;

                    if (drawcell == ColorState.CONDUCTOR)
                        logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.CONDUCTOR);
                    else if (drawcell == ColorState.ELEHEAD)
                        logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.ELEHEAD);
                    else if (drawcell == ColorState.ELETAIL)
                        logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.ELETAIL);
                    else
                        logic.getBefore().setBoardCellState(x_cell, y_cell, Cell.State.EMPTY);
                    //logic.getBefore().printBorderBoardToConsole();
                    drawboard.setActual(logic.getBefore());
                    drawboard.repaint();
                    frame.pack();
                }
            });

            delayTextField1.setText("100");
            generationsTextField1.setText("50");

            frame.add(mainpanel);
            frame.setJMenuBar(menuBar);
            frame.setTitle("Wirewold");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
            frame.pack();
        });

        while (true) {
            while (true) {
                sleep(100);
                if (startflag) {
                    startflag = false;
                    break;
                }
            }
            for (int i = 1; (i <= gennum) && run; i++) {
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