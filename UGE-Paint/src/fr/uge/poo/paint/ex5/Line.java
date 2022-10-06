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


    private Line(LineBuilder builder) {
        this.x1 = builder.x1;
        this.x2 = builder.x2;
        this.y1 = builder.y1;
        this.y2 = builder.y2;
        this.xCenter = builder.xCenter;
        this.yCenter = builder.yCenter;
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

    public static class LineBuilder {
        private final int x1;
        private final int x2;
        private final int y1;
        private final int y2;
        private int xCenter;
        private int yCenter;

        public LineBuilder(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
            this.computeCenter();
        }

        private void computeCenter() {
            this.xCenter = (this.x1 + this.x2)/2;
            this.yCenter = (this.y1 + this.y2)/2;
        }

        public Line build() {
            return new Line(this);
        }
    }
}
