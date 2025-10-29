package org.example;

import javax.swing.*;

public class ImageTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fixed Position Frame");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Remove window decorations (title bar, borders)
        frame.setUndecorated(true);

        // Set fixed location and prevent moving
        frame.setLocation(100, 100);
        frame.setAlwaysOnTop(true); // Optional: keep it above other windows

        frame.setVisible(true);
    }
}
