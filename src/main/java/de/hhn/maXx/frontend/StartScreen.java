package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Die Klasse StartScreen beinhaltet die Definition des Startfenster,
 * von dem aus ein spiel gestartet werden oder ein vorhandenes Spiel geladen
 * werden kann.
 * 
 * @author Lukas Vier, Henri Staudenrausch, Dennis Mayer
 * @version 3, 27.04.23
 */

public class StartScreen extends JFrame {

    public StartScreen() {
        super();
        this.setTitle("MaxGuI");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x312e2b));
        this.setSize(316, 239);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // label Überschrift
        JLabel titleLabel = new JLabel("MaXGuI");
        titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 300, 100);
        this.getContentPane().add(titleLabel, 0);

        // neues Spiel Button
        JButton startButton = new JButton("Start");
        startButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0x514e4b));
        startButton.setFocusable(false);
        startButton.addActionListener(e -> new Game());
        startButton.setBounds(25, 100, 100, 50);
        this.getContentPane().add(startButton, 1);

        // fenster schließen Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0x514e4b));
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBounds(175, 100, 100, 50);
        this.getContentPane().add(exitButton, 2);

        this.setVisible(true);
    }
}
