package de.hhn.maXx.stateMachine;

import de.hhn.maXx.util.Direction;

/**
 * Interface als Grundlage für die States.
 *
 * @author Nico Vogel
 * @version 1, 19.12.2022
 **/
public interface State {
    boolean isWhitesTurn();
    void move(Direction direction);
}
