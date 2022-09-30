package fr.uge.poo.paint.ex4;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class Paint {
    private static void drawAll(Graphics2D graphics, List<GraphicElement> elements) {
        elements.forEach(graphicElement -> graphicElement.draw(graphics));
    }

    private static void callback(List<GraphicElement> elements, int x, int y) {
        GraphicElement closest = elements.stream()
                .min(Comparator.comparingInt(graphicElement -> graphicElement.distance(x, y)))
                .orElseThrow();
        System.out.println(closest);
    }

    public static void main(String[] args) throws IOException {

        var path = Paths.get("draw2.txt");
        var elements = loadFile(path);

        SimpleGraphics area = new SimpleGraphics("area", 800, 600);
        area.clear(Color.WHITE);
        area.render(graphics2D -> drawAll(graphics2D, elements));
        area.waitForMouseEvents((x, y) -> callback(elements, x, y));
    }

    private static List<GraphicElement> loadFile(Path path) throws IOException {
        try(var lines = Files.lines(path)) {
            return lines.map(line -> GraphicElement.fromLine(line.split(" "))).toList();
        }
    }
}
