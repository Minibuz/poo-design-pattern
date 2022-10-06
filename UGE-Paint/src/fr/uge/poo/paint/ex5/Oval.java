package fr.uge.poo.paint.ex5;

import java.awt.*;

public final class Oval implements GraphicElement {

    private final CommunBox communBox;

    public Oval(CommunBox box) {
        this.communBox = box;
    }

    @Override
    public void draw(Graphics2D graphics, Color color) {
        graphics.setColor(color);
        graphics.drawOval(communBox.x, communBox.y, communBox.width, communBox.height);
    }

    @Override
    public int distance(int x, int y) {
        return communBox.distance(x, y);
    }

    @Override
    public String toString() {
        return "Oval{" +
                "communBox=" + communBox +
                '}';
    }
}
