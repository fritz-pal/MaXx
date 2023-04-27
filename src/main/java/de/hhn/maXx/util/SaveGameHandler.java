package de.hhn.maXx.util;

import de.hhn.maXx.game.Game;

import java.io.*;

/**
 * Die Klasse MaXxButton implementiert das speichern un auslesen von
 * Spielständen in Dateien
 *
 * @author Nico Vogel
 * @version 1, 27.04.23
 */

public class SaveGameHandler {
    // Methode zum Serialisieren eines Spielstandes in eine Datei
    public static void saveGame(File file, Game game) {
        try {
            File newFile = file;
            if (!file.getAbsolutePath().endsWith(".maxx")) {
                newFile = new File(file.getAbsolutePath() + ".maxx");
            }
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(newFile));
            stream.writeObject(game);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Derealisieren eines Spielstandes aus einer Datei
    public static boolean loadGame(File file) {
        try {
            if (!file.exists())
                return false;
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
            Game game = (Game) stream.readObject();
            stream.close();
            game.makeWindow();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
