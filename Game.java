package org.example;

public class Game {
    private runGame game;
    public Game(runGame g) {
        game=g;
    }
    public boolean run() {
        return game.run();
    }
}
interface runGame{
    boolean run();
}
