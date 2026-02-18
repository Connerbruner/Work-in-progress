package org.example;

import org.example.Cards.UnoGame;

import java.util.ArrayList;
import java.util.Arrays;

public class UnoTest {

    public static void main(String[] args) {
        UnoGame game = UnoGame.GAMES[0];
        for (int i = 0; i < PlayableCharacter.PLAYABLE_GAME_CHARACTERS.length; i++) {
            UnoGame.startingFillHand(PlayableCharacter.PLAYABLE_GAME_CHARACTERS[i]);
        }
        System.out.println(PlayableCharacter.PLAYABLE_GAME_CHARACTERS.length);
        game.playGame(new ArrayList<>(Arrays.asList(PlayableCharacter.PLAYABLE_GAME_CHARACTERS)));
    }
}
