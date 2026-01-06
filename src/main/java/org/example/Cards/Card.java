package org.example.Cards;

import org.example.Display.Screen;

import java.awt.*;

public class Card {
    private int number;
    private int color;
    private boolean faceDown;
    private boolean isWild;
    private Screen cardScreen = new Screen(100,140);
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

    public Card(int n, int c) {
        number = n;
        color = c;
        faceDown = true;
        setupScreen();
    }

    public Card(boolean wild) {
        number = 0;
        color = 0;
        isWild = true;
        faceDown = true;
        setupScreen();

    }
    public void setupScreen() {
        cardScreen.setVisible(false);

    }
}