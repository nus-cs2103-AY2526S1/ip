package frenny;

import java.util.Objects;
import java.util.Scanner;

import command.CommandType;
import command.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * The Frenny class is the main entry point for the Frenny application.
 * It initializes the application, processes user input, and manages the task list.
 */
public class Frenny {
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Constructs a Frenny application with the specified file path for storage.
     *
     * @param filePath The file path where tasks will be stored.
     */
    public Frenny(String filePath) {
        assert filePath != null : "File path cannot be null";
        storage = new Storage(filePath);
        Ui.showLine();
        Ui.showIntro();
        Ui.showLine();
        TaskList taskList = new TaskList();
        this.taskList = taskList;
        storage.readFile(taskList);
    }

    /**
     * Runs the main loop of the Frenny application, processing user input until the user decides to exit.
     */
    public void run() {
        Scanner consoleScanner = new Scanner(System.in);
        while (true) {
            String input = consoleScanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            CommandType commandEnum = CommandType.fromString(command);
            Ui.showLine();
            if (Objects.equals(commandEnum, CommandType.BYE)) {
                Ui.showOutro();
                break;
            } else {
                Parser.processInput(taskList, input);
            }
            storage.writeFile(taskList);
            Ui.showLine();
        }
    }

    /**
     * Starts the Frenny CLI application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = PROJECT_DIR + "/data/frenny.txt";
        new Frenny(filePath).run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = Parser.processInput(taskList, input);
        // Save the updated task list to storage
        storage.writeFile(taskList);
        return response;
    }
}
