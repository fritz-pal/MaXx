package de.hhn.maXx;

import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.MyIO;

public class InputManager {
    private InputManager() {}

    public static Direction getInput(String message) {
        String direction = MyIO.promptAndRead(message);
        while (!isViableDirection(direction)) {
            MyIO.promptAndRead("Try again: ");
        }
        return switch (direction.toLowerCase()) {
            case "n" -> Direction.UP;
            case "e" -> Direction.RIGHT;
            case "s" -> Direction.DOWN;
            case "w" -> Direction.LEFT;
            case "d" -> Direction.DIAGONAL;
        };
    }

    private static boolean isViableDirection(String str) {
        return switch (str.toLowerCase()) {
            case "s", "n", "e", "w", "d" -> true;
            default -> false;
        };
    }
}
