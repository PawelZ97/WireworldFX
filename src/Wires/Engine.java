package Wires;


/**
 * Created by zychp_w10 on 14.05.2017.
 */
public class Engine {
    private Board before;
    private Board after;

    public Engine(Board before, Board after) {
        this.before = before;
        this.after = after;
    }

    public void setBefore(Board before) {
        this.before = before;
    }

    public Board getBefore() {
        return before;
    }

    public void setAfter(Board after) {
        this.after = after;
    }

    public Board getAfter() {
        return after;
    }

    public void copy(){
        before.setBoard(after.getBoard().clone());
    }

    public void calculate(){
        int x = before.getX();
        int y = before.getY();

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