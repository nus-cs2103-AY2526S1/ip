package parser;

import commands.*;
import exceptions.JackException;

public class Parser {

    // Parse user input and return the corresponding Commands.Command
    public static Command parse(String userInput) throws JackException {
        assert userInput != null : "User input should not be null";
        String[] parts = userInput.split(" ", 2);  // Split the command into the main command and its argument
        assert parts.length > 0 && parts[0] != null && !parts[0].isEmpty() : "Command cannot be empty";
        String command = parts[0].toLowerCase();
        String arg = parts.length > 1 ? parts[1].trim() : null;

        switch (command) {

        case "list":
            return new ListCommand();

        case "todo":
            return new AddTodoCommand(arg);

        case "deadline":
            return new AddDeadlineCommand(arg);

        case "event":
            return new AddEventCommand(arg);

        case "mark":
            return new MarkTaskCommand(arg);

        case "unmark":
            return new UnmarkTaskCommand(arg);

        case "delete":
            return new DeleteTaskCommand(arg);

        case "bye":
            return new ExitCommand();  // Exit the program

        case "find":
            return new FindCommand(arg);

        case "sortdeadlines":
            return new SortDeadlinesCommand();

        default:
            throw new JackException("Invalid command: " + command);  // Handle invalid command
        }
    }
}
