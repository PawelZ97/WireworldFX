package Wires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class NewDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField xsizetextField1;
    private JTextField ysizetextField2;
    private JLabel errorLabel;

    private int x_size;
    private int y_size;
    private int x_size_max;
    private int y_size_max;
    private boolean readsucccesful;

    public NewDialog(int x_size_max, int y_size_max) {
        this.x_size_max = x_size_max;
        this.y_size_max = y_size_max;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        x_size = 0;
        y_size = 0;
        try {
            x_size = Integer.parseInt(xsizetextField1.getText());
            y_size = Integer.parseInt(ysizetextField2.getText());
            if (x_size < 1 || x_size > x_size_max) {
                errorLabel.setText("X must be from 1 to " + x_size_max);
                pack();
            }
            if (y_size < 1 || y_size > y_size_max) {
                errorLabel.setText("Y must be from 1 to " + y_size_max);
                pack();
            }
            if (x_size > 0 && x_size <= x_size_max && y_size > 0 && y_size <= y_size_max) {
                readsucccesful = true;
                dispose();
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Size must be a number");
            pack();
        }
    }

    private void onCancel() {
        readsucccesful = false;
        dispose();
    }

    public boolean isReadsucccesful() {
        return readsucccesful;
    }

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }
}
