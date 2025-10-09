package org.example;

import javax.swing.*;

public class TextScreen extends Screen {
    private JLabel[] texts;
    private int charPerLine;
    private volatile boolean textIsTyping = true; // Shared flag to control the thread

    public TextScreen(int height,int width, int linewidth, int lineCount) {
        super(height, width);
        charPerLine = linewidth;
        texts = new JLabel[lineCount];
        for(int i=0; i<texts.length; i++) {
            texts[i] = new JLabel();
        }
    }

    public void sPrintln(String str) {
        sPrintln("", str);
    }
    public void sPrintln(String name, String str) {
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
    public void setText(String str) {
        int lines = 0;
        String[] string = {"","","",""};
        String[] words = str.split(" ");
        for(String word : words) {
            if((string[lines]+word).length()<charPerLine) {
                string[lines] += word+" ";
            } else {
                lines++;
                string[lines] += word+" ";

            }
        }

        for(int i=0; i<string.length; i++) {
            texts[string.length-i-1].setText(string[i]);
            texts[i].setVisible(false);
            texts[i].setBounds(
                    (getWidth()-texts[i].getPreferredSize().width) / 2,
                    (int) ((getHeight() - (Main.VCR_FONT.getSize() * 1.1)*i) / 1.1),
                    texts[i].getPreferredSize().width + Main.VCR_FONT.getSize(),
                    (int) (Main.VCR_FONT.getSize() * 1.1));
            if(texts[i].getText().isEmpty()) {
                texts[i].setVisible(false);
            } else {
                texts[i].setVisible(true);

            }
        }


    }

}
