package de.hhn.maXx.maXxUtils;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Fraction implements Cloneable {
    private BigInteger nominator;
    private BigInteger denominator;

    public Fraction(BigInteger nominator, BigInteger denominator) {
        //TODO
    }

    public Fraction(int nominator, int denominator) {
        //TODO
    }

    public void add(Fraction fraction) {
        //TODO
    }

    private void reduce() {
        //TODO
    }

    @Override
    public String toString() {
        //TODO
        return super.toString();
    }

    public BigInteger getNominator() {
        return nominator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public double getDoubleValue() {
        return nominator.doubleValue() / denominator.doubleValue();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //TODO
        return super.clone();
    }
}
