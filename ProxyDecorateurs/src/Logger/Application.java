package Logger;

public class Application {

    public static void main(String[] args) {
        System.out.println("Do something");
        Logger logger = new AllLogger();
        logger.filter(Logger.Level.WARNING);
        System.out.println("Info");
        logger.log(Logger.Level.INFO, "Info");
        System.out.println("Warning");
        logger.log(Logger.Level.WARNING, "Warning");
        System.out.println("Error");
        logger.log(Logger.Level.ERROR, "Error");
    }
}
