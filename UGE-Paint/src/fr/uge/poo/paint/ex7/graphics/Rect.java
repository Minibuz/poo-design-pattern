package fr.uge.poo.paint.ex7.graphics;

import fr.uge.poo.paint.ex7.GraphicSize;
import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;

import java.awt.*;
import java.util.function.Consumer;

public final class Rect implements GraphicElement {

    private final CommunBox communBox;

    public Rect(CommunBox box) {
        this.communBox = box;
    }

    @Override
    public void draw(LibraryAdapter library, Color color) {
        library.drawRect(communBox.x, communBox.y, communBox.width, communBox.height, color);
    }

    @Override
    public int distance(int x, int y) {
         return communBox.distance(x, y);
    }

    @Override
    public GraphicSize size() {
        return communBox.size();
    }

}
