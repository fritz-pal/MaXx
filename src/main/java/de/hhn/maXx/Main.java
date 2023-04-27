package de.hhn.maXx;

import de.hhn.maXx.frontend.StartScreen;

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
 * @author Henri Staudenrausch 215994, Lukas Vier 215997, Nico Vogel 215998, Nadine Schoch, Dennis Mayer 215964
 * @version 3, 27.04.2023
 **/

public class Main {
    public static void main(String[] args) {
        new StartScreen();
    }
}