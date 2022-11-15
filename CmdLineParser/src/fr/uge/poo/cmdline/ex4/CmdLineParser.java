package fr.uge.poo.cmdline.ex4;

import java.util.*;
import java.util.function.Consumer;

public class CmdLineParser {

    private final HashMap<String, Option> registeredOptions = new HashMap<>();
    private final List<String> requiredOption = new ArrayList<>();
    private final List<String> foundOption = new ArrayList<>();

    public void addFlag(String option, Runnable runnable) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(runnable);

        Option op = new Option.OptionBuilder(option).noParameter(runnable).build();
        var optInMap = registeredOptions.putIfAbsent(option, op);
        if( optInMap != null ) {
            throw new IllegalStateException("Option is already set in the options");
        }
        if( op.isRequired() ) {
            requiredOption.add(option);
        }
    }

    public void addOptionWithOneParameter(String option, Consumer<String> consumer) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(consumer);

        Option op = new Option.OptionBuilder(option).withParameter(consumer).build();
        var optInMap = registeredOptions.putIfAbsent(option, op);
        if (optInMap != null) {
            throw new IllegalStateException("Option is already set in the options");
        }
        if( op.isRequired() ) {
            requiredOption.add(option);
        }
    }

    public void addOption(Option option) {
        Objects.requireNonNull(option);

        var optInMap = registeredOptions.putIfAbsent(option.getName(), option);
        if (optInMap != null) {
            throw new IllegalStateException("Option is already set in the options");
        }
        if( option.isRequired() ) {
            requiredOption.add(option.getName());
        }
    }

    public List<String> process(List<String> arguments) {
        ArrayList<String> files = new ArrayList<>();

        Iterator<String> iterator = arguments.iterator();
        while(iterator.hasNext()) {
            var argument = iterator.next();

            var option = registeredOptions.get(argument);
            if (option != null) {
                option.getAction().accept(iterator);
                if ( option.isRequired() ) {
                    foundOption.add(option.getName());
                }
            } else {
                if( argument.startsWith("-") ) {
                    throw new NoParameterGiven(argument + " should be specified as an option");
                }
                files.add(argument);
            }
        }
        if(!foundOption.equals(requiredOption)) {
            throw new NoParameterGiven("No obligatory option found");
        }
        foundOption.removeAll(foundOption);
        return files;
    }
}
