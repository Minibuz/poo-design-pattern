package fr.uge.poo.paint.ex7.adapter;

import fr.uge.poo.coolgraphics.CoolGraphics;
import fr.uge.poo.paint.ex7.graphics.GraphicElement;

import java.awt.*;
import java.util.Comparator;
import java.util.List;

public class CoolGraphicAdapter implements LibraryAdapter {

    private final CoolGraphics graphics;

    static CoolGraphics.ColorPlus asColor(MyColor color) {
        return switch (color) {
            case Black -> CoolGraphics.ColorPlus.BLACK;
            case White -> CoolGraphics.ColorPlus.WHITE;
            case Orange -> CoolGraphics.ColorPlus.ORANGE;
        };
    }

    public CoolGraphicAdapter(String title, int width, int height) {
        this.graphics = new CoolGraphics(title, width, height);
    }


    @Override
    public void clear(MyColor color) {
        graphics.repaint(asColor(color));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, MyColor color) {
        graphics.drawLine(x1, y1, x2, y2, asColor(color));
    }

    @Override
    public void drawOval(int x, int y, int width, int height, MyColor color) {
        graphics.drawEllipse(x, y, width, height, asColor(color));
    }

    @Override
    public void waitForMouseEvents(MouseClickCallBack callback) {
        graphics.waitForMouseEvents(callback::onClick);
    }
}
