package app;

import commands.Command;
import commands.CommandFarewell;
import commands.CommandTaskDeadline;
import commands.CommandTaskDelete;
import commands.CommandTaskEvent;
import commands.CommandTaskFind;
import commands.CommandTaskList;
import commands.CommandTaskMark;
import commands.CommandTaskTodo;
import commands.CommandTaskUndo;
import commands.CommandTaskUnmark;
import errors.BoopError;

/**
 * The Parser class is responsible for interpreting raw user input
 * and converting it into executable Command objects.
 *
 * It examines the first word of the input to determine the
 * command type and constructs the appropriate Command instance.
 */
public final class Parser {
    /**
     * Returns the next command to be executed
     *
     * @param nextLine the raw input string used to generate the next command
     * @return Command generated from the next line user inputs
     * @throws BoopError
     */
    public Command getNextCommand(String nextLine) throws BoopError {
        String[] words = nextLine.split(" ", 2);
        String commandName = words[0].trim();

        switch (commandName) {
        case "bye" -> {
            return new CommandFarewell();
        }
        case "mark" -> {
            return new CommandTaskMark(nextLine);
        }
        case "unmark" -> {
            return new CommandTaskUnmark(nextLine);
        }
        case "delete" -> {
            return new CommandTaskDelete(nextLine);
        }
        case "list" -> {
            return new CommandTaskList();
        }
        case "todo" -> {
            return new CommandTaskTodo(nextLine);
        }
        case "deadline" -> {
            return new CommandTaskDeadline(nextLine);
        }
        case "event" -> {
            return new CommandTaskEvent(nextLine);
        }
        case "find" -> {
            return new CommandTaskFind(nextLine);
        }
        case "undo" -> {
            return new CommandTaskUndo();
        }
        default -> throw new BoopError(Messages.ERROR_UNKNOWN_COMMAND);
        }
    }
}
