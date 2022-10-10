package fr.uge.poo.paint.ex7;


public record GraphicSize(int width, int height) {
    public GraphicSize(int width, int height) {
        this.width = Math.max(width, 500);
        this.height = Math.max(height, 500);
    }
}
