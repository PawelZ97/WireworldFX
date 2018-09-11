package Wires.Elements;

import Wires.Cell;

/**
 * Created by zychp_w10 on 27.05.2017.
 */
public interface WireComponent {
    Cell.State getState(int x, int y);
    int getX_size();
    int getY_size();
    int getX_handler();
    int getY_handler();
}
