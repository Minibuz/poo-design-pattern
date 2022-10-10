package fr.uge.poo.paint.ex7.adapter;

import fr.uge.poo.paint.ex7.graphics.GraphicElement;
import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class SimpleGraphicsAdapter implements LibraryAdapter {

    private final SimpleGraphics simpleGraphics;

    private GraphicElement closestFromClick = null;

    public SimpleGraphicsAdapter(String title, int width, int height) {
        this.simpleGraphics = new SimpleGraphics("area", width, height);
    }


    @Override
    public void clear(Color color) {
        simpleGraphics.clear(color);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(color);
            graphics2D.drawLine(x1, y1, x2, y2);
        });
    }

    @Override
    public void drawRect(int x, int y, int width, int height, Color color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(color);
            graphics2D.drawRect(x, y, width, height);
        });
    }

    @Override
    public void drawOval(int x, int y, int width, int height, Color color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(color);
            graphics2D.drawOval(x, y, width, height);
        });
    }

    @Override
    public void drawAll(List<GraphicElement> elements) {
        elements.forEach(graphicElement -> graphicElement.draw(this, Color.BLACK));
    }

    @Override
    public void waitForMouseEvents(List<GraphicElement> elements) {
        simpleGraphics.waitForMouseEvents((x, y) -> callback(elements, x, y));
    }

    private void callback(List<GraphicElement> elements, int x, int y) {
        if(closestFromClick != null) {
            clear(Color.WHITE);
            drawAll(elements);
        }

        this.closestFromClick = elements.stream()
                .min(Comparator.comparingInt(graphicElement -> graphicElement.distance(x, y)))
                .orElseThrow();
        simpleGraphics.render(graphics2D -> closestFromClick.draw(this, Color.ORANGE));
    }
}
