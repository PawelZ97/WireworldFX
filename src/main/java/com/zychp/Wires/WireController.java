package com.zychp.Wires;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class WireController {
    @FXML private Button playButton;
    @FXML private Button stepButton;
    @FXML private Button stopButton;
    @FXML private Button resetButton;
    @FXML private Label generationLabel;
    @FXML private ProgressBar generationProgressBar;
    @FXML private Canvas drawCanvas;
    @FXML private AnchorPane drawingPane;
    @FXML private Tab drawTab;
    @FXML private Button conductorButton;
    @FXML private Button eleheadButton;
    @FXML private Button eletailButton;
    @FXML private Button emptyButton;
    @FXML private Button clearAllButton;
    @FXML private Slider speedSlider;
    @FXML private TextField speedTextField;
    @FXML private Slider generationSlider;
    @FXML private TextField generationTextField;
    @FXML private ListView<String> wireComponentListView;
    @FXML private Button doneButton;


    private PrintBoardFX printBoardFX;
    private MouseDrawFX mouseDrawFX;
    private Board board = new Board(25,25);
    private final WireLogicEngine logic = new WireLogicEngine(board);
    private final Timeline timeline = new Timeline();

    private UseElementFX useElementFX;


    @FXML private void initialize() {
        printBoardFX = new PrintBoardFX(board,drawCanvas,drawingPane);
        mouseDrawFX = new MouseDrawFX(Cell.State.CONDUCTOR,board,printBoardFX);
        useElementFX = new UseElementFX(board,printBoardFX);

        drawCanvas.setOnMouseClicked(mouseDrawFX::boardMouseClickedDragged);
        drawCanvas.setOnMouseDragged(mouseDrawFX::boardMouseClickedDragged);
        drawCanvas.setOnMouseMoved(mouseDrawFX::boardMouseMoved);
        drawCanvas.setOnMouseExited(mouseDrawFX::boardMouseExited);

        setAutoBoardResizing(true);
        initializeTimeline();
        initializeSpeedSlider();

        drawDebug(true);

        ObservableList<String> list = FXCollections.observableArrayList("Diode","DiodeRev","OrGate","AndGate");
        wireComponentListView.setItems(list);
    }

    private void initializeSpeedSlider(){
        speedSlider.setLabelFormatter(new StringConverter<>()
        {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "1";
                if (n < 1.5) return "10";
                if (n < 2.5) return "100";
                if (n < 3.5) return "500";
                if (n < 4.5) return "1000";
                return "2000";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "1":
                        return 0d;
                    case "10":
                        return 1d;
                    case "100":
                        return 2d;
                    case "500":
                        return 3d;
                    case "1000":
                        return 4d;
                    case "2000":
                        return 5d;

                    default:
                        return 3d;
                }
            }
        });
    }

    private void initializeTimeline(){
        timeline.setRate(2);
        timeline.setCycleCount(1000);
        timeline.getKeyFrames().add(new KeyFrame(
                Duration.millis(1000),
                ae -> {
                    try {
                        logic.tick();
                        int cnt = logic.getCounter();
                        generationLabel.setText(Integer.toString(cnt));
                        generationProgressBar.setProgress(1.0*cnt/timeline.getCycleCount());
                        printBoardFX.draw();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
    }

    private void drawDebug(boolean enabled){
        if (enabled) {
            for (int i = 0; i < 5; i++) {
                board.setBoardCellState(i + 3, 3, com.zychp.Wires.Cell.State.CONDUCTOR);
                board.setBoardCellState(i + 3, 8, com.zychp.Wires.Cell.State.CONDUCTOR);
                board.setBoardCellState(3, 4 + i, com.zychp.Wires.Cell.State.CONDUCTOR);
                board.setBoardCellState(8, 4 + i, com.zychp.Wires.Cell.State.CONDUCTOR);
                board.setBoardCellState(5, 3, com.zychp.Wires.Cell.State.ELEHEAD);
                board.setBoardCellState(6, 3, com.zychp.Wires.Cell.State.ELETAIL);
            }
        }
    }

    @FXML private void speedSliderMouseRelased() {
        int value =(int)speedSlider.getValue();
        double valueD;
        switch (value) {
            case 0:
                valueD = 1;
                break;
            case 1:
                valueD = 10;
                break;
            case 2:
                valueD = 100;
                break;
            case 3:
                valueD = 500;
                break;
            case 4:
                valueD = 1000;
                break;
            case 5:
                valueD = 2000;
                break;

            default:
                valueD = 500;
        }
        speedTextField.setText(String.valueOf((int)valueD));
        timeline.setRate(1000/valueD);
    }

    @FXML private void wireComponentListViewClicked(){
        useElementFX.setComponent(wireComponentListView.getSelectionModel().getSelectedItem());
        drawCanvas.setOnMouseClicked(useElementFX::boardMouseClicked);
        drawCanvas.setOnMouseMoved(useElementFX::boardMouseMoved);
        drawCanvas.setOnMouseDragged(useElementFX::boardMouseExited);
    }

    @FXML private void doneButtonPressed() {
        drawCanvas.setOnMouseClicked(mouseDrawFX::boardMouseClickedDragged);
        drawCanvas.setOnMouseDragged(mouseDrawFX::boardMouseClickedDragged);
        drawCanvas.setOnMouseMoved(mouseDrawFX::boardMouseMoved);
        drawCanvas.setOnMouseExited(mouseDrawFX::boardMouseExited);
    }

    @FXML private void speedTextFieldAction() {
        double valueD = Double.parseDouble(speedTextField.getText());
        timeline.setRate(1000/valueD);
    }

    @FXML private void generationSliderMouseRelased() {
        int value = (int)generationSlider.getValue();
        generationTextField.setText(String.valueOf(value));
        timeline.setCycleCount(value);
    }

    @FXML private void generationTextFieldAction() {
        timeline.setCycleCount(Integer.parseInt(generationTextField.getText()));

    }

    @FXML private void playButtonPressed() {
        logic.saveBoard();
        logic.setCounter(0);
        enablePreviewDrawing(false);
        timeline.play();
        stopButton.setVisible(true);
        playButton.setVisible(false);

    }

    @FXML private void stepButtonPressed() {
        logic.tick();
        logic.setCounter(0);
        generationLabel.setText("1");
        printBoardFX.draw();
    }

    @FXML private void stopButtonPressed() {
        timeline.stop();
        enablePreviewDrawing(true);
        playButton.setVisible(true);
        stopButton.setVisible(false);
    }

    @FXML private void resetButtonPressed() {
       logic.revertBoard();
       printBoardFX.draw();
    }

    @FXML private void conductorButtonPressed()  { mouseDrawFX.setMouseDrawState(com.zychp.Wires.Cell.State.CONDUCTOR); }
    @FXML private void eleheadButtonPressed()  { mouseDrawFX.setMouseDrawState(com.zychp.Wires.Cell.State.ELEHEAD); }
    @FXML private void eletailButtonPressed()  { mouseDrawFX.setMouseDrawState(com.zychp.Wires.Cell.State.ELETAIL); }
    @FXML private void emptyButtonPressed()  { mouseDrawFX.setMouseDrawState(Cell.State.EMPTY); }
    @FXML private void clearAllButtonPressed() { mouseDrawFX.clearAllBoard(); }


    private void setAutoBoardResizing(boolean value) {
        if (value) {
            drawingPane.widthProperty().addListener(boardPaneSizeListener);
            drawingPane.heightProperty().addListener(boardPaneSizeListener);
        } else {
            drawingPane.widthProperty().removeListener(boardPaneSizeListener);
            drawingPane.heightProperty().removeListener(boardPaneSizeListener);
        }
    }

    private void enablePreviewDrawing(boolean val) {
        if (val) {
            drawCanvas.setOnMouseMoved(mouseDrawFX::boardMouseMoved);
        } else {
            drawCanvas.setOnMouseMoved(null);
        }
    }

    private ChangeListener<Number> boardPaneSizeListener = (observable, oldValue, newValue) -> {
        try {
            printBoardFX.draw();
        } catch (Exception ignored) {
        }
    };
}



