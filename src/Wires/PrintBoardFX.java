package Wires;

import Wires.Cell.State;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;

public class PrintBoardFX {
    private static int scale = 40;
    private Board board;
    private Canvas drawingCanvas;
    private AnchorPane drawingPane;

    PrintBoardFX(Board board, Canvas drawingCanvas, AnchorPane drawingPane) {
        this.board = board;
        this.drawingCanvas = drawingCanvas;
        this.drawingPane = drawingPane;
    }

    public static int getScale() {
        return scale;
    }

    public void draw() {
        scale = calculateMaxScale();
        clearBoard();
        drawCells();
        drawNet();
    }

    private void drawCells(){
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        for (int i = 0; i < board.getX_size(); i++){
            for (int j = 0; j < board.getY_size(); j++) {
                State state = board.getBoardCellState(i,j);
                if (state.equals(State.CONDUCTOR)) gc.setFill(Color.YELLOW);
                else if (state.equals(State.ELEHEAD)) gc.setFill(Color.RED);
                else if (state.equals(State.ELETAIL)) gc.setFill(Color.BLUE);
                else gc.setFill(Color.LIGHTGREY);
                gc.fillRect(i * scale, j * scale, scale, scale);
            }
        }
    }

    private void drawNet() {
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.setLineCap(StrokeLineCap.SQUARE);
        int x_size = board.getX_size();
        int y_size = board.getY_size();
        for (int i = 0; i <= x_size; i++) {
            gc.strokeLine(snap(i * scale), 0, snap(i * scale), scale *  y_size);
        }
        for (int i = 0; i <= y_size; i++) {
            gc.strokeLine(0, snap(i * scale), scale * x_size, snap(i * scale));
        }
    }

    private void clearBoard() {
        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
    }

    private int calculateMaxScale() {
        drawingCanvas.setHeight(drawingPane.getHeight());
        drawingCanvas.setWidth(drawingPane.getWidth());
        double canvasWidth = drawingPane.getWidth();
        double canvasHeight = drawingPane.getHeight();
        int x_size = board.getX_size();
        int y_size = board.getY_size();

        int x_scale = (int) canvasWidth / (x_size);
        int y_scale = (int) canvasHeight / (y_size);

        if (x_scale < y_scale) {
            return  x_scale;
        } else {
            return  y_scale;
        }
    }

    private double snap(double val) {
        return ((int) val) + .5;
    }
}
