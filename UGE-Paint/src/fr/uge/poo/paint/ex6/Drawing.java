package fr.uge.poo.paint.ex6;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static fr.uge.poo.paint.ex6.GraphicElement.fromLine;

public class Drawing {

    private final static Color BASE_COLOR = Color.BLACK;
    private final static Color HIGHLIGHTED_COLOR = Color.ORANGE;

    private final List<GraphicElement> elements;
    private GraphicElement closestFromClick = null;

    private Drawing(List<GraphicElement> elements) {
        this.elements = elements;
    }

    public static Drawing loadFile(Path path) throws IOException {
        try(var lines = Files.lines(path)) {
            return new Drawing(lines.map(line -> fromLine(line.split(" "))).toList());
        }
    }

    public void display() {
        SimpleGraphics area = new SimpleGraphics("area", 800, 600);
        area.clear(Color.WHITE);
        area.render(graphics2D -> drawAll(graphics2D, elements));
        area.waitForMouseEvents((x, y) -> callback(area, elements, x, y));
    }

    private void drawAll(Graphics2D graphics, List<GraphicElement> elements) {
        elements.forEach(graphicElement -> graphicElement.draw(graphics, Color.BLACK));
    }

    private void callback(SimpleGraphics area, List<GraphicElement> elements, int x, int y) {
        if(closestFromClick != null) {
            area.clear(Color.WHITE);
            area.render(graphics2D -> drawAll(graphics2D, elements));
        }

        this.closestFromClick = elements.stream()
                .min(Comparator.comparingInt(graphicElement -> graphicElement.distance(x, y)))
                .orElseThrow();
        area.render(graphics2D -> closestFromClick.draw(graphics2D, HIGHLIGHTED_COLOR));
    }
}
