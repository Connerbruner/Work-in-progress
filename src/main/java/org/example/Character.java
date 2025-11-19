package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Character {
    private String name;
    private String path;
    private String expression;
    private Screen screen;
    private JLabel label;
    private int heightShort = 0;
    private boolean isFlipped = false;
    ArrayList<Card> hand = new ArrayList<>();
    //awakeness fitness sanity self-esteem Illness
    static final Character[] ALL_CHARACTERS = {
            PlayableCharacter.PLAYABLE_CHARACTERS[0],
            PlayableCharacter.PLAYABLE_CHARACTERS[1]

    };

    public Character(String n, String folder) {
        name = n;
        path = folder;
        screen = new Screen(200, 200);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();
    }

    public Character(String n, String folder, int h) {
        name = n;
        path = folder;
        screen = new Screen(200, 200);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();
        heightShort = h;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Screen getScreen() {
        return screen;
    }


    public ImageIcon getExpression(String expression) {
        return new ImageIcon(path + "/" + expression + ".png");
    }

    public boolean hasExpression(String expression) {
        return (new File(path + "/" + expression + ".png").exists());
    }

    public String getCurrentExpression() {
        return expression;
    }

    public void changeExpression(String expression) {
        this.expression = expression;
        screen.setBackground(getExpressionPreview(expression));
        if (isFlipped) {
            label.setIcon(Main.flipImage(Main.scaleImage(label.getWidth(), label.getHeight(), getExpression(expression))));
        } else {
            label.setIcon(Main.scaleImage(label.getWidth(), label.getHeight(), getExpression(expression)));

        }
        screen.setVisible(true);
    }

    public void setVisible(boolean visible) {
        screen.setVisible(visible);
        label.setVisible(visible);
    }

    public void setVisible(boolean s, boolean l) {
        screen.setVisible(s);
        label.setVisible(l);
    }

    public ImageIcon getExpressionPreview(String expression) {
        double zoomratio = 0.25;
        BufferedImage image = Main.toBufferedImage(getExpression(expression).getImage());
        int height = -1;
        for (int i = 0; i < image.getHeight() && height == -1; i++) {
            for (int j = 0; j < image.getWidth() && height == -1; j++) {
                if (new Color(image.getRGB(j, i), true).getAlpha() == 255) {
                    height = i - 10;
                }
            }
        }
        int width = (int) ((image.getHeight() - height) * zoomratio);
        int bestX = 0;
        int minTransparentPixels = Integer.MAX_VALUE;
        for (int x = 0; x <= image.getWidth() - width; x++) {
            int transparentCount = 0;

            for (int y = height; y < height + width && y < image.getHeight(); y++) {
                for (int dx = 0; dx < width; dx++) {
                    int pixelX = x + dx;
                    if (pixelX >= image.getWidth()) continue;

                    Color pixel = new Color(image.getRGB(pixelX, y), true);
                    if (pixel.getAlpha() == 0) {
                        transparentCount++;
                    }
                }
            }

            if (transparentCount <= minTransparentPixels) {
                minTransparentPixels = transparentCount;
                bestX = x;
            } else {
                System.out.println(transparentCount);
                System.out.println(minTransparentPixels);
                break;
            }
        }


        System.out.println("Detected head top at Y = " + height);
        System.out.println("Cropping at X = " + bestX + ", Y = " + height + ", size = " + width);


        return Main.cropImageIcon(getExpression(expression), bestX, height, width, width);
    }

    public int getHeightShort() {
        return heightShort;
    }

    public JLabel getLabel() {
        return label;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
}
