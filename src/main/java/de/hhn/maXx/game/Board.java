package de.hhn.maXx.game;

import de.hhn.maXx.frontend.Sound;
import de.hhn.maXx.util.*;

import java.util.stream.IntStream;

/**
 * Die Klasse Board beinhaltet alle Methoden, um Spieler zu bewegen,
 * Spielfelder zu befüllen und Informationen zum Spielbrett zu erlangen.
 *
 * @author Lukas Vier, Henri Staudenrausch, Nico Vogel
 * @version 2, 19.12.22
 */

public class Board {
    private final Field[][] grid;
    private final Game game;
    private IntVector2 whitePos, blackPos;

    public Board(Game game) {
        this.game = game;
        this.grid = new Field[8][8];
        fillField();
        this.whitePos = new IntVector2(3, 2);
        this.blackPos = new IntVector2(4, 5);
        setFieldState(this.whitePos, FieldState.WHITE);
        setFieldState(this.blackPos, FieldState.BLACK);
    }

    private static boolean posOutOfBounds(IntVector2 pos) {
        return pos.x > 7 || pos.x < 0 || pos.y > 7 || pos.y < 0;
    }

    public FieldState getFieldState(IntVector2 pos) {
        return this.grid[pos.x][pos.y].getState();
    }

    public void setFieldState(IntVector2 pos, FieldState state) {
        this.grid[pos.x][pos.y].setState(state);
    }

    public Fraction getFraction(IntVector2 pos) {
        if (this.grid[pos.x][pos.y].getState() == FieldState.FRACTION) {
            return this.grid[pos.x][pos.y].getFraction();
        } else {
            return Fraction.ZERO;
        }
    }

    private void fillField() {
        IntStream.range(0, 8).forEach(
                x -> IntStream.range(0, 8).forEach(
                        y -> this.grid[x][y] = new Field()
                )
        );
    }

    private boolean movePossible(IntVector2 target) {
        return !posOutOfBounds(target) && !getFieldState(target).equals(FieldState.BLACK) && !getFieldState(target).equals(FieldState.WHITE);
    }

    public boolean movePlayer(boolean isWhite, Direction direction) {
        IntVector2 target = getNewCoords(isWhite, direction);
        if (movePossible(target)) {
            if (getFieldState(target).equals(FieldState.FRACTION))
                Sound.play(SoundType.CAPTURE);
            else Sound.play(SoundType.MOVE);
            if (isWhite) {
                if (getFieldState(target).equals(FieldState.FRACTION))
                    game.addScoreWhite(getFraction(target));
                setFieldState(this.whitePos, FieldState.EMPTY);
                setFieldState(target, FieldState.WHITE);
                this.whitePos = target;
            } else {
                if (getFieldState(target).equals(FieldState.FRACTION))
                    game.addScoreBlack(getFraction(target));
                setFieldState(this.blackPos, FieldState.EMPTY);
                setFieldState(target, FieldState.BLACK);
                this.blackPos = target;
            }
            return true;
        }
        return false;
    }

    private IntVector2 getNewCoords(boolean isWhite, Direction direction) {
        IntVector2 pos = isWhite ? this.whitePos : this.blackPos;

        pos = pos.add(switch (direction) {
            case RIGHT -> new IntVector2(1, 0);
            case LEFT -> new IntVector2(-1, 0);
            case DOWN -> new IntVector2(0, 1);
            case UP -> new IntVector2(0, -1);
            case DIAGONAL -> isWhite ? new IntVector2(1, -1) : new IntVector2(-1, 1);
        });
        return pos;
    }

    public Field[][] getGrid() {
        return this.grid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sb.append(this.grid[i][j].getState().toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public IntVector2 getPlayerPos(boolean isWhite) {
        return isWhite ? this.whitePos : this.blackPos;
    }
}