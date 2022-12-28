package Logger;

import java.nio.file.Path;

public class AllLogger implements Logger {

    private PathLogger pathLogger = new PathLogger(Path.of("/tmp/logs.txt"));

    private SystemLogger systemLogger = SystemLogger.getInstance();

    @Override
    public void log(Level level, String message) {
        systemLogger.log(level, message);
        pathLogger.log(level, message);
    }

    @Override
    public void filter(Level level) {
        systemLogger.filter(level);
        pathLogger.filter(level);
    }
}
