package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;

/**
 * DeadlineTask is a subclass of Task.
 * The class handles creation of a deadline task when user uses the deadline command.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate deadline;

    /**
     * Deadline Task Constructor.
     *
     * @param taskName Name of the task; must not be null or empty.
     * @param deadline Deadline of the task.
     * @throws BotException If there is an invalid argument.
     */
    public DeadlineTask(String taskName, String deadline) throws BotException {
        super(taskName);
        assert taskName != null && !taskName.isEmpty() : "Task name must not be null or empty";

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            String errorMessage = "Invalid date format for deadline: " + deadline + "\n" + "Expected: yyyy-MM-dd\n";
            throw new InvalidArgumentException(errorMessage);
        }
    }

    @Override
    public String stringFormatCompleteStatus() {
        return "[D]" + super.stringFormatCompleteStatus();
    }

    @Override
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }

        String keywordLowerCase = keyword.toLowerCase();
        String taskNameLowerCase = getTaskName().toLowerCase();

        if (taskNameLowerCase.contains(keywordLowerCase)) {
            return true;
        }

        assert this.deadline != null : "Deadline should not be null";
        String deadlineString = this.deadline.format(DISPLAY_FORMAT).toLowerCase();

        if (deadlineString.contains(keywordLowerCase)) {
            return true;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(keyword);
            if (parsedDate.equals(this.deadline)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            // safely ignore as we want to match user input to existing tasks
            // if the keyword cannot be parsed it should not be treated as datetime
        }

        return false;
    }

    @Override
    public String toSaveFormat() {
        return "D | "
                + super.stringFormatCompleteStatus() + "| "
                + getTaskName() + " | "
                + this.deadline;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.deadline.format(DISPLAY_FORMAT)
                + ")";
    }
}
