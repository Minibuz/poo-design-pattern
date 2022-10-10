package fr.uge.poo.paint.ex7.graphics;

import fr.uge.poo.paint.ex7.GraphicSize;

public final class CommunBox {
    final int x;
    final int y;
    final int width;
    final int height;
    final int xCenter;
    final int yCenter;

    public CommunBox(int x, int y, int width, int height) {
        if(width<0 || height<0) {
            throw new IllegalArgumentException();
        }

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xCenter = this.x + this.width/2;
        this.yCenter = this.y + this.height/2;
    }

    public int distance(int x, int y) {
        return ((x - xCenter) * (x - xCenter)) + ((y - yCenter) * (y - yCenter));
    }

    public GraphicSize size() {
        return new GraphicSize(x+width, y+height);
    }
}
