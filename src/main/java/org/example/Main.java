package org.example;

import org.example.Cards.UnoGame;
import org.example.Display.Phone;
import org.example.Display.SceneScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import static javax.imageio.ImageIO.read;

public class Main {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_RATIO = ((screenSize.getWidth() / 2560) + (screenSize.getHeight() / 1440)) / 2;
    public static Phone phone = new Phone();
    public static SceneScreen scene;
    public static final Font VCR_FONT;

    static {
        try {
            VCR_FONT = Font.createFont(Font.TRUETYPE_FONT, Main.getResource("Ui/VCR_OSD_MONO_1.001.ttf")).deriveFont(Font.BOLD, 20f);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        phone.setVisible(true);
//        for(GameCharacter character : ALL_GAME_CHARACTERS) {
//            UnoGame.startingFillHand(character);
//        }
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

    public static int randomWithWeights(double[] weights) {
        if (weights.length == 0) return 0;
        double total = 0;
        for (double w : weights) total += w;
        double r = Math.random() * total; // scale r to match actual sum
        for (int i = 0; i < weights.length; i++) {
            r -= weights[i];
            if (r <= 0) return i;
        }
        return weights.length - 1; // never return -1
    }

    public static ImageIcon flipImage(ImageIcon icon) {
        BufferedImage image = iconToBuffer(icon);

        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth() / 2; i++) {
                int leftPixel = image.getRGB(i, j);
                int rightPixel = image.getRGB(image.getWidth() - i - 1, j);
                image.setRGB(i, j, rightPixel);
                image.setRGB(image.getWidth() - i - 1, j, leftPixel);
            }
        }

        return new ImageIcon(image);
    }

    public static BufferedImage iconToBuffer(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB // Supports transparency
        );
        Graphics2D g2d = bi.createGraphics();
        g2d.setComposite(AlphaComposite.Src); // Ensures alpha is preserved
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();
        return bi;
    }


    public static ImageIcon cropImageIcon(ImageIcon originalIcon, int x, int y, int cropWidth, int cropHeight) {
        Image originalImage = originalIcon.getImage();
        int imageWidth = originalImage.getWidth(null);
        int imageHeight = originalImage.getHeight(null);

        cropWidth = Math.min(cropWidth, imageWidth - x);
        cropHeight = Math.min(cropHeight, imageHeight - y);
        x = Math.max(0, Math.min(x, imageWidth - cropWidth));
        y = Math.max(0, Math.min(y, imageHeight - cropHeight));

        BufferedImage bufferedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, null);
        g2d.dispose();

        BufferedImage croppedImage = bufferedImage.getSubimage(x, y, cropWidth, cropHeight);
        return new ImageIcon(croppedImage);
    }

    public static ImageIcon scaleImage(double scale, ImageIcon i) {
        return scaleImage((int) (i.getIconWidth() * scale), (int) (i.getIconHeight() * scale), i);
    }
    public static File getResource(String path) {
        try {
            java.net.URL url = Main.class.getResource("/" + path);
            if (url == null) {
                System.out.println("Resource not found: " + path);
                return null;
            }
            return new File(url.toURI());
        } catch (URISyntaxException e) {
            System.out.println("No find");
            return null;
        }
    }
    public static ImageIcon getResourceImage(String path) {
        try {
            return new ImageIcon(read(Main.getResource(path)));
        } catch (IOException e) {
            System.out.println("No find");
            return null;
        }
    }
        public ImageIcon getBackground(boolean isPhoto,String name) {
        if (isPhoto) {
            return getResourceImage("Background/Photos/" + name + ".png");
        } else {
            return getResourceImage("Background/Handrawn/" + name + ".png");
        }
    }
    public ImageIcon getCharacterImage(String character,String name) {
            return getResourceImage("Characters/"+character+"/" + name + ".png");
    }

}
