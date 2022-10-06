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
        if(tokens.length < 1) {
            throw new IllegalArgumentException();
        }

        Shapes shape = Shapes.getShape(tokens[0]);
        return switch (shape) {
            case LINE -> new Line(
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]));
            case RECT -> new Rect(
                    new CommunBox(Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4])));
            case OVAL -> new Oval(
                    new CommunBox(Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            Integer.parseInt(tokens[3]),
                            Integer.parseInt(tokens[4])));
        };
    }

    enum Shapes {
        LINE("line"),
        OVAL("ellipse"),
        RECT("rectangle");

        private final String call;
        Shapes(String call) {
            this.call = call;
        }

        public static Shapes getShape(String string) {
            switch (string) {
                case "line" -> {
                    return LINE;
                }
                case "rectangle" -> {
                    return RECT;
                }
                case "ellipse" -> {
                    return OVAL;
                }
                default -> {
                    throw new IllegalArgumentException();
                }
            }
        }
    }
}
