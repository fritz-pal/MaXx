package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Field;
import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class MaXxButton extends JButton {
    public static final int BUTTON_SIZE = 800 / 8;
    private final int x;
    private final int y;
    private final JLabel nom;
    private final JLabel den;
    private final Game game;
    boolean isDark;
    private FieldState state = FieldState.FRACTION;

    public MaXxButton(Game game, JPanel panel, int x, int y) {
        this.game = game;
        this.x = x;
        this.y = y;
        isDark = (x + y) % 2 == 0;

        //button settings
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);
        this.addMouseListener(mouseListener());

        //nominator label
        nom = makeNumberLabel(false);
        this.add(nom);

        //denominator label
        den = makeNumberLabel(true);
        this.add(den);

        this.setBackground((isDark ? new Color(0x2F, 0x31, 0x36) : new Color(0x36, 0x39, 0x3F)));

        //add button to panel
        panel.add(this);
    }

    private void drawGradientCircle(Graphics2D g2d) {
        Color[] colors = {Color.RED, (isDark ? new Color(0x2F, 0x31, 0x36) : new Color(0x36, 0x39, 0x3F))};
        RadialGradientPaint rgp = new RadialGradientPaint(new Point(50, 50), (float) BUTTON_SIZE / 2, new float[]{0.0f, 0.8f}, colors);
        g2d.setPaint(rgp);
        g2d.fill(new Ellipse2D.Double(5, 5, BUTTON_SIZE - 10, BUTTON_SIZE - 10));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (state == FieldState.FRACTION) {
            //draw a horizontal line
            g.setColor(new Color(0x96, 0x98, 0x9D));
            ((Graphics2D) g).setStroke(new BasicStroke(3));
            g.drawLine(BUTTON_SIZE / 5, BUTTON_SIZE / 2, BUTTON_SIZE / 5 * 4, BUTTON_SIZE / 2);
            nom.setVisible(true);
            den.setVisible(true);
        } else {
            nom.setVisible(false);
            den.setVisible(false);
        }
        if (state == FieldState.WHITE) {
            if (game.isWhitesTurn()) {
                drawGradientCircle((Graphics2D) g);
            }
            g.setColor(Color.WHITE);
            g.fillOval(BUTTON_SIZE / 3, BUTTON_SIZE / 3, BUTTON_SIZE / 3, BUTTON_SIZE / 3);
        }
        if (state == FieldState.BLACK) {
            if (!game.isWhitesTurn()) {
                drawGradientCircle((Graphics2D) g);
            }
            g.setColor(Color.BLACK);
            g.fillOval(BUTTON_SIZE / 3, BUTTON_SIZE / 3, BUTTON_SIZE / 3, BUTTON_SIZE / 3);
        }
    }

    // creates a label with the given number formatted as a fraction
    private JLabel makeNumberLabel(boolean bottom) {
        JLabel num = new JLabel("");
        num.setBounds(0, bottom ? BUTTON_SIZE / 2 + 1 : 0, BUTTON_SIZE, BUTTON_SIZE / 2);
        num.setForeground(new Color(0x96, 0x98, 0x9D));
        num.setFont(new Font("Jetbrains Mono", Font.PLAIN, BUTTON_SIZE / 4));
        num.setVerticalAlignment(bottom ? JLabel.TOP : JLabel.BOTTOM);
        num.setHorizontalAlignment(JLabel.CENTER);
        return num;
    }

    public void update(Field field) {
        state = field.getState();
        if (state == FieldState.FRACTION) {
            Fraction f = field.getFraction();
            nom.setText(f.getNumerator() + "");
            den.setText(f.getDenominator() + "");
        }
        repaint();
    }

    private MouseListener mouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //TODO
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorderPainted(false);
            }
        };
    }
}


