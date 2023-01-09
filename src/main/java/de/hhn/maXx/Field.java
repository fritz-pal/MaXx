package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

/**
 * Eine Klasse um die einzelnen Felder des Spielbretts zu beschreiben.
 * Im Konstruktor wird eine zufällige Fraction mit einem Wert zwischen 1 und 8 ausgewählt.
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
        int randDen = (int) (Math.random() * 490 + 10);
        int randNum = (int) (Math.random() * (Math.min(randDen * 8, 1000) - randDen)) + randDen;
        fraction = new Fraction(randNum, randDen);
        if (!fractionFine()) initFraction();
    }

    private boolean fractionFine() {
        int numLen = fraction.getNumerator().toString().length();
        int denLen = fraction.getDenominator().toString().length();
        return numLen > 1 && numLen < 4 && denLen > 1 && denLen < 4;
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
