package org.example.Timeline;

import org.example.PlayableCharacter;

public class Change {
    private PlayableCharacter playableCharacter;
    private String name;
    private int choices;
    private int day;
    private boolean effective=true;
    private boolean unStoppable=false;
    public Change(PlayableCharacter p,String n,int c,int d) {
        playableCharacter=p;
        name=n;
        choices=c;
        day=d;
    }
    public Change(PlayableCharacter p,String n,int c,int d,boolean un) {
        playableCharacter=p;
        name=n;
        choices=c;
        day=d;
        unStoppable=un;
    }

    public boolean isUnStoppable() {
        return unStoppable;
    }

    public boolean isEffective() {
        return effective;
    }

    public void deactivate() {
        effective=false;
    }
    public void activate(int d, int c) {
        day=d;
        effective=true;
        choices=c;
    }

    public String getName() {
        return name;
    }

    public int getChoices() {
        return choices;
    }

    public int getDay() {
        return day;
    }
}
