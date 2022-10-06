package fr.uge.poo.paint.ex5;

public class GraphicElementFactory {

    static GraphicElement fromLine(String[] tokens) {
        if(tokens.length < 1) {
            throw new IllegalArgumentException();
        }

        String name = tokens[0];
        return switch (name) {
            case "line" -> new Line.LineBuilder(
                    Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4]))
                    .build();
            case "rectangle" -> new Rect(
                    new CommunBox(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4])));
            case "ellipse" -> new Oval(
                    new CommunBox(Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]),
                    Integer.parseInt(tokens[4])));
            default -> throw new DrawingNotImplementException("Type of drawing not supported");
        };
    }
}
