package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class JoyStickButton extends JButton {
    private final JPanel panel;
    private final Direction direction;
    Image image;
    Game game;
    private boolean hovering = false;
    private boolean pressed = false;

    public JoyStickButton(Direction direction, JPanel panel, Game game) {
        this.game = game;
        this.panel = panel;
        this.direction = direction;

        //button settings
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBackground(null);
        this.addMouseListener(mouseListener());

        image = new ImageIcon("src/main/resources/" + direction.toString().toLowerCase() + ".png").getImage();
        panel.add(this);
    }

    //called when the window is resized
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(pressed) g2d.setColor(new Color(114, 137, 218, 100));
        else g2d.setColor(new Color(114, 137, 218, 200));
        if (hovering) {
            g2d.fill(new RoundRectangle2D.Double(-1, -1, getWidth(), getHeight(), 50, 50));
        } else {
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 100, 100));
        }
        if(direction != Direction.DIAGONAL) g2d.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        else{
            g2d.drawImage(new ImageIcon("src/main/resources/diagonal_" + (game.isWhitesTurn() ? "white" : "black") + ".png").getImage(), 0, 0, getWidth(), getHeight(), null);

        }
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                game.move(direction);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                pressed = false;
            }
        };
    }
}
