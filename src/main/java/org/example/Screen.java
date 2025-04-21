package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Screen {
    static final Font VCR_FONT;
    static final int MAX_CHAR_PER_LINE = 50;
    private static final JLabel BORDER_BACKGROUND = new JLabel();
    private static final JLabel BACKGROUND = new JLabel();
    private static final JLabel[] TEXTS = new JLabel[]{new JLabel(),new JLabel(), new JLabel(),new JLabel()};
    private static final JFrame SYSTEM = new JFrame("");
    private static final ArrayList<JButton> CHOICES = new ArrayList<>();
    private static final ArrayList<Component> SCREEN_ELEMENTS = new ArrayList<>();
    private static final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private static final GraphicsDevice[] SCREENS = ge.getScreenDevices();
    private static final GraphicsDevice PRIMARY_MONITOR = getHighestResolutionDisplay();
    private static final DisplayMode PRIMARY_MONITOR_DM = PRIMARY_MONITOR.getDisplayMode();
    static final int SCREEN_HEIGHT = PRIMARY_MONITOR_DM.getHeight();
    static final int SCREEN_WIDTH = PRIMARY_MONITOR_DM.getWidth();
    static final int USABLE_WIDTH = (PRIMARY_MONITOR_DM.getHeight() * 4) / 3;
    private static boolean mouseReleased = false;
    private static volatile boolean textIsTyping = true; // Shared flag to control the thread

    static {
        try {
            VCR_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/org/example/Ui/VCR_OSD_MONO_1.001.ttf")).deriveFont(Font.BOLD, 50f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {
        SYSTEM.setLayout(null);
        SYSTEM.setUndecorated(true);
        SYSTEM.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Add TEXT1
        for(JLabel jLabel : TEXTS) {
            jLabel.setFont(VCR_FONT);
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setBounds(0, (int) (SCREEN_HEIGHT * 0.9), USABLE_WIDTH, (int) (VCR_FONT.getSize() * 1.1));
            jLabel.setOpaque(true); // Make JLabel background visible
            jLabel.setBackground(Color.BLACK); // Background color
            jLabel.setForeground(Color.WHITE); // Text color
            SYSTEM.add(jLabel);
        }


        BACKGROUND.setBounds((SCREEN_WIDTH - USABLE_WIDTH) / 2, 0, USABLE_WIDTH, SCREEN_HEIGHT);
        setBackground(false, "scars");
        BACKGROUND.setVisible(true);
        SYSTEM.add(BACKGROUND);


        SYSTEM.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                mouseReleased = true;
                System.out.println("Mouse released at coordinates: (" + e.getX() + ", " + e.getY() + ")");
            }
        });

        BORDER_BACKGROUND.setIcon(createBackground(PRIMARY_MONITOR_DM.getWidth(), PRIMARY_MONITOR_DM.getHeight()));
        BORDER_BACKGROUND.setBounds(0, 0, PRIMARY_MONITOR_DM.getWidth(), PRIMARY_MONITOR_DM.getHeight()); // Make sure it covers the full screen
        SYSTEM.add(BORDER_BACKGROUND);

        PRIMARY_MONITOR.setFullScreenWindow(SYSTEM);
        SYSTEM.setVisible(true); // Make the frame visible AFTER everything is configured
    }

    public static void setBackground(Boolean isPhoto, String name) {
        if (isPhoto) {
            BACKGROUND.setIcon(scaleImage(SCREEN_WIDTH, SCREEN_HEIGHT, new ImageIcon("src/main/java/org/example/Background/Photos/" + name + ".png")));
        } else {
            BACKGROUND.setIcon(scaleImage(SCREEN_WIDTH, SCREEN_HEIGHT, new ImageIcon("src/main/java/org/example/Background/Handrawn/" + name + ".png")));
        }
    }

    public static void addElement(Component component) {
        SCREEN_ELEMENTS.add(component);
        SYSTEM.add(component);
        SYSTEM.getContentPane().setComponentZOrder(BORDER_BACKGROUND, SYSTEM.getContentPane().getComponentCount() - 1);
        SYSTEM.getContentPane().setComponentZOrder(BACKGROUND, SYSTEM.getContentPane().getComponentCount() - 2);
    }

    public static void removeElement(Component component) {
        component.setVisible(false);
        SCREEN_ELEMENTS.remove(component);
        SYSTEM.remove(component);
    }

    public static void waitTillClick() {
        mouseReleased = false;
        while (!mouseReleased) {
            Main.wait(1);
        }
    }

    public static void sPrintln(String str) {
        sPrintln("", str);
    }

    public static void sPrintln(String name, String str) {
        setText("");
        Thread thread = new Thread(() -> {
            String text = name;
            if(name.length()>0) {
                 text = name + ": ";
            }
            for (char c : (str + "...").toCharArray()) {
                if (!textIsTyping) break; // Gracefully exit the loop if the thread is stopped
                text += c;
                setText(text);
                Main.wait(10);

            }

        });

        textIsTyping = true; // Reset the flag to start the thread
        thread.start(); // Start the thread

        waitTillClick(); // Wait for user interaction

        textIsTyping = false; // Set the flag to stop the thread gracefully
        try {
            thread.join(); // Ensure the thread finishes execution before proceeding
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void setText(String str) {
        int lines = 0;
        String[] string = {"","","",""};
        String[] words = str.split(" ");
        for(String word : words) {
            if((string[lines]+word).length()<MAX_CHAR_PER_LINE) {
                string[lines] += word+" ";
            } else {
                lines++;
                string[lines] += word;

            }
        }
        for(int i=0; i<string.length; i++) {
            TEXTS[i].setText(string[i]);
            TEXTS[i].setVisible(true);
            TEXTS[i].setBounds(
                    (SCREEN_WIDTH-TEXTS[i].getPreferredSize().width) / 2,
                    (int) ((SCREEN_HEIGHT - (VCR_FONT.getSize() * 1.1)*i) / 1.1),
                    TEXTS[i].getPreferredSize().width + VCR_FONT.getSize(),
                    (int) (VCR_FONT.getSize() * 1.1));
            if(TEXTS[i].getText().isEmpty()) {
                TEXTS[i].setVisible(false);
            }
        }


    }

    public static int choice(String text, String[] choices) {
        setText(text);
        final int[] clickedIndex = {-1};

        // Create and add buttons with fixed size to the frame
        for (int i = 0; i < choices.length; i++) {
            JButton button = new JButton(choices[i]);
            // Capture the current index
            int finalI = i;

            button.addActionListener(e -> {
                clickedIndex[0] = finalI;
                synchronized (SYSTEM) {
                    SYSTEM.notify(); // Notify the waiting thread
                }
            });
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setBackground(Color.BLACK); // Background color
            button.setForeground(Color.WHITE); // Text color
            button.setFont(VCR_FONT);
            button.setVisible(true);
            button.setBounds(
                    (SCREEN_WIDTH-button.getPreferredSize().width) / 2,
                    (int) ((SCREEN_HEIGHT - (VCR_FONT.getSize() * 1.1)) / 2) + ((int) (VCR_FONT.getSize() * 1.25) * i),
                    button.getPreferredSize().width + VCR_FONT.getSize(),
                    (int) (VCR_FONT.getSize() * 1.1)
            );

            CHOICES.add(button);
            addElement(button);

            SYSTEM.getContentPane().setComponentZOrder(button, 1);
        }

        // Repaint and revalidate to ensure buttons are rendered
        SYSTEM.revalidate();
        SYSTEM.repaint();

        // Wait for a button to be clicked
        synchronized (SYSTEM) {
            try {
                while (clickedIndex[0] == -1) {
                    SYSTEM.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Remove buttons after a selection is made
        for (JButton button : CHOICES) {
            removeElement(button);
        }
        CHOICES.clear();
        return clickedIndex[0];
    }

    public static ImageIcon createBackground(int width, int height) {
        // Create a buffered image with specified width and height
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.GRAY);
        int squareWidth = (height * 4) / 3;
        g.fillRect((width - squareWidth) / 2, 0, squareWidth, height);
        g.dispose();
        return new ImageIcon(image);
    }

    public static ImageIcon scaleImage(int width, int height, ImageIcon i) {
        int originalWidth = i.getIconWidth();
        int originalHeight = i.getIconHeight();

        double originalAspectRatio = (double) originalWidth / originalHeight;
        double targetAspectRatio = (double) width / height;

        int finalWidth, finalHeight;

        if (originalAspectRatio > targetAspectRatio) {
            // Fit to width, adjust height to maintain aspect ratio
            finalWidth = width;
            finalHeight = (int) (width / originalAspectRatio);
        } else {
            // Fit to height, adjust width to maintain aspect ratio
            finalHeight = height;
            finalWidth = (int) (height * originalAspectRatio);
        }

        return new ImageIcon(i.getImage().getScaledInstance(finalWidth, finalHeight, Image.SCALE_SMOOTH));
    }

    public static ImageIcon scaleImage(double scale, ImageIcon i) {
        return scaleImage((int) (i.getIconWidth() * scale), (int) (i.getIconHeight() * scale), i);
    }

    public static GraphicsDevice getHighestResolutionDisplay() {

        GraphicsDevice highestResolutionDisplayMode = null;
        int highestResolution = 0; // Start with zero to compare resolutions.

        for (GraphicsDevice screen : SCREENS) {
            GraphicsDevice displayMode = screen;
            int resolution = displayMode.getDisplayMode().getWidth() * displayMode.getDisplayMode().getHeight(); // Calculate total pixel area.

            if (resolution > highestResolution) {
                highestResolution = resolution;
                highestResolutionDisplayMode = displayMode; // Update the highest resolution mode.
            }
        }

        return highestResolutionDisplayMode; // Return the DisplayMode of the highest-resolution screen.
    }


}