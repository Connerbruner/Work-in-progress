package org.example;

import java.util.ArrayList;

public class Game {
    private ArrayList<Card> deck = new ArrayList<>();

    private long timeLimit;
    private long turnTime;
    private int cardsPerTurn;
    private int colorCount;
    private Rule[] rules;
    private Ratio ratios;
    public Game() {
        timeLimit=60000;
        turnTime=5000;
        cardsPerTurn=1;
        rules=new Rule[0];
        ratios = new Ratio();
    }
    public Game(long t,int cardsper,int turn,int color,Ratio ratio,Rule[] rule) {
        timeLimit=t;
        cardsPerTurn=cardsper;
        colorCount=color;
        ratios=ratio;
        rules=rule;
        turnTime=turn;
    }
    public void fillDeck() {
        for(int i=0; i<colorCount; i++) {
            for(int j=0; j< ratios.getWild(); j++) {

            }
        }
    }
}
