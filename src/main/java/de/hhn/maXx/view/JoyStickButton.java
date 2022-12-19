package de.hhn.maXx.view;

import de.hhn.maXx.maXxUtils.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class JoyStickButton extends JButton {
    private final MaXxWindow window;
    private final Direction direction;
    Image image;
    private int x;
    private int y;
    private boolean hovering = false;
    private boolean pressed = false;

    public JoyStickButton(MaXxWindow window, Direction direction) {
        this.window = window;
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
        this.directionSettings();
        this.setBounds(x, y, 40, 40);
        window.getContentPane().add(this);
    }


    private void directionSettings() {
        this.y = window.getHeight() / 2;
        this.x = window.getWidth() / 6;
        switch (direction) {
            case UP -> {
                x -= 50;
                y -= 50;
            }
            case DOWN -> {
                x -= 50;
                y += 50;
            }
            case LEFT -> x -= 100;
            case DIAGONAL -> x -= 50;
        }
    }

    //called when the window is resized
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        directionSettings();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(114, 137, 218));
        if (hovering) {
            g2d.fill(new RoundRectangle2D.Double(-1, -1, getWidth(), getHeight(), 25, 25));
        } else {
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 50, 50));
        }
        g2d.drawImage(image, 0, 0, 40, 40, null);
        this.setBounds(x, y + (pressed ? 2 : 0), 40, 40);
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
