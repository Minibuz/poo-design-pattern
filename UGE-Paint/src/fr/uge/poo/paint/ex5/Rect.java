package fr.uge.poo.paint.ex5;

import java.awt.*;

public final class Rect implements GraphicElement {

    private final int x1;
    private final int y1;
    private final int width;
    private final int height;
    private final int xCenter;
    private final int yCenter;

    public Rect(RectBuilder builder) {
        this.x1 = builder.x1;
        this.y1 = builder.y1;
        this.width = builder.width;
        this.height = builder.height;
        this.xCenter = builder.xCenter;
        this.yCenter = builder.yCenter;
    }

    @Override
    public void draw(Graphics2D graphics, Color color) {
        graphics.setColor(color);
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

    public static class RectBuilder {
        private final int x1;
        private final int y1;
        private final int width;
        private final int height;
        private int xCenter;
        private int yCenter;

        public RectBuilder(int x1, int y1, int width, int height) {
            this.x1 = x1;
            this.y1 = y1;
            this.width = width;
            this.height = height;
            this.computeCenter();
        }

        private void computeCenter() {
            this.xCenter = this.x1 + this.width/2;
            this.yCenter = this.y1 + this.height/2;
        }

        public Rect build() {
            return new Rect(this);
        }
    }
}
