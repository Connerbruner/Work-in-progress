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
        layeredPane.add(c.getLabel(), JLayeredPane.MODAL_LAYER);

        setPositionCharacter(c, 0 + 600 * (characters.size() % 2));
        c.setFlipped(characters.size() % 2 == 1);
        c.setVisible(true);
    }

    public void remove(Character c) {
        characters.remove(c);
        layeredPane.remove(c.getLabel());
        c.setVisible(false);
    }

    public Character getCharacter(int i) {
        return characters.get(i);
    }

    public TextScreen getTextScreen() {
        return textScreen;
    }

    public void setPositionCharacter(int i, int x) {
        setPositionCharacter(characters.get(i), x);
    }

    public void setPositionCharacter(Character character, int x) {
        character.getLabel().setBounds(x, 145 + character.getHeightShort(), 421, 600);
    }

    public void setupScene() {
        for (int i = 0; i < characters.size(); i++) {
            Character character = characters.get(i);
            int width = character.getScreen().getWidth();
            character.getScreen().setLocaction(((i % 2) * (getWidth())) + getX() - width / 2, getHeight() + 10 + (width / 2 * i) + 10);
            character.changeExpression("default");
        }

        textScreen = new TextScreen(150, 600);
        textScreen.setVisible(true);
    }

    public void sPrintln(int i, String str) {
        textScreen.sPrintln(this, i, str);
    }

    public void sPrintln(String str) {
        textScreen.sPrintln(this, str);
    }
}
