package org.example.Cards;

import org.example.Display.Screen;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//0-9 (skip) (reverse) (+2) (+4) anything over 13 is a blank card
public class Card {
    private int number;
    private Color color;
    private boolean faceDown = true;
    private boolean isWild;
    private Screen cardScreen = new Screen(100, 140);
    public final static Color[] ALL_COLORS = new Color[]{
            Color.red,
            Color.blue,
            Color.yellow,
            Color.green,
            Color.pink,
            Color.CYAN,
            Color.MAGENTA,
            Color.black,
    };
    public static boolean isValidCombo(Card choice,Card top) {
        return choice.isWild ||
                choice.getColor()==top.getColor() ||
                choice.getNumber()==top.getNumber();
    }

    public Card(int n, int c) {
        number = n;
        color = ALL_COLORS[c];
        setupScreen();
    }

    public Card(boolean wild, boolean isPlus) {
        number = (isPlus) ? 14 : 10;
        color = Color.BLACK;
        isWild = true;
        setupScreen();
    }

    public void setupScreen() {
        cardScreen.setBackground(getCardImage());
        cardScreen.setVisible(false);
    }

    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
        if (faceDown) {
            cardScreen.setBackground("/src/main/java/org/example/Ui/Cards/back");
        } else {
            cardScreen.setBackground(getCardImage());
        }
    }

    public ImageIcon getCardImage() {
        BufferedImage cardImage;
        try {
            cardImage = Main.iconToBuffer(new ImageIcon("/src/main/java/org/example/Ui/Cards/" + number));
            for (int i = 0; i < cardImage.getHeight(); i++) {
                for (int j = 0; j < cardImage.getWidth(); j++) {
                    int pixel = cardImage.getRGB(i, j);
                    if ((pixel >> 24) == 0x00) {
                        cardImage.setRGB(i, j, color.getRGB());
                    }
                }
            }
        } catch (Exception e) {
            cardImage = new BufferedImage(cardScreen.getWidth(), cardScreen.getHeight(), BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < cardImage.getHeight(); i++) {
                for (int j = 0; j < cardImage.getWidth(); j++) {
                    cardImage.setRGB(i, j, color.getRGB());
                }
            }
        }
        return new ImageIcon(cardImage);
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }
}