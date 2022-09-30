package fr.uge.poo.paint.ex2;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Paint {

    private static List<GraphicElement> elements;

    private static void drawAll(Graphics2D graphics) {
        elements.forEach(graphicElement -> graphicElement.draw(graphics));
    }

    public static void main(String[] args) throws IOException, URISyntaxException {

        var path = Paths.get("draw1.txt");
        loadFile(path);

        SimpleGraphics area = new SimpleGraphics("area", 800, 600);
        area.clear(Color.WHITE);
        area.render(Paint::drawAll);
    }

    private static void loadFile(Path path) throws IOException {
        try(var lines = Files.lines(path)) {
            elements = lines.map(line -> {
                String[] tokens = line.split(" ");
                int x1 = Integer.parseInt(tokens[1]);
                int y1 = Integer.parseInt(tokens[2]);
                int x2 = Integer.parseInt(tokens[3]);
                int y2 = Integer.parseInt(tokens[4]);

                return (GraphicElement) new Line(x1, y1, x2, y2);
            }).toList();
        }
    }
}
