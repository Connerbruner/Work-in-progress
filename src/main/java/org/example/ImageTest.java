package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ImageTest {

    public static void main(String[] args) {
        for (String fontName : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (fontName.toLowerCase().contains("vcr")) { // Search for VCR fonts
                System.out.println(fontName);
            }
        }
    }
}
