package de.hhn.maXx.util;

import java.math.BigInteger;

/**
 * Eine Subklasse von Number die immutable
 * ist, den Datentyp "Rationale Zahl" (Bruch mit ganzzahligem
 * Zähler und Nenner = BigInteger) realisiert und das Interface Comparable
 * implementiert.
 *
 * @author Henri Staudenrausch, Nadine Schoch, Dennis Mayer
 * @version 3, 19.12.2022
 **/
public class Fraction extends Number implements Comparable<Fraction> {
    protected BigInteger numerator;
    protected BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) throw new ArithmeticException("Denominator must not be zero!");
        BigInteger gcd = numerator.gcd(denominator);
        BigInteger newNum = numerator.divide(gcd);
        BigInteger newDen = denominator.divide(gcd);
        if (newDen.compareTo(BigInteger.ZERO) < 0) {
            newNum = newNum.negate();
            newDen = newDen.negate();
        }
        this.numerator = newNum;
        this.denominator = newDen;
    }

    public Fraction(String numerator, String denominator) throws NumberFormatException {
        this(new BigInteger(numerator), new BigInteger(denominator));
    }

    public Fraction(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    public static Fraction parseFraction(String s) throws NumberFormatException, ArithmeticException {
        try {
            String[] numbers = s.replace(" ", "").split("/", 2);
            BigInteger num = new BigInteger(numbers[0]);
            BigInteger den = new BigInteger(numbers[1]);
            return new Fraction(num, den);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NumberFormatException();
        }
    }

    public Fraction add(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator).add(this.denominator.multiply(other.numerator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    public Fraction subtract(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator).subtract(this.denominator.multiply(other.numerator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    public Fraction multiply(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.numerator);
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    public Fraction divide(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator);
        BigInteger newDen = this.denominator.multiply(other.numerator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    public Fraction reduce() {
        BigInteger gcd = numerator.gcd(denominator);
        BigInteger newNum = numerator.divide(gcd);
        BigInteger newDen = denominator.divide(gcd);
        if (newDen.compareTo(BigInteger.ZERO) < 0) {
            newNum = newNum.negate();
            newDen = newDen.negate();
        }
        return new Fraction(newNum, newDen);
    }

    public boolean isInteger() {
        return denominator.equals(BigInteger.ONE);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public int compareTo(Fraction other) {
        BigInteger num1 = this.numerator.multiply(other.denominator);
        BigInteger num2 = other.numerator.multiply(this.denominator);
        return num1.compareTo(num2);
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fraction f)) return false;
        if (!numerator.equals(f.numerator)) return false;
        return denominator.equals(f.denominator);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }
}
