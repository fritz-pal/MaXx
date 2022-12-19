package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class Board {
    Field[][] grid;
    int wx = 3, wy = 2, bx = 4, by = 5;

    public Board() {
        grid = new Field[8][8];
        fillField();
        grid[wx][wy].setState(FieldState.WHITE);
        grid[bx][by].setState(FieldState.BLACK);
    }

    public FieldState getFieldState(int x, int y) {
        return grid[x][y].getState();
    }

    public void setFieldState(int x, int y, FieldState state) {
        grid[x][y].setState(state);
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

    private boolean movePossible(int xpos, int ypos) {
        if (!(0 <= xpos && xpos <= 7 && 0 <= ypos && ypos <= 7))
            return false;
        if (grid[xpos][ypos].getState() != FieldState.FRACTION || grid[xpos][ypos].getState() != FieldState.EMPTY)
            return false;
        return true;
    }

    public boolean movePlayer(boolean isWhite, Direction direction) {
        int xpos, ypos;
        if (isWhite) {
            xpos = wx;
            ypos = wy;
        } else {
            xpos = bx;
            ypos = by;
        }
        switch (direction) {
            case RIGHT -> xpos += 1;
            case LEFT -> xpos -= 1;
            case DOWN -> ypos += 1;
            case UP -> ypos -= 1;
            case DIAGONAL -> {
                if (isWhite) {
                    xpos += 1;
                    ypos -= 1;
                } else {
                    xpos -= 1;
                    ypos += 1;
                }
                ;
            }
        }
        if (movePossible(xpos, ypos)) {
            if (isWhite) {
                if (getFieldState(xpos, ypos).equals(FieldState.FRACTION))
                    Game.getInstance().addScoreWhite(grid[xpos][ypos].getFraction());
                setFieldState(wx, wy, FieldState.EMPTY);
                setFieldState(xpos, ypos, FieldState.WHITE);
                wx = xpos;
                wy = ypos;
            } else {
                if (getFieldState(xpos, ypos).equals(FieldState.FRACTION))
                    Game.getInstance().addScoreBlack(grid[xpos][ypos].getFraction());
                setFieldState(bx, by, FieldState.EMPTY);
                setFieldState(xpos, ypos, FieldState.BLACK);
                bx = xpos;
                by = ypos;
            }
            return true;
        }
        return false;
    }
}