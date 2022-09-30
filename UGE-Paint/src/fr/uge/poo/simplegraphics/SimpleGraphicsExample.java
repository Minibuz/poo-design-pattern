package fr.uge.poo.simplegraphics;



import java.awt.Color;
import java.awt.Graphics2D;

public class SimpleGraphicsExample {
    private static void drawAll(Graphics2D graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(100, 20, 40, 140);
        graphics.drawRect(240, 20, 40, 140);
        graphics.drawLine(140, 20, 240, 160);
        graphics.drawLine(140, 160, 240, 20);
        graphics.drawArc(100, 20, 40, 140, 0, 180);
        graphics.drawArc(100, 20, 40, 140, 0, -180);
        graphics.drawArc(240, 20, 40, 140, 0, 180);
        graphics.drawArc(240, 20, 40, 140, 0, -180);
    }

    public static void main(String[] args) {
        SimpleGraphics area = new SimpleGraphics("area", 800, 600);
        area.clear(Color.WHITE);
        area.render(SimpleGraphicsExample::drawAll);
        //canvas.render(graphics -> drawAll(graphics));
    }
}
