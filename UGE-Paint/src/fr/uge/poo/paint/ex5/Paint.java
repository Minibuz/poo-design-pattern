package fr.uge.poo.paint.ex5;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import static fr.uge.poo.paint.ex5.GraphicElement.fromLine;

public class Paint {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("draw2.txt");
        new Drawing(loadFile(path)).display();
    }

    private static List<GraphicElement> loadFile(Path path) throws IOException {
        try(var lines = Files.lines(path)) {
            return lines.map(line -> fromLine(line.split(" "))).toList();
        }
    }
}
