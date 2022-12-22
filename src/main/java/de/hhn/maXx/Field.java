package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

/**
 * Eine Klasse um die einzelnen Felder des Spielbretts zu beschreiben.
 * Im Konstruktor wird eine zufällige Fraction mit einem Wert zwischen 1 und 5 ausgewählt.
 *
 * @author Nadine Schoch, Henri Staudenrausch
 * @version 2, 19.12.22
 */
public class Field {
    private FieldState state;
    private final Fraction fraction;

    public Field() {
        state = FieldState.FRACTION;
        fraction = alt();
    }
/*
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
*/
    private Fraction alt() {
        Fraction erg;
        Fraction base = new Fraction(Math.min((int)(Math.random() * 4) + 2, 9), 1); //Random Fraction between 2 and 5 (Integer)
        int offsetDen = (int)(Math.random() * 190 + 10);
        int offsetNum = (int)(offsetDen * - 1 + Math.random() * offsetDen * 2);
        Fraction offset = new Fraction(offsetNum, offsetDen);
        erg = base.add(offset);
        if (!fractionFine(erg))
            erg = alt();
        return erg;
    }

    private boolean fractionFine(Fraction fraction) {
        int numLen = fraction.getNumerator().toString().length();
        int deLen = fraction.getDenominator().toString().length();
        return numLen > 1 && numLen < 4 && deLen > 1 && deLen < 4;
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
