package fr.uge.poo.paint.ex7;

import fr.uge.poo.paint.ex7.adapter.CoolGraphicAdapter;
import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;
import fr.uge.poo.paint.ex7.adapter.SimpleGraphicsAdapter;
import fr.uge.poo.paint.ex7.graphics.GraphicElement;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static fr.uge.poo.paint.ex7.graphics.GraphicElement.fromLine;

public final class Drawing {

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
            case simplegraphics -> graphic = new SimpleGraphicsAdapter("area", maxSize.width(), maxSize.height(), elements);
            case coolgraphics -> graphic = new CoolGraphicAdapter("area", maxSize.width(), maxSize.height(), elements);
            default -> throw new IllegalArgumentException();
        }
        graphic.clear(Color.WHITE);
        graphic.drawAll();
        graphic.waitForMouseEvents();
    }

    private GraphicSize computeWindowSize() {
        var size = elements.stream().map(GraphicElement::size).reduce(GraphicSize::Union).orElseGet(() -> new GraphicSize(500, 500));
        if(size.width() < 500) {
            size = new GraphicSize(500, size.height());
        }
        if(size.height() < 500) {
            size = new GraphicSize(size.width(), 500);
        }
        return size;
    }
}
