package nacho.commands;

import java.util.Arrays;
import java.util.Map;

import nacho.ChatContext;
import nacho.exceptions.UserInputException;

/**
 * Takes User Input directly to parse and execute the respective target Commands respectively
 */
public class CommandDispatcher {
    // Command Registry Mappings
    private final Map<String, Command> registry;

    public CommandDispatcher(Map<String, Command> registry) {
        this.registry = registry;
    }

    /**
     * Determines which command to hand over depending on input
     * @param userInput raw input string from user
     * @param context information and methods for chat ui
     */
    public void dispatch(String userInput, ChatContext context) {

        // Run Commands and Catch Command Related Errors
        try {
            String[] parts = userInput.split(" ");
            String commandName = parts[0];
            String[] args = Arrays.copyOfRange(parts, 1, parts.length);

            // Check against command registry and execute respective command
            Command cmd = registry.get(commandName);
            if (cmd != null) {
                cmd.execute(args, context);
            } else {
                context.reply("Sorry I don't know this command. \n\n"
                        + "Try 'help' for a list of commands to use!");
                context.setLatestMessageWrong();
            }
        } catch (UserInputException e) {
            context.reply(e.getMessage());
            context.setLatestMessageWrong(); // Tags latest message as error for styling use
        } catch (Exception e) {
            context.reply("Unexpected Error!! Nacho doesn't really know either T^T");
            context.setLatestMessageWrong(); // Tags latest message as error for styling use
        }
    }

}
