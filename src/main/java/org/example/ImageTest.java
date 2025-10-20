package org.example;

import javax.swing.*;
import java.awt.*;

public class ImageTest {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Image Test");
            frame.setUndecorated(true); // Required for transparency
            frame.setBackground(new Color(0, 0, 0, 0)); // Transparent background
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);

            JLabel imageLabel = new JLabel();
            imageLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            imageLabel.setOpaque(false); // Allow transparency

            String imagePath = Character.ALL_CHARACTERS[0].getPath()+"/test.png"; // Change as needed
            ImageIcon icon = new ImageIcon(imagePath);

            if (icon.getIconWidth() == -1) {
                System.out.println("Image not found or failed to load: " + imagePath);
            } else {
                ImageIcon scaled = Main.scaleImage(frame.getWidth(), frame.getHeight(), icon);
                imageLabel.setIcon(scaled);
            }

            frame.add(imageLabel);
            frame.setVisible(true);
        });
    }


}
