package org.example.Cards;

import org.example.Display.Screen;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardScreen extends Screen {
    private int card;
    public static final double UNO_CARD_RATIO = 2.5/3.5;
    public static final int CARD_HEIGHT = 250;
    public static final int CARD_WIDTH = (int) (250*UNO_CARD_RATIO);
    public static final ImageIcon FACEDOWN = (Main.getResourceImage("Ui/Cards/card_back.png"));
    private ImageIcon cardImage;
    public CardScreen() {
        super(CARD_HEIGHT,CARD_WIDTH);
    }
    public CardScreen(int number) {
        super(CARD_HEIGHT, CARD_WIDTH);
        setCard(number);

    }



    public void setCard(int card) {
        this.card = card;
        setCardImage();
    }
    public void setFaceDown(boolean faceDown) {
        if (faceDown) {
            setBackground(FACEDOWN);
        } else {
            setBackground(cardImage);
        }
    }
    public void setCardImage() {
        ImageIcon cardImage = new ImageIcon();
        try {
            cardImage = (Main.getResourceImage("Ui/Cards/" + card+".png"));

        } catch (Exception e) {
            System.out.println("We fucked up");
        }
        setBackground(cardImage);
    }

    public void setCardImage(int number) {
        setCard(number);
        setCardImage();
    }
}
