package hermione.commands;

import hermione.parsers.CommandParser;
import hermione.storage.TaskStorage;
import hermione.utils.AiUtils;

/**
 * Represents a command that uses AI to generate and execute commands
 * from natural language input.
 * This command allows users to issue commands in natural language,
 * which are then converted to valid app commands and executed.
 */
public class AiCommandCommand extends Command {

    private final AiUtils aiUtils;

    /**
     * Constructor for the AiCommandCommand class.
     *
     * @param storage  The TaskStorage instance used to manage tasks.
     * @param argument The user's natural language command request.
     */
    public AiCommandCommand(TaskStorage storage, String argument) {
        super(storage, argument);
        this.aiUtils = new AiUtils();
    }

    /**
     * Executes the command to generate and run an app command from natural language.
     * Takes the user's natural language request, generates a valid app command using AI,
     * validates and executes the command, then returns the result.
     *
     * @return A string containing the result of executing the generated command.
     */
    @Override
    public String execute() {
        if (argument.isEmpty()) {
            return "Please provide a command request after @cmd. "
                    + "For example: @cmd add a task to buy groceries by tomorrow";
        }

        try {
            // Get AI to generate the command
            String generatedCommand = aiUtils.commandAiToGenerate(argument);

            // Clean up the generated command (remove quotes if present)
            generatedCommand = generatedCommand.trim().replaceAll("^\"|\"$", "");

            // Parse the generated command
            String commandString = getCommandString(generatedCommand);
            String commandArgument = getArgument(generatedCommand);

            // Validate that it's a valid command (not another @ai or @cmd)
            if (commandString.startsWith("@")) {
                return "Sorry, I couldn't generate a valid command from your request. Please try rephrasing.";
            }

            // Execute the command
            Command command = CommandParser.parse(commandString, commandArgument, storage);
            String result = command.execute();

            return "AI generated command: '" + generatedCommand + "'\n\nResult:\n" + result;
        } catch (Exception e) {
            return "Sorry, I couldn't execute your request. Error: " + e.getMessage();
        }
    }

    /**
     * Extracts the command string from a full command message.
     *
     * @param message The full command message.
     * @return The command string.
     */
    private String getCommandString(String message) {
        return message.trim().split(" ")[0];
    }

    /**
     * Extracts the argument from a full command message.
     *
     * @param message The full command message.
     * @return The argument string.
     */
    private String getArgument(String message) {
        String commandWord = getCommandString(message);
        return message.substring(commandWord.length()).trim();
    }
}

