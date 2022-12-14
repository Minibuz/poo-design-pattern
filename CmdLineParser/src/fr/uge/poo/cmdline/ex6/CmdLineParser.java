package fr.uge.poo.cmdline.ex6;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class CmdLineParser {

    public static class Option {

        private final String name;
        private final Consumer<Iterator<String>> action;
        private final HashSet<String> aliases;

        private final HashSet<String> conflicted;

        /// Can be null
        private final String documentation;
        private final boolean required;

        private Option(Option.Builder builder) {
            this.name = builder.name;
            this.action = builder.action;
            this.required = builder.required;
            this.aliases = builder.aliases;
            this.documentation = builder.documentation;
            this.conflicted = builder.conflicted;
        }

        public String getName() {
            return name;
        }

        public Consumer<Iterator<String>> getAction() {
            return action;
        }

        public HashSet<String> getAliases() {
            return aliases;
        }

        public String getDocumentation() {
            return documentation;
        }

        public boolean isRequired() {
            return required;
        }

        public HashSet<String> getConflicted() {
            return conflicted;
        }

        public static class Builder {
            private final String name;
            private boolean required = false;
            private final Consumer<Iterator<String>> action;
            private final HashSet<String> aliases = new HashSet<>();
            private String documentation;

            private final HashSet<String> conflicted = new HashSet<>();

            public Builder(String name, int arguments, Consumer<List<String>> action) {
                this.name = Objects.requireNonNull(name);
                this.action = (iterator -> {
                    List<String> args = new ArrayList<>();
                    for(int i = 0; i<arguments; i++) {
                        if (iterator.hasNext()) {
                            var value = iterator.next();
                            if (value.startsWith("-")) {
                                throw new NoParameterGivenException(value + " is not a parameter but an option");
                            }
                            args.add(value);
                        } else {
                            throw new NoParameterGivenException( "Not enough arguments");
                        }
                    }
                    Objects.requireNonNull(action).accept(args);
                });
            }

            public Option.Builder required() {
                this.required = true;
                return this;
            }

            public Option.Builder alias(String name) {
                aliases.add(Objects.requireNonNull(name));
                return this;
            }

            public Option.Builder documentation(String documentation) {
                this.documentation = Objects.requireNonNull(documentation);
                return this;
            }

            public Option.Builder conflictWith(String conflict) {
                this.conflicted.add(Objects.requireNonNull(conflict));
                return this;
            }

            public Option build() {
                return new Option(this);
            }
        }

        static public Option.Builder IntOptionBuilder(String name, IntConsumer action) {
            return new Option.Builder(Objects.requireNonNull(name), 1, l -> {
                if(l.size() != 1) {
                    throw new NoParameterGivenException("No parameters");
                }
                try {
                    var number = Integer.parseInt(l.get(0));
                    Objects.requireNonNull(action).accept(number);
                } catch (NumberFormatException e) {
                    throw new NoParameterGivenException("Not an int");
                }
            });
        }
    }

    private static class OptionsManager {

        private final HashMap<String, Option> byName = new HashMap<>();

        private final HashSet<OptionsManagerObserver> observers = new HashSet<>();

        /**
         * Register the option with all its possible names
         * @param option
         */
        void register(Option option) {
            register(option.name, option);
            for (var alias : option.aliases) {
                register(alias, option);
            }
        }

        private void register(String name, Option option) {
            if (byName.containsKey(name)) {
                throw new IllegalStateException("Option " + name + " is already registered.");
            }

            observers.forEach(
                    optionsManagerObserver ->
                            optionsManagerObserver.onRegisteredOption(this, option));

            byName.put(name, option);
        }

        /**
         * This method is called to signal that an option is encountered during
         * a command line process
         *
         * @param optionName
         * @return the corresponding object option if it exists
         */

        Optional<Option> processOption(String optionName) {
            var opt = Optional.ofNullable(byName.get(optionName));
            opt.ifPresent(option -> observers.forEach(
                    optionsManagerObserver ->
                            optionsManagerObserver.onProcessedOption(this, option)));
            return opt;
        }

        /**
         * This method is called to signal the method process of the CmdLineParser is finished
         */
        void finishProcess() {
            observers.forEach(
                    optionsManagerObserver ->
                            optionsManagerObserver.onFinishedProcess(this));
        }

        void observe(OptionsManagerObserver observer) {
            observers.add(observer);
        }
    }

    interface OptionsManagerObserver {

        void onRegisteredOption(OptionsManager optionsManager,Option option);

        void onProcessedOption(OptionsManager optionsManager,Option option);

        void onFinishedProcess(OptionsManager optionsManager);
    }

    private static class RequiredObserver implements OptionsManagerObserver {

        private final HashSet<Option> requiredOptions = new HashSet<>();
        private final HashSet<Option> foundOptions = new HashSet<>();

        @Override
        public void onRegisteredOption(OptionsManager optionsManager, Option option) {
            if(option.isRequired()) {
                requiredOptions.add(option);
            }
        }

        @Override
        public void onProcessedOption(OptionsManager optionsManager, Option option) {
            if(option.isRequired()) {
                foundOptions.add(option);
            }
        }

        @Override
        public void onFinishedProcess(OptionsManager optionsManager) {
            if(!foundOptions.containsAll(requiredOptions)) {
                throw new NoParameterGivenException("Not all required parameters found");
            }
        }
    }

    private static class ConflictObserver implements OptionsManagerObserver {

        private final List<Option> options = new ArrayList<>();

        @Override
        public void onRegisteredOption(OptionsManager optionsManager, Option option) {
            // Do nothing
        }

        @Override
        public void onProcessedOption(OptionsManager optionsManager, Option option) {
            var conflicted = option.getConflicted();
            var names = option.getAliases();
            names.add(option.getName());

            options.forEach(opt -> {
                var conflict = opt.getConflicted();

                var optNames = opt.getAliases();
                optNames.add(opt.getName());

                if(conflict.stream().anyMatch(names::contains) || conflicted.stream().anyMatch(optNames::contains)) {
                    throw new NoParameterGivenException("Conflict");
                }
            });

            options.add(option);
        }

        @Override
        public void onFinishedProcess(OptionsManager optionsManager) {
            // Do nothing
        }
    }

    private static class DocumentationObserver implements OptionsManagerObserver {

        private final HashSet<Option> options = new HashSet<>();

        @Override
        public void onRegisteredOption(OptionsManager optionsManager, Option option) {
            options.add(option);
        }

        @Override
        public void onProcessedOption(OptionsManager optionsManager, Option option) {
            // Do nothing
        }

        @Override
        public void onFinishedProcess(OptionsManager optionsManager) {
            // Do nothing
        }

        public void usage() {
            options.stream()
                    .sorted(Comparator.comparing(Option::getName))
                    .forEach(opt -> System.out.println(opt.getName() + ": " + opt.getDocumentation()));
        }
    }

    private static class LoggerObserver implements OptionsManagerObserver {

        @Override
        public void onRegisteredOption(OptionsManager optionsManager, Option option) {
            System.out.println("Option "+option+ " is registered");
        }

        @Override
        public void onProcessedOption(OptionsManager optionsManager, Option option) {
            System.out.println("Option "+option+ " is processed");
        }

        @Override
        public void onFinishedProcess(OptionsManager optionsManager) {
            System.out.println("Process method is finished");
        }
    }

    private final OptionsManager optionsManager;
    private final DocumentationObserver observer;

    CmdLineParser() {
        this.optionsManager = new OptionsManager();
        this.observer = new DocumentationObserver();
        optionsManager.observe(new RequiredObserver());
        optionsManager.observe(new ConflictObserver());
        optionsManager.observe(this.observer);
        optionsManager.observe(new LoggerObserver());
    }

    public void addFlag(String name, Runnable runnable) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(runnable);

        Option option =
                new Option.Builder(
                        name,
                        0,
                        list -> runnable.run()).build();
        optionsManager.register(option);
    }

    public void addOptionWithOneParameter(String name, Consumer<String> consumer) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(consumer);

        Option option =
                new Option.Builder(
                        name,
                        1,
                        list -> consumer.accept(list.get(0))).build();
        optionsManager.register(option);
    }

    public void addOption(Option option) {
        Objects.requireNonNull(option);

        optionsManager.register(option);
    }

    public List<String> process(List<String> arguments) {
        ArrayList<String> files = new ArrayList<>();

        Iterator<String> iterator = arguments.iterator();
        while(iterator.hasNext()) {
            var argument = iterator.next();

            var optOption = optionsManager.processOption(argument);
            if (optOption.isPresent()) {
                optOption.get()
                        .getAction()
                        .accept(iterator);
            } else {
                if( argument.startsWith("-") ) {
                    throw new NoParameterGivenException(argument + " should be specified as an option");
                }
                files.add(argument);
            }
        }
        optionsManager.finishProcess();
        return files;
    }

    public void usage() {
        this.observer.usage();
    }
}
