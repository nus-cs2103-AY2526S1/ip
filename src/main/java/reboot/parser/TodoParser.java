package reboot.parser;

import static reboot.parser.Parser.isOneElement;

import reboot.RebootException;
import reboot.command.AddCommand;
import reboot.command.Command;
import reboot.command.ErrorCommand;
import reboot.task.Todo;
import reboot.task.recurring.RecurringTodo;

/**
 * Represents a parser that will make sense of user todo input.
 */
public class TodoParser {

    /**
     * Returns an AddCommand for the chatbot to complete from specified input.
     * If unable to understand input, throw IllegalArgumentException.
     * @param input Input of user to be converted to chatbot command.
     */
    public static Command parse(String[] input) throws RebootException {

        if (isOneElement(input)) {
            throw new RebootException("Proper usage: todo {description}");
        }

        assert input[1] != null : "Description should not be null";

        String[] todoStrings = input[1].split(" /recurring ", 2);

        boolean isRecurringTodo = !isOneElement(todoStrings);

        if (isRecurringTodo) {
            if (Parser.isNotValidRecurrence(todoStrings[1])) {
                return new ErrorCommand("Only daily/weekly/monthly recurrence is supported");
            }
            return new AddCommand(new RecurringTodo(todoStrings[0], false, todoStrings[1]));
        } else {
            return new AddCommand(new Todo(input[1], false));
        }
    }
}
