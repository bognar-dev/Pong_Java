package src.games.basic;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import static java.lang.Thread.currentThread;

public class SoundEffect implements Runnable{

    File soundFile;

    public SoundEffect(String filePath) {
        soundFile = new File(filePath);
    }

    private void setFiles(String filePath) {
        // Open an audio input stream.
        soundFile = new File(filePath);
    }

    public void makeSound() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while (currentThread().isAlive()) {

        }
    }
}
