package de.hhn.maXx.stateMachine;

import de.hhn.maXx.util.Direction;

public interface State {
    void move(Direction direction);
}
