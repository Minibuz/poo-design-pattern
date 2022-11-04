package fr.uge.poo.cmdline.ex3.options;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public interface Option {
    String getOptionName();
    Optional<Runnable> getRunnable();
    Optional<Consumer<List<String>>> getConsumer();
    Optional<List<String>> getDefaultValue();
}
