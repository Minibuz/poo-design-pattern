package fr.uge.poo.paint.ex7.adapter;

import fr.uge.poo.coolgraphics.CoolGraphics;
import fr.uge.poo.paint.ex7.graphics.GraphicElement;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class CoolGraphicAdapter implements LibraryAdapter {

    private final CoolGraphics graphics;

    private final List<GraphicElement> elements;

    private GraphicElement closestFromClick = null;

    public CoolGraphicAdapter(String title, int width, int height, List<GraphicElement> elements) {
        this.graphics = new CoolGraphics(title, width, height);
        this.elements = elements;
    }


    @Override
    public void clear(Color color) {
        graphics.repaint(ColorAdapter.asColorPlus(color));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        graphics.drawLine(x1, y1, x2, y2, ColorAdapter.asColorPlus(color));
    }

    @Override
    public void drawRect(int x, int y, int width, int height, Color color) {
        graphics.drawLine(x, y, x+width, y, ColorAdapter.asColorPlus(color));
        graphics.drawLine(x+width, y, x+width, y+height, ColorAdapter.asColorPlus(color));
        graphics.drawLine(x+width, y+height, x, y+height, ColorAdapter.asColorPlus(color));
        graphics.drawLine(x, y+height, x, y, ColorAdapter.asColorPlus(color));
    }

    @Override
    public void drawOval(int x, int y, int width, int height, Color color) {
        graphics.drawEllipse(x, y, width, height, ColorAdapter.asColorPlus(color));
    }

    @Override
    public void drawAll() {
        elements.forEach(
                element -> element.draw(this, ColorAdapter.asColor(CoolGraphics.ColorPlus.BLACK)));
    }

    @Override
    public void waitForMouseEvents() {
        graphics.waitForMouseEvents(this::callback);
    }

    private void callback(int x, int y) {
        if(closestFromClick != null) {
            clear(Color.WHITE);
            drawAll();
        }

        this.closestFromClick = elements.stream()
                .min(Comparator.comparingInt(graphicElement -> graphicElement.distance(x, y)))
                .orElseThrow();
        closestFromClick.draw(this, Color.ORANGE);
    }
}
