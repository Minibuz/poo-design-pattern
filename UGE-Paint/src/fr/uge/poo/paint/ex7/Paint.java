package fr.uge.poo.paint.ex7;

import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Paint {
    public static void main(String[] args) throws IOException {
        var libraryUse = LibraryAdapter.Library.coolgraphics;
        if(args.length>0 && "-legacy".equals(args[0])) {
            libraryUse = LibraryAdapter.Library.simplegraphics;
        }
        var path = Paths.get("draw-big.txt");
        Drawing.loadFile(path, libraryUse).display();
    }
}
