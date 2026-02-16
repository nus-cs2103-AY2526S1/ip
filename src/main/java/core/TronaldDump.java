package core;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import commands.Command;
import commands.CommandFactory;
import util.Parser;
import util.Storage;
import util.TronaldDumpException;

/**
 * Main class for the TronaldDump chatbot application.
 */
public class TronaldDump {
    private Storage storage;
    private TaskList taskList;
    private CommandFactory commandFactory;

    /**
     * Constructor for TronaldDump class.
     * Initializes storage, loads existing tasks, and creates the command factory.
     */
    public TronaldDump() {
        this.storage = new Storage();
        this.taskList = storage.load();
        this.commandFactory = new CommandFactory(taskList, storage);
    }

    /**
     * Runs the TronaldDump application.
     */
    public void run() {
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                Command command = processCommand(input);
                if (command.isExit()) {
                    break;
                }
            } catch (TronaldDumpException e) {

                Ui.showError(e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Processes the user input and returns the corresponding Command object.
     * Valid user inputs are "todo", "event", "deadline", "list", "mark", "unmark", "delete", "find", "sort", and "bye".
     * If the input is invalid, a TronaldDumpException is thrown with an appropriate error message.
     * @param input String input from the user representing the commands given to the chatbot.
     * @return Command object corresponding to the user input.
     * @throws TronaldDumpException
     */
    public Command processCommand(String input) throws TronaldDumpException {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String errorMessageForInvalidInput = "I HATE DEMOCRATS! IF YOU WANT TO ADD A TASK, I ONLY UNDERSTAND TODO, EVENT, AND DEADLINE TASKS!\n"
                            + "ELSE, TRY LIST, MARK, UNMARK, DELETE, FIND, SORT OR BYE TO EXIT!";
        String[] parts = Parser.parse(input);
        Ui.showHorizontalLine();
        if (parts.length == 0) {
            throw new TronaldDumpException(
                    errorMessageForInvalidInput);
        }

        String commandType = parts[0].toLowerCase();
        Command command = commandFactory.getCommand(commandType);

        if (command == null) {
            throw new TronaldDumpException(
                    errorMessageForInvalidInput);
        }

        // Execute the command (including exit commands) to capture their output
        command.execute(input, parts);
        
        if (command.isExit()) {
            return command;
        }

        assert command != null : "Command should not be null after processing";
        // Save after every command execution
        storage.save(taskList.getAllTasks());
        return command;
    }

    public static void main(String[] args) {
        new TronaldDump().run();
    }

    public String getResponse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        
        try {
            // Capture the actual output from commands
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));
            
            // Process the command (this will print to our captured stream)
            processCommand(input);
            
            // Restore original System.out
            System.setOut(originalOut);
            
            // Get the captured output and clean it up
            String capturedOutput = outputStream.toString().trim();
            
            // Return the captured output or a default message if empty
            return capturedOutput.isEmpty() ? "Command processed successfully." : capturedOutput;

        } catch (TronaldDumpException e) {
            return e.getMessage();
        } catch (Exception e) {
            // Handle any unexpected errors
            return "An unexpected error occurred: " + e.getMessage();
        }
    }
}
