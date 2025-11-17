package sophia;

/**
 * A parser that interprets input strings representing event tasks.
 * <p>
 * Expected input format:
 * {@code event <description> /from <start> /to <end>}
 * </p>
 *
 * Example:
 * <pre>
 * EventTaskParser parser = new EventTaskParser("project meeting /from 2025-09-01 /to 2025-09-02");
 * Task task = parser.parse();
 * </pre>
 */
public class EventTaskParser implements Parser {
    private final String argument;

    /**
     * Constructs an {@code EventTaskParser} with the given arguments.
     *
     * @param arguments the raw input string excluding the 'event' keyword
     */
    public EventTaskParser(String arguments) {
        this.argument = arguments;
    }

    /**
     * Parses the stored arguments into an {@link EventTask}.
     *
     * @return a new {@code EventTask} with description, start date, and end date
     * @throws SophiaException if the input format is invalid
     */
    @Override
    public Task parse() throws SophiaException {
        String[] taskInfo = this.argument.split("/");
        String from = taskInfo[taskInfo.length - 2].split("from ")[1].trim();
        String to = taskInfo[taskInfo.length - 1].split("to ")[1].trim();
        if (Parser.isValidDate(from) && Parser.isValidDate(to)) {
            return new EventTask(taskInfo[0].trim(), from, to);
        }
        return null;
    }
}
