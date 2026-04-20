package org.example.Timeline;

import java.util.ArrayList;
import java.util.Arrays;

public class Cannon {
    private Change[] defaultCannon;
    private ArrayList<Change> cannon = new ArrayList<>();
    private ArrayList<Change> past = new ArrayList<>();
    public Cannon(Change[] c) {
        defaultCannon=c;
        cannon.addAll(Arrays.asList(c));
    }
    public void addItem(Change change) {
        cannon.add(change);
    }
    public void addPastItem(Change change) {
        past.add(change);
    }
    public void newCannon() {
        cannon.clear();
        past.clear();
    }
    public void resetCompletely() {
        cannon.clear();
        past.clear();
        cannon.addAll(Arrays.asList(defaultCannon));
    }
    public void restart() {
        cannon.addAll(past);
        past.clear();
    }
}