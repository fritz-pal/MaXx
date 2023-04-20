package de.hhn.maXx.game;

import de.hhn.maXx.frontend.MaXxWindow;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.GameStatus;
import de.hhn.maXx.util.IntVector2;

/**
 * Klasse zum Managen des Spiels
 *
 * @author Lukas Vier, Dennis Mayer, Nico Vogel, Henri Staudenrausch
 * @version 2, 20.04.23
 */

public class Game {
    private final MaXxWindow window;
    private final Board board;
    private Fraction scoreW;
    private Fraction scoreB;
    private boolean whitesTurn = true;
    private GameStatus gameStatus = GameStatus.CONTINUE;

    public Game() {
        window = new MaXxWindow(this);
        this.board = new Board(this);
        this.scoreW = new Fraction(0, 1);
        this.scoreB = new Fraction(0, 1);
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
        }
    }

    public void move(Direction direction) {
        System.out.println("Move: " + direction);
        if (board.movePlayer(whitesTurn, direction)) {
            whitesTurn = !whitesTurn;
            window.update();
        }
        if (gameDone()) {
            // TODO call finishedscreen
            // TODO lock moving when gamestatus != Continue
        }
        // Score console ausgabe zum Testen: System.out.println("White "+ scoreW.intValue() + " black "+ scoreB.intValue() + " " + gameStatus);
    }

    public boolean isWhitesTurn() {
        return whitesTurn;
    }

    // check scores above winning value, set gamestate for locking moves
    public boolean gameDone() {
        if (scoreW.doubleValue() > 53d) {
            gameStatus = GameStatus.WHITE_WIN;
            return true;
        } else if (scoreB.doubleValue() > 53d) {
            gameStatus = GameStatus.BLACK_WIN;
            return true;
        }
        return false;
    }
}
