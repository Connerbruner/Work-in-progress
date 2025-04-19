package org.example;

public class Job {
    private final String name;
    private final Game[] games;
    private Character charatcher;
    private final int numberOfGames;
    private final int min;
    private final int bonus;
    private final int daysBettewnChecks;
    private int goodGames = 0;


    public Job(String s, Game[] g, int m, int b, int n, int d) {
        name = s;
        games = g;
        min = m;
        bonus = b;
        numberOfGames = n;
        daysBettewnChecks = d;
    }

    public void setCharatcher(Character c) {
        charatcher = c;
    }

    public void run() {
        for (int i = 0; i < numberOfGames; i++) {
            if (games[Main.random(games)].run()) goodGames++;
        }
        if(Main.daysSurvived%daysBettewnChecks==0) {
            charatcher.addBalance((min*daysBettewnChecks)+(goodGames*bonus));
            goodGames = 0;
        }
    }

}
