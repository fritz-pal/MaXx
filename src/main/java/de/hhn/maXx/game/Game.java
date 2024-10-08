package de.hhn.maXx.game;

import de.hhn.maXx.frontend.MaXxWindow;
import de.hhn.maXx.frontend.Sound;
import de.hhn.maXx.util.*;

import java.io.Serializable;

/**
 * Klasse zum Managen des Spiels
 *
 * @author Lukas Vier 215997, Dennis Mayer 215964, Nico Vogel 215998, Henri Staudenrausch 215994
 * @version 3, 27.04.23
 */

public class Game implements Serializable {
    private transient MaXxWindow window;
    private final Board board;
    private Fraction scoreW;
    private Fraction scoreB;
    private boolean whitesTurn;

    public Game() {
        this.window = new MaXxWindow(this);
        this.board = new Board(this);
        this.scoreW = new Fraction(0, 1);
        this.scoreB = new Fraction(0, 1);
        this.whitesTurn = true;
        this.window.update();
    }

    public void makeWindow() {
        window = new MaXxWindow(this);
        window.update();
    }

    public Fraction getScoreW() {
        return this.scoreW;
    }

    public Fraction getScoreB() {
        return this.scoreB;
    }

    public Board getBoard() {
        return this.board;
    }

    public void addScoreBlack(Fraction fraction) {
        this.scoreB = this.scoreB.add(fraction);

    }

    public void addScoreWhite(Fraction fraction) {
        this.scoreW = this.scoreW.add(fraction);
    }

    // erkennen von Knopfdruck auf Steuerkreuz
    public void buttonClicked(IntVector2 pos) {
        IntVector2 playerPos = board.getPlayerPos(whitesTurn);
        IntVector2 dif = playerPos.subtract(pos);
        if (dif.equals(new IntVector2(0, 1))) {
            move(Direction.UP);
        } else if (dif.equals(new IntVector2(0, -1))) {
            move(Direction.DOWN);
        } else if (dif.equals(new IntVector2(1, 0))) {
            move(Direction.LEFT);
        } else if (dif.equals(new IntVector2(-1, 0))) {
            move(Direction.RIGHT);
        } else if ((dif.equals(new IntVector2(1, -1)) && !whitesTurn)
                || (dif.equals(new IntVector2(-1, 1)) && whitesTurn)) {
            move(Direction.DIAGONAL);
        } else {
            Sound.play(SoundType.INVALID_MOVE);
        }
    }

    // Bewegt den Spielstein (mit Errorsound und Check nach Sieg)
    public void move(Direction direction) {
        System.out.println("Move: " + direction);
        if (board.movePlayer(whitesTurn, direction)) {
            whitesTurn = !whitesTurn;
            window.update();
        } else {
            Sound.play(SoundType.INVALID_MOVE);
        }
        gameDone();
    }

    // gibt zurück wer am zug ist
    public boolean isWhitesTurn() {
        return whitesTurn;
    }

    // kontrolliert die Scores nach Werten über Siegpunktzahl
    public void gameDone() {
        if (scoreW.doubleValue() > 53D) {
            window.displayWin(true);
        } else if (scoreB.doubleValue() > 53D) {
            window.displayWin(false);
        }
    }
}
