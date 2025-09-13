package hermione.ui.console;

import java.util.Scanner;

import hermione.Hermione;
import hermione.parsers.ResponseResult;
import hermione.utils.UiUtils;

/**
 * Represents the console user interface for the Hermione application.
 * Handles user input and output, allowing interaction with the application.
 */
public class ConsoleUi {

    private static final String DIVIDER = "----------------------------------------";
    private static boolean isRunning = true;

    private final Scanner scanner;
    private final String name;

    private final Hermione hermione = new Hermione("data/tasks.csv");

    /**
     * Creates a new ConsoleUi instance with the specified name and input processor.
     *
     * @param name The name of the application or bot.
     */
    public ConsoleUi(String name) {
        this.scanner = new Scanner(System.in);
        this.name = name;
    }

    /**
     * Exits the application by setting the running state to false.
     * This will cause the main loop in the start method to terminate.
     */
    public static void exit() {
        isRunning = false;
    }

    /**
     * Starts the console user interface, displaying a greeting message
     * and entering a loop to process user input commands until the application is
     * exited.
     */
    public void start() {
        printMessage(UiUtils.getGreeting(name));

        while (isRunning) {
            String input = getUserInput();
            ResponseResult result = hermione.getResponse(input);
            printMessage(result.getMessage());
        }
    }

    private void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    private String getUserInput() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
