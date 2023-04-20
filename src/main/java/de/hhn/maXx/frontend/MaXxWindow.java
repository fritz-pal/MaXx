package de.hhn.maXx.frontend;

import de.hhn.maXx.game.Board;
import de.hhn.maXx.game.Field;
import de.hhn.maXx.game.Game;
import de.hhn.maXx.util.Direction;
import de.hhn.maXx.util.IntVector2;

import javax.swing.*;
import java.awt.*;

public class MaXxWindow extends JFrame {
    private final MaXxButton[][] field = new MaXxButton[8][8];
    private final JPanel fieldPanel = new JPanel();
    private final JoyStickButton diagonalButton;
    private final JPanel joystickPanel = new JPanel();
    private final ScorePanel whiteScore;
    private final ScorePanel blackScore;
    private final Game game;

    public MaXxWindow(Game game) {
        this.game = game;
        //window settings
        this.setTitle("MaXGuI");
        this.setSize(1116, 839);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(0x29, 0x2B, 0x2F));

        //field panel
        fieldPanel.setBounds(0, 0, 800, 800);
        fieldPanel.setLayout(new GridLayout(8, 8));
        fieldPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        this.add(fieldPanel);

        //progress bars
        whiteScore = new ScorePanel(game, true);
        blackScore = new ScorePanel(game, false);
        this.add(whiteScore);
        this.add(blackScore);

        //joystick panel
        joystickPanel.setBounds(850, 500, 200, 200);
        joystickPanel.setLayout(new GridLayout(3, 3));
        joystickPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        this.add(joystickPanel);

        //joystick buttons
        joystickPanel.add(getEmptyPanel(), 0);
        new JoyStickButton(Direction.UP, joystickPanel, game);
        joystickPanel.add(getEmptyPanel());
        new JoyStickButton(Direction.LEFT, joystickPanel, game);
        diagonalButton = new JoyStickButton(Direction.DIAGONAL, joystickPanel, game);
        new JoyStickButton(Direction.RIGHT, joystickPanel, game);
        joystickPanel.add(getEmptyPanel());
        new JoyStickButton(Direction.DOWN, joystickPanel, game);

        //field buttons
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[j][i] = new MaXxButton(game, fieldPanel, new IntVector2(j, i));
            }
        }
        this.setVisible(true);
        this.displayWin(true);
    }

    public void displayWin (boolean player){
        fieldPanel.setEnabled(false);
        JLabel notification = new JLabel();


        if(player == true){
            //White
            JInternalFrame whiteWin = new JInternalFrame("Winner");
            whiteWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            notification.setText("White player won the game!");
            whiteWin.add(notification);
            whiteWin.setBounds(500,500,150, 150);
            this.add(whiteWin);
            whiteWin.setVisible(true);

        } else {
            //Black
            JInternalFrame blackWin = new JInternalFrame("Winner");
            blackWin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            notification.setText("Black player won the game!");
            blackWin.add(notification);
            blackWin.setVisible(true);
            this.add(blackWin);


        }

        // TODO

    }

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
    }

    private JPanel getEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(new Color(0x29, 0x2B, 0x2F));
        return emptyPanel;
    }
}
