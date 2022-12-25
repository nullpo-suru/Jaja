package ru.dkx86.jaja;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Paths;

public final class Ded {
    private final int posX;
    private final int posY;

    private boolean isUnded;
    private boolean isHD;


    public Ded(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public Ded makeUnded() {
        isUnded = true;
        return this;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public BufferedImage getImage() {
        try {
            return ImageIO.read(Paths.get(getName()).toFile());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        }
    }

    private String getName() {
        if (isUnded) {
            return isHD ? "hd_ded.png" : "ded.png";
        }
        return "dedok.png";
    }


    public Ded makeHD() {
        isHD = true;
        return this;
    }
}
