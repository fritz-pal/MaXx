package de.hhn.maXx.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Hilfsklasse zum Lesen und Schreiben der Konsole.
 *
 * @author Henri Staudenrausch, Nadine Schoch
 * @version 3, 12.12.2022
 **/
public final class MyIO {
    static Scanner sc = new Scanner(System.in);

    private MyIO() {
    }

    // Erfragt einen String vom Nutzer über die Knosole
    public static String promptAndRead(String prompt) {
        try {
            System.out.println(prompt);
            return sc.nextLine();
        } catch (NoSuchElementException | IllegalStateException e) {
            return promptAndRead("Error - do it again: ");
        }
    }

    // Erfragt einen Integer Wert vom Nutzer über die Knosole
    public static int readInt(String prompt) {
        try {
            return Integer.parseInt(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readInt("Error - do it again: ");
        }
    }

    // Erfragt einen Double Wert vom Nutzer über die Knosole
    public static double readDouble(String prompt) {
        try {
            return Double.parseDouble(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readDouble("Error - do it again: ");
        }
    }

    // Erfragt einen Long Wert vom Nutzer über die Knosole
    public static long readLong(String prompt) {
        try {
            return Long.parseLong(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readLong("Error - do it again: ");
        }
    }

    // Erfragt einen Float Wert vom Nutzer über die Knosole
    public static float readFloat(String prompt) {
        try {
            return Float.parseFloat(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readFloat("Error - do it again: ");
        }
    }

    // Erfragt einen Short Wert vom Nutzer über die Knosole
    public static short readShort(String prompt) {
        try {
            return Short.parseShort(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readShort("Error - do it again: ");
        }
    }

    // Erfragt einen Byte Wert vom Nutzer über die Knosole
    public static byte readByte(String prompt) {
        try {
            return Byte.parseByte(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readByte("Error - do it again: ");
        }
    }

    // Erfragt einen BigInteger Wert vom Nutzer über die Knosole
    public static BigInteger readBigInteger(String prompt) {
        try {
            return new BigInteger(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readBigInteger("Error - do it again: ");
        }
    }

    // Erfragt einen BigDecimal Wert vom Nutzer über die Knosole
    public static BigDecimal readBigDecimal(String prompt) {
        try {
            return new BigDecimal(promptAndRead(prompt).trim());
        } catch (NumberFormatException e) {
            return readBigDecimal("Error - do it again: ");
        }
    }

    // Ausgabe über die Konsole in einer Zeile ohne Umbruch
    public static void write(Object o) {
        System.out.print(o);
    }

    // Ausgabe über die Konsole in einer Zeile mit Umbruch
    public static void writeln(Object o) {
        System.out.println(o);
    }
}
