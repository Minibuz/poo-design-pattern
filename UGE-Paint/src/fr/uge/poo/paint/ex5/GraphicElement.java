package fr.uge.poo.paint.ex5;

import java.awt.*;

public sealed interface GraphicElement permits Line, Oval, Rect {

    /**
     * Draw the element
     */
    void draw(Graphics2D graphics, Color color);

    /**
     * Compute the distance between the center of the graphic element and the x, y given in argument.
     *
     * @param x
     * @param y
     * @return
     */
    int distance(int x, int y);

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
