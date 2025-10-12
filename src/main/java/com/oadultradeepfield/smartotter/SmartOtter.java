package com.oadultradeepfield.smartotter;

import com.oadultradeepfield.smartotter.command.ByeCommand;
import com.oadultradeepfield.smartotter.command.CommandContext;
import com.oadultradeepfield.smartotter.command.CommandParser;
import com.oadultradeepfield.smartotter.command.Executable;
import com.oadultradeepfield.smartotter.util.FileManager;

/**
 * Main class for the SmartOtter interactive console application.
 */
public class SmartOtter {
    private final SmartOtterUI ui;
    private final FileManager fileManager;
    private final CommandParser parser;
    private final CommandContext context;

    /**
     * Constructs a new {@code SmartOtter} instance.
     *
     * <p>This initializes the user interface, file manager, command parser, and command context using
     * the tasks loaded from the specified file. A shutdown hook is also registered to automatically
     * save tasks back to the file when the program exits.
     *
     * @param filePath the path to the file where tasks are stored and retrieved
     */
    public SmartOtter(String filePath) {
        this.ui = new SmartOtterUI();
        this.fileManager = new FileManager(filePath);
        this.parser = new CommandParser();
        this.context = new CommandContext(this.fileManager.readTasksFromFile());

        // Add shutdown hook to save tasks
        Runtime.getRuntime()
            .addShutdownHook(new Thread(() -> this.fileManager.saveTasksToFile(context.tasks())));
    }

    /**
     * Runs interactive loop until user exits.
     */
    public static void main(String[] args) {
        new SmartOtter(SmartOtterConstant.SAVE_PATH).run();
    }

    /**
     * Starts the main execution loop of the {@code SmartOtter} application.
     *
     * <p>This method displays a greeting, then continuously reads user input, parses it into a
     * command, and executes it against the current context. The loop continues until a {@code
     * ByeCommand} is executed.
     *
     * <p>Errors specific to the application are caught and shown as messages, while all other
     * unexpected exceptions are displayed as errors.
     */
    public void run() {
        ui.showGreeting();

        while (true) {
            try {
                String input = ui.readCommand();
                Executable executable = parser.parse(input);
                executable.execute(context);

                if (executable instanceof ByeCommand) {
                    break;
                }
            } catch (SmartOtterException e) {
                ui.showMessage(e.getMessage());
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     *
     * <p>This method parses the input as a command and executes it against the current context,
     * returning the result as a string instead of printing to console. This allows the GUI to display
     * the response in dialog boxes.
     *
     * @param input the user's input string to process as a command
     * @return the response string to display to the user
     */
    public String getResponse(String input) {
        try {
            Executable executable = parser.parse(input);
            String result = executable.execute(context);

            fileManager.saveTasksToFile(context.tasks());

            return result;
        } catch (SmartOtterException e) {
            return e.getMessage();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
