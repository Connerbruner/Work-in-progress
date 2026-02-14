package org.example.Cards;

import org.example.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;

public class UnoGame {
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> discard = new ArrayList<>();

    //in millis
    private long timeLimit;
    private int roundLimit;
    private long turnTime;
    private int cardsPerTurn;
    private Rule[] rules;
    private Ratio ratio;


    public UnoGame(Character[] c) {
        this(60000, 5000, 1, new Ratio(), new Rule[0], c);
    }

    public UnoGame(long time, int cardsPer, int turn, Ratio r, Rule[] rule, Character[] c) {
        timeLimit = time;
        roundLimit = -1;
        cardsPerTurn = cardsPer;
        ratio = r;
        rules = rule;
        turnTime = turn;

    }
    public UnoGame(int rounds, int cardsPer, int turn, Ratio r, Rule[] rule, Character[] c) {
        timeLimit = -1;
        roundLimit = rounds;
        cardsPerTurn = cardsPer;
        ratio = r;
        rules = rule;
        turnTime = turn;

    }
    public UnoGame(long time,int rounds, int cardsPer, int turn, Ratio r, Rule[] rule, Character[] c) {
        timeLimit = time;
        roundLimit = rounds;
        cardsPerTurn = cardsPer;
        ratio = r;
        rules = rule;
        turnTime = turn;

    }

    public void playGame(ArrayList<GameCharacter> characters) {
        long startTime = System.currentTimeMillis();
        long endTime = timeLimit;
        int roundCount = roundLimit;
    }
    public boolean endGame(long startTime,)

    public void singleLoop(ArrayList<GameCharacter> characters) {
        for (int i = 0; i < characters.size(); i++) {
            GameCharacter character = characters.get(0);
            if (character.hasValidCard(discard.get(0))) {
                Card card = character.getChosenCard(discard.get(0));
                addDiscard(card);
            } else {
                drawCard(character);
            }
        }
    }

    public void drawCard(GameCharacter c) {
        c.addCard(deck.get(0));
        deck.remove(0);
    }

    public void checkReshuffle() {
        if (deck.size() * 3 < discard.size() || deck.size() < 5) {
            Card topDiscard = getTopDiscard();
            discard.remove(0);

            deck.addAll(discard);
            Collections.shuffle(deck);
            discard.clear();

            discard.add(topDiscard);
        }
    }

    public Card getTopDiscard() {
        return discard.get(0);
    }

    public void addDiscard(Card c) {
        discard.add(0, c);
    }


    public void fillDeck() {
        deck.clear();
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
