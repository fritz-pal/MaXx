package de.hhn.maXx.view;

import de.hhn.maXx.maXxUtils.Direction;

import javax.swing.*;
import java.awt.*;

public class MaXxWindow extends JFrame {
    JLabel title;
    MaXxButton[][] field = new MaXxButton[8][8];

    public MaXxWindow() {
        //window settings
        setTitle("MaXx");
        setSize(1000, 800);
        getRootPane().setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(0x20, 0x22, 0x25)));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(true);
        setIconImage(new ImageIcon("src/main/resources/icon.png").getImage());
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x29, 0x2B, 0x2F));

        //field buttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = new MaXxButton(this, i, j);
            }
        }

        //joystick buttons
        JoyStickButton left = new JoyStickButton(this, Direction.LEFT);
        JoyStickButton right = new JoyStickButton(this, Direction.RIGHT);
        JoyStickButton down = new JoyStickButton(this, Direction.DOWN);
        JoyStickButton up = new JoyStickButton(this, Direction.UP);
        JoyStickButton diagonal = new JoyStickButton(this, Direction.DIAGONAL);
    }

    //called when the window is resized
    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public void update() {
        //TODO
    }
}
