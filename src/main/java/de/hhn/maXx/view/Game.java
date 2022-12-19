package de.hhn.maXx.view;

import de.hhn.maXx.maXxUtils.Fraction;
import de.hhn.maXx.maXxUtils.FieldState;

public interface Game {
    public void update (int x, int y, Fraction fraction, FieldState state);
}
