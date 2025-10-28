package org.example;

import javax.swing.*;
import java.util.ArrayList;

public class SceneScreen extends Screen {
    private ArrayList<Character> characters = new ArrayList<>();
    private TextScreen textScreen;
    public SceneScreen() {
        super(800, 1066);
    }
    public void add(Character c) {
        characters.add(c);
        add(c.getLabel(), JLayeredPane.MODAL_LAYER);
        c.getLabel().setBounds(0,0,600,800);
        c.setVisible(true);
    }
    public void remove(Character c) {
        characters.remove(c);
        remove(c.getLabel());
        c.setVisible(false);
    }

    public Character getCharacter(int i) {
        return characters.get(i);
    }

    public TextScreen getTextScreen() {
        return textScreen;
    }

    public void setPositionCharacter(int i, int x) {
        characters.get(i).getLabel().setBounds(x,0,421,600);
    }
    public void setupScene() {

        for(int i=0; i<characters.size(); i++) {
            Character character = characters.get(i);
            int width = character.getScreen().getWidth();
            character.getScreen().setLocaction(((i%2)*(getWidth()))+getX()-width/2,getHeight()+10+(width/2*i)+10);
            character.changeExpression("default");
        }


        textScreen = new TextScreen(150,600);
        textScreen.setVisible(true);
        textScreen.sPrintln(this,"THIS IS A TEST");
        textScreen.sPrintln(this,0,"THis is more of a test");
        textScreen.sPrintln(this,1,"THis is more of a test");




    }
}
