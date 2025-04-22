package org.example;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Main {
    public static Date currentDate = new Date(2025,2,17);
    public static int daysSurvived = 0;

    public static void main(String[] args) {

        Screen.init();
        Screen.titleScreen();

    }
    public static int random(int low, int high) {
        int range = high - low + 1;
        return (int) (Math.random() * range) + low;
    }
    public static boolean strIsInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int random(Object[] arr) {
        return (int) (Math.random() * arr.length);
    }
    public static void wait(int millis) {
        long startTime = System.currentTimeMillis();
        while (startTime+millis>System.currentTimeMillis());
    }
}
