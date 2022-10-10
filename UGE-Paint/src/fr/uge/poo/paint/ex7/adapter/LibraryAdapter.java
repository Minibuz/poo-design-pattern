package fr.uge.poo.paint.ex7.adapter;

import fr.uge.poo.coolgraphics.CoolGraphics;
import fr.uge.poo.paint.ex7.GraphicSize;
import fr.uge.poo.paint.ex7.graphics.CommunBox;
import fr.uge.poo.paint.ex7.graphics.GraphicElement;

import java.awt.*;
import java.util.List;

public interface LibraryAdapter {
    enum Library {
        simplegraphics(),
        coolgraphics()
    }

    void clear(Color color);

    void drawLine(int x1, int y1, int x2, int y2, Color color);

    void drawRect(int x, int y, int width, int height, Color color);

    void drawOval(int x, int y, int width, int height, Color color);

    void drawAll(List<GraphicElement> elements);

    void waitForMouseEvents(List<GraphicElement> elements);
}
