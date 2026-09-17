package org.example;

import org.example.Cards.CardScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Arrays.asList;

public class Handtest {
    static ArrayList<CardScreen>[] cards = new ArrayList[2];
    static int handSize =4;
    public static void main(String[] args) {
        Rectangle bounds = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .getBounds();

        System.out.println(bounds.width + "x" + bounds.height);
        int coloumCount = (cards.length+1)/2;
        int zoneWidth = bounds.width/coloumCount;
        int cardGap = (int) ((zoneWidth*0.66)/handSize);
        if(cardGap>CardScreen.CARD_WIDTH/1.1) {
            cardGap= (int) (CardScreen.CARD_WIDTH/1.1);
        }
        System.out.println(coloumCount);
        System.out.println((zoneWidth*0.66)/handSize);


        System.out.println(bounds.toString());
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new ArrayList<>();
            int y = bounds.y+(bounds.height/32);
            if(i%2==0) {
                y+=bounds.height/1.33;
            }
            int x = bounds.x+(bounds.width/(coloumCount*cards.length*2))+(zoneWidth*(int)(i/2));
            for(int j=0; j < handSize; j++) {

                cards[i].add(new CardScreen(Main.random(2,14)));
                cards[i].get(cards[i].size()-1).setLocation((x+(j*cardGap)),y);
                cards[i].get(cards[i].size()-1).setVisible(true);
            }
        }
    }

}
