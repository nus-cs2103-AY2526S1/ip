package aqua.storage.parser;

import aqua.exception.IllegalDataException;
import aqua.exception.InvalidArgumentException;
import aqua.task.Deadline;
import aqua.task.Task;

/**
 * Parses input strings to create Deadline tasks.
 */
public class DeadlineParser extends TaskParser {
    /**
     * Parses a string input to create a Deadline task.
     * The input format is expected to be "description ^ deadline".
     *
     * @param input the string input containing the task details
     * @return a Deadline task created from the input details
     * @throws IllegalDataException if the input data is invalid
     */
    @Override
    public Task parse(String input) throws IllegalDataException {
        String[] parts = input.split("\\^");
        String description = parts.length > 0 ? parts[0].trim() : "";
        String deadline = parts.length > 1 ? parts[1].trim() : "";

        try {
            return new Deadline(description, deadline);
        } catch (InvalidArgumentException e) {
            throw new IllegalDataException("Failed to load deadline data");
        }
    }
}
