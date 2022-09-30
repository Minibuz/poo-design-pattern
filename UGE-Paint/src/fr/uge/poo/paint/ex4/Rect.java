package fr.uge.poo.paint.ex4;

import java.awt.*;

public final class Rect implements GraphicElement {

    private final int x1;
    private final int y1;
    private final int width;
    private final int height;
    private final int xCenter;
    private final int yCenter;

    public Rect(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;

        this.xCenter = x1 + width/2;
        this.yCenter = y1 + height/2;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawRect(x1, y1, width, height);
    }

    @Override
    public int distance(int x, int y) {
        return ((x - xCenter) * (x - xCenter)) + ((y - yCenter) * (y - yCenter));
    }

    @Override
    public String toString() {
        return "Rect{" +
                "x1=" + x1 +
                ", y1=" + y1 +
                ", width=" + width +
                ", height=" + height +
                ", xCenter=" + xCenter +
                ", yCenter=" + yCenter +
                '}';
    }
}
