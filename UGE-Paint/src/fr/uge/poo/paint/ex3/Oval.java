package fr.uge.poo.paint.ex3;

import java.awt.*;

public final class Oval implements GraphicElement {

    private final int x1;
    private final int y1;
    private final int width;
    private final int height;

    public Oval(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawOval(x1, y1, width, height);
    }
}
