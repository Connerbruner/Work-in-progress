package org.example;

public class Charatcher {
    private String name;
    private String path;
    private String expression;
    private double[] statsInfluence;
    private int[] stats;
    private int balance;
    private Job job;
    private boolean isDead;

    public Charatcher(String n, String folder, double[] s, Job j) {
        name = n;
        path = folder;
        statsInfluence = s;
        job = j;
        stats = new int[]{100, 100, 100, 100, 100};
        expression = "hidden";
        balance = 100;
        isDead = false;

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

    public void setBalance(int balance) {
        this.balance = balance;
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
