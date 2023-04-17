package de.hhn.maXx.stateMachine;

import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.GameStatus;

/**
 * Manager für die State Machine
 *
 * @author Nico Vogel
 * @version 1, 19.12.2022
 **/
public class StateManager implements State {
    State currentState;

    public StateManager(Game game) {
        this.currentState = new PlayerTurnState(true, game);
    }

    @Override
    public boolean isWhitesTurn() {
        return this.currentState.isWhitesTurn();
    }

    @Override
    public GameStatus turn() {
        return this.currentState.turn();
    }

    public void setCurrentState(State newState) {
        this.currentState = newState;
    }
}
