package com.zychp.Wires;

import com.zychp.Wires.Elements.*;
import javafx.scene.input.MouseEvent;

public class UseElementFX {
    private WireComponent component;
    private Board board;
    private PrintBoardFX printBoardFX;

    UseElementFX(Board board, PrintBoardFX printBoardFX) {
        this.board = board;
        this.printBoardFX = printBoardFX;
    }

    public void setComponent(String name) {
        switch (name) {
            case "Diode":
                component = new Diode();
                break;
            case "DiodeRev":
                component = new DiodeRev();
                break;
            case "OrGate":
                component = new OrGate();
                break;
            case "AndGate":
                component = new AndGate();
                break;
            default:
                component = null;
                break;
        }
    }

    public void boardMouseMoved(MouseEvent event){
        int x_pos = convertToPos(event.getX());
        int y_pos = convertToPos(event.getY());
        x_pos -= component.getX_handler();
        y_pos -= component.getY_handler();

        if (x_pos >= 0 && (x_pos+component.getX_size()) <= board.getX_size()
        && y_pos >= 0 && (y_pos+component.getY_size()) <= board.getY_size()) {
            printBoardFX.drawElementPreview(x_pos, y_pos, component);
        }
    }

    public void boardMouseClicked(MouseEvent event){
        int x_pos = convertToPos(event.getX());
        int y_pos = convertToPos(event.getY());
        x_pos -= component.getX_handler();
        y_pos -= component.getY_handler();

        if (x_pos >= 0 && (x_pos+component.getX_size()) <= board.getX_size()
                && y_pos >= 0 && (y_pos+component.getY_size()) <= board.getY_size()) {
            for (int i = 0; i < component.getX_size(); i++) {
                for (int j = 0; j < component.getY_size(); j++) {
                    if (component.getState(i,j).equals(Cell.State.CONDUCTOR)) board.setBoardCellState(i+x_pos,j+y_pos, Cell.State.CONDUCTOR);
                    else if (component.getState(i,j).equals(Cell.State.ELEHEAD)) board.setBoardCellState(i+x_pos,j+y_pos, Cell.State.ELEHEAD);
                    else if (component.getState(i,j).equals(Cell.State.ELETAIL)) board.setBoardCellState(i+x_pos,j+y_pos, Cell.State.ELETAIL);
                    else  board.setBoardCellState(i+x_pos,j+y_pos, Cell.State.EMPTY);
                }
            }
        }
        printBoardFX.draw();
    }

    public void boardMouseExited(MouseEvent event){
        printBoardFX.draw();
    }

    private int convertToPos(double coord) {
        return (int) coord / PrintBoardFX.getScale();
    }
}

