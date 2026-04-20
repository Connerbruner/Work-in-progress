package org.example.Timeline;

import org.example.GameCharacter;

import java.util.ArrayList;

public abstract class StoryEvent extends Event{
    GameCharacter arcCharacter;
    StoryEvent[] nextEvents;
    boolean locked;
    public StoryEvent(GameCharacter c,boolean l) {
        arcCharacter=c;
        locked=l;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void nextEvents(boolean[] unlocks) {
        for (int i = 0; i < nextEvents.length; i++) {
            nextEvents[i].setLocked(unlocks[i]);
        }
    }
    public void linkUp(StoryEvent[] storyEvents) {
        nextEvents=storyEvents;
    }


}
