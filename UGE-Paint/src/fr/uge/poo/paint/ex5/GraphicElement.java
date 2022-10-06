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
}
