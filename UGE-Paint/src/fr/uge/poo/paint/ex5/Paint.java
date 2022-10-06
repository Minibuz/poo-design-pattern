package fr.uge.poo.paint.ex5;

import java.io.IOException;
import java.nio.file.Paths;

public class Paint {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("draw2.txt");
        Drawing.loadFile(path).display();
    }
}
