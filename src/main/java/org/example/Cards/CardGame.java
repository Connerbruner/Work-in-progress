    package org.example.Cards;

    import org.example.GameCharacter;

    import javax.smartcardio.Card;
    import java.util.*;

    public class CardGame {
        private static Queue<Integer> deck = new LinkedList<>();
        private static Stack<Integer> discard = new Stack<>();
        private static boolean displayMode = true;
        private static boolean three = false;
        private static Scanner scanner = new Scanner(System.in);
        private static ArrayList<Integer>[] handDisplays;

        public static void startingFillHand(GameCharacter c) {
            c.getHand().clear();
            for (int i = 0; i < 4; i++) {
                drawCard(c);
            }
        }

        public static void playGame(int roundLimit,LinkedList<GameCharacter> characters) {
            int roundCount = 0;

            while (roundCount<roundLimit) {
                    if(deck.isEmpty()) {
                        fillDeck();
                    }

                    System.out.println(characters.peek().getName()+" turn");
                    System.out.println("Pile value: "+CardGame.getPileValue(discard));
                    if(discard.isEmpty()) {
                        System.out.println("Discard empty");

                    } else {
                        System.out.println("Top card:"+discard.peek());

                    }
                    ArrayList<Integer> cards = characters.peek().getHand();
                    System.out.print("Current hand: ");
                    for (int i = 0; i < cards.size(); i++) {
                        System.out.print(cards.get(i)+" ");
                    }
                    System.out.println();
                    singleTurn(characters.peek());
                    characters.add(characters.poll());
                    while (three) {
                        GameCharacter character = characters.peek();
                        if(character.getHand().contains(3)) {
                            if(character.three(discard)) {
                                three=true;
                                characters.add(characters.poll());
                                character.getHand().remove(character.getHand().indexOf(3));
                            }
                        } else {
                            three=false;
                            assert characters.peek() != null;
                            takePile(characters.peek());
                        }
                    }
                    System.out.println();
                    roundCount++;

            }
            System.out.println("Game ended after " + roundCount + " rounds");
            System.out.flush();
        }

        public int getPileValue() {
           return getPileValue(discard);
        }
        public static int getPileValue(Stack<Integer> pile) {
            Stack<Integer> pileClone = (Stack<Integer>) pile.clone();
            double rawValue = 0;
            double sizeWeight = 0.5+(0.2*(Math.pow(pile.size(),2.25)));
            while (!pileClone.isEmpty()) {
                switch (pileClone.peek()) {
                    case 2: {
                        rawValue+=14;
                        pileClone.pop();
                        break;
                    }
                    default: {
                        rawValue+=pileClone.pop();
                        break;
                    }
                }
            }
            double score = (int) ((rawValue/pile.size())/sizeWeight)-10;
            if(score>0) {
                score+=0.5;
            } else {
                score-=0.5;
            }
            return (int) score;

        }
        public static void takePile(GameCharacter c) {
            System.out.println(c.getName()+" takes pile of size "+discard.size());
            while (!discard.isEmpty()) {
                c.getHand().add(discard.pop());
            }

        }
        public static void takePile() {
            while (!discard.isEmpty()) {
                discard.pop();
            }
        }
        public static int getTopDiscard() {
            if(discard.isEmpty()) {
                return 0;
            }
            return discard.peek();
        }
        public static void singleTurn(GameCharacter character) {
            if (character.hasValidCard(getTopDiscard())) {
                int card = character.choseCard(discard);
                System.out.println(character.getName()+" plays "+card);
                switch (card) {
                    case -1: {
                        takePile(character);
                        break;
                    }
                    case 10: {
                        takePile();
                        break;
                    }
                    case 3: {
                        three=true;
                        break;
                    }
                    default: {
                        while (character.getHand().contains(card)) {
                            discard.push(card);
                            character.getHand().remove(character.getHand().indexOf(card));
                        }
                        break;

                    }
                }
            } else {
                takePile(character);
            }
            while (character.getHand().size()<4) {
                drawCard(character);
                System.out.println(character.getName()+" draws a card");
            }
        }

        public static boolean isPlayable(int choice, int top) {
            switch (choice) {
                case 2, 10 , 3: {
                    return true;
                }
                default: {
                    return choice> top;
                }

            }
        }

        public static void drawCard(GameCharacter c) {
            c.addCard(deck.poll());
        }





        public static void fillDeck() {
            deck.clear();
            for (int i = 0; i < 4; i++) {
                for (int j = 2; j < 14; j++) {
                    deck.add(j);
                }

            }
            Collections.shuffle((List<Integer>) deck);

        }

        public boolean isDisplayMode() {
            return displayMode;
        }

        public void setDisplayMode(boolean displayMode) {
            CardGame.displayMode = displayMode;
        }
    }
