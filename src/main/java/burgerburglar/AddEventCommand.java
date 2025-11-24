package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Command to add an event task to the task list.
 * <p>
 * The command expects input in the format: description /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm
 */
public class AddEventCommand extends Command {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final String args;

    /**
     * Constructs a new AddEventCommand with the given user input.
     *
     * @param args the raw input string containing the description, start time, and optional end time
     */
    public AddEventCommand(String args) {
        assert args != null && !args.isBlank() : "Arguments for AddEventCommand cannot be null or blank";
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";

        String[] partsFrom = args.split("/from", 2);
        guardInvalidDescription(partsFrom);

        String description = partsFrom[0].trim();
        String[] partsTo = partsFrom[1].split("/to", 2);
        String fromStr = partsTo[0].trim();
        String toStr = partsTo.length > 1 ? partsTo[1].trim() : "";

        LocalDateTime from = parseDate(fromStr);
        LocalDateTime to = toStr.isEmpty() ? null : parseDate(toStr);

        Event event = new Event(description, from, to);
        tasks.addTask(event);
        assert tasks.getTasks().contains(event) : "Event should be added to TaskList";

        storage.save(tasks);
        return "BURGER ADDED: " + event + "\nNOW YOU HAVE " + tasks.size() + " TASK(S).";
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
            throw new BurgerException("BURGER ERROR: Invalid date/time format! Use yyyy-MM-dd HHmm");
        }
    }

    /**
     * Guard clause to check that the description and /from part are valid.
     *
     * @param partsFrom the split input array
     * @throws BurgerException if input is invalid
     */
    private void guardInvalidDescription(String[] partsFrom) throws BurgerException {
        if (partsFrom.length < 2 || partsFrom[0].trim().isEmpty()) {
            throw new BurgerException("BURGER ERROR: Event must have a description and /from time.");
        }
    }
}

