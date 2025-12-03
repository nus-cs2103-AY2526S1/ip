package aqua.command.parser;

import aqua.exception.InvalidArgumentException;
import aqua.task.Task.Priority;

/**
 * Parses priority from user input.
 */
public class PriorityParser {
    /**
     * Parses Task.Priority given an input string.
     *
     * @param input The user's input
     * @return Priority specified by the input
     * @throws InvalidArgumentException If the priority is not recognized
     */
    public static Priority parse(String input) throws InvalidArgumentException {
        String lowerStr = input.toLowerCase();

        switch (lowerStr) {
        case "high", "2" -> {
            return Priority.HIGH;
        }
        case "medium", "1" -> {
            return Priority.MEDIUM;
        }
        case "low", "0" -> {
            return Priority.LOW;
        }
        default -> throw new InvalidArgumentException("Acceptable values of priority are <HIGH | MEDIUM | LOW>");
        }
    }
}
