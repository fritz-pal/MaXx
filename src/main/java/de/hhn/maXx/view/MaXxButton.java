package de.hhn.maXx.view;

import de.hhn.maXx.maXxUtils.FieldState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MaXxButton extends JButton {
    public static final int BUTTON_SIZE = 400 / 8;
    private final int x;
    private final int y;
    boolean hovering = false;
    private MaXxWindow window;
    private JLabel nom;
    private JLabel den;
    private FieldState state = FieldState.FRACTION;

    public MaXxButton(MaXxWindow window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;

        //button settings
        this.setBounds(x * BUTTON_SIZE + window.getWidth() / 2 - BUTTON_SIZE * 4, y * BUTTON_SIZE + window.getHeight() / 2 - BUTTON_SIZE * 4, BUTTON_SIZE, BUTTON_SIZE);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);
        this.addMouseListener(mouseListener());

        //nominator label
        nom = makeNumberLabel("321", false);
        this.add(nom);

        //denominator label
        den = makeNumberLabel("123", true);
        this.add(den);

        //chess pattern
        if ((x + y) % 2 == 0) {
            this.setBackground(new Color(0x2F, 0x31, 0x36));
        } else {
            this.setBackground(new Color(0x36, 0x39, 0x3F));
        }

        //add button to window
        window.getContentPane().add(this);
    }


    //called when the window is resized
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBounds(x * BUTTON_SIZE + window.getWidth() / 2 - BUTTON_SIZE * 4, y * BUTTON_SIZE + window.getHeight() / 2 - BUTTON_SIZE * 4, BUTTON_SIZE, BUTTON_SIZE);

        g.setColor(new Color(0x96, 0x98, 0x9D));
        if (state == FieldState.FRACTION) {
            //draw a horizontal line
            g.drawLine(BUTTON_SIZE / 5, BUTTON_SIZE / 2, BUTTON_SIZE / 5 * 4, BUTTON_SIZE / 2);
            nom.setVisible(true);
            den.setVisible(true);
        } else {
            nom.setVisible(false);
            den.setVisible(false);
        }
    }

    // creates a label with the given number formatted as a fraction
    private JLabel makeNumberLabel(String text, boolean bottom) {
        JLabel num = new JLabel("321");
        num.setBounds(0, bottom ? BUTTON_SIZE / 2 + 1 : 0, BUTTON_SIZE, BUTTON_SIZE / 2);
        num.setForeground(new Color(0x96, 0x98, 0x9D));
        num.setFont(new Font("Jetbrains Mono", Font.PLAIN, BUTTON_SIZE / 4));
        num.setVerticalAlignment(bottom ? JLabel.TOP : JLabel.BOTTOM);
        num.setHorizontalAlignment(JLabel.CENTER);
        return num;
    }

    public void update() {
        //TODO update denominator and nominator labels
        repaint();
    }

    private MouseListener mouseListener() {
        return new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //TODO
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //TODO
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


