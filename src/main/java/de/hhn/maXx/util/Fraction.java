package de.hhn.maXx.util;

import java.math.BigInteger;

/**
 * Eine Subklasse von Number die immutable
 * ist, den Datentyp "Rationale Zahl" (Bruch mit ganzzahligem
 * Zähler und Nenner = BigInteger) realisiert und das Interface Comparable
 * implementiert.
 *
 * @author Henri Staudenrausch, Nadine Schoch, Dennis Mayer, Nico Vogel
 * @version 4, 20.12.2022
 **/
public class Fraction extends Number implements Comparable<Fraction> {
    public static final Fraction ZERO = new Fraction(0, 1);
    protected BigInteger numerator;
    protected BigInteger denominator;

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    // Konstruktor Biginteger
    public Fraction(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO))
            throw new ArithmeticException("Denominator must not be zero!");
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

    // Konstruktor String
    public Fraction(String numerator, String denominator) throws NumberFormatException {
        this(new BigInteger(numerator), new BigInteger(denominator));
    }

    // Konstruktor long
    public Fraction(long numerator, long denominator) {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    // Konstruktor Strings (1/2 Notation)
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

    // addieren von Brüchen
    public Fraction add(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator).add(this.denominator.multiply(other.numerator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    // subtrahieren von Brüchen
    public Fraction subtract(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator)
                .subtract(this.denominator.multiply(other.numerator));
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    // multiplizieren von Brüchen
    public Fraction multiply(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.numerator);
        BigInteger newDen = this.denominator.multiply(other.denominator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    // dividieren von Brüchen
    public Fraction divide(Fraction other) {
        BigInteger newNum = this.numerator.multiply(other.denominator);
        BigInteger newDen = this.denominator.multiply(other.numerator);
        Fraction result = new Fraction(newNum, newDen);
        return result.reduce();
    }

    // kürzen von Brüchen
    public Fraction reduce() {
        BigInteger gcd = this.numerator.gcd(denominator);
        BigInteger newNum = this.numerator.divide(gcd);
        BigInteger newDen = this.denominator.divide(gcd);
        if (newDen.compareTo(BigInteger.ZERO) < 0) {
            newNum = newNum.negate();
            newDen = newDen.negate();
        }
        return new Fraction(newNum, newDen);
    }

    // kontrollieren, ob der Bruch eine Ganzzahl ist
    public boolean isInteger() {
        return this.denominator.equals(BigInteger.ONE);
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    @Override
    public int compareTo(Fraction other) {
        BigInteger num1 = this.numerator.multiply(other.denominator);
        BigInteger num2 = other.numerator.multiply(this.denominator);
        return num1.compareTo(num2);
    }

    // rückgabe in verschiedenen Datentypen
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
        return this.numerator.doubleValue() / this.denominator.doubleValue();
    }

    // überprüfen auf Gleichheit
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Fraction f))
            return false;
        if (!this.numerator.equals(f.numerator))
            return false;
        return this.denominator.equals(f.denominator);
    }
}
