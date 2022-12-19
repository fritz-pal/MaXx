package de.hhn.maXx.stateMachine;


import de.hhn.maXx.util.Direction;

public class StateManager implements State {
    State currentState;

    public StateManager() {
        currentState = new PlayerTurnState(true);
    }

    @Override
    public void move(Direction direction) {
        currentState.move(direction);
    }

    public void setCurrentState(State newState) {
        currentState = newState;
    }
}
