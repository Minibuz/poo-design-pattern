package fr.uge.poo.paint.ex8;


public record GraphicSize(int width, int height) {
    public GraphicSize(int width, int height) {
        this.width = Math.max(width, 500);
        this.height = Math.max(height, 500);
    }

    public GraphicSize Union(GraphicSize size) {
        return new GraphicSize(width > size.width() ? width : size.width(), height > size.height() ? height : size.height());
    }
}
