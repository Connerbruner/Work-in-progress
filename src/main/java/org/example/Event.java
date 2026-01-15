package org.example;

public class Event {
    private EventCheck check;
    private EventVoid run;
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

}
interface EventVoid {
    boolean run(GameCharacter c);
}
interface EventCheck {
    boolean run(GameCharacter c);
}
