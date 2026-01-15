package org.example.Cards;

public class Ratio {
    private int[] cycle;
    private int colorCount;
    private int[] wildNumbers;
    private int[] numbers;
    private int[] powerNumbers;
    private int cyclePerColor;

    public Ratio(int[] nums, int[] wildNum, int[] pn, int[] c, int colors, int cycles) {
        numbers = nums;
        wildNumbers = wildNum;
        powerNumbers = pn;
        cycle = c;
        colorCount = colors;
        cyclePerColor = cycles;
    }

    public Ratio() {
        this(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},new int[]{13, 14},new int[]{10, 11, 12, 13},new int[]{9, 3, 1},4,2);
    }

    public int getWild() {
        return cycle[2];
    }

    public int getNormalCards() {
        return cycle[0];
    }

    public int getPowerCards() {
        return cycle[1];
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getValidNumberCard(int i) {
        return numbers[i % numbers.length];
    }

    public int getValidPowerCard(int i) {
        return powerNumbers[i % powerNumbers.length];
    }

    public int getWildCard(int i) {
        return wildNumbers[i % wildNumbers.length];
    }

    public int[] getWildNumbers() {
        return wildNumbers;
    }

    public int[] getCycle() {
        return cycle;
    }

    public int getCyclePerColor() {
        return cyclePerColor;
    }
}
