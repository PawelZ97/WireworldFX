package Wires;


/**
 * Created by zychp_w10 on 14.05.2017.
 */
public class WireLogicEngine {
    private int x_size;
    private int y_size;
    private Board before;
    private Board after;

    public WireLogicEngine(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;
        this.before = new Board(x_size,y_size);
        this.after = new Board(x_size, y_size);
    }

    public WireLogicEngine(Board before) {
        this.before = before;
        this.x_size = before.getX_size();
        this.y_size = before.getY_size();
        this.after = new Board(x_size, y_size);
    }

    public void setBefore(Board before) {
        this.before = before;
        this.x_size = before.getX_size();
        this.y_size = before.getY_size();
        this.after = new Board(x_size, y_size);
    }

    public Board getBefore() {
        return before;
    }

    public void tick() throws Exception {
        calculate();
        copy();
        after = new Board(x_size, y_size);
        //getBefore().printBoardToConsole();
    }

    private void copy(){
        before.setBoard(after.getBoard().clone());
    }

    private void calculate(){
        int x = before.getX_size();
        int y = before.getY_size();

        for(int i =0; i<x;i++) {
            for(int j = 0; j <y; j++) {
                if (before.getBoardCellState(i,j).equals(Cell.State.EMPTY)) {
                    after.setBoardCellState(i,j, Cell.State.EMPTY);
                }
                else if(before.getBoardCellState(i,j).equals(Cell.State.ELEHEAD)) {
                    after.setBoardCellState(i,j, Cell.State.ELETAIL);
                }
                else if(before.getBoardCellState(i,j).equals(Cell.State.ELETAIL)) {
                    after.setBoardCellState(i,j, Cell.State.CONDUCTOR);
                }
                else {
                    int sum=0;
                    for (int k = j-1; k <= j+1; k++)
                    {
                        if (before.getBoardCellState(i-1,k).equals(Cell.State.ELEHEAD))
                            sum ++;
                        if (before.getBoardCellState(i+1,k).equals(Cell.State.ELEHEAD))
                            sum ++;
                                            }
                    if (before.getBoardCellState(i,j-1).equals(Cell.State.ELEHEAD))
                        sum ++;
                    if (before.getBoardCellState(i,j+1).equals(Cell.State.ELEHEAD))
                        sum ++;
                    if (sum == 1 || sum == 2)
                        after.setBoardCellState(i,j, Cell.State.ELEHEAD);
                    else
                        after.setBoardCellState(i,j, Cell.State.CONDUCTOR);
                }
            }
        }
    }
}