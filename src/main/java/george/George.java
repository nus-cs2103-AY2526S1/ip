package george;

import java.io.IOException;
import java.util.Scanner;

import george.command.Command;
import george.command.CommandParser;
import george.command.ExitCommand;
import george.exceptions.GeorgeException;
import george.task.TaskManager;
import george.ui.Ui;

/**
 * Main class for the George task management application.
 * Coordinates the user interface, command parsing, and task management.
 */
public class George {
    private static final String fileName = "george.txt";
    private final Ui ui;
    private TaskManager manager;
    private boolean isInitialised = false;

    /**
     * Constructs a George application instance.
     */
    public George() {
        this.ui = new Ui();
    }

    /**
     * Main entry point for the George application.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        George george = new George();
        george.run();
    }

    /**
     * Runs the main application loop, handling initialization and user interaction.
     */
    private void run() {
        ui.showWelcome();
        try {
            TaskManager manager = start();
            echo(manager);
        } catch (IOException e) {
            ui.showError("Error starting taskmanager: " + e.getMessage());
        }
    }

    /**
     * Initializes the task manager and loads existing tasks from storage.
     *
     * @return An initialized TaskManager instance
     * @throws IOException If an error occurs during task loading
     */
    private TaskManager start() throws IOException {
        TaskManager manager = new TaskManager(fileName);
        manager.load();
        isInitialised = true;
        return manager;
    }

    /**
     * Generates a response for the user's chat message.
     * @param input The input given by the user.
     * @return A string that is the response of the chatbot to the user.
     */
    public String getResponse(String input) {
        try {
            // Initialize TaskManager if not already done
            if (!isInitialised) {
                initializeTaskManager();
            }

            Command command = CommandParser.parse(input);
            String response = command.execute(manager);

            return response;

        } catch (GeorgeException e) {
            return e.toString();
        } catch (NumberFormatException e) {
            return "Please provide a valid task number!";
        } catch (IOException e) {
            return "Error loading tasks: " + e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Initializes the task manager and loads existing tasks from storage.
     *
     * @throws IOException If an error occurs during task loading
     */
    private void initializeTaskManager() throws IOException {
        manager = new TaskManager(fileName);
        manager.load();
        isInitialised = true;
    }

    /**
     * Main command loop that reads user input, parses commands, and executes them.
     *
     * @param manager The TaskManager instance to operate on
     */
    private void echo(TaskManager manager) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                Command command = CommandParser.parse(input);
                command.execute(manager);

                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (GeorgeException e) {
                System.out.println(e.toString());
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid task number!");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
