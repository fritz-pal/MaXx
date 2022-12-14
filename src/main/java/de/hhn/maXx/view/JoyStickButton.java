package de.hhn.maXx.view;

import de.hhn.maXx.maXxUtils.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

public class JoyStickButton extends JButton {
    JLabel symbol;
    private MaXxWindow window;
    private Direction direction;
    private int x;
    private int y;
    private boolean hovering = false;

    public JoyStickButton(MaXxWindow window, Direction direction) {
        this.window = window;
        this.direction = direction;

        //button settings
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);
        this.setBackground(new Color(0x29, 0x2B, 0x2F));
        this.addMouseListener(mouseListener());

        //add symbol
        symbol = new JLabel();
        symbol.setBounds(0, 0, 40, 40);
        symbol.setForeground(new Color(0x96, 0x98, 0x9D));
        symbol.setFont(new Font("Jetbrains Mono", Font.PLAIN, 10));
        symbol.setVerticalAlignment(JLabel.CENTER);
        symbol.setHorizontalAlignment(JLabel.CENTER);
        this.add(symbol);

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
                symbol.setText("UP");
            }
            case DOWN -> {
                x -= 50;
                y += 50;
                symbol.setText("DOWN");
            }
            case LEFT -> {
                x -= 100;
                symbol.setText("LEFT");
            }
            case RIGHT -> symbol.setText("RIGHT");
            case DIAGONAL -> y -= 50;
        }
    }

    //called when the window is resized
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        directionSettings();
        Graphics2D g2d = (Graphics2D) g;
        if (hovering) {
            g2d.setColor(new Color(0x63, 0x70, 0xF4));
            g2d.fill(new RoundRectangle2D.Double(-1, -1, getWidth(), getHeight(), 25, 25));
        } else {
            g2d.setColor(new Color(0x36, 0x39, 0x3F));
            g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 50, 50));
        }
        this.setBounds(x, y, 40, 40);
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
            }
        };
    }
}
