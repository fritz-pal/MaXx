package de.hhn.maXx;

import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.IntVector2;

/**
 * Statische Klasse zum Ausgeben des Spielbretts und Spielstands auf der Konsole.
 *
 * @author Henri Staudenrausch
 * @version 1, 19.12.22
 */
public class ConsoleGame {
    private ConsoleGame() {
    }

    public static void paint() {
        clearConsole();
        System.out.println("┌" + "─".repeat(74) + "┐");
        String emptyLine = "│" + " ".repeat(74) + "│";

        for (int y = 0; y < 8; y++) {
            StringBuilder line1 = new StringBuilder("│ ");
            StringBuilder line2 = new StringBuilder("│ ");
            StringBuilder line3 = new StringBuilder("│ ");
            for (int x = 0; x < 8; x++) {
                IntVector2 current = new IntVector2(x, y);
                switch (Game.getInstance().getBoard().getFieldState(current)) {
                    case FRACTION -> {
                        Fraction fraction = Game.getInstance().getBoard().getFraction(current);
                        int spacesNum = 3 - fraction.getNumerator().toString().length();
                        int spacesDen = 3 - fraction.getDenominator().toString().length();
                        line1.append("   ").append(" ".repeat(spacesNum)).append(fraction.getNumerator()).append("   ");
                        line2.append("  ─────  ");
                        line3.append("   ").append(" ".repeat(spacesDen)).append(fraction.getDenominator()).append("   ");
                    }
                    case EMPTY -> {
                        line1.append("         ");
                        line2.append("         ");
                        line3.append("         ");
                    }
                    case WHITE -> {
                        line1.append(" ┌─────┐ ");
                        line2.append(" │  W  │ ");
                        line3.append(" └─────┘ ");
                    }
                    case BLACK -> {
                        line1.append(" ┌─────┐ ");
                        line2.append(" │  B  │ ");
                        line3.append(" └─────┘ ");
                    }
                }
            }
            line1.append(" │");
            line2.append(" │");
            line3.append(" │");

            addScore(y, line1, line2, line3);

            System.out.println(emptyLine + "\n" + line1 + "\n" + line2 + "\n" + line3);
        }
        System.out.println(emptyLine);
        System.out.println("└" + "─".repeat(74) + "┘");
    }

    private static void addScore(int lineCount, StringBuilder line1, StringBuilder line2, StringBuilder line3) {
        Fraction weiss = Game.getInstance().getScoreW();
        Fraction schwarz = Game.getInstance().getScoreB();
        switch (lineCount) {
            case 1 -> {
                int lengthNum = weiss.getNumerator().toString().length();
                line1.append("           ").append(weiss.getNumerator());
                line2.append("     Weiß: ").append("─".repeat(lengthNum));
                line3.append("           ").append(weiss.getDenominator());
            }
            case 5 -> {
                int lengthNum = schwarz.getNumerator().toString().length();
                line1.append("           ").append(schwarz.getNumerator());
                line2.append("  Schwarz: ").append("─".repeat(lengthNum));
                line3.append("           ").append(schwarz.getDenominator());
            }
            case 6 ->
                    line2.append(" │").append("█".repeat(schwarz.intValue())).append(" ".repeat(Math.max(53 - schwarz.intValue(), 0))).append("│ ").append(schwarz.intValue());
            case 2 ->
                    line2.append(" │").append("█".repeat(weiss.intValue())).append(" ".repeat(Math.max(53 - weiss.intValue(), 0))).append("│ ").append(weiss.intValue());
        }
        switch (lineCount) {
            case 6, 2 -> {
                line1.append(" ┌").append("─".repeat(53)).append("┐");
                line3.append(" └").append("─".repeat(53)).append("┘");
            }
        }
    }

    public static void clearConsole() {
        System.out.println("\n".repeat(100));
    }
}
