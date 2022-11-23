package fr.uge.poo.cmdline.ex5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Option {

    private String name;
    private Consumer<Iterator<String>> action;
    private List<String> aliases;
    private String documentation;
    private boolean required;

    private Option(OptionBuilder optionBuilder) {
        this.name = optionBuilder.name;
        this.action = optionBuilder.action;
        this.required = optionBuilder.required;
        this.aliases = optionBuilder.aliases;
        this.documentation = optionBuilder.documentation;
    }

    public String getName() {
        return name;
    }

    public Consumer<Iterator<String>> getAction() {
        return action;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getDocumentation() {
        return documentation;
    }

    public boolean isRequired() {
        return required;
    }

    public static class OptionBuilder {
        private String name;
        private boolean required = false;
        private Consumer<Iterator<String>> action;
        private List<String> aliases = new ArrayList<>();
        private String documentation;

        public OptionBuilder(String name, Consumer<List<String>> action, int arguments) {
            this.name = name;
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
                action.accept(args);
            });
        }

        public OptionBuilder required() {
            this.required = true;
            return this;
        }

        public OptionBuilder alias(String name) {
            Objects.requireNonNull(name);
            aliases.add(name);
            return this;
        }

        public OptionBuilder documentation(String documentation) {
            Objects.requireNonNull(documentation);
            this.documentation = documentation;
            return this;
        }

        public Option build() {
            return new Option(this);
        }
    }
}
