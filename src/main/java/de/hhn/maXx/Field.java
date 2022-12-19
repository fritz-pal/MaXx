package de.hhn.maXx;

/*
 *
 */

import de.hhn.maXx.util.Fraction;

import de.hhn.maXx.util.FieldState;

import java.math.BigInteger;

public class Field {

    private FieldState state;
    private Fraction fraction;

    // Konstruktoren
    public Field(Fraction fraction, FieldState state) {
        this.fraction = fraction;
        this.state = state;
    }

    public Field() {
        state = FieldState.FRACTION;
        initFraction();
    }

    private void initFraction() {
        int randNum = (int) (Math.random() * 1000);
        int randDen = (int) (Math.random() * 1000);
        if (randDen == 0){
            initFraction();
            return;
        }
        fraction = new Fraction(randNum, randDen);
        if (fraction.getNumerator().compareTo(new BigInteger("9")) <= 0 || fraction.getNumerator().compareTo(new BigInteger("999")) > 0) {
            initFraction();
        }else if(fraction.getDenominator().compareTo(new BigInteger("9")) <= 0 || fraction.getDenominator().compareTo(new BigInteger("999")) > 0){
            initFraction();
        }
    }

    public void setState(FieldState state) {
        this.state = state;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public FieldState getState() {
        return state;
    }


}
