package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImageTest {
    public static void main(String[] args) {
        // Example list of button texts
        String[] buttonLabels = {"Option 1", "Option 2", "Option 3", "Option 4","Option 5"};
        int clickedIndex = getButtonIndex(buttonLabels);
        System.out.println("The clicked button index is: " + clickedIndex);
    }

    public static int getButtonIndex(String[] buttonLabels) {
        JFrame frame = new JFrame("Select an Option");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600); // Adjust frame size as needed

        // Create a layout with 1 column and vertical spacing
        frame.setLayout(new GridLayout(0, 1, 0, 10)); // 0 horizontal gap, 10 vertical gap

        final int[] clickedIndex = {-1}; // To store the index of the clicked button

        // Create and add buttons with fixed size to the frame
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);
            button.setPreferredSize(new Dimension(250, 50)); // Set constant width and height for buttons

            // Add an ActionListener to capture button clicks
            int index = i; // Capture the current index
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clickedIndex[0] = index;
                    System.out.println("Button " + index + " (" + buttonLabels[index] + ") clicked!");
                    synchronized (frame) {
                        frame.notify(); // Notify the waiting thread
                    }
                }
            });

            frame.add(button);
        }

        frame.pack(); // Adjusts the frame size to fit the components
        frame.setVisible(true);

        // Wait for a button to be clicked
        synchronized (frame) {
            try {
                while (clickedIndex[0] == -1) {
                    frame.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        frame.dispose();
        return clickedIndex[0];
    }
}
