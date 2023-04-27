package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse ScorePannel zeigt die aktuellen Scores der Spieler,
 * sowohl als Bruch, als auch als "pogress bar" auf dem MaXx Window aus.
 * 
 * @author Nico Vogel
 * @version 2, 27.04.23
 */
public class ScorePanel extends JPanel {
    private final boolean scoreOfWhite;
    Game game;

    public ScorePanel(Game game, boolean scoreOfWhite) {
        this.game = game;
        this.scoreOfWhite = scoreOfWhite;
        this.setBounds(800, 300 + (scoreOfWhite ? 0 : 75), 301, 50);
        repaint();
    }

    public void updateScore() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(36, 128, 70));
        for (int i = 0; i < Math.max(
                (getWidth() - 1) / 53. * (scoreOfWhite ? game.getScoreW() : game.getScoreB()).doubleValue(),
                getWidth()); i++) {
            g.drawLine(i, 0, i, 49);
        }
        g.setColor(new Color(30, 31, 34));
        for (int i = (int) ((getWidth() - 1) / 53.
                * (scoreOfWhite ? game.getScoreW() : game.getScoreB()).doubleValue()); i < (getWidth() - 1); i++) {
            g.drawLine(i, 0, i, 49);
        }
        ((Graphics2D) g).setStroke(new BasicStroke(2));
        g.setColor(Color.WHITE);
        g.drawLine(0, 25, (getWidth() - 1), 25);
        g.setFont(new Font("Jetbrains Mono", Font.BOLD, 15));
        g.drawString(String.valueOf(scoreOfWhite ? game.getScoreW().getNumerator() : game.getScoreB().getNumerator()),
                2, 18);
        g.drawString(
                String.valueOf(scoreOfWhite ? game.getScoreW().getDenominator() : game.getScoreB().getDenominator()), 2,
                42);
        g.dispose();
    }
}
