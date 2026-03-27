package phuc;

import phuc.exception.PhucException;

/**
 * Launcher class that serves as the entry point for the Phuc application.
 * This class initializes and starts the main application.
 */
public class Launcher {
    /**
     * Starts the Phuc application.
     *
     * @param args command line arguments (not used)
     * @throws PhucException if any application-specific error occurs during startup
     */
    public static void main(String[] args) throws PhucException {
        Phuc phuc = new Phuc();
        phuc.runForCommandLineInterface();
    }
}
