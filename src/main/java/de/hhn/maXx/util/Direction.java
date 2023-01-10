package de.hhn.maXx.util;

/**
 * Ein Enum, der die Richtung einer Bewegung anzeigt.
 *
 * @author Henri Staudenrausch
 * @version 1, 19.12.22
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    DIAGONAL;

    public static Direction fromString(String s) {
        return switch (s.toLowerCase()) {
            case "left", "w" -> LEFT;
            case "right", "e", "o" -> RIGHT;
            case "up", "n" -> UP;
            case "down", "s" -> DOWN;
            case "diagonal", "d" -> DIAGONAL;
            default -> null;
        };
    }
}
