package moon.parser.usercommand;

import moon.commands.AddTodoCommand;
import moon.commands.enums.Command;
import moon.models.Todo;
import moon.parser.exceptions.ParseException;
import moon.parser.util.ExtractString;
import moon.parser.util.ParseChecker;

/**
 * Parser for the {@link Command#TODO} command.
 * <p>
 * Expected format:
 * <pre>
 *   todo {task description}
 * </pre>
 * Example:
 * <pre>
 *   todo read book
 * </pre>
 */
public class AddTodoCommandParser implements CommandParser<AddTodoCommand> {
    private static final Command COMMAND = Command.TODO;
    private static final boolean IS_TASK_NAME = true;

    /**
     * Parses a user input string into an {@link AddTodoCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddTodoCommand} containing the new {@link Todo}
     * @throws ParseException if the task description is missing or empty
     */
    @Override
    public AddTodoCommand parse(String input) throws ParseException {
        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        // Here it is checking if the event name parameter is empty
        ParseChecker.isParameterEmpty(input, COMMAND, IS_TASK_NAME);

        String todoName = ExtractString.extract(input, COMMAND.getKeyword());

        Todo newTodo = new Todo(todoName);
        return new AddTodoCommand(newTodo);
    }
}
