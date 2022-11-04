package fr.uge.poo.cmdline.ex2;

import java.util.*;
import java.util.function.Consumer;

public class CmdLineParser {

    private final HashMap<String, Consumer<Iterator<String>>> registeredOptions = new HashMap<>();

    public void registerOption(String option, Runnable runnable) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(runnable);

        Consumer<Iterator<String>> value = registeredOptions.putIfAbsent(option, it -> runnable.run());
        if( value != null ) {
            throw new IllegalStateException("Option is already set in the options");
        }
    }

    public void registerWithParameter(String option, Consumer<String> consumer) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(consumer);

        Consumer<Iterator<String>> value = registeredOptions.putIfAbsent(option, it -> {
            if(it.hasNext()) {
                consumer.accept(it.next());
            } else {
                throw new NoParameterGiven(option + " need at least one parameter");
            }
        });
        if( value != null ) {
            throw new IllegalStateException("Option is already set in the options");
        }
    }

    public List<String> process(String[] arguments) {
        ArrayList<String> files = new ArrayList<>();

        Iterator<String> iterator = Arrays.stream(arguments).iterator();
        while(iterator.hasNext()) {
            var argument = iterator.next();

            var consumer = registeredOptions.get(argument);
            if (consumer != null) {
                consumer.accept(iterator);
            } else {
                files.add(argument);
            }
        }
        return files;
    }
}
