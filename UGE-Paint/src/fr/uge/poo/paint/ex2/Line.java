package fr.uge.poo.paint.ex2;

import java.awt.*;
import java.util.Objects;

public class Line implements GraphicElement {

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    // private Color color;


    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawLine(x1, y1, x2, y2);
    }
}
