package burgerburglar;

/**
 * Parses user input into a {@link Command} executable by {@link BurgerBurglar}.
 * <p>
 * The Parser interprets the first word of the input as the command keyword and
 * the remaining text as arguments for that command.
 */
public class Parser {

    /**
     * Parses raw user input into a {@link Command}.
     *
     * @param input the raw string input from the user
     * @return a {@link Command} representing the requested action
     * @throws BurgerException if the input does not match any known command
     */
    public static Command parse(String input) throws BurgerException {
        assert input != null && !input.isBlank() : "Input must not be null or blank";

        String commandWord = extractCommandWord(input);
        String args = extractArguments(input);

        return createCommand(commandWord, args);
    }

    /**
     * Extracts the command keyword from input.
     *
     * @param input the raw input string
     * @return the first word of the input
     */
    private static String extractCommandWord(String input) {
        String[] parts = input.trim().split(" ", 2);
        return parts[0];
    }

    /**
     * Extracts the arguments portion of the input.
     *
     * @param input the raw input string
     * @return the portion after the command keyword, or empty string if none
     */
    private static String extractArguments(String input) {
        String[] parts = input.trim().split(" ", 2);
        return parts.length > 1 ? parts[1] : "";
    }

    /**
     * Maps a command keyword and its arguments to a {@link Command}.
     *
     * @param commandWord the command keyword
     * @param args        the command arguments
     * @return the corresponding {@link Command} object
     * @throws BurgerException if the command keyword is unknown
     */
    private static Command createCommand(String commandWord, String args) throws BurgerException {
        switch (commandWord.toLowerCase()) {
        case "hi":
            return new GreetingCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(args, true);
        case "unmark":
            return new MarkCommand(args, false);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new AddTodoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "burger":
            return new BurgerCommand();
        case "find":
            return new FindCommand(args);
        case "help":
            return new HelpCommand();
        default:
            throw new BurgerException("BURGER DOESN’T GET IT.");
        }
    }
}
