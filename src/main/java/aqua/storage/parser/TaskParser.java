package aqua.storage.parser;

import aqua.exception.IllegalDataException;
import aqua.task.Task;

/**
 * Abstract class for parsing task data from strings.
 */
public abstract class TaskParser {
    /**
     * Parse task given a input string
     *
     * @param input The user's input
     * @return task specified by the input string
     * @throws IllegalDataException If the data is illegal or cannot be parsed
     */
    public abstract Task parse(String input) throws IllegalDataException;
}
