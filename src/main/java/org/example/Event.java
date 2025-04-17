package org.example;

public class Event {
    private EventCheck check;
    private EventVoid run;
    public Event(EventCheck c,EventVoid r) {
        check=c;
        run=r;
    }
    public Event(EventVoid r) {
        check=(Charatcher c)->true;
        run=r;
    }
    public boolean checkRun(Charatcher c) {
        if(check.run(c)) {
            return run.run(c);
        }
        return false;
    }

}
interface EventVoid {
    boolean run(Charatcher c);
}
interface EventCheck {
    boolean run(Charatcher c);
}
