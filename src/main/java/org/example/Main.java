package org.example;

import java.util.Date;

public class Main {
    public static Date currentDate = new Date(2025,2,17);
    public static int daysSurvived = 0;

    public static void main(String[] args) {
        Screen.init();
    }
    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }

    public static int random(Object[] arr) {
        return (int) (Math.random() * arr.length);
    }
}
