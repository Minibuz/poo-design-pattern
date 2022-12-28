package Logger;

public interface Logger {

    enum Level {
        ERROR, WARNING, INFO
    }

    void log(Level level, String message);

    void filter(Level level);
}
