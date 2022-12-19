package de.hhn.maXx;

import de.hhn.maXx.maXxUtils.MyIO;
import de.hhn.maXx.view.ConsoleGame;
import de.hhn.maXx.view.MaXxWindow;

public class Main {

    public static void main(String[] args) {
        String type = MyIO.promptAndRead("Wenn Sie in der Konsole spiele wollen tippen Sie \"console\". Wen Sie in einem GUI spielen wollen tippen Sie \"gui\"");

        if (type.equals("gui")) {
            MaXxWindow window = new MaXxWindow();
            window.setVisible(true);
        }else if (type.equals("console")){
            ConsoleGame consoleGame = new ConsoleGame();
        }
    }
}
