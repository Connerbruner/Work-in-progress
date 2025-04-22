package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageTest extends JFrame {
    public ImageTest() {
        setTitle("Hover Button Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new FlowLayout());

        JButton button = new JButton("Hover Me");
        button.setOpaque(true);
        button.setBorderPainted(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.BLUE);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(null);
                button.setForeground(Color.BLACK);
            }
        });

        add(button);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageTest::new);
    }
}