package org.example;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

public class TextScreen extends Screen {
    private JLabel[] texts;
    private int charPerLine;
    private volatile boolean textIsTyping = true; // Shared flag to control the thread

    public TextScreen(int height,int width) {
        super(height, width,false);
        charPerLine = (int) (width/(Main.VCR_FONT.getSize()*0.66));
        texts = new JLabel[(int) (height/(Main.VCR_FONT.getSize()))];
        for(int i=0; i<texts.length; i++) {
            texts[i] = new JLabel();
            JLabel jLabel = texts[i];
            jLabel.setFont(Main.VCR_FONT);
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setVerticalAlignment(SwingConstants.TOP);
            jLabel.setBounds(0, height/texts.length, (int) (width*0.9), (int) (Main.VCR_FONT.getSize() * 1.1));
            jLabel.setVisible(false);
            add(jLabel);
        }
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {
                Screen.setMouseReleased(true);
            }

        });
    }
    public void sPrintln(SceneScreen screen,String str) {
        int x = screen.getWidth()/2+screen.getX()-getWidth()/2;
        int y = screen.getHeight()+screen.getY()-getHeight()+10;
        sPrintln("", str,x,y);
    }
    public void sPrintln(SceneScreen scene,int i, String str) {
        Character character = scene.getCharacter(i);
        int x = character.getScreen().getWidth()+character.getScreen().getX()+30;
        if(i%2==1) {
            x -= getWidth()+character.getScreen().getWidth()+60;
        }
        int y = character.getScreen().getY()-character.getScreen().getHeight()/6;
        sPrintln(character.getName(), str,x,y);
    }
    public void sPrintln(String name, String str, int x,int y) {
        clear();
        System.out.println(x+" "+y);
        setLocaction(x, y);
        setVisible(true);
        Thread thread = new Thread(() -> {
            String text = name;
            if(name.length()>0) {
                text = name + ": ";
            }
            for (char c : (str + "...").toCharArray()) {
                if (!textIsTyping) break;
                text += c;
                setText(text);
                Main.wait(10);

            }

        });

        textIsTyping = true;
        thread.start();

        waitTillClick();

        textIsTyping = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void setText(String str) {
        int lines = 0;
        String[] textLines = new String[texts.length];
        Arrays.fill(textLines, "");
        String[] words = str.split(" ");
        for(String word : words) {
            if((textLines[lines]+word).length()<charPerLine) {
                textLines[lines] += word+" ";
            } else {
                lines++;
                textLines[lines] += word+" ";

            }
        }

        for (int i = 0; i < textLines.length; i++) {
            texts[i].setText(textLines[i]);
            texts[i].setBounds(
                    (getWidth() - texts[i].getPreferredSize().width) / 2,
                    (int) ((Main.VCR_FONT.getSize() * 1.1) * i),
                    texts[i].getPreferredSize().width + Main.VCR_FONT.getSize(),
                    (int) (Main.VCR_FONT.getSize() * 1.1));
            texts[i].setVisible(!textLines[i].isEmpty());
        }


    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        charPerLine = (int) (width/(Main.VCR_FONT.getSize()*0.66));
        texts = new JLabel[(int) (height/(Main.VCR_FONT.getSize()))];
        for(int i=0; i<texts.length; i++) {
            texts[i] = new JLabel();
            JLabel jLabel = texts[i];
            jLabel.setFont(Main.VCR_FONT);
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setVerticalAlignment(SwingConstants.TOP);
            jLabel.setBounds(0, height/texts.length, (int) (width*0.9), (int) (Main.VCR_FONT.getSize() * 1.1));
            jLabel.setVisible(false);
            add(jLabel);
        }
    }
    public void clear() {
        for (JLabel label : texts) {
            label.setText("");
            label.setVisible(false);
        }
    }

}
