package de.hhn.maXx.view;

import javax.swing.*;
import java.awt.*;

public class MaXxButton extends JButton {
    public static final int BUTTON_SIZE = 400/8;
    private final int x;
    private final int y;
    private MaXxWindow window;
    private int value = 0; //test

    public MaXxButton(MaXxWindow window, int x, int y) {
        this.window = window;
        this.x = x;
        this.y = y;

        this.setText("");
        this.setBounds(x * BUTTON_SIZE + window.getWidth() / 2 - BUTTON_SIZE * 4, y * BUTTON_SIZE + window.getHeight() / 2 - BUTTON_SIZE * 4, BUTTON_SIZE, BUTTON_SIZE);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);

        //nominator label
        this.add(makeNumberLabel("312", false));

        //denominator label
        this.add(makeNumberLabel("123", true));

        //line


        //chess pattern
        if ((x + y) % 2 == 0) {
            this.setBackground(new Color(0x2F, 0x31, 0x36));
        } else {
            this.setBackground(new Color(0x36, 0x39, 0x3F));
        }

        window.getContentPane().add(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBounds(x * BUTTON_SIZE + window.getWidth() / 2 - BUTTON_SIZE * 4, y * BUTTON_SIZE + window.getHeight() / 2 - BUTTON_SIZE * 4, BUTTON_SIZE, BUTTON_SIZE);

        //draw a horizontal line
        g.setColor(new Color(0x96, 0x98, 0x9D));
        g.drawLine(BUTTON_SIZE/5, BUTTON_SIZE/2, BUTTON_SIZE/5 * 4, BUTTON_SIZE/2);
    }

    private JLabel makeNumberLabel(String text, boolean bottom){
        JLabel num = new JLabel("321");
        num.setBounds(0, bottom ? BUTTON_SIZE / 2 + 1 : 0, BUTTON_SIZE, BUTTON_SIZE / 2);
        num.setForeground(new Color(0x96, 0x98, 0x9D));
        num.setFont(new Font("Jetbrains Mono", Font.PLAIN, BUTTON_SIZE/4));
        num.setVerticalAlignment(bottom ? JLabel.TOP : JLabel.BOTTOM);
        num.setHorizontalAlignment(JLabel.CENTER);
        return num;
    }
}

