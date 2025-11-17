package sophia;

/**
 * A parser that interprets input strings representing deadline tasks.
 * <p>
 * Expected input format:
 * {@code deadline <description> /by <date>}
 * </p>
 *
 * Example:
 * <pre>
 * DeadlineTaskParser parser = new DeadlineTaskParser("submit report /by 2025-09-01");
 * Task task = parser.parse();
 * </pre>
 */
public class DeadlineTaskParser implements Parser {
    private final String argument;

    /**
     * Constructs a {@code DeadlineTaskParser} with the given arguments.
     *
     * @param argument the raw input string excluding the 'deadline' keyword
     */
    public DeadlineTaskParser(String argument) {
        this.argument = argument;
    }

    /**
     * Parses the stored arguments into a {@link DeadlineTask}.
     *
     * @return a new {@code DeadlineTask} with description and deadline date
     * @throws SophiaException if the input format is invalid
     */
    @Override
    public Task parse() throws SophiaException {
        String[] taskInfo = this.argument.split("/by ");
        String description = taskInfo[0].trim();
        String deadline = taskInfo[taskInfo.length - 1].trim();
        if (Parser.isValidDate(deadline)) {
            return new DeadlineTask(description, deadline );
        }
        return null;
    }
}
