package fr.uge.poo.paint.ex7.adapter;

import java.awt.*;

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

    void drawRect(int x, int y, int width, int height, MyColor color);

    void drawOval(int x, int y, int width, int height, MyColor color);

    void waitForMouseEvents(MouseClickCallBack callBack);
}
