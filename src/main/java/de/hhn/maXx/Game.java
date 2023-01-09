package de.hhn.maXx;

import de.hhn.maXx.stateMachine.StateManager;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.GameStatus;

/**
 * Klasse zum Managen des Spiels (Singleton)
 *
 * @author Dennis Mayer, Nico Vogel, Henri Staudenrausch
 * @version 1, 19.12.22
 */

public class Game {
    private static Game instance = null;
    private Board board;
    private StateManager stateManager;
    private Fraction scoreW;
    private Fraction scoreB;

    private Game() {
        init();
    }
    
    private void init(){
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
        if (instance == null)
            instance = new Game();
        instance.init();
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

    public GameStatus continueGame() {
        ConsoleGame.clearConsole();
        ConsoleGame.paint();
        return stateManager.turn();
    }


}
