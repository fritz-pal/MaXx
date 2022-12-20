package de.hhn.maXx.util;

/**
 * Eine Klasse die einen durch Ganzzahlen definierten Punkt repräsentiert.
 *
 * @author Nico Vogel
 * @version 1, 20.12.2022
 */

public class IntVector2 {
    private final int x;
    private final int y;

    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public IntVector2 add(IntVector2 other) {
        return new IntVector2(this.x + other.x, this.y + other.y);
    }

    public boolean equals(IntVector2 vector2) {
        return vector2.x == this.x && vector2.y == this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntVector2 vector2) {
            return equals(vector2);
        }
        return super.equals(obj);
    }
}
