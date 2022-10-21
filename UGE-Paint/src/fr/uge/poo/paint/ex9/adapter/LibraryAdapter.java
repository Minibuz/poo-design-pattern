package fr.uge.poo.paint.ex9.adapter;

public interface LibraryAdapter {

    enum MyColor {
        Black, White, Orange
    }

    @FunctionalInterface
    interface MouseClickCallBack {
        void onClick(int x, int y);
    }

    void clear(MyColor color);
    void drawLine(int x1, int y1, int x2, int y2, MyColor color);
    void drawOval(int x, int y, int width, int height, MyColor color);

    void waitForMouseEvents(MouseClickCallBack callBack);

    default void drawRect(int x, int y, int width, int height, MyColor color) {
        drawLine(x, y, x+width, y, color);
        drawLine(x+width, y, x+width, y+height, color);
        drawLine(x+width, y+height, x, y+height, color);
        drawLine(x, y+height, x, y, color);
    }

    default void launch() {
        // Do nothing, can be use for render optimization
    }
}
