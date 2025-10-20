package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Character {
    private String name;
    private String path;
    private String expression;
    private Screen screen;
    private JLabel label;
    //awakeness fitness sanity self-esteem Illness
    static final Character[] ALL_CHARACTERS = {
            PlayableCharacter.PLAYABLE_CHARACTERS[0],
            PlayableCharacter.PLAYABLE_CHARACTERS[1]

    };
    public Character(String n, String folder) {
        name = n;
        path = folder;
        screen = new Screen(225,250);
        screen.setLayout(null);
        screen.setLocationRelativeTo(null);
        label = new JLabel();

    }
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Screen getScreen() {
        return screen;
    }


    public ImageIcon getExpression(String expression) {
        return new ImageIcon(path+"/"+expression+".png");
    }
    public boolean hasExpression(String expression) {
        return  (new File(path+"/"+expression+".png").exists());
    }

    public String getCurrentExpression() {
        return expression;
    }

    public void changeExpression(String expression) {
        this.expression = expression;
        screen.setBackground(this,expression);
        label.setIcon(new ImageIcon( path+"/"+expression+".png"));
        screen.setVisible(true);
    }
    public void setVisible(boolean visible) {
        screen.setVisible(visible);
        label.setVisible(visible);
    }
    public void setVisible(boolean s,boolean l) {
        screen.setVisible(s);
        label.setVisible(l);
    }

    public JLabel getLabel() {
        return label;
    }
}
