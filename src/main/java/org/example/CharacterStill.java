package org.example;

import javax.swing.*;

public class CharacterStill {
    JLabel label;
    Charatcher charatcher;

    public CharacterStill(int x,int y,int h,int w,Charatcher c) {
        charatcher=c;
        label = new JLabel(c.getExpression("default"));
        label.setBounds(x,y,h,w);
        label.setVisible(true);
        Screen.addElement(label);
    }
    public CharacterStill(DefaultPosition x,Charatcher c) {
        charatcher=c;
        label = new JLabel(c.getExpression("default"));
        label.setBounds(x.getX(),x.getY(),x.getH(),x.getW());
        label.setVisible(true);
        Screen.addElement(label);
    }

    public void changeExpression(String expression) {
        label.setIcon(charatcher.getExpression(expression));
    }
    public void setVisible(boolean b) {
        label.setVisible(b);
    }
    public void setBounds(int x,int y,int h,int w) {
        label.setBounds(x,y,h,w);
    }
    public void remove() {
        Screen.removeElement(label);
    }
}
