package stewie;

import static stewie.ui.Ui.HR;

import java.util.Scanner;

import stewie.command.Command;
import stewie.command.CommandType;
import stewie.exceptions.CommandException;
import stewie.parser.Parser;
import stewie.storage.Storage;
import stewie.task.TaskList;
import stewie.ui.Ui;

/**
 * Main class for the Stewie chatbot.
 * Handles user input, command parsing, and task operations.
 */
public class Stewie {
    private static final String DATA_FILE_PATH = "./data/tasks.txt";
    private static Storage storage = new Storage(DATA_FILE_PATH);
    private static TaskList taskList = storage.loadTaskList();

    private CommandType commandType = CommandType.UNKNOWN;

    /**
     * Main method that starts the application and handles the main program loop.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.print(Ui.showGreeting(true));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();

            try {
                Command command = Parser.parseCommand(input);
                String response = command.execute(taskList, storage);

                System.out.print(HR);
                System.out.println(Ui.formatPrintable(response));
                System.out.print(HR);

                if (command.isExit()) {
                    break;
                }
            } catch (CommandException e) {
                System.out.print(HR);
                System.out.println(Ui.formatPrintable(e.getMessage()));
                System.out.print(HR);
            }
        }
    }

    /**
     * Processes the input string to determine the corresponding command and executes it.
     *
     * @param input The string input that represents a user command.
     * @return The result of the command execution, or an error message if the command fails.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input);
            commandType = command.getCommandType();
            return command.execute(taskList, storage);

        } catch (CommandException e) {
            return e.getMessage();
        }
    }

    /**
     * Returns the type of this command.
     *
     * @return the command type as a string
     */
    public CommandType getCommandType() {
        return commandType;
    }
}
