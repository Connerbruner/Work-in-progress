package org.example;

import org.example.Cards.Card;
import org.example.Display.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class GameCharacter {
    private String name;
    private String path;
    private String expression;
    private Screen screen;
    private JLabel label;
    private int heightShort = 0;
    private boolean isFlipped = false;
    private double[] cardWeights;
    ArrayList<Card> hand = new ArrayList<>();
    //awakeness fitness sanity self-esteem Illness
    static final GameCharacter[] ALL_GAME_CHARACTERS = {
            PlayableCharacter.PLAYABLE_GAME_CHARACTERS[0],
            PlayableCharacter.PLAYABLE_GAME_CHARACTERS[1]

    };
    public void addCard(Card c) {
        hand.add(c);
    }
    public GameCharacter(String n, String folder,double[] aiWeights) {
        cardWeights = aiWeights;
        name = n;
        path = folder;
        screen = new Screen(200, 200);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();
    }

    public GameCharacter(String n, String folder, int h,double[] aiWeights) {
        this(n,folder,aiWeights);
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
        BufferedImage image = Main.iconToBuffer(getExpression(expression));
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
    public Card getChosenCard(Card topCard) {
        ArrayList<Card> tempHand = (ArrayList<Card>) hand.clone();
        for (int i = 0; i < hand.size(); i++) {
            if(!Card.isValidCombo(tempHand.get(i),topCard)) {
                tempHand.remove(i);
            }
        }
        double[] weightsArr = new double[tempHand.size()];
        for (int i = 0; i < tempHand.size(); i++) {
            weightsArr[i]=(cardWeights[tempHand.get(i).getNumber()]);
        }
        return tempHand.get(Main.randomWithWeights(weightsArr));

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
