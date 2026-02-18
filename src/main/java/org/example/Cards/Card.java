package org.example.Cards;

import org.example.Display.Screen;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

//0-9 (skip) (reverse) (+2) (+4) anything over 13 is a blank card
public class Card {
    public static final double UNO_CARD_RATIO = 2.5/3.5;
    private static int cardHeight = 250;
    private int number;
    private Color color;
    private boolean faceDown = true;
    private boolean isWild;
    private Screen cardScreen = new Screen(cardHeight, (int) (cardHeight*UNO_CARD_RATIO));
    public static final ImageIcon FACEDOWN = new ImageIcon("/src/main/java/org/example/Ui/Cards/back");
    private ImageIcon faceUp;
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
        faceUp = getCardImage();
        cardScreen.setVisible(false);

    }

    public Card(boolean wild, boolean isPlus) {
        this((isPlus) ? 14 : 10,ALL_COLORS.length-1);
        isWild = wild;
    }



    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
        if (faceDown) {
            cardScreen.setBackground(FACEDOWN);
        } else {
            cardScreen.setBackground(faceUp);
        }
    }

    public ImageIcon getCardImage() {
            BufferedImage cardImage ;
            try {
                cardImage = Main.iconToBuffer(new ImageIcon("src/main/java/org/example/Ui/Cards/" + number+".png"));
                System.out.println(number);
                for (int i = 0; i < cardImage.getWidth(); i++) {
                    for (int j = 0; j < cardImage.getHeight(); j++) {
                        int pixel = cardImage.getRGB(i, j);
                        if ((pixel & 0xFF000000) == 0) {
                            cardImage.setRGB(i, j, color.getRGB());
                        }
                    }
                }
            } catch (Exception e) {
                return new ImageIcon();
                //cardImage = new BufferedImage(cardHeight, (int) (cardHeight*UNO_CARD_RATIO), BufferedImage.TYPE_INT_ARGB);
            }
            return new ImageIcon(cardImage);
    }

    public static void setCardHeight(int cardHeight) {
        Card.cardHeight = cardHeight;
    }

    public static int getCardHeight() {
        return cardHeight;
    }

    public void setCardScreen(Screen cardScreen) {
        this.cardScreen = cardScreen;
    }

    public int getNumber() {
        return number;
    }

    public Color getColor() {
        return color;
    }
}