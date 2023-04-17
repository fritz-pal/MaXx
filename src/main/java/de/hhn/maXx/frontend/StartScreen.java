package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        super();
        this.setTitle("MaxGuI");
        this.setLayout(new GridLayout(2, 1));
        this.getContentPane().setBackground(new Color(0x312e2b));
        this.setSize(300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("MaXGuI");
        titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        this.getContentPane().add(titleLabel, 0);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0x514e4b));
        startButton.setFocusable(false);
        startButton.addActionListener(e -> new Game());
        this.getContentPane().add(startButton, 1);

        this.setVisible(true);
    }
}
