package Wires;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
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
        board  = new Board(10,10);
        int size = board.getX_size();
        for(int i = 0; i<size; i++){
            board.setBoardCellState(5,i, Cell.State.CONDUCTOR);
        }
        for(int i = 0; i<size; i++){
            board.setBoardCellState(i,5, Cell.State.CONDUCTOR);
        }
        printBoardFX = new PrintBoardFX(board,drawCanvas,drawingPane);
        DrawBoardFX drawBoardFX = new DrawBoardFX(Cell.State.CONDUCTOR,board,printBoardFX);
        drawCanvas.setOnMouseClicked(drawBoardFX::boardMousePressedDragged);
        drawCanvas.setOnMouseDragged(drawBoardFX::boardMousePressedDragged);
    }

    @FXML private void playButtonPressed() throws Exception {
        generationLabel.setText(String.valueOf(i++));
        setAutoBoardResizing(true);
        printBoardFX.draw();
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



