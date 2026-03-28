package inputhandler;

import command.Command;
import command.Commands;
import exceptions.MarkExceptions;
import exceptions.UnknownCommandException;
import task.TaskList;

/**
 * Parses and handles user input, converting it into a corresponding Command.
 */
public class InputHandler {

    public static Command handle(String msg, TaskList taskList) throws MarkExceptions {
        String[] stringParts = msg.trim().split(" ", 2);
        String command = stringParts[0].toUpperCase();
        String arg = stringParts.length > 1 ? stringParts[1] : "";

        try {
            Commands commands = Commands.valueOf(command);
            return commands.create(arg, taskList);
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

}
