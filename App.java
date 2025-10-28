package org.example;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class App {
    public static final App[] ALL_APPS = {
            new App("Notes") {
                @Override
                public void run() throws IOException {
                    String notes = "";
                    BufferedReader br = new BufferedReader(new FileReader("notes.txt"));
                    String str = "";
                    while ((str = br.readLine()) != null) {
                        notes += str+"\n";
                    }
                    br.close();
                    JFrame jFrame = new JFrame("notes");
                    jFrame.setSize(500,500);
                    JTextField field = new JTextField(notes);
                    jFrame.add(field);
                    jFrame.setVisible(true);
                    jFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"))) {
                                writer.write(field.getText());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            }
    };
    private String name;

    public App(String n) {
        name = n;
    }

    public void run() throws IOException {

    }

    public String getName() {
        return name;
    }
}
