//package org.example;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class Screen {
//    private static final JLabel BORDER_BACKGROUND = new JLabel();
//    private static final JLabel BACKGROUND = new JLabel();
//    private static final JLabel TITLE_BANNER = new JLabel();
//    private static final JButton[] MENU_BUTTONS = {new JButton("Play"),new JButton("Setup"),new JButton("Special Features"),new JButton("Help")};
//    private static final JLabel[] TEXTS = new JLabel[]{new JLabel(),new JLabel(), new JLabel(),new JLabel()};
//    private static final JFrame SYSTEM = new JFrame("");
//    private static final ArrayList<JButton> CHOICES = new ArrayList<>();
//    private static final ArrayList<Component> SCREEN_ELEMENTS = new ArrayList<>();
//
//
//
//    public static void init() {
//        SYSTEM.setLayout(null);
//        SYSTEM.setUndecorated(true);
//        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        // Add TEXT1
//        for(JLabel jLabel : TEXTS) {
//            jLabel.setFont(VCR_FONT);
//            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            jLabel.setBounds(0, (int) (SCREEN_HEIGHT * 0.9), USABLE_WIDTH, (int) (VCR_FONT.getSize() * 1.1));
//            jLabel.setOpaque(true); // Make JLabel background visible
//            jLabel.setBackground(Color.BLACK); // Background color
//            jLabel.setForeground(Color.WHITE); // Text color
//            jLabel.setVisible(false);
//            SYSTEM.add(jLabel);
//        }
//
//        for (int i = 0; i < MENU_BUTTONS.length; i++) {
//            JButton button = MENU_BUTTONS[i];
//            button.setFont(VCR_FONT);
//            button.setBounds(((USABLE_WIDTH/4)*(i+1))-button.getPreferredSize().width/2,(5*SCREEN_HEIGHT)/6,button.getPreferredSize().width,button.getPreferredSize().height);
//            button.setVisible(false);
//            button.setBorderPainted(false);
//            button.setFocusPainted(false);
//            button.setBackground(Color.LIGHT_GRAY);
//            button.addMouseListener(new MouseAdapter() {
//                @Override
//                public void mouseEntered(MouseEvent e) {
//                    button.setBackground(Color.BLACK);
//                    button.setForeground(Color.WHITE);
//                }
//                @Override
//                public void mouseExited(MouseEvent e) {
//                    button.setBackground(Color.LIGHT_GRAY);
//                    button.setBorderPainted(false);
//                    button.setFocusPainted(false);
//                    button.setForeground(Color.BLACK);
//                    SYSTEM.getContentPane().setComponentZOrder(button, 0);
//
//                }
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                    List buttons = Arrays.asList(MENU_BUTTONS);
//                    menuIndex = buttons.indexOf(button);
//                }
//            });
//            SYSTEM.add(button);
//            SYSTEM.getContentPane().setComponentZOrder(button, 0);
//
//        }
//
//        BACKGROUND.setBounds((SCREEN_WIDTH - USABLE_WIDTH) / 2, 0, USABLE_WIDTH, SCREEN_HEIGHT);
//        BACKGROUND.setVisible(false);
//        SYSTEM.add(BACKGROUND);
//
//        TITLE_BANNER.setIcon(scaleImage(USABLE_WIDTH,SCREEN_HEIGHT,new ImageIcon("src/main/java/org/example/Ui/title screen.png")));
//        TITLE_BANNER.setBounds((SCREEN_WIDTH-USABLE_WIDTH)/2,100,USABLE_WIDTH,SCREEN_HEIGHT);
//        TITLE_BANNER.setVisible(false);
//        SYSTEM.add(TITLE_BANNER);
//        SYSTEM.getContentPane().setComponentZOrder(TITLE_BANNER, 1);
//
//
//
//
//        BORDER_BACKGROUND.setIcon(createBackground(PRIMARY_MONITOR_DM.getWidth(), PRIMARY_MONITOR_DM.getHeight()));
//        BORDER_BACKGROUND.setBounds(0, 0, PRIMARY_MONITOR_DM.getWidth(), PRIMARY_MONITOR_DM.getHeight()); // Make sure it covers the full screen
//        SYSTEM.add(BORDER_BACKGROUND);
//
//        PRIMARY_MONITOR.setFullScreenWindow(SYSTEM);
//        SYSTEM.setVisible(true); // Make the frame visible AFTER everything is configured
//    }
//    public static void titleScreen() {
//        boolean atTitleScreen = true;
//        TITLE_BANNER.setVisible(true);
//        for(JButton button : MENU_BUTTONS) {
//            button.setVisible(true);
//            SYSTEM.getContentPane().setComponentZOrder(button, 0);
//
//        }
//        Thread thread = new Thread(() -> {
//            File[] backgrounds = new File("src/main/java/org/example/Background/Photos").listFiles();
//            while (menuIndex==-1) {
//                String name = backgrounds[Main.random(backgrounds)].getName();
//                name = name.substring(0,name.length()-4);
//                setBackground(true, name);
//                Main.wait(Main.random(3000,10000));
//            }
//        });
//        thread.start();
//        while (menuIndex==-1) {
//            Main.wait(1);
//        }
//        System.out.println(menuIndex);
//        for(JButton button : MENU_BUTTONS) {
//            button.setVisible(false);
//        }
//        TITLE_BANNER.setVisible(false);
//
//
//
//    }
//
//}
