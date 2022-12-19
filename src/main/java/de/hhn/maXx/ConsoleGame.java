package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class ConsoleGame {
    Field[][] grid = new Field[8][8];

    public void update(int x, int y, Fraction fraction, FieldState state) {
        //TODO
    }

    public void paint() {
        System.out.println("┌" + "─".repeat(40) + "┐");

        for (int y = 0; y < 8; y++) {
            String line1 = "│";
            String line2 = "│";
            String line3 = "│";
            String emptyLine = "│" + "─".repeat(40) + "│";
            for (int x = 0; x < 8; x++) {
                Field field = grid[x][y];
                if (field.getState().equals(FieldState.FRACTION)) {
                    line1 += " " + field.getFraction().getNumerator() + " ";
                    line2 += " ─── ";
                    line3 += " " + field.getFraction().getNumerator() + " ";
                }
            }
            line1 += "│";
            line2 += "│";
            line3 += "│";

            System.out.println(emptyLine + line1 + "\n" + line2 + "\n" + line3 + emptyLine);
        }

        System.out.println("└" + "─".repeat(40) + "┘");
    }
}
