package fr.uge.poo.cmdline.ex3;

import java.util.*;
import java.util.function.Consumer;

public class CmdLineParser {

    private final HashMap<String, Consumer<Iterator<String>>> registeredOptions = new HashMap<>();

    public void registerOption(String option, Runnable runnable) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(runnable);

        var run = Optional.ofNullable(registeredOptions.putIfAbsent(option, it -> runnable.run()));
        if(run.isPresent()) {
            throw new IllegalStateException("Option is already set in the options");
        }
    }

    public void registerWithParameter(String option, Consumer<String> consumer) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(consumer);

        var run = Optional.ofNullable(registeredOptions.putIfAbsent(option, it -> {
            if(it.hasNext()) {
                consumer.accept(it.next());
            } else {
                throw new NoParameterGivenException(option + " need at least one parameter");
            }
        }));
        if(run.isPresent()) {
            throw new IllegalStateException("Option is already set in the options");
        }
    }

    public List<String> process(String[] arguments) {
        ArrayList<String> files = new ArrayList<>();

        var iterator = Arrays.stream(arguments).iterator();
        while(iterator.hasNext()) {
            var argument = iterator.next();
            var optional = Optional.ofNullable(registeredOptions.get(argument));
            if (optional.isPresent()) {
                var optionsValues = optional.get();
                optionsValues.accept(iterator);
            } else {
                files.add(argument);
            }
        }
        return files;
    }
}
