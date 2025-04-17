package org.example;
public class Main {
    public static void main(String[] args) {
        new GameBoard();

    }
    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }

    public static int random(Object[] arr) {
        return (int) (Math.random() * arr.length);
    }
}
