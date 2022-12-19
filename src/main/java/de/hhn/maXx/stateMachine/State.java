package de.hhn.maXx.stateMachine;

import de.hhn.maXx.util.Direction;

public interface State {
    boolean isWhitesTurn();
    void move(Direction direction);
}
