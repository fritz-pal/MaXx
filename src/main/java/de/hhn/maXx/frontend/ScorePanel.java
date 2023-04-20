package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private final JLabel numerator;
    private final JLabel denominator;
    private final boolean scoreOfWhite;
    Game game;
    public ScorePanel(Game game, boolean scoreOfWhite) {
        this.game = game;
        this.scoreOfWhite = scoreOfWhite;
        setBounds(850, 100, 200, 50);
        setLayout(new BorderLayout());
        setBackground(new Color(49, 49, 49));
        numerator = new JLabel();
        denominator = new JLabel();
        add(numerator, BorderLayout.PAGE_START);
        add(denominator, BorderLayout.PAGE_END);
        repaint();
    }

    public void updateScore() {
        numerator.setText(String.valueOf(
                (scoreOfWhite?game.getScoreW():game.getScoreB()).getNumerator()
        ));
        denominator.setText(String.valueOf(
                (scoreOfWhite?game.getScoreW():game.getScoreB()).getDenominator()
        ));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(28, 90, 90));
        for (int i = 0;
             i < 200 / 53. * (scoreOfWhite?game.getScoreW():game.getScoreB()).doubleValue();
             i++) {
            g.drawLine(i, 0, i, 49);
        }
        g.setColor(new Color(255, 255, 255));
        g.drawLine(0, 25, 199, 25);
    }
}
