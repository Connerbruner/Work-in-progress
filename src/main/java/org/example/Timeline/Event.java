package org.example.Timeline;

import org.example.GameCharacter;

public class Event {
    private EventCheck check;
    private EventVoid run;
    private int eventTicks=0;
    public Event(EventCheck c,EventVoid r) {
        check=c;
        run=r;
    }
    public Event(EventVoid r) {
        check=(GameCharacter c)->true;
        run=r;
    }
    public boolean checkRun(GameCharacter c) {
        if(check.run(c)) {
            return run.run(c);
        }
        return false;
    }

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
interface EventVoid {
    boolean run(GameCharacter c);
}
interface EventCheck {
    boolean run(GameCharacter c);
}
