package de.hhn.maXx.game;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

/**
 * Eine Klasse um die einzelnen Felder des Spielbretts zu beschreiben.
 * Im Konstruktor wird eine zufällige Fraction mit einem Wert zwischen 1 und 8
 * ausgewählt.
 *
 * @author Nadine Schoch, Henri Staudenrausch, Lukas Vier
 * @version 3, 27.04.22
 */
public class Field {
    private FieldState state;
    private Fraction fraction;

    public Field() {
        this.state = FieldState.FRACTION;
        initFraction();
    }

    // generiert einen neuen Bruch
    private void initFraction() {
        int randDen = (int) (Math.random() * 490 + 10);
        int randNum = (int) (Math.random() * (Math.min(randDen * 8, 1000) - randDen)) + randDen;
        this.fraction = new Fraction(randNum, randDen);
        if (!fractionFine())
            initFraction();
    }

    // kontrolliert einen Bruch nach den Anforderungen
    private boolean fractionFine() {
        int numLen = this.fraction.getNumerator().toString().length();
        int denLen = this.fraction.getDenominator().toString().length();
        return numLen > 1 && numLen < 4 && denLen > 1 && denLen < 4;
    }

    public Fraction getFraction() {
        return this.fraction;
    }

    public FieldState getState() {
        return this.state;
    }

    public void setState(FieldState state) {
        this.state = state;
    }

}