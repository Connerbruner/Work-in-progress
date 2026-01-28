package org.example.Timeline;

import org.example.GameCharacter;
import org.example.PlayableCharacter;

public class Change {
    private PlayableCharacter playableCharacter;
    private String name;
    private int choices;
    private int day;
    private boolean effective=true;
    public Change(PlayableCharacter p,String n,int c,int d) {
        playableCharacter=p;
        name=n;
        choices=c;
        day=d;
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
