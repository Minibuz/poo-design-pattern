package fr.uge.poo.cmdline.ex5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class Option {

    private String name;
    private Consumer<Iterator<String>> action;
    private List<String> aliases;
    private String documentation;
    private boolean required;

    private Option(Builder builder) {
        this.name = builder.name;
        this.action = builder.action;
        this.required = builder.required;
        this.aliases = builder.aliases;
        this.documentation = builder.documentation;
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

    public static class Builder {
        private String name;
        private boolean required = false;
        private Consumer<Iterator<String>> action;
        private List<String> aliases = new ArrayList<>();
        private String documentation;

        public Builder(String name, Consumer<List<String>> action, int arguments) {
            Objects.requireNonNull(name);
            Objects.requireNonNull(action);

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

        public Builder required() {
            this.required = true;
            return this;
        }

        public Builder alias(String name) {
            Objects.requireNonNull(name);
            aliases.add(name);
            return this;
        }

        public Builder documentation(String documentation) {
            Objects.requireNonNull(documentation);
            this.documentation = documentation;
            return this;
        }

        public Option build() {
            return new Option(this);
        }
    }

    static public Builder IntOptionBuilder(String name, IntConsumer action) {
        return new Builder(name, l -> {
            if(l.size() != 1) {
                throw new NoParameterGivenException("No parameters");
            }
            try {
                var number = Integer.parseInt(l.get(0));
                action.accept(number);
            } catch (NumberFormatException e) {
                throw new NoParameterGivenException("Not an int");
            }
        }, 1);
    }
}
