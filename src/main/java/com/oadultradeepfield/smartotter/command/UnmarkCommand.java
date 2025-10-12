package com.oadultradeepfield.smartotter.command;

import java.util.Optional;

import com.oadultradeepfield.smartotter.SmartOtterException;
import com.oadultradeepfield.smartotter.task.Task;
import com.oadultradeepfield.smartotter.util.CustomIO;

/**
 * Marks a specified task as not done.
 */
public record UnmarkCommand(int taskNumber) implements Executable {
    /**
     * Creates an {@code UnmarkCommand} for the given task number.
     *
     * @param taskNumber the 1-based index of the task to unmark
     */
    public UnmarkCommand {
    }

    /**
     * Parses input to create an {@code UnmarkCommand}.
     *
     * @param input string representing the 1-based task number
     * @return a new {@code UnmarkCommand} instance
     * @throws SmartOtterException the input is not a valid integer
     */
    public static Executable fromInput(String input) throws SmartOtterException {
        try {
            int num = Integer.parseInt(input);
            return new UnmarkCommand(num);
        } catch (NumberFormatException e) {
            throw new SmartOtterException("Invalid task number: %s".formatted(input));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(CommandContext context) {
        assert context != null : "Context cannot be null";

        // Convert 1-based to 0-based index and use getTask()
        Optional<Task> taskToUnmark = context.getTask(taskNumber - 1);

        if (taskToUnmark.isPresent()) {
            Task task = taskToUnmark.get();
            task.setDone(false);

            String message = "Got it! I have marked the task as not done\n%s".formatted(task);
            CustomIO.printPretty(message);
            return message;
        } else {
            String errorMessage =
                CustomIO.formatError("There is no task with number %d".formatted(taskNumber));
            CustomIO.printPretty(errorMessage);
            return errorMessage;
        }
    }
}
