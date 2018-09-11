package Wires;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class WireController {
    @FXML private Button playButton;
    @FXML private Button stepButton;
    @FXML private Button stopButton;
    @FXML private Label generationLabel;
    @FXML private Canvas drawCanvas;
    @FXML private AnchorPane drawingPane;
    @FXML private Tab drawTab;

    private PrintBoardFX printBoardFX;
    private Board board;
    int i =0;

    @FXML private void initialize() throws Exception {
        drawCanvas.setOnMouseClicked(this::boardMousePressedDragged);
        drawCanvas.setOnMouseDragged(this::boardMousePressedDragged);
    }

    @FXML private void playButtonPressed() throws Exception {
        generationLabel.setText(String.valueOf(i++));
        setAutoBoardResizing(true);
        board  = new Board(10,10);
        int size = board.getX_size();
        for(int i = 0; i<size; i++){
            board.setBoardCellState(5,i, Cell.State.CONDUCTOR);
        }
        for(int i = 0; i<size; i++){
            board.setBoardCellState(i,5, Cell.State.CONDUCTOR);
        }
        printBoardFX = new PrintBoardFX(20,board,drawCanvas,drawingPane);
        printBoardFX.draw();
    }


    private void boardMousePressedDragged(MouseEvent event) {
        int x_pos = printBoardFX.convertToPos(event.getX());
        int y_pos = printBoardFX.convertToPos(event.getY());
        if (printBoardFX.verifyPos(x_pos) && printBoardFX.verifyPos(y_pos)) {
            try {
                if (event.getButton() == MouseButton.PRIMARY) {
                    board.setBoardCellState(x_pos,y_pos, Cell.State.CONDUCTOR);
                    printBoardFX.draw();
                }
                if (event.getButton() == MouseButton.SECONDARY) {
                    board.setBoardCellState(x_pos,y_pos, Cell.State.EMPTY);
                    printBoardFX.draw();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setAutoBoardResizing(boolean value) {
        if (value) {
            drawingPane.widthProperty().addListener(boardPaneSizeListener);
            drawingPane.heightProperty().addListener(boardPaneSizeListener);
        } else {
            drawingPane.widthProperty().removeListener(boardPaneSizeListener);
            drawingPane.heightProperty().removeListener(boardPaneSizeListener);
        }
    }


    private ChangeListener<Number> boardPaneSizeListener = (observable, oldValue, newValue) -> {
        try {
            printBoardFX.draw();
        } catch (Exception e) {
        }
    };

}



