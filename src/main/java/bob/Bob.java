package bob;

import java.io.IOException;
import java.util.Scanner;

import bob.command.Command;
import bob.command.ExitCommand;
import bob.exception.InvalidInputException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Bob is the main class for the application.
 */
public class Bob {
    private TaskList tasks;
    private Storage storage;
    private final Ui ui;

    /**
     * Constructs Bob.
     * <p>
     * It constructs and initialises tasks, storage and ui.
     *
     * @param filePath the file path to load tasks from and save tasks to.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = this.storage.loadTaskList();
        } catch (RuntimeException e) {
            ui.showError("Error initializing storage: " + e.getMessage());
            // Create a dummy storage that won't persist
            this.storage = null;
            tasks = new TaskList(null);
        } catch (IOException e) {
            ui.showError("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList(this.storage);
        } catch (InvalidInputException e) {
            ui.showError("Error loading tasks from file, using empty task list: " + e.getMessage());
            tasks = new TaskList(this.storage);
        }
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        new Bob("data/tasklist.txt").run();
    }

    /**
     * Gets response from Bob for the given input.
     *
     * @param input the user input
     * @return the response from Bob
     */
    public String getResponse(String input) {
        Command command = Parser.parse(input);
        // Capture the output instead of printing it directly
        command.execute(tasks, ui, storage);
        return ui.getResponse();
    }

    /**
     * Runs the main loop for the command line application.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printSection("Hello! I'm Bob.\nHow can I help you?");
        while (true) {
            System.out.print("> ");
            Command command = Parser.parse(scanner.nextLine());
            command.execute(tasks, ui, storage);
            if (command instanceof ExitCommand) {
                break;
            }
        }
        scanner.close();
    }
}
