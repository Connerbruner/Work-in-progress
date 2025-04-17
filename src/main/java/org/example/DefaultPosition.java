package org.example;

public enum DefaultPosition {
    TOP_LEFT(0, 0,100,100),
    TOP_RIGHT(0, 100,100,100),
    BOTTOM_LEFT(100, 0,100,100),
    BOTTOM_RIGHT(100, 100,100,100),
    CENTER(50, 50,100,100);

    private final int x;
    private final int y;
    private final int h;
    private final int w;


    DefaultPosition(int x, int y,int h,int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    @Override
    public String toString() {
        return "Position: (" + x + ", " + y + ")";
    }
}
