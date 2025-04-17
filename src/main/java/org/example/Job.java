package org.example;

public class Job {
    private Game[] games;
    private Charatcher charatcher;
    private int numberOfGames;
    private int min;
    private int bonus;
    public Job(Charatcher c,Game[] g,int m,int b, int n) {
        charatcher=c;
        games=g;
        min=m;
        bonus=b;
        numberOfGames=n;

    }
    public void run() {
        int goodGames =0;
        for(int i=0; i<numberOfGames; i++) {
            if(games[Main.random(games)].run()) goodGames++;
        }
        charatcher.addBalance(min+(goodGames*bonus));
    }

}
