package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class  Screen extends JFrame {
    private JLabel background = new JLabel();
    private static boolean mouseReleased = false;

    public Screen(int height,int width) {
        super();
        setUndecorated(true);
        setSize(width,height);
        FrameDragListener frameDragListener = new FrameDragListener(this);
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);
        setLocationRelativeTo(null);

        background.setBounds(0, 0, width, height);
        background.setOpaque(false);
        add(background);
    }

    public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
            mouseReleased=true;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
    public void setBackground(Boolean isPhoto, String name) {
        background.setVisible(true);
        if (isPhoto) {
            setBackground("src/main/java/org/example/Background/Photos/" + name + ".png");
        } else {
            setBackground("src/main/java/org/example/Background/Handrawn/" + name + ".png");
        }
    }
    public void setBackground(Character character, String expression) {
        setBackground(character.getPath()+"/" + expression + ".png");
    }
    public void setBackground(String path) {
        setVisible(true);
        background.setIcon(Main.scaleImage(getWidth(),getHeight(),new ImageIcon(path)));
    }

    public static void waitTillClick() {
        mouseReleased = false;
        while (!mouseReleased) {
            Main.wait(1);
        }
    }
    public void setLocaction(int x,int y) {
        setBounds(x,y,getWidth(),getHeight());
    }
}
