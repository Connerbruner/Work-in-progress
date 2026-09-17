    package org.example;

import org.example.Cards.CardGame;
import org.example.Display.Screen;
import org.example.Timeline.Cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

    public class GameCharacter {
    private String name, expression;
    private Screen screen;
    private JLabel label;
    private int heightShort, balance;
    private boolean isDead = false;
    private boolean isFlipped = false;
    //Stats in order: awakeness fitness sanity self-esteem Illness
    private int[] stats;
    //play types in order: Aggressive,clearout,lowest,pileCheck
    private double[] statsInfluence, playWeights;
    private ArrayList<Integer> hand = new ArrayList<>();
    private Cannon cannon;

    public void addCard(int c) {
        hand.add(c);
    }

    public GameCharacter(String n, double[] sI, double[] a) {
        playWeights = a;
        name = n;
        statsInfluence = sI;
        stats = new int[]{100, 100, 100, 100, 100};
        heightShort = 0;

        screen = new Screen(200, 200);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();
    }

    public GameCharacter(String n, int h, double[] sI, double[] playWeights) {
        this(n, sI, playWeights);
        heightShort = h;
    }
    public GameCharacter(String n, int h, double[] sI, double[] playWeights, Cannon c) {
        this(n, sI, playWeights);
        heightShort = h;
        cannon=c;
    }
    public GameCharacter(String n, double[] sI, double[] playWeights, Cannon c) {
        this(n,0, sI, playWeights,c);
    }

    public void reset() {
        stats = new int[]{100, 100, 100, 100, 100};
        changeExpression("hidden");
        balance = 100;
        isDead = false;
    }

    public int choseCard(Stack<Integer> discard) {
        int topCard = CardGame.getTopDiscard();
        Collections.sort(hand);
        int plan = Main.randomWithWeights(playWeights);
        switch (plan) {
            case 0: {
                if(CardGame.getPileValue(discard)<=-6 && hand.contains(3)) {
                    return 3;
                }
                if(CardGame.isPlayable(hand.get(hand.size()-1), topCard)) {
                    return hand.get(hand.size()-1);
                } else if (hand.contains(2)){
                    return 2;
                }else if (hand.contains(10)) {
                    return 10;
                }else if (hand.contains(3)) {
                    return 3;
                }
                return -1;

            }

            case 2: {
                if(hand.contains(2)) {
                    hand.remove(hand.indexOf(2));
                    hand.add(2);
                }
                if(hand.contains(10)) {
                    hand.remove(hand.indexOf(10));
                    hand.add(10);
                }
                if(hand.contains(3)) {
                    hand.remove(hand.indexOf(3));
                    hand.add(3);
                }
                for (int i = 0; i < hand.size(); i++) {
                    if(CardGame.isPlayable(hand.get(i), topCard)) {
                        return hand.get(i);
                    }
                }
            }
            default: {
                if(CardGame.getPileValue(discard)>0 && plan!=1) {
                    if(Main.random(CardGame.getPileValue(discard),10)>9) {
                        return -1;
                    }
                }
                if(hand.contains(2)) {
                    hand.remove(hand.indexOf(2));
                    hand.add(2);
                }
                if(hand.contains(10)) {
                    hand.remove(hand.indexOf(10));
                    hand.add(10);
                }
                if(hand.contains(3)) {
                    hand.remove(hand.indexOf(3));
                    hand.add(3);
                }
                int i ,maxcount=0, res=0;
                for (i = 0; !CardGame.isPlayable(hand.get(i), topCard); i++);
                for (; i < hand.size(); i++) {
                    int count = 0;
                    for (int j = 0; j < hand.size(); j++) {
                        if (hand.get(0) == hand.get(i))
                            count++;
                    }
                    if (count > maxcount || (count == maxcount && hand.get(i) > res)) {
                        maxcount = count;
                        res = hand.get(i);
                    }
                }
                return res;

                }


        }
    }
    public boolean three(Stack<Integer> discard) {
        boolean three = hand.contains(3);
        if(three) {
            int value = CardGame.getPileValue(discard);
            three = Main.random(value-5,value)<0;
        }
        return three;
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


    public boolean hasValidCard(int topCard) {
        for (int i = 0; i < hand.size(); i++) {
            if (CardGame.isPlayable(hand.get(i), topCard)) {
                return true;
            }
        }
        return false;
    }

    public void playCard(int card) {
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

    public ArrayList<Integer> getHand() {
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
