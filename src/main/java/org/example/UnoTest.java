package org.example;

import org.example.Cards.CardGame;
import org.example.Timeline.Game;

import java.util.Arrays;
import java.util.LinkedList;

public class UnoTest {

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();
            System.out.flush();
        });

        try {
            CardGame.fillDeck();
            for (int i = 0; i < Game.PLAYABLE_GAME_CHARACTERS.length; i++) {
                CardGame.startingFillHand(Game.PLAYABLE_GAME_CHARACTERS[i]);
            }
            CardGame.playGame(20,new LinkedList<>(Arrays.asList(Game.PLAYABLE_GAME_CHARACTERS)));
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.flush();
        }
    }
}
