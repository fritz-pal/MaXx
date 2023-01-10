package de.hhn.maXx.game;

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
        this.board = new Board();
        this.scoreW = new Fraction(0, 1);
        this.scoreB = new Fraction(0, 1);
        this.stateManager = new StateManager();
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
        return this.scoreW;
    }


    public Fraction getScoreB() {
        return this.scoreB;
    }
    

    public Board getBoard() {
        return this.board;
    }

    public StateManager getStateManager() {
        return this.stateManager;
    }

    public void addScoreBlack(Fraction fraction) {
        this.scoreB = this.scoreB.add(fraction);

    }

    public void addScoreWhite(Fraction fraction) {
        this.scoreW = this.scoreW.add(fraction);
    }

    public GameStatus continueGame() {
        ConsoleGame.clearConsole();
        ConsoleGame.paint();
        return this.stateManager.turn();
    }
}
