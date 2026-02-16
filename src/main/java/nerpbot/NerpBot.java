package nerpbot;

import nerpbot.task.Deadline;
import nerpbot.task.Event;
import nerpbot.task.ToDo;

import java.io.IOException;

/**
 * Represents the main chatbot class that handles user interaction,
 * command parsing, and overall application flow.
 */
public class NerpBot {
    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    public static final String ERROR_PREFIX = "☹ OOPS!!! ";
    public static final String CONFIRM_PREFIX = "🤔 CONFIRM? ";

    // Semantic interpretation state
    private boolean semanticModeEnabled = true;
    private String pendingCommand = null;
    private boolean awaitingConfirmation = false;

    /**
     * Constructs a NerpBot instance with the specified file path for storage.
     *
     * @param filePath The path to the storage file.
     */
    public NerpBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList tempTaskList;
        try {
            tempTaskList = new TaskList(storage.load(), storage);
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tempTaskList = new TaskList(storage);
        }
        this.taskList = tempTaskList;
    }

    /**
     * Processes the user's input and returns the chatbot's response.
     *
     * @param input The user's command as a string.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        ui.showWelcome();

        try {
            // Handle confirmation responses
            if (awaitingConfirmation) {
                return handleConfirmation(input);
            }

            // Handle semantic mode toggle
            if (input.trim().equalsIgnoreCase("semantic on")) {
                semanticModeEnabled = true;
                return "Semantic interpretation is now ON. I'll try to understand natural language!";
            }
            if (input.trim().equalsIgnoreCase("semantic off")) {
                semanticModeEnabled = false;
                return "Semantic interpretation is now OFF. Use direct commands only.";
            }

            // Try semantic interpretation if enabled
            if (semanticModeEnabled) {
                SemanticInterpreter.InterpretationResult result = SemanticInterpreter.interpret(input);

                if (result.isNaturalLanguage() && result.needsConfirmation()) {
                    // Store pending command and ask for confirmation
                    pendingCommand = result.getCommand();
                    awaitingConfirmation = true;
                    return CONFIRM_PREFIX + "I interpreted that as:\n\n"
                            + "📝 " + result.getExplanation() + "\n"
                            + "💻 Command: " + result.getCommand() + "\n\n"
                            + "Type 'yes' to confirm, 'no' to cancel, or type a different command.";
                }

                // Use the interpreted command (either direct command or high-confidence interpretation)
                input = result.getCommand();
            }

            return executeCommand(input);
        } catch (Exception e) {
            return ERROR_PREFIX + ui.showError(e.getMessage());
        }
    }

    /**
     * Handles user confirmation for semantic interpretation.
     */
    private String handleConfirmation(String input) {
        String lowerInput = input.trim().toLowerCase();

        if (lowerInput.equals("yes") || lowerInput.equals("y") || lowerInput.equals("confirm")) {
            awaitingConfirmation = false;
            String command = pendingCommand;
            pendingCommand = null;
            try {
                return "✅ Confirmed!\n\n" + executeCommand(command);
            } catch (Exception e) {
                return ERROR_PREFIX + ui.showError(e.getMessage());
            }
        } else if (lowerInput.equals("no") || lowerInput.equals("n") || lowerInput.equals("cancel")) {
            awaitingConfirmation = false;
            pendingCommand = null;
            return "❌ Cancelled. What would you like to do instead?";
        } else {
            // User typed something else - treat as new command
            awaitingConfirmation = false;
            pendingCommand = null;
            return getResponse(input);
        }
    }

    /**
     * Executes a parsed command.
     */
    private String executeCommand(String input) throws NerpBotException, IOException {
        String commandWord = Parser.getCommandWord(input);
        String commandArgs = Parser.getCommandArgs(input);

        switch (commandWord) {
            case "help":
                return ui.showHelp();
            case "bye":
                return ui.showExit();
            case "list":
                return taskList.listTasks();
            case "mark": {
                int idx = Integer.parseInt(commandArgs) - 1;
                return taskList.markTask(idx);
            }
            case "unmark": {
                int idx = Integer.parseInt(commandArgs) - 1;
                return taskList.unmarkTask(idx);
            }
            case "delete": {
                int idx = Integer.parseInt(commandArgs) - 1;
                return taskList.deleteTask(idx);
            }
            case "todo": {
                if (commandArgs.isBlank()) {
                    throw new NerpBotException("you didn't provide a description for the todo.");
                }
                return taskList.addTask(new ToDo(commandArgs));
            }
            case "deadline": {
                String[] parts = commandArgs.split(" /by ", 2);
                return taskList.addTask(new Deadline(parts[0], parts[1]));
            }
            case "event": {
                String[] parts = commandArgs.split(" /from | /to ", 3);
                return taskList.addTask(new Event(parts[0], parts[1], parts[2]));
            }
            case "find": {
                if (commandArgs.isBlank()) {
                    throw new NerpBotException("you didn't provide a keyword to search for.");
                }
                return taskList.findTasks(commandArgs);
            }
            default:
                throw new NerpBotException("idk what that means. Try 'semantic on' to enable natural language interpretation!");
        }
    }

    /**
     * Returns the welcome message when the bot is first loaded.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.showWelcome() + "\n\n💡 Semantic mode is ON - I can understand natural language!\n"
                + "Try: \"I need to buy groceries by tomorrow\"";
    }

    /**
     * Checks if semantic interpretation mode is enabled.
     *
     * @return true if semantic mode is enabled.
     */
    public boolean isSemanticModeEnabled() {
        return semanticModeEnabled;
    }

    /**
     * Checks if the bot is awaiting confirmation.
     *
     * @return true if awaiting confirmation.
     */
    public boolean isAwaitingConfirmation() {
        return awaitingConfirmation;
    }

    /**
     * Shows the help window.
     */
    public void showHelpWindow() {
        ui.showHelpWindow();
    }
}
