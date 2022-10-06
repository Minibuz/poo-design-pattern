package fr.uge.poo.paint.ex5;

import java.awt.*;

public final class Line implements GraphicElement {

    private final int x1;
    private final int x2;
    private final int y1;
    private final int y2;
    // private Color color;
    private final int xCenter;
    private final int yCenter;


    public Line(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.xCenter = (this.x1 + this.x2)/2;
        this.yCenter = (this.y1 + this.y2)/2;
    }

    @Override
    public void draw(Graphics2D graphics, Color color) {
        graphics.setColor(color);
        graphics.drawLine(x1, y1, x2, y2);
    }

    @Override
    public int distance(int x, int y) {
        return ((x - xCenter) * (x - xCenter)) + ((y - yCenter) * (y - yCenter));
    }

    @Override
    public String toString() {
        return "Line{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                ", y1=" + y1 +
                ", y2=" + y2 +
                ", xCenter=" + xCenter +
                ", yCenter=" + yCenter +
                '}';
    }
}
