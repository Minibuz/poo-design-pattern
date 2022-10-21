package fr.uge.poo.paint.ex9.adapter;

import fr.uge.poo.simplegraphics.SimpleGraphics;

import java.awt.*;
import java.util.function.Consumer;

public class SimpleGraphicsAdapter implements LibraryAdapter {

    private final Object lock = new Object();

    private final SimpleGraphics simpleGraphics;

    private Consumer<Graphics2D> consumer = graphics2D -> {};

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
        this.consumer = consumer.andThen(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawLine(x1, y1, x2, y2);
        });
    }

    @Override
    public void drawRect(int x, int y, int width, int height, MyColor color) {
        this.consumer = consumer.andThen(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawRect(x, y, width, height);
        });
    }

    @Override
    public void drawOval(int x, int y, int width, int height, MyColor color) {
        this.consumer = consumer.andThen(graphics2D -> {
            graphics2D.setColor(asColor(color));
            graphics2D.drawOval(x, y, width, height);
        });
    }

    @Override
    public void waitForMouseEvents(MouseClickCallBack callBack) {
        simpleGraphics.waitForMouseEvents(callBack::onClick);
    }

    @Override
    public void launch() {
        synchronized (lock) {
            simpleGraphics.render(consumer);
            consumer = graphics2D -> {};
        }
    }
}
