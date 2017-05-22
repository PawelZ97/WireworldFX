package Wires;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class WireLogic {
    private Board bef;
    private Board aft;
    private WireLogicEngine eng;

    public WireLogic() throws Exception {
        bef = new Board(10, 10);
        aft = new Board(10, 10);
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

        System.out.println("BEFORE:");
        bef.printBoardConsole();
        System.out.println("AFTER:");
        aft.printBoardConsole();
    }

    public WireLogicEngine getEng() {
        return eng;
    }

    public void tick() throws Exception {
        eng.calculate();
        eng.copy();
        eng.setAfter(new Board(10, 10));
        eng.getBefore().printBoardConsole();
    }
}


