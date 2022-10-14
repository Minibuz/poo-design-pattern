package fr.uge.poo.paint.ex7.graphics;

import fr.uge.poo.paint.ex7.GraphicSize;
import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;

import java.awt.*;

public final class Oval implements GraphicElement {

    private final CommunBox communBox;

    public Oval(CommunBox box) {
        this.communBox = box;
    }


    @Override
    public void draw(LibraryAdapter library, LibraryAdapter.MyColor color) {
        library.drawOval(communBox.x, communBox.y, communBox.width, communBox.height, color);
    }

    @Override
    public int distance(int x, int y) {
        return communBox.distance(x, y);
    }

    @Override
    public GraphicSize size() {
        return communBox.size();
    }

    @Override
    public String toString() {
        return "Oval{" +
                "communBox=" + communBox +
                '}';
    }
}
