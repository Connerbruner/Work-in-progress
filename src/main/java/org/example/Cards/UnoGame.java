package org.example.Cards;

import org.example.GameCharacter;

import java.util.ArrayList;
import java.util.Collections;

public class UnoGame {

    public static final UnoGame[] GAMES = {
        new UnoGame()
    };

    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> discard = new ArrayList<>();
    //in millis
    private long timeLimit;
    private int roundLimit;
    private long turnTime;
    private int cardsPerTurn;
    private Rule[] rules;
    private Ratio ratio;


    public UnoGame() {
        this(60000, 5000, 1, new Ratio(), new Rule[0]);
    }

    public UnoGame(long time, int cardsPer, int turn, Ratio r, Rule[] rule) {
        this(time,-1,cardsPer,turn,r,rule);


    }
    public UnoGame(int rounds, int cardsPer, int turn, Ratio r, Rule[] rule) {
        this(-1,rounds,cardsPer,turn,r,rule);


    }
    public UnoGame(long time,int rounds, int cardsPer, int turn, Ratio r, Rule[] rule) {
        timeLimit = time;
        roundLimit = rounds;
        cardsPerTurn = cardsPer;
        ratio = r;
        rules = rule;
        turnTime = turn;
        fillDeck();

    }
    public static void startingFillHand(GameCharacter c,UnoGame deck) {
        c.getHand().clear();
        for (int i = 0; i < 7; i++) {
            deck.drawCard(c);
        }
    }
    public static void startingFillHand(GameCharacter c) {
        startingFillHand(c,GAMES[0]);
    }

    public void playGame(ArrayList<GameCharacter> characters) {
        long startTime = System.currentTimeMillis();
        int roundCount = 0;
        discard.add(deck.get(0));
        deck.remove(0);
        while (!endGame(startTime,roundCount)) {
            singleLoop(characters);
        }
    }
    public boolean endGame(long startTime,int rounds) {
        boolean shouldEnd = false;
        if(roundLimit>-1) {
            shouldEnd = rounds>=roundLimit;
        }
        if(timeLimit>-1) {
            shouldEnd = startTime+timeLimit>System.currentTimeMillis() || shouldEnd;
        }
        return shouldEnd;
    }

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
