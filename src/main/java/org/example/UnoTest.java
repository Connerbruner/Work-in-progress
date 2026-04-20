package org.example;

import org.example.Cards.UnoGame;
import org.example.Timeline.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class UnoTest {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();
            System.out.flush();
        });

        try {
            UnoGame game = UnoGame.GAMES[0];
            for (int i = 0; i < Game.PLAYABLE_GAME_CHARACTERS.length; i++) {
                UnoGame.startingFillHand(Game.PLAYABLE_GAME_CHARACTERS[i]);
            }
            game.playGame(new LinkedList<>(Arrays.asList(Game.PLAYABLE_GAME_CHARACTERS)));
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.flush();
        }
    }
}
