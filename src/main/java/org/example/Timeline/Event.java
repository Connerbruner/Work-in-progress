package org.example.Timeline;

import org.example.GameCharacter;

public abstract class Event {
    private int eventTicks=0;
    public abstract boolean checkRun(GameCharacter c);
    public abstract void runEvent(GameCharacter c);

    public void setEventTicks(int eventTicks) {
        this.eventTicks = eventTicks;
    }
    public void increaseTicks(int amount) {
        this.eventTicks += amount;
    }
    public void decreaseTicks(int amount) {
        this.eventTicks -= amount;
    }
    public int getEventTicks() {
        return eventTicks;
    }
}
