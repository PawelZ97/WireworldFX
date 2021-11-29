package com.zychp.Wires;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DrawBoardFX {
    private Cell.State actualState;
    private Board board;
    private PrintBoardFX printBoardFX;

    DrawBoardFX(Cell.State actualState, Board board, PrintBoardFX printBoardFX) {
        this.actualState = actualState;
        this.board = board;
        this.printBoardFX = printBoardFX;
    }

    public void setActualState(Cell.State actualState) {
        this.actualState = actualState;
    }

    public void clearAllBoard() {
        board.setAllBoard(Cell.State.EMPTY);
        printBoardFX.draw();
    }

    public void boardMouseClickedDragged(MouseEvent event) {
        int x_pos = convertToPos(event.getX());
        int y_pos = convertToPos(event.getY());
        if (verifyPos(x_pos, y_pos)) {
            if (event.getButton() == MouseButton.PRIMARY) {
                board.setBoardCellState(x_pos, y_pos, actualState);
                printBoardFX.draw();
            }
            if (event.getButton() == MouseButton.SECONDARY) {
                board.setBoardCellState(x_pos, y_pos, Cell.State.EMPTY);
                printBoardFX.draw();
            }
        }
    }

    public void boardMouseMoved(MouseEvent event){
        int x_pos = convertToPos(event.getX());
        int y_pos = convertToPos(event.getY());
        if (verifyPos(x_pos, y_pos)) {
            printBoardFX.drawPreview(x_pos,y_pos,actualState);
        }
    }

    public void boardMouseExited(MouseEvent event){
        printBoardFX.draw();
    }

    private boolean verifyPos(int x_pos, int y_pos) {
        return (x_pos >= 0) && (x_pos < board.getX_size()) && (y_pos >=0) && (y_pos < board.getY_size());
    }

    private int convertToPos(double coord) {
        return (int) coord / PrintBoardFX.getScale();
    }
}
