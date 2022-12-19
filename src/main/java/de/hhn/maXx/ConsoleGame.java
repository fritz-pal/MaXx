package de.hhn.maXx;

import de.hhn.maXx.util.Fraction;

public class ConsoleGame {
    private ConsoleGame() {
    }

    public static void paint() {
        clearConsole();
        System.out.println("┌" + "─".repeat(72) + "┐");
        String emptyLine = "│" + " ".repeat(72) + "│";


        for (int y = 0; y < 8; y++) {
            String line1 = "│";
            String line2 = "│";
            String line3 = "│";
            for (int x = 0; x < 8; x++) {
                switch (Game.getInstance().getBoard().getFieldState(x, y)) {
                    case FRACTION -> {
                        Fraction fraction = Game.getInstance().getBoard().getFraction(x, y);
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

            Fraction weiss = Game.getInstance().getScoreW();
            Fraction schwarz = Game.getInstance().getScoreB();
            switch (y) {
                case 1 -> {
                    int lengthNum = weiss.getNumerator().toString().length();
                    line1 += "           " + weiss.getNumerator();
                    line2 += "     Weiß: " + "─".repeat(lengthNum) + "    " + weiss.doubleValue();
                    line3 += "           " + weiss.getDenominator();
                }
                case 5 -> {
                    int lengthNum = schwarz.getNumerator().toString().length();
                    line1 += "           " + schwarz.getNumerator();
                    line2 += "  Schwarz: " + "─".repeat(lengthNum) + "    " + schwarz.doubleValue();
                    line3 += "           " + schwarz.getDenominator();
                }
                case 6 -> {
                    line1 += " ┌" + "─".repeat(53) + "┐";
                    line2 += " │" + "█".repeat(schwarz.intValue()) + " ".repeat(53 - schwarz.intValue()) + "│";
                    line3 += " └" + "─".repeat(53) + "┘";
                }
                case 2 -> {
                    line1 += " ┌" + "─".repeat(53) + "┐";
                    line2 += " │" + "█".repeat(weiss.intValue()) + " ".repeat(53 - weiss.intValue()) + "│";
                    line3 += " └" + "─".repeat(53) + "┘";

                }
            }

            System.out.println(emptyLine + "\n" + line1 + "\n" + line2 + "\n" + line3);
        }
        System.out.println(emptyLine);
        System.out.println("└" + "─".repeat(72) + "┘");
    }

    public static void clearConsole() {
        System.out.println("\n".repeat(100));
    }
}
