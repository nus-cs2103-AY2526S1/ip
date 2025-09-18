package jimbot.command;

import java.util.function.Function;

import jimbot.exception.InvalidInputException;
import jimbot.exception.InvalidToDoException;
import jimbot.exception.JimbotException;

/**
 * Represents the different commands that Jimbot can recognize.
 * Provides a utility method to parse a string input into a Commands value.
 *
 * @author limjimin-nus
 */
public enum Commands {
    HI(GreetCommand::new),
    BYE(input -> new ExitCommand()),
    CLEAR(ClearCommand -> new ClearCommand()),
    DATE(FindDateCommand::new),
    DEADLINE(AddDeadlineCommand::new),
    DELETE(DeleteCommand::new),
    EVENT(AddEventCommand::new),
    FIND(FindCommand::new),
    HELP(input -> new HelpCommand()),
    LIST(input -> new ListCommand()),
    MARK(MarkCommand::new),
    TODAY(FindDateCommand::new),
    TODO(AddToDoCommand::new),
    UNMARK(UnmarkCommand::new);

    private final Function<String, Command> factory;

    // I used AI to find a way for me to implement both enums and commands.
    // factory used to construct the command objects based on the command enums.
    Commands(Function<String, Command> factory) {
        this.factory = factory;
    }

    /**
     * Converts a string input and its extracted command word into a corresponding
     * {@link Command} object.
     *
     * @param input User input string to be converted into a command.
     * @return {@link Command} object corresponding to the user input.
     */
    public static Command fromString(String input) throws JimbotException {
        if (input == null || input.isEmpty()) {
            throw new InvalidToDoException();
        }

        String cmd = input.trim().split(" ")[0];

        // Check for date command
        if (cmd.contains("/")) {
            return DATE.factory.apply(input);
        }

        return switch (cmd) {
        case "hi" -> HI.factory.apply("Jimbot");
        case "bai", "bye", "goodbye" -> BYE.factory.apply(input);
        case "clear" -> CLEAR.factory.apply(input);
        case "deadline" -> DEADLINE.factory.apply(input);
        case "delete" -> DELETE.factory.apply(input);
        case "event" -> EVENT.factory.apply(input);
        case "find" -> FIND.factory.apply(input);
        case "help" -> HELP.factory.apply(input);
        case "list" -> LIST.factory.apply(input);
        case "mark" -> MARK.factory.apply(input);
        case "today" -> TODAY.factory.apply(input);
        case "todo" -> TODO.factory.apply(input);
        case "unmark" -> UNMARK.factory.apply(input);
        default -> throw new InvalidInputException(input);
        };
    }
}
