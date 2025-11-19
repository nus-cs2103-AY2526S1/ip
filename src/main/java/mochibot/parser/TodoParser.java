package mochibot.parser;

import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.task.Todo;

/**
 * This class is responsible for handling the "todo" command input.
 */
public class TodoParser {

    /**
     * Parses the "todo" command input and returns a corresponding {@code AddCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return an {@code AddCommand} that contains a {@code Todo} task
     * @throws MochiBotException.MissingTaskNameException if the task name is empty
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        String taskName = String.join(" ", Arrays.copyOfRange(inputs, 1, inputs.length));
        if (taskName.isEmpty()) {
            throw new MochiBotException.MissingTaskNameException();
        }
        Todo todo = new Todo(taskName);
        return new AddCommand(todo);
    }
}
