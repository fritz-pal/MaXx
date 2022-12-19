package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.stateMachine.StateManager;
import de.hhn.maXx.util.GameStatus;
import de.hhn.maXx.util.MyIO;

/**
 * Ein Singleton der das gesamte Spiel darstellt.
 *
 * @author Nico Vogel, Dennis Mayer
 * @version 1, 19.12.22
 */
public class Game {
    private static Game instance = null;
    private final Board board;
    private Fraction scoreW;
    private Fraction scoreB;
    private final StateManager stateManager;

    private Game() {
        board = new Board();
        scoreW = new Fraction(0, 1);
        scoreB = new Fraction(0, 1);
        stateManager = new StateManager();
    }

    public Fraction getScoreW() {
        return scoreW;
    }

    public Fraction getScoreB() {
        return scoreB;
    }

    public Board getBoard() {
        return board;
    }


    public StateManager getStateManager() {
        return stateManager;
    }

    public void addScoreBlack(Fraction fraction) {
        scoreB = scoreB.add(fraction);

    }

    public void addScoreWhite(Fraction fraction) {
        scoreW = scoreW.add(fraction);
    }

    public GameStatus tick() {
        ConsoleGame.paint();
        Direction direction = getInput(getInstance().getStateManager().isWhitesTurn());
        getInstance().getStateManager().move(direction);
        if (scoreB.doubleValue() >= 53)
            return GameStatus.BLACK_WIN;
        if (scoreW.doubleValue() >= 53)
            return GameStatus.WHITE_WIN;
        return GameStatus.CONTINUE;
    }

    public static Game getInstance() {
        if (instance == null) {
            throw new NullPointerException();
        }
        return instance;
    }

    public static void startNewInstance() {
        instance = new Game();
    }

    public Direction getInput(boolean isWhite) {
        Direction dir;
        String input = MyIO.promptAndRead((isWhite ? "Weiß" : "Schwarz") + " ist an der Reihe. \nGebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein, um dich zu bewegen:");
        dir = Direction.fromString(input);
        while (dir == null) {
            input = MyIO.promptAndRead("Gebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein:");
            dir = Direction.fromString(input);
        }
        return dir;
    }
}
