package fr.uge.poo.paint.ex6;

import java.awt.*;

public final class Rect implements GraphicElement {

    private final CommunBox box;

    public Rect(CommunBox box) {
        this.box = box;
    }

    @Override
    public void draw(Graphics2D graphics, Color color) {
        graphics.setColor(color);
        graphics.drawRect(box.x, box.y, box.width, box.height);
    }

    @Override
    public int distance(int x, int y) {
         return box.distance(x, y);
    }

    @Override
    public GraphicSize size() {
        return box.size();
    }

}
