package fr.uge.poo.paint.ex7;

import fr.uge.poo.paint.ex7.adapter.LibraryAdapter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

public class Paint {

    /**
     * Usage :
     *  U need a first param as a file then u can put -legacy or nothing to select the graphics
     *
     * @param args
     * @throws IOException
     *          File cannot be read.
     */
    public static void main(String[] args) throws IOException {
        if(args.length<1) {
            throw new IllegalArgumentException("first arg need to be a file name -" +
                    "file need to be at the root of the project");
        }
        var path = Paths.get(args[0]);

        var libraryUse = LibraryAdapter.Library.coolgraphics;
        if(args.length>1 && "-legacy".equals(args[1])) {
            libraryUse = LibraryAdapter.Library.simplegraphics;
        }
        Drawing.loadFile(path, libraryUse).display();
    }
}
