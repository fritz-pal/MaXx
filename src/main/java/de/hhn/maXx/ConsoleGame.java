package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class ConsoleGame {
    Board board;

    public ConsoleGame(Board board) {
        this.board = board;
        paint();
    }

    public void update(int x, int y, Fraction fraction, FieldState state) {
        //TODO
    }

    public void paint() {
        System.out.println("\n".repeat(100));
        System.out.println("┌" + "─".repeat(72) + "┐");
        String emptyLine = "│" + " ".repeat(72) + "│";

        for (int y = 0; y < 8; y++) {
            String line1 = "│";
            String line2 = "│";
            String line3 = "│";
            for (int x = 0; x < 8; x++) {
                switch (board.getFieldState(x, y)) {
                    case FRACTION -> {
                        Fraction fraction = board.getFraction(x, y);
                        int spacesNum = 3 - fraction.getNumerator().toString().length();
                        int spacesDen = 3 - fraction.getDenominator().toString().length();
                        line1 += "   " + " ".repeat(spacesNum) + fraction.getNumerator() + "   ";
                        line2 += "  ─────  ";
                        line3 += "   " + " ".repeat(spacesDen) + fraction.getDenominator() + "   ";
                    }
                    case EMPTY -> {
                        line1 += "         ";
                        line2 += "         ";
                        line3 += "         ";
                    }
                    case WHITE -> {
                        line1 += "         ";
                        line2 += "    W    ";
                        line3 += "         ";
                    }
                    case BLACK -> {
                        line1 += "         ";
                        line2 += "    B    ";
                        line3 += "         ";
                    }
                }
            }
            line1 += "│";
            line2 += "│";
            line3 += "│";

            System.out.println(emptyLine + "\n" + line1 + "\n" + line2 + "\n" + line3);
        }
        System.out.println(emptyLine);
        System.out.println("└" + "─".repeat(72) + "┘");
    }
}
