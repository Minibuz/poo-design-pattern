package fr.uge.poo.cmdline.ex3.options;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public record OptionNoArg(String name, Runnable run) implements Option {

    public OptionNoArg {
        Objects.requireNonNull(name);
        Objects.requireNonNull(run);
    }

    @Override
    public String getOptionName() {
        return this.name;
    }

    @Override
    public Optional<Runnable> getRunnable() {
        return Optional.of(this.run);
    }

    @Override
    public Optional<Consumer<List<String>>> getConsumer() {
        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getDefaultValue() {
        return Optional.empty();
    }
}
