package fr.uge.poo.paint.ex9.graphics;

import fr.uge.poo.paint.ex9.GraphicSize;
import fr.uge.poo.paint.ex9.adapter.LibraryAdapter;

public final class Rect implements GraphicElement {

    private final CommunBox communBox;

    public Rect(CommunBox box) {
        this.communBox = box;
    }

    @Override
    public void draw(LibraryAdapter library, LibraryAdapter.MyColor color) {
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
