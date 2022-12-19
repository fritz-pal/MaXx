package de.hhn.maXx.stateMachine;

import de.hhn.maXx.util.Direction;

import static de.hhn.maXx.Game.getInstance;

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
    public void move(Direction direction) {
        if (getInstance().getBoard().movePlayer(isWhite, direction)){
            getInstance().getStateManager().setCurrentState(new PlayerTurnState(!isWhite));
        }else{
            System.out.println("Impossible move");
        }
    }
}
