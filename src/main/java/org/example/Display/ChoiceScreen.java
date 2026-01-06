package org.example.Display;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class ChoiceScreen extends TextScreen {
    private final ArrayList<JButton> buttons = new ArrayList<>();
    public ChoiceScreen(int height, int width) {
        super(height, width);
    }


    public Component addButton(JButton button) {
        buttons.add(button);
        return super.add(button);
    }
    public int buttonPressedIndex() {
        for(int i=0; i<buttons.size(); i++) {
            if(buttons.get(i).getModel().isPressed()) {
                return i;
            }
        }
        return -1;
    }

}
