package org.example;

public class PlayableCharacter extends Character {
    private Job job;
    private double[] statsInfluence;
    private int[] stats;
    private int balance;
    private boolean isDead;

    static final Job[] JOBS = {
            new Job("Getting up",new Game[]{},0,0,5,0),
            new Job("Fish market",new Game[]{},130,0,7,0),
    };
    static final Character[] PLAYABLE_CHARACTERS = {
            new PlayableCharacter("Carina","src/main/java/org/example/Charachters/Carina",new double[] {1,1,1.5,0.5,1},JOBS[1],35),
            new PlayableCharacter("Orion","src/main/java/org/example/Charachters/Orion",new double[] {1,1.5,0.5,0.5,1},JOBS[0])

    };
    public PlayableCharacter(String n, String folder, double[] s, Job j) {
        super(n, folder);
        statsInfluence = s;
        job = j;
        stats = new int[]{100, 100, 100, 100, 100};
        balance = 100;
        isDead = false;

    }
    public PlayableCharacter(String n, String folder, double[] s, Job j,int h) {
        super(n, folder,h);
        statsInfluence = s;
        job = j;
        stats = new int[]{100, 100, 100, 100, 100};
        balance = 100;
        isDead = false;

    }

    public void reset() {
        stats = new int[]{100, 100, 100, 100, 100};
        changeExpression("hidden");
        balance = 100;
        isDead = false;
    }

    public void runJob() {
        job.run();
    }

    public double getStatInfluence(int i) {
        return statsInfluence[i];
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void subtractBalance(int sub) {
        this.balance -= sub;
    }

    public double getStats(int i) {
        return stats[i];
    }

    public int getBalance() {
        return balance;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void addBalance(int amount) {
        balance+=amount;
    }
}
