package Wires;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class WireController {
    @FXML private Button playButton;
    @FXML private Button stepButton;
    @FXML private Button stopButton;
    @FXML private Label generationLabel;
    @FXML private Canvas drawCanvas;
    @FXML private AnchorPane drawingPane;

    int i = 0;

    @FXML private void playButtonPressed() throws Exception {
        Board board = new Board(10,10);
        int size = board.getX_size();
        for(int i = 0; i<size; i++){
            board.setBoardCellState(5,i, Cell.State.CONDUCTOR);
        }
        for(int i = 0; i<size; i++){
            board.setBoardCellState(i,5, Cell.State.CONDUCTOR);
        }
        DrawBoardFX drawBoardFX = new DrawBoardFX(20,board,drawCanvas,drawingPane);
        drawBoardFX.draw();
        generationLabel.setText(String.valueOf(i++));
    }

}



