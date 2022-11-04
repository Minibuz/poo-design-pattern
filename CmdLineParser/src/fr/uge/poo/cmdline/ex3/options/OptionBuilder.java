package fr.uge.poo.cmdline.ex3.options;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class OptionBuilder {

    private String optionName;
    private Runnable runnable;
    private Consumer<List<String>> consumer;
    private List<String> defaultValueArgs = Collections.emptyList();

    OptionBuilder(String name) {
        Objects.requireNonNull(name);
        this.optionName = name;
    }

    public OptionBuilder setNoOption(Runnable runnable) {
        Objects.requireNonNull(runnable);
        this.runnable = runnable;
        return this;
    }

    public OptionBuilder setOptions(Consumer<List<String>> consumer, List<String> defaultValue) {
        Objects.requireNonNull(consumer);
        this.consumer = consumer;
        this.defaultValueArgs = defaultValue;
        return this;
    }

    public Option build() {
        if (runnable != null) {
            return new OptionNoArg(optionName, runnable);
        } else {
            return new OptionWithArgs(optionName, consumer, defaultValueArgs);
        }
    }
}