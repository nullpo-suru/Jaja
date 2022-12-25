package ru.dkx86.jaja;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {
        var canvas = new Canvas();
        Timer timer = new Timer(1000, e -> {
            canvas.tick();
            canvas.repaint();
        });
        timer.setInitialDelay(2000);
        initFrame(canvas);
        timer.start();
        playSound(Path.of("bells.wav").toFile());

    }

    public static synchronized void playSound(final File url) {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }


    private static void initFrame(JPanel panel) {
        JFrame frame = new JFrame();
        frame.setTitle("JaJaism");
        frame.setSize(1280, 720);
        frame.setResizable(false);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
