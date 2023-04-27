package de.hhn.maXx.util;

/**
 * Ein Enum, der die Namen der Audiodateien anzeigt.
 *
 * @author Henri Staudenrausch
 * @version 1, 27.04.23
 */
public enum SoundType {
    MOVE,
    CAPTURE,
    WIN,
    INVALID_MOVE;

    public String getFileName() {
        return switch (this) {
            case CAPTURE -> "capture.wav";
            case MOVE -> "move.wav";
            case WIN -> "win.wav";
            case INVALID_MOVE -> "invalidMove.wav";
        };
    }
}
