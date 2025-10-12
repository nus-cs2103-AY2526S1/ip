package com.oadultradeepfield.smartotter.task;

import java.time.LocalDateTime;
import java.util.Optional;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.util.DateParser;

/**
 * Utility class for parsing task strings into {@link Task} objects.
 */
public class TaskParser {
    /**
     * Parses a line of text into a {@link Task}.
     *
     * <p>Expected format: {@code type | status | name | [deadline/from] | [to]}.
     *
     * @param line the line to parse
     * @return the parsed {@link Task}
     * @throws SmartOtterException if the line is invalid or cannot be parsed
     */
    public static Optional<Task> parse(String line) throws SmartOtterException {
        // Split by " | "
        String[] parts = line.split("\\s*\\|\\s*");

        if (parts.length < 3) {
            throw new SmartOtterException("Not enough parts");
        }

        String type = parts[0];
        int status;

        try {
            status = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new SmartOtterException("Invalid status number");
        }

        if (status != 0 && status != 1) {
            throw new SmartOtterException("Invalid status value");
        }

        String taskName = parts[2];
        LocalDateTime deadlineOrFrom = null;
        LocalDateTime to = null;

        // Parse the optional dates
        if (parts.length > 3) {
            Optional<LocalDateTime> parsed = DateParser.parse(parts[3]);
            if (parsed.isEmpty()) {
                throw new SmartOtterException("Invalid deadline or start time: " + parts[3]);
            }
            deadlineOrFrom = parsed.get();
        }

        if (parts.length > 4) {
            Optional<LocalDateTime> parsedTo = DateParser.parse(parts[4]);
            if (parsedTo.isEmpty()) {
                throw new SmartOtterException("Invalid end time: " + parts[4]);
            }
            to = parsedTo.get();

            if (parsedTo.get().isBefore(deadlineOrFrom) || parsedTo.get().isEqual(deadlineOrFrom)) {
                throw new SmartOtterException("Start time must be before end time.");
            }
        }

        return createTask(type, status, taskName, deadlineOrFrom, to);
    }

    /**
     * Creates a {@link Task} object based on type and properties.
     *
     * @param type           the task type ("T" = ToDo, "D" = Deadline, "E" = Event)
     * @param status         0 if not done, 1 if done
     * @param taskName       the task description
     * @param deadlineOrFrom the deadline (for Deadline) or start time (for Event)
     * @param deadlineOrTo   the end time (for Event), may be {@code null}
     * @return the created {@link Task}, or {@code null} if type is unknown
     */
    public static Optional<Task> createTask(
        String type,
        int status,
        String taskName,
        LocalDateTime deadlineOrFrom,
        LocalDateTime deadlineOrTo) {
        Task task =
            switch (type) {
            case "T" -> new ToDoTask(taskName);
            case "D" -> new DeadlineTask(taskName, deadlineOrFrom);
            case "E" -> new EventTask(taskName, deadlineOrFrom, deadlineOrTo);
            default -> null;
            };

        if (task != null && status == 1) {
            task.setDone(true);
        }

        return Optional.ofNullable(task);
    }
}
