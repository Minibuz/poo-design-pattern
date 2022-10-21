package fr.uge.poo.paint.ex8.adapter;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;

public class SimpleGraphicsAdapter implements LibraryAdapter {

    private final SimpleGraphics simpleGraphics;

    static Color asColor(MyColor color) {
        return switch (color) {
            case Black -> Color.BLACK;
            case White -> Color.WHITE;
            case Orange -> Color.ORANGE;
        };
    }

    public SimpleGraphicsAdapter(String title, int width, int height) {
        this.simpleGraphics = new SimpleGraphics(title, width, height);
    }


    @Override
    public void clear(MyColor color) {
        simpleGraphics.clear(asColor(color));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, MyColor color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawLine(x1, y1, x2, y2);
        });
    }

    @Override
    public void drawRect(int x, int y, int width, int height, MyColor color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawRect(x, y, width, height);
        });
    }

    @Override
    public void drawOval(int x, int y, int width, int height, MyColor color) {
        simpleGraphics.render(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawOval(x, y, width, height);
        });
    }

    @Override
    public void waitForMouseEvents(MouseClickCallBack callBack) {
        simpleGraphics.waitForMouseEvents(callBack::onClick);
    }
}
