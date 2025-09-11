package jimbot.command;

import java.util.function.Function;

import jimbot.exception.InvalidDateTimeException;

/**
 * Represents the different commands that Jimbot can recognize.
 * Provides a utility method to parse a string input into a Commands value.
 *
 * @author limjimin-nus
 */
public enum Commands {
    HI(GreetCommand::new),
    BYE(input -> new ExitCommand()),
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
    UNKNOWN(ResponseCommand::new),
    UNMARK(UnmarkCommand::new);

    private final Function<String, Command> factory;

    Commands(Function<String, Command> factory) {
        this.factory = factory;
    }

    /**
     * Convert a string input into the corresponding Commands enum value.
     * Handles case-insensitive command strings and recognizes date inputs.
     *
     * @param input User input string to be converted into a command.
     * @return Corresponding Commands enum value.
     * @throws InvalidDateTimeException If the input only contains a date but does not match the date pattern.
     */
    public static Command fromString(String input) {
        if (input == null || input.isEmpty()) {
            return UNKNOWN.factory.apply(input);
        }

        String[] tokens = input.trim().split(" ", 2);
        String cmd = tokens[0].toLowerCase();

        // Check for date input
        if (cmd.contains("/")) {
            return DATE.factory.apply(input);
        }

        return switch (cmd) {
        case "hi" -> HI.factory.apply("Jimbot");
        case "bye", "goodbye" -> BYE.factory.apply(input);
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
        default -> UNKNOWN.factory.apply(input);
        };
    }
}
