package izayoi.logger;

/**
 * Represents a logger for the interactive command line a user will be using
 */
public class CliLogger implements Logger {

    @Override
    public void log(String string) {
        System.out.println(string);
    }


}
