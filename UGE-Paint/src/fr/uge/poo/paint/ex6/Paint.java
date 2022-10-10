package fr.uge.poo.paint.ex6;

import java.io.IOException;
import java.nio.file.Paths;

public class Paint {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("draw-big.txt");
        Drawing.loadFile(path).display();
    }
}
