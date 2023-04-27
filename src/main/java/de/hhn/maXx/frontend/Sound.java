package de.hhn.maXx.frontend;

import de.hhn.maXx.util.SoundType;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Die Klasse Sound liest die .wav Dateien aus dem
 * Ressourcen directory aus und spielt sie ab.
 *
 * @author Henri Staudenrausch 215994
 * @version 2, 27.04.23
 */
public class Sound {
    public static void play(SoundType soundType) {
        String fileName = soundType.getFileName();

        try {
            InputStream soundStream = Sound.class.getResourceAsStream("/" + fileName);
            InputStream bufferedIn = new BufferedInputStream(soundStream);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
