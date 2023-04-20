package de.hhn.maXx.game;

import de.hhn.maXx.frontend.MaXxWindow;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.GameStatus;

/**
 * Klasse zum Managen des Spiels (Singleton)
 *
 * @author Dennis Mayer, Nico Vogel, Henri Staudenrausch
 * @version 1, 19.12.22
 */

public class Game {
    private final MaXxWindow window;
    private final Board board;
    private Fraction scoreW;
    private Fraction scoreB;
    private boolean whitesTurn = true;

    public Game() {
        window = new MaXxWindow(this);
        this.board = new Board(this);
        this.scoreW = new Fraction(0, 1);
        this.scoreB = new Fraction(0, 1);
        window.update(board);
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

    public void move(Direction direction) {
        System.out.println("Move: " + direction);
        if (board.movePlayer(whitesTurn, direction)) {
            whitesTurn = !whitesTurn;
            window.update(board);
        }
    }

    public boolean isWhitesTurn() {
        return whitesTurn;
    }
}