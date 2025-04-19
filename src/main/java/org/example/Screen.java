package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Screen {
    private static final JLabel LABEL = new JLabel();
    private static final JLabel TEXT1 = new JLabel();
    private static final JLabel TEXT2 = new JLabel();
    private static final JTextField INPUT = new JTextField(10);
    private static final JFrame SYSTEM = new JFrame("");
    private static final ArrayList<JLabel> SCREEN_ELEMENTS = new ArrayList<>();
    static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    static final GraphicsDevice gd = ge.getDefaultScreenDevice();
    static final DisplayMode dm = gd.getDisplayMode();
    static final int SCREEN_HEIGHT = dm.getHeight();
    static final int SCREEN_WIDTH = dm.getHeight();


    public static void init() {

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
        LABEL.setIcon(createBackground(dm.getWidth(),dm.getHeight()));
        SYSTEM.add(INPUT);
        SYSTEM.add(LABEL);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SYSTEM.setVisible(true);
        SYSTEM.pack();
    }
    public static void addElement(JLabel label) {
        SCREEN_ELEMENTS.add(label);
        SYSTEM.add(label);
    }
    public static void removeElement(JLabel label) {
        SCREEN_ELEMENTS.remove(label);
        SYSTEM.remove(label);
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
    public static void setBackground(String path) {

    }

    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static ImageIcon createBackground(int width, int height) {
        // Create a buffered image with specified width and height
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.GRAY);
        int squareWidth = (height*4)/3;
        g.fillRect((width - squareWidth) / 2, 0, squareWidth, height);
        g.dispose();
        return new ImageIcon(image);
    }
    public static ImageIcon scaleImage(int w,int h,ImageIcon i) {
        return new ImageIcon( i.getImage().getScaledInstance(w,h,Image.SCALE_SMOOTH));
    }





}