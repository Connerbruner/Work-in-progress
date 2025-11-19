package org.example;

public class Ratio {
    int normalCards;
    int zeros;
    int powerCards;
    int wild;
    public Ratio(int n,int z,int pwr,int p,int w) {
        normalCards=n;
        zeros=z;
        powerCards=pwr;
        wild=w;
    }
    public Ratio() {
        normalCards=18;
        zeros=1;
        powerCards=3;
        wild=1;
    }
    public int getWild() {
        return wild;
    }
    public int getZeros() {
        return zeros;
    }
    public int getNormalCards() {
        return normalCards;
    }
    public int getPowerCards() {
        return powerCards;
    }
}
