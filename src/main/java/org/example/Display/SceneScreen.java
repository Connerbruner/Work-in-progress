package org.example.Display;

import org.example.GameCharacter;

import javax.swing.*;
import java.util.ArrayList;

public class SceneScreen extends Screen {
    private ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
    private TextScreen textScreen;

    public SceneScreen() {
        super(800, 1066);
    }

    public void add(GameCharacter c) {
        gameCharacters.add(c);
        layeredPane.add(c.getLabel(), JLayeredPane.PALETTE_LAYER);

        setPositionCharacter(c,   600 * (gameCharacters.size() % 2));
        c.setFlipped(gameCharacters.size() % 2 == 1);
        c.setVisible(true);
    }

    public void remove(GameCharacter c) {
        gameCharacters.remove(c);
        layeredPane.remove(c.getLabel());
        c.setVisible(false);
    }

    public GameCharacter getCharacter(int i) {
        return gameCharacters.get(i);
    }

    public TextScreen getTextScreen() {
        return textScreen;
    }

    public void setPositionCharacter(int i, int x) {
        setPositionCharacter(gameCharacters.get(i), x);
    }

    public void setPositionCharacter(GameCharacter gameCharacter, int x) {
        gameCharacter.getLabel().setBounds(x, 170 + gameCharacter.getHeightShort(), 421, 600);
    }

    public void setupScene() {
        for (int i = 0; i < gameCharacters.size(); i++) {
            GameCharacter gameCharacter = gameCharacters.get(i);
            int width = gameCharacter.getScreen().getWidth();
            gameCharacter.getScreen().setLocation(((i % 2) * (getWidth())) + getX() - width / 2, getHeight() + 10 + (width / 2 * i) + 10);
            gameCharacter.changeExpression("default");
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
