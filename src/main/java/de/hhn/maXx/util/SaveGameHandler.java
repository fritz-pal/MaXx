package de.hhn.maXx.util;

import de.hhn.maXx.frontend.*;
import de.hhn.maXx.game.*;

import java.io.*;

public class SaveGameHandler {
    public static void saveGame(String path, Game game) {
        File file = new File(path);
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Game loadGame(String path) {

    }
}
