package Wires;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class Cell {
    enum State {EMPTY, ELEHEAD, ELETAIL, CONDUCTOR};
    private State state;

    public Cell(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return state == cell.state;
    }

    @Override
    public String toString() {
        if (state.equals(State.CONDUCTOR)) {
            return "C";
        }
        else if (state.equals(State.ELETAIL)) {
            return "T";
        }

        else if (state.equals(State.ELEHEAD)) {
            return "H";
        }

        else  {
            return "_";
        }
    }
}

