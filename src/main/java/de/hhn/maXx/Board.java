package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class Board {
    Field[][] grid;

    public Board() {
        grid = new Field[8][8];
        fillField();
        grid[3][2].setState(FieldState.WHITE);
        grid[4][5].setState(FieldState.BLACK);
    }

    public FieldState getFieldState(int x, int y) {
        return grid[x][y].getState();
    }

    public Fraction getFraction(int x, int y) {
        if (grid[x][y].getState() == FieldState.FRACTION) {
            return grid[x][y].getFraction();
        } else {
            return new Fraction(0, 1);
        }

    }

    private void fillField() {
        for(int i = 0; i<8; i++){
            for(int j = 0; j<8;j++){
                grid[i][j] = new Field();
            }
        }
    }

    public boolean movePlayer(boolean isWhite, Direction direction) {
        return true;
    }
}