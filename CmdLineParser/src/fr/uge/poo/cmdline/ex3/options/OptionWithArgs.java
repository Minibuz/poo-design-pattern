package fr.uge.poo.cmdline.ex3.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public record OptionWithArgs(String name, Consumer<List<String>> consumer, List<String> defaultValue) implements Option {

    public OptionWithArgs {
        Objects.requireNonNull(name);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(defaultValue);
    }

    @Override
    public String getOptionName() {
        return this.name;
    }

    @Override
    public Optional<Runnable> getRunnable() {
        return Optional.empty();
    }

    @Override
    public Optional<Consumer<List<String>>> getConsumer() {
        return Optional.of(this.consumer);
    }

    @Override
    public Optional<List<String>> getDefaultValue() {
        return Optional.of(this.defaultValue);
    }
}
