package org.example;

import java.awt.*;

public class Card {
    private int number;
    private int color;
    private boolean faceDown;
    private boolean isWild;

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
    }

    public Card(boolean wild) {
        number = 0;
        color = 0;
        isWild = true;
        faceDown = true;
    }
}