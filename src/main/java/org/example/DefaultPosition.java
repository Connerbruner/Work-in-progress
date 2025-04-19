package org.example;

public enum DefaultPosition {
    TOP_LEFT(0, 0,1100,1562),
    TOP_RIGHT(0, 100,1100,1562),
    BOTTOM_LEFT(100, 0,1100,1562),
    BOTTOM_RIGHT(100, 100,1100,1562),
    CENTER(Screen.SCREEN_WIDTH/2,Screen.SCREEN_HEIGHT/2,1100/2,1562/2);

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
}
