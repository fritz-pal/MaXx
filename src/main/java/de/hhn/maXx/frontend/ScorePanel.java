package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    private final boolean scoreOfWhite;
    Game game;
    public ScorePanel(Game game, boolean scoreOfWhite) {
        this.game = game;
        this.scoreOfWhite = scoreOfWhite;
        setBounds(800, 100 + (scoreOfWhite?0:75), 301, 50);
        repaint();
    }

    public void updateScore() {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(new Color(28, 90, 90));
        for (int i = 0;
             i < Math.max((getWidth()-1) / 53. * (scoreOfWhite?game.getScoreW():game.getScoreB()).doubleValue(), getWidth());
             i++) {
            g.drawLine(i, 0, i, 49);
        }
        g.setColor(new Color(58, 58, 58));
        for (int i = (int)((getWidth()-1) / 53. * (scoreOfWhite?game.getScoreW():game.getScoreB()).doubleValue());
             i < (getWidth()-1);
             i++) {
            g.drawLine(i, 0, i, 49);
        }
        g.setColor(new Color(255, 255, 255));
        g.drawLine(0, 25, (getWidth()-1), 25);
        g.drawString(String.valueOf(scoreOfWhite?game.getScoreW().getNumerator():game.getScoreB().getNumerator()),0, 24);
        g.drawString(String.valueOf(scoreOfWhite?game.getScoreW().getDenominator():game.getScoreB().getDenominator()),0, 36);
    }
}
