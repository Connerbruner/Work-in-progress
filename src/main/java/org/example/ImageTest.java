package org.example;

import javax.swing.*;
import java.awt.*;

public class ImageTest extends ChoiceScreen {
    int appCount = 15;
    int keyCount = 30;

    private JButton[] buttons = new JButton[appCount+keyCount];
    JPanel appsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel keyboardWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel mainContentPanel = new JPanel();
    JPanel keyboardPanel = new JPanel(new GridLayout(3, 10, 5, 5));
    JPanel appsPanel = new JPanel(new GridLayout(3, 5, 20, 20));

    Dimension squareSize = new Dimension(45, 45);
    Dimension largeSize = new Dimension(72, 72);



    public static void main(String[] args) {
        new ImageTest();
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

    // Custom JButton that enforces square size
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
