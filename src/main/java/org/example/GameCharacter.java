    package org.example;

import org.example.Cards.Card;
import org.example.Display.Screen;
import org.example.Timeline.Cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameCharacter {
    private String name, expression;
    private Screen screen;
    private JLabel label;
    private int heightShort, balance;
    private boolean isDead = false;
    private boolean isFlipped = false;
    private int[] stats;
    private double[] statsInfluence, cardWeights;
    private ArrayList<Card> hand = new ArrayList<>();
    private Cannon cannon;
    //awakeness fitness sanity self-esteem Illness

    public void addCard(Card c) {
        hand.add(c);
    }

    public GameCharacter(String n, double[] sI, double[] aiWeights) {
        cardWeights = aiWeights;
        name = n;
        statsInfluence = sI;
        stats = new int[]{100, 100, 100, 100, 100};
        heightShort = 0;

        screen = new Screen(200, 200);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();
    }

    public GameCharacter(String n, int h, double[] sI, double[] aiWeights) {
        this(n, sI, aiWeights);
        heightShort = h;
    }
    public GameCharacter(String n, int h, double[] sI, double[] aiWeights,Cannon c) {
        this(n, sI, aiWeights);
        heightShort = h;
        cannon=c;
    }
    public GameCharacter(String n, double[] sI, double[] aiWeights,Cannon c) {
        this(n,0, sI, aiWeights,c);
    }

    public void reset() {
        stats = new int[]{100, 100, 100, 100, 100};
        changeExpression("hidden");
        balance = 100;
        isDead = false;
    }

    public void choseCard(Card topCard) {

    }

    public Cannon getCannon() {
        return cannon;
    }

    public String getName() {
        return name;
    }


    public Screen getScreen() {
        return screen;
    }


    public ImageIcon getExpression(String expression) {
        return new ImageIcon(Main.getResourceImage("Characters/" +name+ expression + ".png").getImage());
    }

    public boolean hasExpression(String expression) {
        return Main.getResource("Characters/" +name+ expression + ".png").exists();
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
        for (int i = 0; i < tempHand.size(); i++) {
            if (!Card.isValidCombo(tempHand.get(i), topCard)) {
                tempHand.remove(i);
                i--;
            }
        }
        double[] weightsArr = new double[tempHand.size()];
        for (int i = 0; i < tempHand.size(); i++) {
            weightsArr[i] = (cardWeights[tempHand.get(i).getNumber()]);
        }
        return tempHand.get(Main.randomWithWeights(weightsArr));

    }

    public boolean hasValidCard(Card topCard) {
        for (int i = 0; i < hand.size(); i++) {
            if (Card.isValidCombo(hand.get(i), topCard)) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card card) {
        hand.remove(card);
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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public double getStatInfluence(int i) {
        return statsInfluence[i];
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void subtractBalance(int sub) {
        this.balance -= sub;
    }

    public double getStats(int i) {
        return stats[i];
    }

    public int getBalance() {
        return balance;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void addBalance(int amount) {
        balance += amount;
    }
}
