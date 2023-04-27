package de.hhn.maXx.util;

import java.io.Serializable;

/**
 * Eine Klasse die einen durch Ganzzahlen definierten Punkt repräsentiert.
 *
 * @author Nico Vogel
 * @version 1, 20.12.2022
 */

public class IntVector2 implements Serializable {
    public final int x;
    public final int y;

    public IntVector2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Vektoren addieren
    public IntVector2 add(IntVector2 other) {
        return new IntVector2(this.x + other.x, this.y + other.y);
    }

    // Vektoren subtrahieren
    public IntVector2 subtract(IntVector2 other) {
        return new IntVector2(this.x - other.x, this.y - other.y);
    }

    // Vektoren auf Gleichheit überprüfen
    public boolean equals(IntVector2 vector2) {
        return vector2.x == this.x && vector2.y == this.y;
    }

    // objekte auf Gleichheit überprüfen
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IntVector2 vector2) {
            return equals(vector2);
        }
        return super.equals(obj);
    }
}
