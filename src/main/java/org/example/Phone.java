package org.example;

import javax.swing.*;
import java.awt.*;

public class Phone extends ChoiceScreen {
    JPanel mainContentPanel = new JPanel();

    JPanel keyboard = new JPanel(new GridLayout(3, 10, 5, 5));
    JPanel keyboardWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel apps = new JPanel(new GridLayout(2, 5, 20, 20));
    JPanel appsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel topRow = new JPanel(new GridLayout(1, 5, 10, 5));
    JPanel topRowWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel bottomRow = new JPanel(new GridLayout(1, 5, 5, 0));
    JPanel bottomRowWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    Dimension keySize = new Dimension(42, 42);
    Dimension appSize = new Dimension(72, 72);
    Dimension wideSize = new Dimension(90,45);



    int appCount = 10;
    int keyCount = 30;
    int extraRows = 5;
    JButton[] buttons = new JButton[appCount + keyCount + (extraRows * 2)];

    public Phone() {
        super(750, 500, 1, 1);
        setLayout(new BorderLayout());

        // App panel (grid)
        for (int i = 0; i < appCount; i++) {
            SizedButton sizedButton = new SizedButton("",appSize);
            apps.add(sizedButton);
            buttons[i] = sizedButton;
        }
        appsWrapper.add(apps);
        appsWrapper.setBorder(BorderFactory.createEmptyBorder(150, 30, 10, 30));

        for (int i = 0; i < extraRows; i++) {
            SizedButton sizedButton = new SizedButton("",appSize);
            topRow.add(sizedButton);
            buttons[i+appCount] = sizedButton;
        }
        topRowWrapper.add(topRow);
        topRowWrapper.setBorder(BorderFactory.createEmptyBorder(50, 30, 0, 30));

        // Keyboard panel (grid)
        for (int i = 0; i < keyCount; i++) {
            SizedButton sizedButton = new SizedButton("", keySize);
            keyboard.add(sizedButton);
            buttons[i+appCount+extraRows] = sizedButton;
        }
        keyboardWrapper.add(keyboard);

        for (int i = 0; i < extraRows; i++) {
            SizedButton squareButton = new SizedButton("",wideSize);
            bottomRow.add(squareButton);
            buttons[i+extraRows+appCount+keyCount] = squareButton;
        }
        bottomRowWrapper.add(bottomRow);


        // Main content panel
        mainContentPanel.add(appsWrapper);
        mainContentPanel.add(topRowWrapper);
        mainContentPanel.add(keyboardWrapper);
        mainContentPanel.add(bottomRowWrapper);

        add(mainContentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
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
