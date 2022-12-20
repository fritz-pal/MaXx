package de.hhn.maXx.stateMachine;


import de.hhn.maXx.util.Direction;

/**
 * Manager für die State Machine
 *
 * @author Nico Vogel
 * @version 1, 19.12.2022
 **/
public class StateManager implements State {
    State currentState;

    public StateManager() {
        currentState = new PlayerTurnState(true);
    }

    @Override
    public boolean isWhitesTurn() {
        return currentState.isWhitesTurn();
    }

    @Override
    public boolean move(Direction direction) {
        return currentState.move(direction);
    }

    public void setCurrentState(State newState) {
        currentState = newState;
    }
}
