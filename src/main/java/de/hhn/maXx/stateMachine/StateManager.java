package de.hhn.maXx.stateMachine;


import de.hhn.maXx.util.Direction;

public class StateManager implements State {
    State currentState;

    public StateManager() {
        currentState = new WhiteTurnState();
    }

    @Override
    public void move(Direction direction) {

    }
}
