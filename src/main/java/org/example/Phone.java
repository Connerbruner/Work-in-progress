package org.example;

import javax.swing.*;
import java.awt.*;

public class Phone extends ChoiceScreen {
    JLabel keyboard = new JLabel();
    JLabel apps = new JLabel();
    JLabel topRow = new JLabel();
    JLabel bottomRow = new JLabel();

    int appCount = 15;
    int keyCount = 30;
    int extraRows = 5;
    JButton[] buttons = new JButton[appCount + keyCount + (extraRows * 2)];

    public Phone() {
        super(800, 500, 1, 1);
        setLayout(new BorderLayout());
        apps.setLayout(new GridLayout(5, 3, 20, 20));
        keyboard.setLayout(new GridLayout(10, 3, 20, 20));
         bottomRow.setLayout(new GridLayout(5, 1, 20, 20));

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
        }
        for (int i = 0; i < appCount; i++) {
            apps.add(buttons[i]);
        }
        for (int i = 0; i < extraRows; i++) {
            topRow.add(buttons[i]);
        }
        for (int i = 0; i < keyCount; i++) {
            keyboard.add(buttons[i]);
        }
        for (int i = 0; i < extraRows; i++) {
            bottomRow.add(buttons[i]);
        }
        apps.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        topRow.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        keyboard.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        bottomRow.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(apps, BorderLayout.NORTH);
        add(topRow, BorderLayout.AFTER_LAST_LINE);
        add(keyboard, BorderLayout.AFTER_LAST_LINE);
        add(bottomRow, BorderLayout.SOUTH);



    }
    public int getButtonPressedIndex() {
        for (int k = 0; k < buttons.length; k++) {
            if (buttons[k].getModel().isPressed()) {
                return k;
            }
        }
        return -1;
    }

    public int indexOfButton(JButton button) {

        for (int k = 0; k < buttons.length; k++) {
            if (buttons[k] == button) {
                return k;
            }
        }
        return -1;
    }

}
