package izayoi;

import java.util.List;
import java.util.Scanner;

import izayoi.exception.IzayoiException;
import izayoi.input.CommandType;
import izayoi.input.InputReader;
import izayoi.logger.Logger;
import izayoi.task.Task;

/**
 * Handles the inputs during a user session and applies their arguments
 */
public class InputManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    private final TaskManager manager;
    private Logger logger;

    /**
     * Initializes a new InputManager for the user session
     * @param tm the TaskManager for the session
     * @param l the Logger that handles the output of the session
     */
    public InputManager(TaskManager tm, Logger l) {
        this.manager = tm;
        this.logger = l;
    }

    /**
     * Greets the user
     */
    public void hello() {
        logString(" Hello! I'm Sakuya Izayoi\n What can I do for you?");
    }

    /**
     * Refuses the user
     */
    public void refuse() {
        logString("I'm already done with you. Go home.");
    }

    /**
     * Says goodbye to the user
     */
    public void goodbye() {
        logString(" Hmph. About time you stopped talking... Do come again.");
    }

    /**
     * Handles a single line and applies the necessary modifications to the user's tasks
     * @param s the String to be read as a command
     * @return whether the command is an exit command
     */
    public boolean handleLine(String s) {
        InputReader input = new InputReader(s);

        if (input.getCommandType().equals(CommandType.EXIT)) {
            return false;
        }

        switch(input.getCommandType()) {
        case MARK:
            logString(manager.markTask(input.getIndex()));
            break;
        case UNMARK:
            logString(manager.unmarkTask(input.getIndex()));
            break;
        case LIST:
            logString(manager.toString());
            break;
        case DELETE:
            logString(manager.deleteTask(input.getIndex()));
            break;
        case TODO, EVENT, DEADLINE, TIMED:
            try {
                logString(manager.addTask(Task.createTask(input)));
            } catch (IzayoiException e) {
                logString(e.getMessage() + "\nYour insolence has been noted.");
            }
            break;
        case SEARCH:
            logString(manager.findTask(input));
            break;
        case EMPTY:
            break;
        default:
            logString("I don't understand that command. Have you forgotten how to speak english?");
        }
        return true;
    }

    /**
     * Reads the next line from std input and handles it appropriately
     * @return whether the user entered an exit command
     */
    public boolean nextLine() {
        String s = SCANNER.nextLine();
        logLine();
        boolean result = handleLine(s);
        logLine();
        return result;
    }

    /**
     * Reads all lines sequentially and handles them as individual commands
     * @param l the lines to be processed.
     */
    public void readLines(List<String> l) {
        for (String s : l) {
            if (!handleLine(s)) {
                return;
            }
        }
    }

    public void setLogger(Logger l) {
        this.logger = l;
    }

    /**
     * Logs a String to the output
     * @param s the String to be logged
     */
    private void logString(String s) {
        this.logger.log(s);
    }

    /**
     * Logs a line break to the output
     */
    public void logLine() {
        this.logger.log("____________________________________________________________");
    }
}
