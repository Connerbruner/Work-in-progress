package org.example;

import org.example.Timeline.Event;

public class Storyline {
    private Event[][] events;
    private GameCharacter[] gameCharacters;
    private int isBad;
    private int progress;
    public Storyline(Event[][] e, GameCharacter[] c) {
        events=e;
        gameCharacters =c;
        isBad=0;
        progress=0;
    }
    public void reset() {
        isBad=0;
        progress=0;
    }
    public void checkRun(GameCharacter c) {
        Event e = events[isBad][progress];
        if(e.checkRun(c)) {
            progress++;
        }
    }
}
