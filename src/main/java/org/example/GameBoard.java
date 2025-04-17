package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class GameBoard {
    private static final JLabel LABEL = new JLabel();
    private static final JLabel TEXT1 = new JLabel();
    private static final JLabel TEXT2 = new JLabel();
    private static final JTextField INPUT = new JTextField(10);
    private static final JFrame SYSTEM = new JFrame("");
    static final Charatcher[] ALL_CHARACTERS = {};
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    DisplayMode dm = gd.getDisplayMode();


    public GameBoard() {

        //text 1 2 setup

        SYSTEM.add(TEXT2);
        TEXT2.setFont(new Font("Lato", Font.BOLD, 20));
        TEXT2.setHorizontalAlignment(0);
        TEXT2.setBounds(0, 30, 1920, 20);

        SYSTEM.add(TEXT1);
        TEXT1.setFont(new Font("Lato", Font.BOLD, 20));
        TEXT1.setHorizontalAlignment(0);
        TEXT1.setBounds(0, 30, 1920, 20);


        //final setup
        INPUT.setEditable(false);
        LABEL.setIcon(createImageIcon(dm.getWidth(),dm.getHeight()));
        SYSTEM.add(INPUT);
        SYSTEM.add(LABEL);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);

        SYSTEM.pack();
    }


    public static void sPrintln(String str) {
        INPUT.setText(" ");
        INPUT.setEditable(true);
        INPUT.requestFocus();
        TEXT1.setText(str);
        while (INPUT.getText().equals(" ")) ;
        INPUT.setEditable(false);
        SYSTEM.requestFocusInWindow();
    }




    public static int formatInput(String str) {
        if (str.length() > 1) {
            String input = str.charAt(str.length() - 1) + "";
            String confirm = str.charAt(str.length() - 2) + "";
            if (strIsInt(confirm) && input.equals(confirm)) {
                return Integer.parseInt(confirm);
            }
        }
        return 0;
    }

    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void gameLoop() {

    }
    public static ImageIcon createImageIcon(int width, int height) {
        // Create a buffered image with specified width and height
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, width, height);
        g.dispose();
        return new ImageIcon(image);
    }




}
