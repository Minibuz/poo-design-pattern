package fr.uge.poo.paint.ex3;

import java.awt.*;

public sealed interface GraphicElement permits Line, Oval, Rect {

    /**
     * Draw the element
     */
    void draw(Graphics2D graphics);

    static GraphicElement fromLine(String[] tokens) {
        return switch (tokens[0]) {
            case "line" -> new Line(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]));
            case "rectangle" -> new Rect(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]));
            case "ellipse" -> new Oval(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]));
            default -> throw new UnsupportedOperationException("Type of drawing not supported");
        };
    }
}
