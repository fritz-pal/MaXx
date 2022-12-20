package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

import java.math.BigInteger;

/**
 * Eine Klasse um die einzelnen Felder des Spielbretts zu beschreiben.
 * Im Konstruktor wird eine zufällige Fraction mit einem Wert zwischen 1 und 5 ausgewählt.
 *
 * @author Nadine Schoch, Henri Staudenrausch
 * @version 2, 19.12.22
 */
public class Field {

    private FieldState state;
    private Fraction fraction;

    public Field() {
        state = FieldState.FRACTION;
        initFraction();
    }

    private void initFraction() {
        int randDen = (int) (Math.random() * 1000);
        int randNum = (int) (Math.random() * (Math.min(randDen*5, 1000) - randDen)) + randDen;
        if (randDen == 0) {
            initFraction();
            return;
        }
        fraction = new Fraction(randNum, randDen);
        if (fraction.getNumerator().compareTo(new BigInteger("9")) <= 0 || fraction.getNumerator().compareTo(new BigInteger("999")) > 0) {
            initFraction();
        } else if (fraction.getDenominator().compareTo(new BigInteger("9")) <= 0 || fraction.getDenominator().compareTo(new BigInteger("999")) > 0) {
            initFraction();
        }
    }

    public Fraction getFraction() {
        return fraction;
    }

    public FieldState getState() {
        return state;
    }

    public void setState(FieldState state) {
        this.state = state;
    }


}
