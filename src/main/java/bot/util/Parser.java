package bot.util;

import bot.command.AddDeadlineCommand;
import bot.command.AddEventCommand;
import bot.command.AddTodoCommand;
import bot.command.Command;
import bot.command.ExitCommand;
import bot.command.FindCommand;
import bot.command.InvalidCommand;
import bot.command.ListTaskCommand;
import bot.command.MarkTaskCommand;
import bot.command.RemoveTaskCommand;
import bot.command.SortCommand;
import bot.command.UnmarkTaskCommand;

/**
 * A utility class for parsing user input strings into executable commands.
 * This class analyzes the first word of the input to determine the command type
 * and creates the appropriate Command object with the remaining input parameters.
 */
public class Parser {
    /**
     * Parses a user input string and returns the corresponding Command object.
     * The input is split into the command keyword and its parameters, then the
     * appropriate Command subclass is instantiated based on the keyword.
     * <p>
     * Supported commands:
     * - "bye": Exit the application
     * - "list": Display all tasks
     * - "mark": Mark a task as completed
     * - "unmark": Mark a task as not completed
     * - "todo": Add a new to-do task
     * - "deadline": Add a new deadline task
     * - "event": Add a new event task
     * - "delete": Remove a task
     * - "sort": Sort task list
     * - Any other input: Invalid command
     *
     * @param input the user input string to parse
     * @return the Command object corresponding to the parsed input
     */
    public static Command parse(String input) {
        // Split input by space into command type (first word)
        // and other command information (e.g. input param)
        String commandSeparator = " ";
        String[] commandInfo = input.split(commandSeparator, 2);

        String instruction = commandInfo[0];

        // Redirect command type to invoke respective operations
        return switch (instruction) {
            case "bye" -> // Exit program
                    new ExitCommand();
            case "list" -> // Display task list
                    new ListTaskCommand();
            case "mark" -> // Mark a task as done
                    new MarkTaskCommand(commandInfo);
            case "unmark" -> // Mark a task as not done
                    new UnmarkTaskCommand(commandInfo);
            case "todo" -> // Add to-do task to task list
                    new AddTodoCommand(commandInfo);
            case "deadline" -> // Add deadline task to task list
                    new AddDeadlineCommand(commandInfo);
            case "event" -> // Add event task to task list
                    new AddEventCommand(commandInfo);
            case "delete" -> // Delete task from task list
                    new RemoveTaskCommand(commandInfo);
            case "find" -> // Search for tasks by name
                    new FindCommand(commandInfo);
            case "sort" -> // Sort task list by name or date
                    new SortCommand(commandInfo);
            default -> new InvalidCommand();
        };
    }
}
