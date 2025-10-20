package org.example;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_RATIO = ((screenSize.getWidth()/2560)+(screenSize.getHeight()/1440))/2;
    public static Phone phone = new Phone();
    public static SceneScreen scene = new SceneScreen();


    static final Font VCR_FONT;
    static {
        try {
            VCR_FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/org/example/Ui/VCR_OSD_MONO_1.001.ttf")).deriveFont(Font.BOLD, 50f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date currentDate = new Date(2025,2,17);
    public static int daysSurvived = 0;

    public static void main(String[] args) throws IOException {
        Character character = Character.ALL_CHARACTERS[0];
        Character character1 = Character.ALL_CHARACTERS[1];


        scene.setBackground(true,"park2");
        scene.add(character);
        scene.add(character1);
        character.changeExpression("mad");
        character1.changeExpression("mad");
        scene.setupScene();

    }
    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }
    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int random(Object[] arr) {
        return (int) (Math.random() * arr.length);
    }
    public static void wait(int millis) {
        long startTime = System.currentTimeMillis();
        while (startTime+millis>System.currentTimeMillis());
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
}
