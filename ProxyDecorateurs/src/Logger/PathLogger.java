package Logger;

import javax.swing.text.AbstractDocument;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PathLogger implements Logger, AutoCloseable {
    private BufferedWriter writer;

    private Level filter = Level.INFO;

    @Override
    public void log(Level level, String message) {
        if(level.ordinal() <= filter.ordinal()) {
            try {
                writer.newLine();
                writer.write(level + " " + message);
                writer.flush();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    @Override
    public void filter(Level level) {
        filter = level;
    }

    public PathLogger(Path path) {
        try {
            this.writer = Files.newBufferedWriter(path,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void close() throws Exception {
        writer.close();
    }
}
