package de.hhn.maXx.util;

import de.hhn.maXx.game.Game;

import java.io.*;

public class SaveGameHandler {
    public static void saveGame(File file, Game game) {
        try {
            File newFile = file;
            if (!file.getAbsolutePath().endsWith(".maxx")) {
                newFile = new File(file.getAbsolutePath() + ".maxx");
            }
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(newFile));
            stream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean loadGame(File file) {
        try {
            if (!file.exists()) return false;
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            Game game = (Game) stream.readObject();
            game.makeWindow();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
