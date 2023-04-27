package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Field;
import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.IntVector2;
import de.hhn.maXx.util.SaveGameHandler;
import de.hhn.maXx.util.SoundType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Die Klasse MaXxWindow stellt ein tatsächles Spielfenster.
 * Es stellt die Parameter des Spieles grafisch dar
 * und bietet die Inputmöglichkeiten für die Spieler an.
 *
 * @author Henri Staudenrausch, Nico Vogel, Lukas Vier
 * @version 3, 27.04.23
 */
public class MaXxWindow extends JFrame {
    private final MaXxButton[][] field = new MaXxButton[8][8];
    private final JoyStickButton diagonalButton;
    private final ScorePanel whiteScore;
    private final ScorePanel blackScore;
    private final Game game;
    JLabel whiteLabel = new JLabel("White - 0");
    JLabel blackLabel = new JLabel("Black - 0");

    public MaXxWindow(Game game) {
        this.game = game;
        // Parameter Fenster
        this.setTitle("MaXGuI");
        this.setSize(1116, 839);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0x29, 0x2B, 0x2F));
        this.getContentPane().addKeyListener(keyListener());
        this.getContentPane().setFocusable(true);

        //save button
        JButton saveButton = new JButton("Save Game");
        saveButton.setFont(new Font("Arial Black", Font.PLAIN, 14));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(0x36, 0x39, 0x3F));
        saveButton.setFocusable(false);
        saveButton.addActionListener(e -> fileSaver());
        saveButton.setBounds(930, 20, 150, 50);
        this.add(saveButton);

        //field panel
        JPanel fieldPanel = new JPanel();
        fieldPanel.setBounds(0, 0, 800, 800);
        fieldPanel.setLayout(new GridLayout(8, 8));
        fieldPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        this.add(fieldPanel);

        // Label Überschrift
        JLabel titleLabel = new JLabel("MaXGuI");
        titleLabel.setFont(new Font("Arial Black", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBounds(850, 100, 200, 50);
        this.getContentPane().add(titleLabel, 0);

        // Label Tastatur-Input Erklärung
        JLabel infoLabel = new JLabel("WASD/Arrow keys/Space to move!");
        infoLabel.setFont(new Font("Arial Black", Font.BOLD, 12));
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setBounds(800, 720, 300, 50);
        this.getContentPane().add(infoLabel, 0);

        // Hinzufügen und Positionierung der Progress bars
        whiteLabel.setBounds(850, 265, 200, 50);
        whiteLabel.setForeground(new Color(0x96, 0x98, 0x9D));
        whiteLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        this.add(whiteLabel);
        blackLabel.setBounds(850, 340, 200, 50);
        blackLabel.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        blackLabel.setForeground(new Color(0x96, 0x98, 0x9D));
        this.add(blackLabel);
        whiteScore = new ScorePanel(game, true);
        blackScore = new ScorePanel(game, false);
        this.add(whiteScore);
        this.add(blackScore);

        // Hinzufügen des Panel mit grafischem Steuerkreuz
        JPanel joystickPanel = new JPanel();
        joystickPanel.setBounds(850, 500, 200, 200);
        joystickPanel.setLayout(new GridLayout(3, 3));
        joystickPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        this.add(joystickPanel);

        // Hinzufügen der Buttons in das JoystickPanel
        joystickPanel.add(getEmptyPanel(), 0);
        new JoyStickButton(Direction.UP, joystickPanel, game);
        joystickPanel.add(getEmptyPanel());
        new JoyStickButton(Direction.LEFT, joystickPanel, game);
        diagonalButton = new JoyStickButton(Direction.DIAGONAL, joystickPanel, game);
        new JoyStickButton(Direction.RIGHT, joystickPanel, game);
        joystickPanel.add(getEmptyPanel());
        new JoyStickButton(Direction.DOWN, joystickPanel, game);

        // Hinzufügen der Felder in das Grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[j][i] = new MaXxButton(game, fieldPanel, new IntVector2(j, i));
            }
        }
        this.setVisible(true);
    }

    // Überprüfen auf Keyboard inputs zum bewegen
    private KeyAdapter keyListener() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case 37, 65 -> game.move(Direction.LEFT);
                    case 38, 87 -> game.move(Direction.UP);
                    case 39, 68 -> game.move(Direction.RIGHT);
                    case 40, 83 -> game.move(Direction.DOWN);
                    case 32, 10 -> game.move(Direction.DIAGONAL);
                }
            }
        };
    }

    // Anzeigen des "Sieg-Bildschirms"
    public void displayWin(boolean player) {
        Sound.play(SoundType.WIN);
        JOptionPane.showMessageDialog(this, player ? "White won!" : "Black won!");
        this.dispose();
    }

    // neuzeichnen des Fensters mit aktuellen Werten
    public void update() {
        Field[][] grid = game.getBoard().getGrid();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j].update(grid[i][j]);
            }
        }
        whiteScore.updateScore();
        blackScore.updateScore();
        diagonalButton.repaint();

        whiteLabel.setText("White - " + game.getScoreW().intValue());
        blackLabel.setText("Black - " + game.getScoreB().intValue());
    }

    // Personalisiertes Panel mit eigener Farbgebung
    private JPanel getEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        return emptyPanel;
    }

    private void fileSaver() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save your game");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("MaXx Save Game", "maxx"));
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            SaveGameHandler.saveGame(fileChooser.getSelectedFile(), game);
        }
    }
}
