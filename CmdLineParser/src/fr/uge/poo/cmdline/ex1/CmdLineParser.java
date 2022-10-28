package fr.uge.poo.cmdline.ex1;

import java.util.*;
import java.util.function.Function;

public class CmdLineParser {

    private final HashMap<String, Runnable> registeredOptions = new HashMap<>();

    public void registerOption(String option, Runnable runnable) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(runnable);

        var run = Optional.ofNullable(registeredOptions.putIfAbsent(option, runnable));
        if(run.isPresent()) {
            throw new IllegalStateException("Option is already set in the options");
        }
    }

    public List<String> process(String[] arguments) {
        ArrayList<String> files = new ArrayList<>();
        for (String argument : arguments) {
            var optionalRunnable = Optional.ofNullable(registeredOptions.get(argument));
            if (optionalRunnable.isPresent()) {
                optionalRunnable.get().run();
            } else {
                files.add(argument);
            }
        }
        return files;
    }
}
