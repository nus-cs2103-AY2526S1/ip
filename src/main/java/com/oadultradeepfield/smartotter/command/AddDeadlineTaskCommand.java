package com.oadultradeepfield.smartotter.command;

import java.time.LocalDateTime;
import java.util.Optional;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.DeadlineTask;
import com.oadultradeepfield.smartotter.util.DateParser;

/**
 * A command that adds a new deadline task to the task list.
 */
public final class AddDeadlineTaskCommand extends AddTaskCommand {
    /**
     * Creates an {@code AddDeadlineTaskCommand} with the specified task.
     *
     * @param taskName the task name
     * @param deadline the deadline
     */
    public AddDeadlineTaskCommand(String taskName, LocalDateTime deadline) {
        super(new DeadlineTask(taskName, deadline));
    }

    /**
     * Parses input to create an {@code AddDeadlineTaskCommand}.
     *
     * <p>Example: {@code deadline return book /by 2/12/2019}
     *
     * @param input the full user input string without the command word
     * @return a new {@code AddDeadlineTaskCommand} instance
     * @throws SmartOtterException the input format is invalid
     */
    public static Executable fromInput(String input) throws SmartOtterException {
        String[] parts = input.split("/by", 2);

        if (parts.length < 2) {
            throw new SmartOtterException(
                "Incorrect deadline format! Use: deadline <taskName> /by <time>");
        }

        String taskName = parts[0].trim();
        String deadline = parts[1].trim();

        if (taskName.isEmpty() || deadline.isEmpty()) {
            throw new SmartOtterException("Task name and deadline cannot be empty");
        }

        Optional<LocalDateTime> parseDeadline = DateParser.parse(deadline);

        if (parseDeadline.isEmpty()) {
            throw new SmartOtterException("Deadline is not in a valid date and time format");
        }

        return new AddDeadlineTaskCommand(taskName, parseDeadline.get());
    }
}
