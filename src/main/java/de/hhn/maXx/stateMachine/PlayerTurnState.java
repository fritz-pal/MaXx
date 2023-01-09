package de.hhn.maXx.stateMachine;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.GameStatus;
import de.hhn.maXx.util.MyIO;

import static de.hhn.maXx.Game.getInstance;

/**
 * Diese Klasse definiert die Aktion im Zustand: Spieler an der Reihe.
 *
 * @author Nico Vogel
 * @version 1, 19.12.2022
 **/
public class PlayerTurnState implements State{

    boolean isWhite;

    public PlayerTurnState(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public boolean isWhitesTurn() {
        return isWhite;
    }

    @Override
    public GameStatus turn() {
        Direction direction = getInput((isWhite ? "Weiß" : "Schwarz") + " ist an der Reihe. \nGebe LEFT, RIGHT, UP, DOWN oder DIAGONAL ein, um dich zu bewegen:");
        while (!getInstance().getBoard().movePlayer(isWhite, direction))
            direction = getInput("Du kannst nicht in diese Richtung laufen! Versuche es nochmal: ");
        getInstance().getStateManager().setCurrentState(new PlayerTurnState(!isWhite));
        if (getInstance().getScoreB().doubleValue() >= 53) return GameStatus.BLACK_WIN;
        if (getInstance().getScoreW().doubleValue() >= 53) return GameStatus.WHITE_WIN;
        return GameStatus.CONTINUE;
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
}
