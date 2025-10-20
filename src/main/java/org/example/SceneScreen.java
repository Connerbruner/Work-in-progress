package org.example;

import java.util.ArrayList;

public class SceneScreen extends Screen {
    ArrayList<Character> characters = new ArrayList<>();
    public SceneScreen() {
        super(600, 800);
    }
    public void add(Character c) {
        characters.add(c);
        add(c.getLabel());
        c.setVisible(true);
    }
    public void remove(Character c) {
        characters.remove(c);
        remove(c.getLabel());
        c.setVisible(false);
    }
    public void setPosition(int i, int y) {
        characters.get(i).getLabel().setBounds(0,y,421,600);
    }
    public void setupScene() {
        setLocaction(720,0);
        for(int i=0; i<characters.size(); i++) {
            characters.get(i).getScreen().setLocaction(650+((i%2)*650),625+(130*i));
        }

    }
}
