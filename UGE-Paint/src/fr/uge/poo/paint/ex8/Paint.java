package fr.uge.poo.paint.ex8;

import fr.uge.poo.paint.ex8.adapter.CoolGraphicAdapter;
import fr.uge.poo.paint.ex8.adapter.LibraryAdapter;
import fr.uge.poo.paint.ex8.adapter.SimpleGraphicsAdapter;

import java.io.IOException;
import java.nio.file.Paths;

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
        var draw = Drawing.loadFile(path);

        var libraryUse = "coolgraphics";
        if(args.length>1 && "-legacy".equals(args[1])) {
            libraryUse = "simplegraphics";
        }

        GraphicSize maxSize = draw.computeWindowSize(500, 500);
        LibraryAdapter lib = switch (libraryUse) {
            case "simplegraphics" ->
                new SimpleGraphicsAdapter("area", maxSize.width(), maxSize.height());
            case "coolgraphics" ->
                new CoolGraphicAdapter("area", maxSize.width(), maxSize.height());
            default -> throw new IllegalArgumentException();
        };
        lib.clear(LibraryAdapter.MyColor.White);
        draw.drawAll(lib);
        lib.waitForMouseEvents((x,y) -> draw.onClick(lib, x, y));
    }

}
