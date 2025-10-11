package org.example;

import javax.swing.*;
import java.awt.*;

public class Phone extends ChoiceScreen {
    JPanel mainContentPanel = new JPanel();

    JPanel keyboard = new JPanel(new GridLayout(3, 10, 5, 5));
    JPanel keyboardWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel apps = new JPanel(new GridLayout(3, 5, 20, 20));
    JPanel appsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel topRow = new JPanel(new GridLayout(1, 5, 20, 20));
    JPanel topRowWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JPanel bottomRow = new JPanel(new GridLayout(1, 5, 20, 20));
    JPanel bottomRowWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    Dimension keySize = new Dimension(45, 45);
    Dimension appSize = new Dimension(72, 72);



    int appCount = 15;
    int keyCount = 30;
    int extraRows = 5;
    JButton[] buttons = new JButton[appCount + keyCount + (extraRows * 2)];

    public Phone() {
        super(800, 500, 1, 1);
        setLayout(new BorderLayout());

        for (int i = 0; i < appCount; i++) {
            JButton button = new SquareButton("",appSize);
            buttons[i] = button;
            apps.add(buttons[i]);

        }
        appsWrapper.add(apps);
        appsWrapper.setBorder(BorderFactory.createEmptyBorder(100, 30, 10, 30));


        for (int i = 0; i < keyCount; i++) {
            JButton button = new SquareButton("",keySize);
            buttons[i+appCount+extraRows] = button;
            keyboard.add(buttons[i]);
        }
        keyboardWrapper.add(keyboard);
        keyboardWrapper.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));



        mainContentPanel.add(appsWrapper);
        mainContentPanel.add(topRowWrapper);
        mainContentPanel.add(keyboardWrapper);
        mainContentPanel.add(bottomRowWrapper);


        add(mainContentPanel, BorderLayout.CENTER);



    }
    public ImageTest() {
        super(800, 500, 1, 1);
        setLayout(new BorderLayout());

        // Apps panel (grid)
        for (int i = 0; i < appCount; i++) {
            SquareButton squareButton = new SquareButton("",largeSize);
            appsPanel.add(squareButton);
            buttons[i] = squareButton;
        }

        appsWrapper.add(appsPanel);
        appsWrapper.setBorder(BorderFactory.createEmptyBorder(100, 30, 10, 30));

        // Keyboard panel (grid)
        for (int i = 0; i < keyCount; i++) {
            SquareButton squareButton = new SquareButton("", squareSize);
            keyboardPanel.add(squareButton);
            buttons[i+appCount] = squareButton;
        }


        keyboardWrapper.add(keyboardPanel);
        keyboardWrapper.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));

        // Main content panel
        mainContentPanel.add(appsWrapper);
        mainContentPanel.add(keyboardWrapper);

        add(mainContentPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setVisible(true);
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
