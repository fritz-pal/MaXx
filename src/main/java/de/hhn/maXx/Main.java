package de.hhn.maXx;

import de.hhn.maXx.frontend.StartScreen;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Schreiben Sie ein (textbasiertes) Anwendungsprogramm, welches es Benutzern erlaubt, das 2-Personen Spiel
 * „MaXx“ auf Spielbrettern der Größe 8 × 8 zu spielen. 62 der Spiel-Felder werden bei Spielbeginn mit zufällig
 * gewählten Brüchen belegt (Wert > 1, Zähler und Nenner gekürzt, jeweils mindestens zwei-, höchstens
 * dreistellig), die beiden Spielfiguren für Weiß (W) und Schwarz (B) stehen auf freien Feldern (siehe Bild).
 * Weiß und Schwarz ziehen abwechselnd ihre Spielfigur auf ein benachbartes Feld in einer der vier
 * Himmelsrichtungen (N, O, S, W), wobei die dort stehende Zahl entfernt und der Summe des aktuellen
 * Spielers hinzuaddiert wird. Diese wird als Bruch angezeigt. Weiß kann zudem nach oben rechts (NO) sowie
 * Schwarz zudem nach unten links (SW) ziehen. Gewonnen hat derjenige Spieler, der zuerst eine Summe > 53
 * erreicht hat. Man kann abwechselnd Eingaben für Spielzüge des W-Spielers und des B-Spielers machen oder
 * aber zur Steuerung des Programms, etwa zum Beenden.
 * – Überlegen Sie sich vor der Implementierung, welche Klassen, Interfaces, Objekte (für das Spielbrett, die
 * Dialogkomponente, etc.) benötigt werden und welche Methoden. (Denken Sie auch an mögliche spätere Erweiterungen des Spiels.)
 * – Überlegen Sie, wie ein Benutzer das Programm steuern soll und wie die Ausgabe gestaltet wird.
 *
 * @author Henri Staudenrausch, Lukas Vier, Nico Vogel, Nadine Schoch, Dennis Mayer
 * @version 1, 19.12.2022
 **/

public class Main {
    public static void main(String[] args) {
        new StartScreen();

//
//        GameStatus status = null;
//        boolean stop = false;
//        while (!stop) {
//            ConsoleGame.clearConsole();
//            if (status != null) {
//                switch (status) {
//                    case WHITE_WIN -> {
//                        ConsoleGame.paint();
//                        System.out.println("Weiß gewinnt!!");
//                        sound();
//                    }
//                    case BLACK_WIN -> {
//                        ConsoleGame.paint();
//                        System.out.println("Schwarz gewinnt!!");
//                        sound();
//                    }
//                    case INVALID -> System.out.println("Ungültige Eingabe!");
//                }
//            }
//            String input = MyIO.promptAndRead("Schreibe PLAY um zu Spielen oder STOP um das Programm zu Beenden: ");
//            switch (input.toLowerCase()) {
//                case "play" -> {
//                    Game.startNewInstance();
//                    do {
//                        status = Game.getInstance().continueGame();
//                    } while (status == GameStatus.CONTINUE);
//                }
//                case "stop" -> stop = true;
//                default -> status = GameStatus.INVALID;
//            }
//        }
    }

    private static void sound() {
        try {
            File f = new File("src/main/resources/tadaa.wav");
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f.toURI().toURL()));
            clip.start();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}