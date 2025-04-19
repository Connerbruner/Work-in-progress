package org.example;

import javax.swing.*;
import java.io.File;

public class Character {
    private String name;
    private String path;
    private String expression;
    private double[] statsInfluence;
    private int[] stats;
    private int balance;
    private Job job;
    private boolean isDead;
    private CharacterStill characterStill;
    static final Job[] JOBS = {
            new Job("Getting up",new Game[]{},0,0,5,0),
            new Job("Fish market",new Game[]{},130,0,7,0)
    };
    static final Character[] ALL_CHARACTERS = {
            new Character("Carina","src/main/Charachters/Carina/",new double[] {1,1,1.5,0.5,1},JOBS[1])
    };
    public Character(String n, String folder, double[] s, Job j) {
        name = n;
        path = folder;
        statsInfluence = s;
        job = j;
        stats = new int[]{100, 100, 100, 100, 100};
        balance = 100;
        isDead = false;

    }
    public void createCharacterStill(int x,int y,int h,int w) {
        characterStill = new CharacterStill(x,y,h,w,this);
    }
    public void createCharacterStill(DefaultPosition d) {
        characterStill = new CharacterStill(d.getX(),d.getY(),d.getH(),d.getW(),this);
    }

    public CharacterStill getCharacterStill() {
        return characterStill;
    }

    public void runJob() {
        job.run();
    }

    public void reset() {
        stats = new int[]{100, 100, 100, 100, 100};
        expression = "hidden";
        balance = 100;
        isDead = false;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public double getStatInfluence(int i) {
        return statsInfluence[i];
    }

    public double getStats(int i) {
        return stats[i];
    }

    public int getBalance() {
        return balance;
    }
    public ImageIcon getExpression(String expression) {
        if (new File(path+""+expression+".png").exists()) {
            throw new IllegalArgumentException(path+""+expression+".png is bad");
        }
        return new ImageIcon(path+""+expression+".png");
    }

    public String getCurrentExpression() {
        return expression;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void addBalance(int add) {
        this.balance += add;
    }

    public void subtractBalance(int sub) {
        this.balance -= sub;
    }

    public void setExpression(String name) {
        expression = name;
    }


}
