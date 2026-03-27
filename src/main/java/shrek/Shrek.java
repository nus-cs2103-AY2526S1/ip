package shrek;

import java.util.Scanner;

import instruction.Instruction;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;
import util.ShrekException;

/**
 * The main Shrek application class. Initializes and runs the task management system.
 * This class serves as the core logic for processing user commands and managing tasks.
 */
public class Shrek {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a new Shrek instance with the specified file path for data storage.
     *
     * @param filePath the path to the data file
     */
    public Shrek(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Processes a user input command and returns the response.
     * This method replaces the text-based interaction for GUI usage.
     *
     * @param input the user input command
     * @return the response message from executing the command
     */
    public String getResponse(String input) {
        if (input.trim().isEmpty()) {
            return "Please enter a command!";
        }

        try {
            Instruction instruction = Parser.parse(input);
            String response = instruction.execute(tasks, ui, storage);
            return response;
        } catch (ShrekException e) {
            return ui.showError(e.getMessage());
        }
    }

    /**
     * Returns the welcome message for the application.
     * Used by GUI to display initial greeting.
     *
     * @return the welcome message string
     */
    public String getWelcomeMessage() {
        return ui.showWelcome();
    }

    /**
     * The main entry point of the application for text-based mode.
     * This is kept for backward compatibility but primarily used for testing.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Shrek("./data/shrek.txt").runTextMode();
    }

    /**
     * Runs the application in text-based mode for testing purposes.
     * This method is maintained for compatibility and testing.
     */
    public void runTextMode() {
        System.out.println(ui.showWelcome()); // showWelcome() returns a string that we print
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            try {
                Instruction instruction = Parser.parse(input);
                String response = instruction.execute(tasks, ui, storage);
                System.out.println(response);
                isExit = instruction.isExit();
            } catch (ShrekException e) {
                System.out.println(ui.showError(e.getMessage()));
            }
        }
        scanner.close();
    }
}
