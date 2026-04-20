    package org.example.Cards;

    import org.example.GameCharacter;
    import org.example.Main;

    import java.util.*;

    public class UnoGame {
        public static final UnoGame[] GAMES = {
                new UnoGame()
        };

        private Queue<Card> deck = new LinkedList<>();
        private Stack<Card> discard = new Stack<>();
        //in millis
        private long timeLimit;
        private int roundLimit;
        private long turnTime;
        private int cardsPerTurn;
        private Queue<Rule> rules;
        private Ratio ratio;


        public UnoGame() {
            this(3000, 5000, 1, new Ratio(), new LinkedList<>());
        }

        public UnoGame(long time, int cardsPer, int turn, Ratio r, Queue<Rule> rule) {
            this(time, -1, cardsPer, turn, r, rule);


        }

        public UnoGame(int rounds, int cardsPer, int turn, Ratio r, Queue<Rule> rule) {
            this(-1, rounds, cardsPer, turn, r, rule);


        }

        public UnoGame(long time, int rounds, int cardsPer, int turn, Ratio r, Queue<Rule> rule) {
            timeLimit = time;
            roundLimit = rounds;
            cardsPerTurn = cardsPer;
            ratio = r;
            rules = rule;
            turnTime = turn;
            fillDeck();

        }

        public static void startingFillHand(GameCharacter c, UnoGame deck) {
            c.getHand().clear();
            for (int i = 0; i < 7; i++) {
                deck.drawCard(c);
            }
        }

        public static void startingFillHand(GameCharacter c) {
            startingFillHand(c, GAMES[0]);
        }

        public void playGame(LinkedList<GameCharacter> characters) {
            long startTime = System.currentTimeMillis();
            int roundCount = 0;
            discard.add(deck.poll());
            while (!endGame(startTime, roundCount)) {
                try {
                    checkReshuffle();
                    singleTurn(characters.peek());
                    characters.add(characters.poll());
                    roundCount++;
                    Thread.sleep(100); // breathing room for the CPU
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.flush();
                    break;
                }
            }
            System.out.println("Game ended after " + roundCount + " rounds");
            System.out.flush();
        }

        public boolean endGame(long startTime, int rounds) {
            boolean shouldEnd = false;
            if (roundLimit > -1) {
                shouldEnd = rounds >= roundLimit;
            }
            if (timeLimit > -1) {
                shouldEnd = System.currentTimeMillis() >= startTime + timeLimit || shouldEnd;
            }
            return shouldEnd;
        }

        public void singleTurn(GameCharacter character) {
            if (character.hasValidCard(discard.peek())) {
                Card card = character.getChosenCard(discard.peek());
                discardCard(card);
            } else {
                drawCard(character);
            }
        }

        public void drawCard(GameCharacter c) {
            c.addCard(deck.poll());
        }
        public void discardCard(Card todiscard) {
            discard.peek().setVisible(false);
            discard.push(todiscard);
            discard.peek().setFaceDown(false);
            discard.peek().setVisible(true);
        }

        public void checkReshuffle() {
            if (deck.size() * 3 < discard.size() || deck.size() < 5) {
                Card topDiscard = discard.pop();

                deck.addAll(discard);
                Collections.shuffle((List<Card>) deck);
                discard.clear();

                discard.push(topDiscard);
            }
        }


        public void fillDeck() {
            deck.clear();
            for (int i = 0; i < ratio.getColorCount(); i++) {
                for (int j = 0; j < ratio.getCyclePerColor(); j++) {
                    for (int k = 1; ratio.getNumbers().length > k; k++) {
                        if(!ratio.isWildNumber(k)) {
                            deck.add(new Card(k, i));
                        }
                    }
                }
                deck.add(new Card(true, (i % ratio.getWildNumbers().length == 0)));
                deck.add(new Card(0, i));

            }
            Collections.shuffle((List<Card>) deck);

        }
    }
