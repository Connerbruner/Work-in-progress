package org.example.Cards;

import org.example.GameCharacter;
import org.example.PlayableCharacter;

import java.awt.font.GlyphMetrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> discard = new ArrayList<>();
    ArrayList<GameCharacter> characters;


    private long timeLimit;
    private long turnTime;
    private int cardsPerTurn;
    private Rule[] rules;
    private Ratio ratio;


    public Game(Character[] c) {
        this(60000,5000,1, new Ratio(),new Rule[0],c);
        characters = (ArrayList<GameCharacter>) Arrays.stream(c);
    }

    public Game(long t, int cardsPer, int turn, Ratio r, Rule[] rule, Character[] c) {
        timeLimit = t;
        cardsPerTurn = cardsPer;
        ratio = r;
        rules = rule;
        turnTime = turn;
        characters = (ArrayList<GameCharacter>) Arrays.stream(c);

    }
    public void singleLoop() {
        for (int i = 0; i < characters.size(); i++) {
            if( characters.get(i) instanceof PlayableCharacter) {

            } else {

            }
        }
    }
    public void drawCard(GameCharacter c) {
        c.addCard(deck.get(0));
        deck.remove(0);
    }
    public void checkReshuffle() {
        if(deck.size()*3<discard.size() || deck.size()<5) {
            Collections.shuffle(discard);
            deck.addAll(discard);
            discard.clear();
        }
    }
    public void addDiscard(Card c) {
        discard.add(0,c);
    }


    public void fillDeck() {
        for (int i = 0; i < ratio.getColorCount(); i++) {
            for (int j = 0; j < ratio.getCyclePerColor(); j++) {
                for (int k = 0; k < ratio.getNormalCards(); k++) {
                    deck.add(new Card(ratio.getValidNumberCard(k), i));
                }
                for (int k = 0; k < ratio.getPowerCards(); k++) {
                    deck.add(new Card(ratio.getValidPowerCard(k), i));
                }
                for (int k = 0; k < ratio.getWild(); k++) {
                    deck.add(new Card(true, (j % ratio.getWildNumbers().length == 0)));
                }
            }
        }
        Collections.shuffle(deck);
    }
}
