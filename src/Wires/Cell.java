package Wires;

/**
 * Created by zychp_w10 on 12.05.2017.
 */
public class Cell {
    /**
     * Klasa Cell pozwala tworzyć komórki przechowujące jeden z 4 dostępnych stanów.
     * Empty, Elehead, Eletail, Conductor
     */
    public enum State {EMPTY, ELEHEAD, ELETAIL, CONDUCTOR};
    private State state;

    public Cell(State state) {
        /**
         * Tworzy obiekt Cell.
         * @param state Stan tworzonej komórki.
         */
        this.state = state;
    }

    public State getState() {
        /**
         * Zwraca stan komórki.
         * @return Stan komórki.
         */
        return state;
    }

    public void setState(State state) {
        /**
         * Ustawia stan komórki.
         * @param state Stan komórki.
         */

        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        /**
         * Sprawdza czy dwie komórki są identyczne.
         * @param o Obiekt wejściowy.
         * @return Efekt przyrównania stanów.
         */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        return state == cell.state;
    }

    @Override
    public String toString() {
        /**
         * Tłumaczy komórkę na symbol.
         * @return Symbol odpowiadający danemu stanowi.
         */
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

