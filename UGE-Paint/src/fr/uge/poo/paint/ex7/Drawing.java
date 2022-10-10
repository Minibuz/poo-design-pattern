package fr.uge.poo.paint.ex7;

import fr.uge.poo.paint.ex7.adapter.CoolGraphicAdapter;
import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;
import fr.uge.poo.paint.ex7.adapter.SimpleGraphicsAdapter;
import fr.uge.poo.paint.ex7.graphics.GraphicElement;
import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static fr.uge.poo.paint.ex7.graphics.GraphicElement.fromLine;

public final class Drawing {

    private final static Color BASE_COLOR = Color.BLACK;
    private final static Color HIGHLIGHTED_COLOR = Color.ORANGE;
    private final static int WIDTH = 500;
    private final static int HEIGHT = 500;

    private final List<GraphicElement> elements;
    private final LibraryAdapter.Library library;

    private Drawing(List<GraphicElement> elements, LibraryAdapter.Library libraryUsed) {
        this.elements = elements;
        this.library = libraryUsed;
    }

    public static Drawing loadFile(Path path, LibraryAdapter.Library libraryUse) throws IOException {
        try(var lines = Files.lines(path)) {
            return new Drawing(lines.map(line -> fromLine(line.split(" "))).toList(), libraryUse);
        }
    }

    public void display() {
        GraphicSize maxSize = computeWindowSize();
        LibraryAdapter graphic;
        switch (library) {
            case simplegraphics -> graphic = new SimpleGraphicsAdapter("area", maxSize.width(), maxSize.height());
            case coolgraphics -> graphic = new CoolGraphicAdapter("area", maxSize.width(), maxSize.height());
            default -> throw new IllegalArgumentException();
        }
        graphic.clear(Color.WHITE);
        graphic.drawAll(elements);
        graphic.waitForMouseEvents(elements);
    }

    private GraphicSize computeWindowSize() {
        var sizes = elements.stream().map(GraphicElement::size).toList();
        var width = sizes.stream().max(Comparator.comparingInt(GraphicSize::width)).orElseThrow();
        var height = sizes.stream().max(Comparator.comparingInt(GraphicSize::height)).orElseThrow();
        return new GraphicSize(width.width(), height.height());
    }
}
