package izayoi;

import java.io.IOException;
import java.util.Scanner;

import izayoi.file.FileManager;
import izayoi.logger.CliLogger;
import izayoi.logger.Logger;
import izayoi.logger.VoidLogger;

/**
 * Main class for handling user input and output
 * @author POeticPotatoes
 */
public class Izayoi {
    public static final Scanner SCANNER = new Scanner(System.in);
    private final FileManager fm = new FileManager(FileManager.SAVE_FILE);
    private final TaskManager tm = new TaskManager();
    private final InputManager im = new InputManager(tm, new VoidLogger());
    private boolean isAlive = true;

    /**
     * Initializes a new instance of Izayoi
     * @param logger the logger to use for Izayoi's output
     * @param shouldLoadSave whether to load any preexisting task data
     */
    public Izayoi(Logger logger, boolean shouldLoadSave) {
        try {
            im.readLines(fm.readFile());
        } catch (Exception e) {
            System.out.println("Could not read save file.");
            System.out.println(e.getMessage());
        }
        im.setLogger(logger);
        im.hello();
    }

    /**
     * Initializes a new instance of Izayoi. Any prior save is loaded by default
     * @param logger the logger to use for Izayoi's output
     */
    public Izayoi(Logger logger) {
        this(logger, true);
    }

    /**
     * Processes a command and saves the updated task list
     * @param command the command to be processed
     * @return whether Izayoi should exit after this command
     */
    public boolean runCommand(String command) {
        if (!isAlive) {
            im.refuse();
            return false;
        }
        isAlive = im.handleLine(command);
        try {
            fm.writeToFile(tm.commandify());
        } catch (IOException e) {
            System.out.println("Could not save file.");
            System.out.println(e.getMessage());
        }
        if (!isAlive) {
            im.goodbye();
        }
        return isAlive;
    }

    /**
    * Main method to run the application as a CLI
    * @param args command line arguments provided to the program
    */
    public static void main(String[] args) {
        FileManager fm = new FileManager(FileManager.SAVE_FILE);
        TaskManager tm = new TaskManager();
        InputManager im = new InputManager(tm, new VoidLogger());

        try {
            im.readLines(fm.readFile());
        } catch (Exception e) {
            System.out.println("Could not read save file.");
            System.out.println(e.getMessage());
        }
        im.setLogger(new CliLogger());
        im.logLine();
        im.hello();
        im.logLine();
        while (im.nextLine()) {
            try {
                fm.writeToFile(tm.commandify());
            } catch (IOException e) {
                System.out.println("Could not save file.");
                System.out.println(e.getMessage());
            }
        }
        try {
            fm.writeToFile(tm.commandify());
            System.out.println("File successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.goodbye();
        im.logLine();
    }
}
