package Wires;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class WireController {
    @FXML private Button playButton;
    @FXML private Button stepButton;
    @FXML private Button stopButton;
    @FXML private Label generationLabel;
    @FXML private Canvas drawCanvas;
    @FXML private AnchorPane drawingPane;
    @FXML private Tab drawTab;
    @FXML private Button conductorButton;
    @FXML private Button eleheadButton;
    @FXML private Button eletailButton;
    @FXML private Button emptyButton;
    @FXML private Button clearAllButton;


    private PrintBoardFX printBoardFX;
    private DrawBoardFX drawBoardFX;
    private Board board = new Board();
    private WireLogicEngine logic = new WireLogicEngine(board);
    private Timeline timeline;


    @FXML private void initialize() throws Exception {
        for(int i = 0; i<board.getY_size(); i++){
            board.setBoardCellState(5,i, Cell.State.CONDUCTOR);
        }
        for(int i = 0; i<board.getX_size(); i++){
            board.setBoardCellState(i,5, Cell.State.CONDUCTOR);
        }
        printBoardFX = new PrintBoardFX(board,drawCanvas,drawingPane);
        drawBoardFX = new DrawBoardFX(Cell.State.CONDUCTOR,board,printBoardFX);
        drawCanvas.setOnMouseClicked(drawBoardFX::boardMousePressedDragged);
        drawCanvas.setOnMouseDragged(drawBoardFX::boardMousePressedDragged);
    }

    @FXML private void playButtonPressed() {
        timeline = new Timeline(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    try {
                        //generationLabel.setText(Integer.toString(i++));
                        logic.tick();
                        printBoardFX.draw();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML private void stepButtonPressed() throws Exception {
        logic.tick();
        printBoardFX.draw();
    }

    @FXML private void stopButtonPressed() {
        timeline.stop();
    }

    @FXML private void conductorButtonPressed()  { drawBoardFX.setActualState(Cell.State.CONDUCTOR); }
    @FXML private void eleheadButtonPressed()  { drawBoardFX.setActualState(Cell.State.ELEHEAD); }
    @FXML private void eletailButtonPressed()  { drawBoardFX.setActualState(Cell.State.ELETAIL); }
    @FXML private void emptyButtonPressed()  { drawBoardFX.setActualState(Cell.State.EMPTY); }
    @FXML private void clearAllButtonPressed() { drawBoardFX.clearAllBoard(); }


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



