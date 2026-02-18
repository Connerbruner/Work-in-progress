package org.example;

import org.example.Cards.Card;
import org.example.Cards.UnoGame;
import org.example.Display.Screen;

import java.util.ArrayList;

public class PlayableCharacter extends GameCharacter {
    private Job job;
    private double[] statsInfluence;
    private int[] stats;
    private int balance;
    private boolean isDead;
    private boolean isPlayable;
    public static final double[] TEST_VALUES = {0.5,0.5,0.5,0.5,0.5,0.5,0.5
                                        ,0.5,0.5,0.5,0.5,0.5,0.5,0.5,0.5};

    static final Job[] JOBS = {
            new Job("Getting up", new UnoGame(), 0, 0, 5, 0),
            new Job("Fish market", new UnoGame(), 130, 0, 7, 0),
    };
    static final PlayableCharacter[] PLAYABLE_GAME_CHARACTERS = {
            new PlayableCharacter("Carina", "src/main/java/org/example/Charachters/Carina", new double[]{1, 1, 1.5, 0.5, 1}, JOBS[1], 35,TEST_VALUES),
            new PlayableCharacter("Orion", "src/main/java/org/example/Charachters/Orion", new double[]{1, 1.5, 0.5, 0.5, 1}, JOBS[0],TEST_VALUES)
    };

    public PlayableCharacter(String n, String folder, double[] s, Job j, double[] aiWeights) {
        this(n,folder,s,j,0,aiWeights);
    }

    public PlayableCharacter(String n, String folder, double[] s, Job j, int h, double[] aiWeights) {
        super(n, folder, h, aiWeights);
        statsInfluence = s;
        job = j;
        stats = new int[]{100, 100, 100, 100, 100};
        balance = 100;
        isDead = false;
        isPlayable=true;
    }
    public void choseCard(Card topCard) {

    }

    public void reset() {
        stats = new int[]{100, 100, 100, 100, 100};
        changeExpression("hidden");
        balance = 100;
        isDead = false;
    }

    public void runJob() {
        job.run();
    }

    public double getStatInfluence(int i) {
        return statsInfluence[i];
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void subtractBalance(int sub) {
        this.balance -= sub;
    }

    public double getStats(int i) {
        return stats[i];
    }

    public int getBalance() {
        return balance;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void addBalance(int amount) {
        balance += amount;
    }

    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    public boolean isPlayable() {
        return isPlayable;
    }
}
