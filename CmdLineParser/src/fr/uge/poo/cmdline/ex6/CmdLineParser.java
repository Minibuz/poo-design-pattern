package fr.uge.poo.cmdline.ex6;

import java.util.*;
import java.util.function.Consumer;

public class CmdLineParser {

    private interface OptionObserver {
        void onRegister(OptionsManager optM, Option option);
        void onProcess(OptionsManager optM, Option option);
        void onFinish(OptionsManager optM);
    }

    private static class RequiredObserver implements OptionObserver {

        @Override
        public void onRegister(OptionsManager optM, Option option) {

        }

        @Override
        public void onProcess(OptionsManager optM, Option option) {

        }

        @Override
        public void onFinish(OptionsManager optM) {

        }
    }

    private static class ConflictObserver implements OptionObserver {

        @Override
        public void onRegister(OptionsManager optM, Option option) {

        }

        @Override
        public void onProcess(OptionsManager optM, Option option) {

        }

        @Override
        public void onFinish(OptionsManager optM) {

        }
    }

    private static class FoundObserver implements OptionObserver {

        @Override
        public void onRegister(OptionsManager optM, Option option) {

        }

        @Override
        public void onProcess(OptionsManager optM, Option option) {

        }

        @Override
        public void onFinish(OptionsManager optM) {

        }
    }

    private final HashMap<String, Option> registeredOptions = new HashMap<>();
    private final HashSet<Option> requiredOption = new HashSet<>();
    private final HashSet<Option> foundOption = new HashSet<>();

    public void addFlag(String option, Runnable runnable) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(runnable);

        Option op = new Option.Builder(option, list -> runnable.run(), 0).build();
        var optInMap = registeredOptions.putIfAbsent(option, op);
        if( optInMap != null ) {
            throw new IllegalStateException("Option is already set in the options");
        }
        if( op.isRequired() ) {
            requiredOption.add(op);
        }
    }

    public void addOptionWithOneParameter(String option, Consumer<String> consumer) {
        Objects.requireNonNull(option);
        Objects.requireNonNull(consumer);

        Option op = new Option.Builder(option, list -> consumer.accept(list.get(0)), 1).build();
        var optInMap = registeredOptions.putIfAbsent(option, op);
        if (optInMap != null) {
            throw new IllegalStateException("Option is already set in the options");
        }
        if( op.isRequired() ) {
            requiredOption.add(op);
        }
    }

    public void addOptionWithParameters(Option option) {
        Objects.requireNonNull(option);

        var allNames = option.getAliases();
        allNames.add(option.getName());
        allNames.forEach(str -> {
            var optInMap = registeredOptions.putIfAbsent(option.getName(), option);
            if (optInMap != null) {
                throw new IllegalStateException("Option is already set in the options");
            }
        });
        if( option.isRequired() ) {
            requiredOption.add(option);
        }
    }

    public List<String> process(List<String> arguments) {
        ArrayList<String> files = new ArrayList<>();

        Iterator<String> iterator = arguments.iterator();
        while(iterator.hasNext()) {
            var argument = iterator.next();

            var option = registeredOptions.get(argument);
            if( foundOption.contains(option) ) {
                throw new IllegalArgumentException("Already called that option");
            }
            if (option != null) {
                option.getAction().accept(iterator);
                if ( option.isRequired() ) {
                    foundOption.add(option);
                }
            } else {
                if( argument.startsWith("-") ) {
                    throw new NoParameterGivenException(argument + " should be specified as an option");
                }
                files.add(argument);
            }
        }
        if(!foundOption.containsAll(requiredOption)) {
            throw new NoParameterGivenException("No obligatory option found");
        }
        foundOption.clear();
        return files;
    }

    public void usage() {
        var options = registeredOptions.values();
        options.stream()
                .distinct()
                .sorted(Comparator.comparing(Option::getName))
                .forEach(opt -> System.out.println(opt.getName() + ": " + opt.getDocumentation()));
    }
}
