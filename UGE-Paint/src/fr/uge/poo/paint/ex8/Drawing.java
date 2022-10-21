package fr.uge.poo.paint.ex8;

import fr.uge.poo.paint.ex8.adapter.LibraryAdapter;
import fr.uge.poo.paint.ex8.graphics.GraphicElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;

import static fr.uge.poo.paint.ex8.graphics.GraphicElement.fromLine;

public final class Drawing {

    private final List<GraphicElement> elements;

    private GraphicElement closestFromClick;

    private Drawing(List<GraphicElement> elements) {
        this.elements = elements;
    }

    public static Drawing loadFile(Path path) throws IOException {
        try(var lines = Files.lines(path)) {
            return new Drawing(lines.map(line -> fromLine(line.split(" "))).toList());
        }
    }

    public void drawAll(LibraryAdapter libraryAdapter) {
        elements.forEach(element ->
                element.draw(libraryAdapter, LibraryAdapter.MyColor.Black)
        );
    }

    public void onClick(LibraryAdapter library, int x, int y) {
        if(closestFromClick != null) {
            library.clear(LibraryAdapter.MyColor.White);
            drawAll(library);
        }

        this.closestFromClick = elements.stream()
                .min(Comparator.comparingInt(graphicElement -> graphicElement.distance(x, y)))
                .orElseThrow();
        closestFromClick.draw(library, LibraryAdapter.MyColor.Orange);
    }

    public GraphicSize computeWindowSize(int minWidth, int minHeight) {
        var size = elements.stream().map(GraphicElement::size).reduce(GraphicSize::Union).orElseGet(() -> new GraphicSize(500, 500));
        if(size.width() < minWidth) {
            size = new GraphicSize(minWidth, size.height());
        }
        if(size.height() < minHeight) {
            size = new GraphicSize(size.width(), minHeight);
        }
        return size;
    }
}
