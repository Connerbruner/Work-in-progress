package org.example.Timeline;

import org.example.Cards.UnoGame;
import org.example.GameCharacter;
import org.example.Job;
import org.example.PlayableCharacter;

import java.util.Calendar;
import java.util.Date;

public class Game {
    public static final double[] TEST_VALUES = {0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5
                                                , 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5};
    static final Job[] JOBS = {
            new Job("Getting up", new UnoGame(), 0, 0, 5, 0),
            new Job("Fish market", new UnoGame(), 130, 0, 7, 0),
    };


    public static final PlayableCharacter[] PLAYABLE_GAME_CHARACTERS = {
            new PlayableCharacter("Carina",  new double[]{1, 1, 1.5, 0.5, 1}, JOBS[1], 35, TEST_VALUES),
            new PlayableCharacter("Orion", new double[]{1, 1.5, 0.5, 0.5, 1}, JOBS[0], TEST_VALUES)
    };
    public static final GameCharacter[] ALL_GAME_CHARACTERS = {
            PLAYABLE_GAME_CHARACTERS[0],
            PLAYABLE_GAME_CHARACTERS[1]
    };

    private PlayableCharacter player;
    private static Calendar calendar = Calendar.getInstance();
    static {
        calendar.set(2025, Calendar.MARCH, 17);
    }
    private static Date currentDate = calendar.getTime();    private static int daysSurvived = 0;


    public Game(PlayableCharacter p) {
        player = p;
    }
    public void runGame() {
        for (int i = 0; i < ALL_GAME_CHARACTERS.length; i++) {
            ALL_GAME_CHARACTERS[i].reset();
        }
        boolean tutorial=true;
        //ask tutorial
        if(tutorial) {

        }
        incrementDay();
        while (!player.isDead()) {
            //display day

            //end day
            incrementDay();
        }
    }
    public void tutorial() {

    }
    public void incrementDay(int days) {
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, days);
    }
    public void incrementDay() {
        incrementDay(1);
    }

    public static Date getCurrentDate() {
        return currentDate;
    }
    public static int getDaysSurvived() {
        return daysSurvived;
    }
}
