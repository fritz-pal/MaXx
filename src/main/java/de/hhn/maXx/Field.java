package de.hhn.maXx;

/*
*
 */

import de.hhn.maXx.util.Fraction;

import de.hhn.maXx.util.FieldState;

public class Field {

    FieldState state;
    Fraction fraction;

    // Konstruktoren
    public Field (Fraction fraction, FieldState state){
        this.fraction = fraction;
        this.state = state;
    }

    public void setState(FieldState state) {
        this.state = state;
    }

    public Fraction getFraction(){
        return fraction;
    }

    public FieldState getState() {
        return state;
    }


}
