package ru.dkx86.jaja;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Canvas extends JPanel {

    private Random random;
    private final List<Ded> deds = new ArrayList<>();
    private final char[] message = new char[]{'С', ' ', 'Н', 'о', 'в', 'ы', 'м', ' ', 'Г', '.', '.', '.'};
    private int messageTail = 0;
    private final Color[] bgColors;
    private boolean finished;

    public Canvas() {
        random = new Random(System.currentTimeMillis());
        bgColors = new Color[]{
                new Color(255, 0, 0),
                new Color(230, 40, 40),
                new Color(210, 0, 0),
                new Color(200, 0, 0),
                new Color(180, 0, 0),
                new Color(160, 0, 0),
                new Color(140, 0, 0),
                new Color(120, 0, 0),
                new Color(100, 0, 0),
                new Color(80, 0, 0),
                new Color(60, 0, 0),
                new Color(40, 0, 0),
                new Color(128, 128, 128)

        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Dimension dimension = getSize();

        setBackgroundColor(g2);
        g2.fillRect(0, 0, dimension.width, dimension.height);

        deds.forEach(ded -> g2.drawImage(ded.getImage(), ded.getPosX(), ded.getPosY(), null));

        g.setColor(getTextColor());
        g.setFont(new Font(null, Font.ITALIC, 72));
        g2.drawChars(message, 0, messageTail, 110, 110);

    }

    private Color getTextColor() {
        if (messageTail == message.length)
            return Color.RED;
        return Color.WHITE;
    }

    private void updateTail() {
        if (messageTail < message.length)
            messageTail++;
        else
            setFinishState();

    }

    private void setFinishState() {
        finished = true;
        deds.add(new Ded(200, 0).makeUnded().makeHD());
    }

    private void setBackgroundColor(Graphics2D g2) {
        g2.setColor(bgColors[messageTail]);
    }

    public void tick() {
        random = new Random(System.currentTimeMillis());
        if (finished) return;

        if (deds.size() >= message.length / 2) {
            deds.remove(0);
        }

        int posX = random.nextInt(1000);
        int posY = random.nextInt(100) + 100;
        Ded ded = new Ded(posX, posY);
        if (messageTail > message.length / 2)
            ded.makeUnded();
        deds.add(ded);

        updateTail();
    }
}
