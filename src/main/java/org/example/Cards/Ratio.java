package org.example.Cards;

public class Ratio {
    private int colorCount;
    private int[] wildNumbers;
    private int[] numbers;
    private int cyclePerColor;

    public Ratio(int[] nums, int[] wildNum, int colors, int cycles) {
        numbers = nums;
        wildNumbers = wildNum;
        colorCount = colors;
        cyclePerColor = cycles;
    }

    public Ratio() {
        this(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}, new int[]{13, 14}, 4, 2);
    }

    public int getColorCount() {
        return colorCount;
    }

    public int getWildNumber(int i) {
        return wildNumbers[i % wildNumbers.length];
    }

    public int getNumber(int i) {
        return numbers[i];
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int[] getWildNumbers() {
        return wildNumbers;
    }
    public boolean isWildNumber(int number) {
        for (int i = 0; i < wildNumbers.length; i++) {
            if(wildNumbers[i]==number) {
                return true;
            }
        }
        return false;
    }


    public int getCyclePerColor() {
        return cyclePerColor;
    }
}
