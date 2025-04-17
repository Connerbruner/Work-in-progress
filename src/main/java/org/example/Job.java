package org.example;

public class Job {
    private final String name;
    private final Game[] games;
    private Charatcher charatcher;
    private final int numberOfGames;
    private final int min;
    private final int bonus;
    private final int daysBettewnChecks;

    public Job(String s, Game[] g, int m, int b, int n, int d) {
        name = s;
        games = g;
        min = m;
        bonus = b;
        numberOfGames = n;
        daysBettewnChecks = d;
    }

    public void setCharatcher(Charatcher c) {
        charatcher = c;
    }

    public void run() {
        int goodGames = 0;
        for (int i = 0; i < numberOfGames; i++) {
            if (games[Main.random(games)].run()) goodGames++;
        }
        if(Main.currentDate)
    }

}
