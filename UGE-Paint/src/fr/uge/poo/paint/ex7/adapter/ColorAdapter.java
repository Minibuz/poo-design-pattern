package fr.uge.poo.paint.ex7.adapter;

import fr.uge.poo.coolgraphics.CoolGraphics;

import java.awt.*;

public class ColorAdapter {

    public static Color asColor(CoolGraphics.ColorPlus color) {
        return switch (color) {
            case RED -> Color.RED;
            case GREEN -> Color.GREEN;
            case ORANGE -> Color.ORANGE;
            case BLUE -> Color.BLUE;
            case BLACK -> Color.BLACK;
            case WHITE -> Color.WHITE;
        };
    }

    public static CoolGraphics.ColorPlus asColorPlus(Color color) {
        if(color.equals(Color.RED)) return CoolGraphics.ColorPlus.RED;
        if(color.equals(Color.GREEN)) return CoolGraphics.ColorPlus.GREEN;
        if(color.equals(Color.ORANGE)) return CoolGraphics.ColorPlus.ORANGE;
        if(color.equals(Color.BLUE)) return CoolGraphics.ColorPlus.BLUE;
        if(color.equals(Color.BLACK)) return CoolGraphics.ColorPlus.BLACK;
        if(color.equals(Color.WHITE)) return CoolGraphics.ColorPlus.WHITE;
        throw new IllegalArgumentException("That color cannot be used");
    }
}
