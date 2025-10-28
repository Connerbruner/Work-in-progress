package org.example;

public class Storyline {
    private Event[][] events;
    private Character[] characters;
    private int isBad;
    private int progress;
    public Storyline(Event[][] e, Character[] c) {
        events=e;
        characters=c;
        isBad=0;
        progress=0;
    }
    public void reset() {
        isBad=0;
        progress=0;
    }
    public void checkRun(Character c) {
        Event e = events[isBad][progress];
        if(e.checkRun(c)) {
            progress++;
        }
    }
}
