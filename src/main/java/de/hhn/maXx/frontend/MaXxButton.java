package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Field;
import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.FieldState;
import de.hhn.maXx.util.Fraction;
import de.hhn.maXx.util.IntVector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

/**
 * Die Klasse MaXxButton definiert die Grafik und die Interaktionen
 * der Nutzer mit den Feldern des Spielbretts.
 *
 * @author Henri Staudenrausch
 * @version 3, 27.04.23
 */

public class MaXxButton extends JButton {
    public static final int BUTTON_SIZE = 800 / 8;
    private final IntVector2 pos;
    private final JLabel nom;
    private final JLabel den;
    private final Game game;
    boolean isDark;
    private boolean hovering = false;
    private FieldState state = FieldState.FRACTION;

    public MaXxButton(Game game, JPanel panel, IntVector2 pos) {
        this.game = game;
        this.pos = pos;
        isDark = (pos.x + pos.y) % 2 == 0;

        // Generelle Einstellungen
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setLayout(null);
        this.addMouseListener(mouseListener());

        // Label für Zähler
        nom = makeNumberLabel(false);
        this.add(nom);

        // Label für Nenner
        den = makeNumberLabel(true);
        this.add(den);

        this.setBackground((isDark ? new Color(0x2F, 0x31, 0x36) : new Color(0x36, 0x39, 0x3F)));

        // Hinzufügen des Buttons zum Label
        panel.add(this);
    }

    // Hebt den aktiven Spieler grafisch hervor
    private void drawGradientCircle(Graphics2D g2d) {
        Color[] colors = { Color.RED, (isDark ? new Color(0x2F, 0x31, 0x36) : new Color(0x36, 0x39, 0x3F)) };
        RadialGradientPaint rgp = new RadialGradientPaint(new Point(50, 50), (float) BUTTON_SIZE / 2,
                new float[] { 0.0f, 0.8f }, colors);
        g2d.setPaint(rgp);
        g2d.fill(new Ellipse2D.Double(5, 5, BUTTON_SIZE - 10, BUTTON_SIZE - 10));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Zeichnen der Fractions in die entsprechenden Felder
        if (state == FieldState.FRACTION) {
            // Zeichnen einer Horizontalen Linie
            g.setColor(new Color(0x96, 0x98, 0x9D));
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            g.drawLine(BUTTON_SIZE / 5, BUTTON_SIZE / 2, BUTTON_SIZE / 5 * 4, BUTTON_SIZE / 2);
            nom.setVisible(true);
            den.setVisible(true);
        } else {
            nom.setVisible(false);
            den.setVisible(false);
        }
        // Zeichnen der Spieler / Spielsteine auf die entsprechenden Felder
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
        g.dispose();
    }

    // Macht ein Label in der der Bruch dargestellt wird
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

    // Maus Adapter wür Interaktion mit dem Knopf
    private MouseAdapter mouseListener() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (hovering)
                    game.buttonClicked(pos);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hovering = true;
                setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovering = false;
                setBorderPainted(false);
            }
        };
    }
}