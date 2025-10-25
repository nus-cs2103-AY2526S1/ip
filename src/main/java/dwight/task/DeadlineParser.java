package dwight.task;

import dwight.storage.CorruptSaveException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents a parser that creates {@link Deadline} tasks from user input or saved file data. */
public class DeadlineParser extends TaskParser {

    /**
     * Parses a user-provided description into a {@link Deadline} task. The input must include a
     * description and a {@code /by} date.
     *
     * <p>Example format: {@code deadline submit report /by Feb 20 2025}
     *
     * @param description The description string containing the task details and deadline date.
     * @return A new {@code Deadline} task created from the description.
     * @throws IncompleteTaskException If the description does not match the required format.
     */
    @Override
    public Task parse(String description) throws IncompleteTaskException {
        if (description == null || description.trim().isEmpty()) {
            throw new IncompleteTaskException(
                    "The 'deadline' command requires a description and a '/by' date. Format:"
                            + " deadline <description> /by <date e.g. 14 Feb 2025>");
        }

        Pattern pattern = Pattern.compile("^(.+?)\\s*/by\\s+(.+)$");
        Matcher matcher = pattern.matcher(description);

        if (!matcher.matches()) {
            throw new IncompleteTaskException(
                    "The 'deadline' command requires a description and a '/by' date. Format:"
                            + " deadline <description> /by <date e.g. 14 Feb 2025>");
        }

        String dateStr = matcher.group(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        return new Deadline(matcher.group(1), date);
    }

    /**
     * Parses a saved description string from storage into a {@link Deadline} task. The saved data
     * must include both the description and the deadline date.
     *
     * @param description The saved task data.
     * @return A new {@code Deadline} task created from the saved data.
     * @throws CorruptSaveException If the saved data is incomplete or corrupted.
     */
    @Override
    public Task parseFromFile(String description) throws CorruptSaveException {
        assert description != null && !description.trim().isEmpty()
                : "Corrupted save file: Deadline description is empty.";

        String[] parts = description.split("\\|");

        if (parts.length < 2) {
            throw new CorruptSaveException(description);
        }

        description = parts[0].trim();

        String deadlineStr = parts[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate deadline = LocalDate.parse(deadlineStr, formatter);

        return new Deadline(description, deadline);
    }
}
