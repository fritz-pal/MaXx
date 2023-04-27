package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.SaveGameHandler;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        super();
        this.setTitle("MaxGuI");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x312e2b));
        this.setSize(416, 239);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("MaXGuI");
        titleLabel.setFont(new Font("Arial Black", Font.PLAIN, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(0, 0, 400, 100);
        this.add(titleLabel);

        JButton startButton = new JButton("New Game");
        startButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(0x514e4b));
        startButton.setFocusable(false);
        startButton.addActionListener(e -> new Game());
        startButton.setBounds(10, 100, 120, 50);
        this.add(startButton);

        // Erstellen des Load Game Buttons

        JButton loadButton = new JButton("Load Game");
        loadButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        loadButton.setForeground(Color.WHITE);
        loadButton.setBackground(new Color(0x514e4b));
        loadButton.setFocusable(false);
        loadButton.addActionListener(e -> fileChooser());
        loadButton.setBounds(140, 100, 120, 50);
        this.add(loadButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0x514e4b));
        exitButton.setFocusable(false);
        exitButton.addActionListener(e -> System.exit(0));
        exitButton.setBounds(270, 100, 120, 50);
        this.add(exitButton);

        this.setVisible(true);
    }

    private void fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a save file");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Serialized", "ser"));
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            SaveGameHandler.loadGame(fileChooser.getSelectedFile());
        }
    }
}
