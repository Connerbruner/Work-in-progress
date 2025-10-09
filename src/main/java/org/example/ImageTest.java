package org.example;

import javax.swing.*;
import java.awt.*;

public class ImageTest extends ChoiceScreen {
    private JButton[] buttons;

    public ImageTest() {
        // Call the superclass constructor to initialize the JFrame.
        super(800, 500, 1, 1);

        // Set the layout for this Phone class (which is a JFrame).
        setLayout(new BorderLayout());

        // Create the buttons only once.
        int appCount = 15;
        int keyCount = 30;
        buttons = new JButton[appCount + keyCount];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton("Btn " + (i + 1));
        }

        // Panel for the "apps" grid
        JPanel appsPanel = new JPanel(new GridLayout(5, 3, 20, 20));
        for (int i = 0; i < appCount; i++) {
            appsPanel.add(buttons[i]);
        }
        appsPanel.setBorder(BorderFactory.createTitledBorder("Apps"));

        // Panel for the "keyboard" grid
        JPanel keyboardPanel = new JPanel(new GridLayout(10, 3, 20, 20));
        for (int i = appCount; i < appCount + keyCount; i++) {
            keyboardPanel.add(buttons[i]);
        }
        keyboardPanel.setBorder(BorderFactory.createTitledBorder("Keyboard"));

        // Create a main content panel to hold the apps and keyboard vertically.
        // BoxLayout is an excellent choice for stacking components.
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));

        // Add the sub-panels to the main content panel.
        mainContentPanel.add(appsPanel);
        mainContentPanel.add(keyboardPanel);

        // Add the single main content panel to the CENTER of the JFrame's BorderLayout.
        add(mainContentPanel, BorderLayout.CENTER);

        // Add some padding to the main content panel so it doesn't touch the edges.
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Finalize the frame settings
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
