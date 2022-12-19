package de.hhn.maXx;

import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.stateMachine.StateManager;
import de.hhn.maXx.util.GameStatus;

public class Game {
    private static Game instance = null;
    private Board board;
    private Fraction scoreW;
    private Fraction scoreB;
    private StateManager stateManager;

    private Game() {
        board = new Board();
        scoreW = new Fraction(0, 1);
        scoreB = new Fraction(0, 1);
        ConsoleGame view = new ConsoleGame(board);
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
        this.scoreB = scoreB.add(fraction);

    }

    public void addScoreWhite(Fraction fraction) {
        this.scoreW.add(fraction);
    }

    public GameStatus tick() {
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
}
