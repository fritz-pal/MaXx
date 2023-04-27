package de.hhn.maXx.util;

import de.hhn.maXx.frontend.*;
import de.hhn.maXx.game.*;

import java.io.*;

public class SaveGameHandler {
    public static boolean saveGame(File file, Game game) {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Game loadGame(File file) throws IOException, ClassNotFoundException {
        if (!file.exists())
            throw new IOException("File: " + file + " does not exist");
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
        Game game = (Game) stream.readObject();
        game.makeWindow();
        return game;
    }
}
