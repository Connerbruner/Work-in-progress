package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ChoiceScreen extends TextScreen {
    private final ArrayList<JButton> buttons = new ArrayList<>();
    public ChoiceScreen(int height, int width, int linewidth, int lineCount) {
        super(height, width, linewidth, lineCount);
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
