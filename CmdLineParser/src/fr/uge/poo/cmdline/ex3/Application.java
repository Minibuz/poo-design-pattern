package fr.uge.poo.cmdline.ex3;

import java.nio.file.Path;
import java.util.List;

public class Application {

    public static void main(String[] args) throws NoParameterGivenException {
        var optionBuilder = new PaintSettings.PaintSettingsBuilder("paint");
        String[] arguments={"-legacy","-no-borders","filename1","filename2"};
        var cmdParser = new CmdLineParser();
        cmdParser.registerOption("-legacy", () -> optionBuilder.withLegacy(true));
        cmdParser.registerOption("-with-borders", () -> optionBuilder.withBordered(true));
        cmdParser.registerOption("-no-borders", () -> optionBuilder.withBordered(false));
        List<String> result = cmdParser.process(arguments);
        List<Path> files = result.stream().map(Path::of).toList();
        // this code replaces the rest of the application
        files.forEach(System.out::println);
        System.out.println(optionBuilder.build().toString());

    }
}
