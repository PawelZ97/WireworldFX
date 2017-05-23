package Wires;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class WireLogic {
    private int x_size;
    private int y_size;
    private Board bef;
    private Board aft;
    private WireLogicEngine eng;


    public WireLogic(int x_size, int y_size) throws Exception {
        this.x_size = x_size;
        this.y_size = y_size;
        bef = new Board(x_size, y_size);
        aft = new Board(x_size, y_size);
        eng = new WireLogicEngine(bef, aft);

        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(4, i, Cell.State.CONDUCTOR);
        for (int i = 1; i < 9; i++)
            bef.setBoardCellState(6, i, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 1, Cell.State.CONDUCTOR);
        bef.setBoardCellState(5, 8, Cell.State.CONDUCTOR);
        bef.setBoardCellState(4, 4, Cell.State.ELEHEAD);
        bef.setBoardCellState(4, 3, Cell.State.ELETAIL);

        //System.out.println("BEFORE:");
        //bef.printBoardToConsole();
        //System.out.println("AFTER:");
        //aft.printBoardToConsole();
    }

    public WireLogicEngine getEng() {
        return eng;
    }

    public void tick() throws Exception {
        eng.calculate();
        eng.copy();
        eng.setAfter(new Board(x_size, y_size));
        //eng.getBefore().printBoardToConsole();
    }
}


