package Logger;

public class SystemLogger implements Logger {

    private static SystemLogger singleton = null;

    private Level filter = Level.INFO;

    @Override
    public void log(Level level, String message) {
        if(level.ordinal() <= filter.ordinal()) {
            System.err.println(level + " " + message);
        }
    }

    @Override
    public void filter(Level level) {
        filter = level;
    }

    private SystemLogger() {
    }

    public static SystemLogger getInstance() {
        if(singleton == null) {
            singleton = new SystemLogger();
        }
        return singleton;
    }


}