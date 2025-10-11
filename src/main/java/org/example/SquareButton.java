package org.example;

import javax.swing.*;
import java.awt.*;

class SquareButton extends JButton {
    private final Dimension size;

    public SquareButton(String text, Dimension size) {
        super(text);
        this.size = size;
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        return size;
    }
}

