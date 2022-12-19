package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class Board {
    private final Field[][] grid;
    private int wx = 3, wy = 2, bx = 4, by = 5;

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

    private boolean movePossible(int xPos, int yPos) {
        if (xPos > 7 || xPos < 0 || yPos > 7 || yPos < 0) return false;
        return switch (getFieldState(xPos, yPos)) {
            case BLACK, WHITE -> false;
            default -> true;
        };
    }

    public boolean movePlayerPossible(boolean isWhite, Direction direction){
        int[] coords = getNewCoords(isWhite, direction);
        return movePossible(coords[0], coords[1]);
    }

    public boolean movePlayer(boolean isWhite, Direction direction) {
        int[] coords = getNewCoords(isWhite, direction);
        int xPos = coords[0];
        int yPos = coords[1];
        if (movePossible(xPos, yPos)) {
            if (isWhite) {
                if (getFieldState(xPos, yPos).equals(FieldState.FRACTION))
                    Game.getInstance().addScoreWhite(grid[xPos][yPos].getFraction());
                setFieldState(wx, wy, FieldState.EMPTY);
                setFieldState(xPos, yPos, FieldState.WHITE);
                wx = xPos;
                wy = yPos;
            } else {
                if (getFieldState(xPos, yPos).equals(FieldState.FRACTION))
                    Game.getInstance().addScoreBlack(grid[xPos][yPos].getFraction());
                setFieldState(bx, by, FieldState.EMPTY);
                setFieldState(xPos, yPos, FieldState.BLACK);
                bx = xPos;
                by = yPos;
            }
            return true;
        }
        return false;
    }

    private int[] getNewCoords(boolean isWhite, Direction direction){
        int xPos, yPos;
        if (isWhite) {
            xPos = wx;
            yPos = wy;
        } else {
            xPos = bx;
            yPos = by;
        }
        switch (direction) {
            case RIGHT -> xPos += 1;
            case LEFT -> xPos -= 1;
            case DOWN -> yPos += 1;
            case UP -> yPos -= 1;
            case DIAGONAL -> {
                if (isWhite) {
                    xPos += 1;
                    yPos -= 1;
                } else {
                    xPos -= 1;
                    yPos += 1;
                }
            }
        }
    return new int[]{xPos, yPos};
    }
}