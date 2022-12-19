package de.hhn.maXx;

import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

public class ConsoleGame {
    Board board;
    public ConsoleGame(Board board){
        this.board = board;
    }

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
                if (board.getFieldState(x, y).equals(FieldState.FRACTION)) {
                    Fraction fraction =  board.getFraction(x, y);
                    int spacesNum = 3 - fraction.getNumerator().toString().length();
                    int spacesDen = 3 - fraction.getDenominator().toString().length();
                    line1 += " " + fraction.getNumerator() + " ";
                    line2 += " ─── ";
                    line3 += " " + fraction.getNumerator() + " ";
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
