package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class Board {
    Field[][] grid;
    int wx = 3, wy = 2, bx = 4, by = 5;
    int xpos, ypos;

    public Board() {
        grid = new Field[8][8];
        fillField();
        grid[wx][wy].setState(FieldState.WHITE);
        grid[bx][by].setState(FieldState.BLACK);
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
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                grid[i][j] = new Field();
            }
        }
    }

    public boolean movePossible(boolean isWhite, Direction direction) {
        if (isWhite == true) {
            xpos = wx;
            ypos = wy;
        } else if (isWhite == false) {
            xpos = bx;
            ypos = by;
        }
        switch (direction) {
            case RIGHT -> xpos += 1;
            case LEFT -> xpos -= 1;
            case DOWN -> ypos += 1;
            case UP -> ypos -= 1;
            case DIAGONAL -> {
                if (isWhite == true) {
                    xpos -= 1;
                    ypos += 1;
                } else {
                    xpos += 1;
                    ypos -= 1;
                }
                ;
            }
        }
        if (0 <= xpos && xpos <= 7 && 0 <= ypos && ypos <= 7) {
            return true;
        } else {
            return false;
        }
    }

    public void movePlayer(boolean isWhite, Direction direction) {
        if (movePossible(isWhite, direction) == true) {

        } else {

        }
    }
}