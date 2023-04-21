package de.hhn.maXx.util;

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
