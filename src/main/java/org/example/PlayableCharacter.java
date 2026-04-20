package org.example;

import org.example.Cards.Card;
import org.example.Cards.UnoGame;
import org.example.Timeline.Event;

import java.util.ArrayList;
import java.util.LinkedList;

public class PlayableCharacter extends GameCharacter {
    private Job job;
    private boolean isPlayable;
    private LinkedList<Event> staringEvents = new LinkedList<>();
    private LinkedList<Event> eventsToCheck = new LinkedList<>();

    public PlayableCharacter(String n, double[] s, Job j, double[] aiWeights,ArrayList<Event> sE) {
        this(n,s,j,0,aiWeights);
        staringEvents.addAll(sE);
    }
    public PlayableCharacter(String n, double[] s, Job j, double[] aiWeights,Event sE) {
        this(n,s,j,0,aiWeights, sE);
    }
    public PlayableCharacter(String n, double[] s, Job j,int h, double[] aiWeights,Event sE) {
        this(n,s,j,h,aiWeights);
        staringEvents.add(sE);
        eventsToCheck.add(sE);
    }
    public PlayableCharacter(String n, double[] s, Job j, double[] aiWeights) {
        this(n,s,j,0,aiWeights);
    }


    public PlayableCharacter(String n, double[] s, Job j, int h, double[] aiWeights) {
        super(n, h,s, aiWeights);
        job = j;
        isPlayable=true;
    }
    public void addEventToWatch(Event e) {
        eventsToCheck.add(e);
    }
    public Event checkEvents() {
        for (int i = 0; i < eventsToCheck.size(); i++) {
            if(eventsToCheck.peek().checkRun(this)) {
                return eventsToCheck.poll();
            } else {
                eventsToCheck.add(eventsToCheck.poll());
            }
        }
        return null;
    }

    @Override
    public void reset() {
        super.reset();
        eventsToCheck = staringEvents;
    }

    @Override
    public void choseCard(Card topCard) {

    }

    public void runJob() {
        job.run();
    }
    public void setPlayable(boolean playable) {
        isPlayable = playable;
    }

    public boolean isPlayable() {
        return isPlayable;
    }
}
