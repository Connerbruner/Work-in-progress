package org.example.Display;

import org.example.GameCharacter;
import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Screen extends JFrame {
    private JLabel background = new JLabel();
    protected JLayeredPane layeredPane = new JLayeredPane();
    private static boolean mouseReleased = false;
    public static final ArrayList<Screen> SCREENS = new ArrayList<>();
    private static double scale = 1;
    public int baseHeight,baseWidth;

    public Screen(int height, int width) {
        this(height,width,true);
    }

    public Screen(int height, int width, boolean drag) {
        super();
        baseHeight=height;
        baseWidth=width;
        setSize(width, height);
        setAlwaysOnTop(true);
        setResizable(false);
        setLayout(null);

        layeredPane.setBounds(0, 0, width, height);
        layeredPane.setLayout(null);
        setContentPane(layeredPane);

        if (drag) {
            FrameDragListener frameDragListener = new FrameDragListener(this);
            addMouseListener(frameDragListener);
            addMouseMotionListener(frameDragListener);
        }
        background.setBounds(0, 0, width, height);
        layeredPane.add(background, JLayeredPane.DEFAULT_LAYER);
        SCREENS.add(this);

    }

    public static class FrameDragListener extends MouseAdapter {
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
            mouseReleased = true;
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

    public void setBackground(GameCharacter gameCharacter, String expression) {
        setBackground(gameCharacter.getPath() + "/" + expression + ".png");
    }

    public void setBackground(String path) {
        setVisible(true);
        background.setIcon(Main.scaleImage(getWidth(), getHeight(), new ImageIcon(path)));
    }

    public void setBackground(ImageIcon imageIcon) {
        setVisible(true);
        background.setIcon(Main.scaleImage(getWidth(), getHeight(), imageIcon));
    }

    public static void waitTillClick() {
        mouseReleased = false;
        while (!mouseReleased) {
            Main.wait(1);
        }
    }

    public int getBaseHeight() {
        return baseHeight;
    }

    public int getBaseWidth() {
        return baseWidth;
    }

    public static double getScale() {
        return scale;
    }

    public static void setScale(double scale) {
        Screen.scale = scale;
        for (int i = 0; i <SCREENS.size(); i++) {
            SCREENS.get(i).setSize(SCREENS.get(i).getBaseHeight(),SCREENS.get(i).getBaseWidth());
        }
    }

    public static void setMouseReleased(boolean mouse) {
        mouseReleased = mouse;
    }
}
