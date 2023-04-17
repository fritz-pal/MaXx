package de.hhn.maXx.stateMachine;

import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.GameStatus;
import de.hhn.maXx.util.MyIO;


/**
 * Diese Klasse definiert die Aktion im Zustand: Spieler an der Reihe.
 *
 * @author Nico Vogel
 * @version 1, 19.12.2022
 **/
public class PlayerTurnState implements State {
    Game game;
    boolean isWhite;

    public PlayerTurnState(boolean isWhite, Game game) {
        this.isWhite = isWhite;
        this.game = game;
    }

    public static Direction getInput(String prompt) {
        Direction dir;
        String input = MyIO.promptAndRead(prompt);
        dir = Direction.fromString(input);
        while (dir == null) {
            input = MyIO.promptAndRead("Gebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein:");
            dir = Direction.fromString(input);
        }
        return dir;
    }

    @Override
    public boolean isWhitesTurn() {
        return this.isWhite;
    }

    @Override
    public GameStatus turn() {
        Direction direction = getInput((this.isWhite ? "Weiß" : "Schwarz") + " ist an der Reihe. \nGebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein, um dich zu bewegen:");
        while (!game.getBoard().movePlayer(this.isWhite, direction))
            direction = getInput("Du kannst nicht in diese Richtung laufen! Versuche es nochmal: ");
        game.getStateManager().setCurrentState(new PlayerTurnState(!this.isWhite, game));
        if (game.getScoreB().doubleValue() >= 53) return GameStatus.BLACK_WIN;
        if (game.getScoreW().doubleValue() >= 53) return GameStatus.WHITE_WIN;
        return GameStatus.CONTINUE;
    }
}
