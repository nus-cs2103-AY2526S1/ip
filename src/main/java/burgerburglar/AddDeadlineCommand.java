package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to add a deadline task to the task list.
 * <p>
 * The command expects input in the format: description /by yyyy-MM-dd HHmm
 */
public class AddDeadlineCommand extends Command {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String args;

    /**
     * Constructs a new AddDeadlineCommand with the given user input.
     *
     * @param args the raw input string containing the description and deadline
     */
    public AddDeadlineCommand(String args) {
        assert args != null && !args.isBlank() : "Command arguments cannot be null or blank";
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] parts = args.split("/by", 2);

        // Guard clause for invalid input
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: Deadline must have a description and a /by date.");
        }

        String description = parts[0].trim();
        String byStr = parts[1].trim();

        LocalDateTime by = parseDate(byStr);

        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        storage.save(tasks);

        return "BURGER ADDED: " + deadline + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).";
    }

    /**
     * Parses a string into a LocalDateTime using the expected input format.
     *
     * @param dateStr the string representing the date and time
     * @return the parsed LocalDateTime
     * @throws BurgerException if parsing fails
     */
    private LocalDateTime parseDate(String dateStr) throws BurgerException {
        try {
            return LocalDateTime.parse(dateStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new BurgerException("BURGER ERROR: Invalid date format! Use yyyy-MM-dd HHmm");
        }
    }
}
