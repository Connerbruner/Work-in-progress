package org.example;

public class Event {
    private EventCheck check;
    private EventVoid run;
    public Event(EventCheck c,EventVoid r) {
        check=c;
        run=r;
    }
    public Event(EventVoid r) {
        check=(Character c)->true;
        run=r;
    }
    public boolean checkRun(Character c) {
        if(check.run(c)) {
            return run.run(c);
        }
        return false;
    }

}
interface EventVoid {
    boolean run(Character c);
}
interface EventCheck {
    boolean run(Character c);
}
