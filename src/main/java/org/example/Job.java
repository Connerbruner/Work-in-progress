package org.example;

import org.example.Cards.CardGame;
import org.example.Timeline.Game;

public class Job {
    private final String name;
    private PlayableCharacter charatcher;
    private final int numberOfGames;
    private final int min;
    private final int bonus;
    private final int daysBettewnChecks;
    private int goodGames = 0;


    public Job(String s, int m, int b, int n, int d) {
        name = s;
        min = m;
        bonus = b;
        numberOfGames = n;
        daysBettewnChecks = d;
    }

    public void setCharatcher(PlayableCharacter c) {
        charatcher = c;
    }

    public void run() {
        if(Game.getDaysSurvived()%daysBettewnChecks==0 && Game.getDaysSurvived()!=0) {
            charatcher.addBalance((min*daysBettewnChecks)+(goodGames*bonus));
            goodGames = 0;
        }
    }

}
