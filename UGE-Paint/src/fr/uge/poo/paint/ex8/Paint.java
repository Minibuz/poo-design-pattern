package fr.uge.poo.paint.ex8;

import fr.uge.poo.paint.ex8.adapter.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ServiceLoader;

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
        GraphicSize maxSize = draw.computeWindowSize(500, 500);

        Optional<LibraryFactory> libFactory = Optional.empty();
        ServiceLoader<LibraryFactory> serviceLoaderLibraryFactory = ServiceLoader.load(fr.uge.poo.paint.ex8.adapter.LibraryFactory.class);
        for(LibraryFactory factory : serviceLoaderLibraryFactory) {
            System.out.println("yay");
            libFactory = Optional.of(factory);
        }

        LibraryAdapter lib = libFactory.isPresent() ?
            libFactory.get()
                    .withName("area")
                    .withWidth(maxSize.width())
                    .withHeight(maxSize.height())
                    .make()
                : new SimpleGraphicsAdapter("area", maxSize.width(), maxSize.height());
        lib.clear(LibraryAdapter.MyColor.White);
        draw.drawAll(lib);
        lib.waitForMouseEvents((x,y) -> draw.onClick(lib, x, y));
    }

}
