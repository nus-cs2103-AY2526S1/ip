package aqua.storage.parser;

import aqua.exception.IllegalDataException;
import aqua.exception.InvalidArgumentException;
import aqua.task.Task;
import aqua.task.ToDo;

/**
 * Parses input strings to create ToDo tasks.
 */
public class TodoParser extends TaskParser {
    /**
     * Parses a string input to create a ToDo task.
     * The input format is expected to be "description".
     *
     * @param input the string input containing the task details
     * @return a ToDo task created from the input details
     * @throws IllegalDataException if the input data is invalid
     */
    @Override
    public Task parse(String input) throws IllegalDataException {
        try {
            return new ToDo(input.trim());
        } catch (InvalidArgumentException e) {
            throw new IllegalDataException("Failed to load todo data.");
        }
    }
}
