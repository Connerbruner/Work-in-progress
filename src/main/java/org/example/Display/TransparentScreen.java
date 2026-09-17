package org.example.Display;

import java.awt.*;

public class TransparentScreen extends Screen {
    public TransparentScreen(int height, int width) {
        super(height, width);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));

    }
}
