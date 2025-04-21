package org.example;

public enum DefaultPosition {
    RIGHT3_BOTTOM_CHARACTER(1925,475,687,976),
    RIGHT2_BOTTOM_CHARACTER(1525,475,687,976),
    RIGHT1_BOTTOM_CHARACTER(1225,475,687,976),
    CENTER_BOTTOM_CHARACTER(975,475,687,976),
    LEFT1_BOTTOM_CHARACTER(725,475,687,976),
    LEFT2_BOTTOM_CHARACTER(525,475,687,976),
    LEFT3_BOTTOM_CHARACTER(225,475,687,976);

    private final int x;
    private final int y;
    private final int h;
    private final int w;


    DefaultPosition(int x, int y,int w,int h) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getX() {
        return (int) (x*(Screen.USABLE_WIDTH/2560.0));
    }

    public int getY() {
        return (int) (y*(Screen.SCREEN_HEIGHT/1440.0));
    }

    public int getH() {
        return (int) (h*(Screen.SCREEN_HEIGHT/1440.0));
    }

    public int getW() {
        return (int) (w*(Screen.SCREEN_HEIGHT/1440.0));
    }
}
