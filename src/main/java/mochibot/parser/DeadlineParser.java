package mochibot.parser;

import java.time.LocalDateTime;
import java.util.Arrays;

import mochibot.MochiBotException;
import mochibot.command.AddCommand;
import mochibot.command.Command;
import mochibot.task.Deadline;
import mochibot.util.DateTimeParser;

/**
 * This class is responsible for handling the "deadline" command input.
 */
public class DeadlineParser {

    /**
     * Parses the "deadline" command input and returns a corresponding {@code AddCommand}.
     *
     * @param inputs the array of command arguments entered by the user
     * @return an {@code AddCommand} that contains a {@code Deadline} task
     * @throws MochiBotException.MissingEventArgumentsException if "/by" keyword is missing
     * @throws MochiBotException.MissingTaskNameException if the task name is empty
     * @throws MochiBotException.MissingDateException if the start or end date/time is empty
     */
    public static Command parse(String[] inputs) throws MochiBotException {
        int dateByIndex = getByIndex(inputs);
        if (dateByIndex == -1) {
            throw new MochiBotException.MissingDeadlineArgumentsException();
        }
        String taskName = String.join(" ", Arrays.copyOfRange(inputs, 1, dateByIndex));
        if (taskName.isEmpty()) {
            throw new MochiBotException.MissingTaskNameException();
        }
        String deadlineDate = String.join(" ", Arrays.copyOfRange(inputs, dateByIndex + 1, inputs.length));
        if (deadlineDate.isEmpty()) {
            throw new MochiBotException.MissingDateException();
        }
        LocalDateTime deadlineDateTime = DateTimeParser.parseInput(deadlineDate);
        Deadline deadline = new Deadline(taskName, deadlineDateTime);
        return new AddCommand(deadline);
    }

    /**
     * Searches the specified array for the first occurrence of the given item
     * and returns its index.
     *
     * @param strArray the array to search
     * @return the index of the first occurrence of {@code item}, or -1 if not found
     */
    private static int getByIndex(String[] strArray) {
        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals("/by")) {
                return i;
            }
        }
        return -1;
    }
}
