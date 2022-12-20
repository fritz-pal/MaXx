package de.hhn.maXx;

import de.hhn.maXx.stateMachine.StateManager;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.GameStatus;
import de.hhn.maXx.util.MyIO;

/**
 * Klasse zum Managen des Spiels (Singleton)
 *
 * @author Dennis Mayer, Nico Vogel, Henri Staudenrausch
 * @version 1, 19.12.22
 */
public class Game {
    private static Game instance = null;
    private final Board board;
    private final StateManager stateManager;
    private Fraction scoreW;
    private Fraction scoreB;

    private Game() {
        board = new Board();
        scoreW = new Fraction(0, 1);
        scoreB = new Fraction(0, 1);
        stateManager = new StateManager();
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
        boolean isWhite = stateManager.isWhitesTurn();
        Direction direction = getInput((isWhite ? "Weiß" : "Schwarz") + " ist an der Reihe. \nGebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein, um dich zu bewegen:");
        while (!getInstance().getStateManager().move(direction)) {
            direction = getInput("Du kannst nicht in diese Richtung laufen! Versuche es nochmal: ");
        }
        if (scoreB.doubleValue() >= 53) return GameStatus.BLACK_WIN;
        if (scoreW.doubleValue() >= 53) return GameStatus.WHITE_WIN;
        return GameStatus.CONTINUE;
    }

    public Direction getInput(String prompt) {
        Direction dir;
        String input = MyIO.promptAndRead(prompt);
        dir = Direction.fromString(input);
        while (dir == null) {
            input = MyIO.promptAndRead("Gebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein:");
            dir = Direction.fromString(input);
        }
        return dir;
    }
}
