package dwight.task;

import dwight.storage.CorruptSaveException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents a parser that creates {@link Event} tasks from user input or saved file data. */
public class EventParser extends TaskParser {

    /**
     * Parses a user-provided description into an {@link Event} task. The input must include a
     * description, a {@code /from} date, and a {@code /to} date.
     *
     * <p>Example format: {@code event project meeting /from Feb 10 2025 /to Feb 12 2025}
     *
     * @param description The description string containing the event details and dates.
     * @return A new {@code Event} task created from the description.
     * @throws IncompleteTaskException If the description does not match the required format.
     */
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        if (description == null || description.trim().isEmpty()) {
            throw new IncompleteTaskException(
                    "The 'event' command requires a description, a '/from' date, and a '/to' date."
                            + " Format: event <description> /from <date e.g. 14 Feb 2025> /to <date"
                            + " e.g. 14 Feb 2025>");
        }

        Pattern pattern = Pattern.compile("^(.+?)\\s*/from\\s*(.+?)\\s*/to\\s*(.+)$");
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            throw new IncompleteTaskException(
                    "The 'event' command requires a description, a '/from' date, and a '/to' date."
                            + " Format: event <description> /from <date e.g. 14 Feb 2025> /to <date"
                            + " e.g. 14 Feb 2025>");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");

        String fromStr = matcher.group(2);
        LocalDate from = LocalDate.parse(fromStr, formatter);
        String toStr = matcher.group(3);
        LocalDate to = LocalDate.parse(toStr, formatter);

        return new Event(matcher.group(1), from, to);
    }

    /**
     * Parses a saved description string from storage into an {@link Event} task. The saved data
     * must include the description, start date, and end date.
     *
     * @param description The serialized task data from storage.
     * @return A new {@code Event} task created from the saved data.
     * @throws CorruptSaveException If the saved data is incomplete or corrupted.
     */
    @Override
    public Task parseFromFile(String description) throws CorruptSaveException {
        assert description != null && !description.trim().isEmpty()
                : "Corrupted save file: Event description is empty.";

        String[] parts = description.split("\\|");

        if (parts.length < 3) {
            throw new CorruptSaveException(description);
        }

        description = parts[0].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String fromStr = parts[1].trim();
        LocalDate from = LocalDate.parse(fromStr, formatter);
        String toStr = parts[2].trim();
        LocalDate to = LocalDate.parse(toStr, formatter);

        return new Event(description, from, to);
    }
}
