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
            case "left" -> LEFT;
            case "right" -> RIGHT;
            case "up" -> UP;
            case "down" -> DOWN;
            case "diagonal" -> DIAGONAL;
            default -> null;
        };
    }
}
