package aries.util;

import aries.AriesException;

/**
 * Utility class for handling task index validation.
 */
public class IndexHandling {
    /**
     * Validates and converts a task number string to a zero-based index.
     *
     * @param number    The task number as a string.
     * @param taskCount The total number of tasks available.
     * @return The zero-based index corresponding to the task number.
     * @throws AriesException If the input is invalid or out of range.
     */
    public static int getValidIndex(String number, int taskCount) throws AriesException {
        if (number == null || number.isEmpty()) {
            throw new AriesException("Please specify task number. e.g., mark 1, unmark 2, delete 3");
        }

        if (taskCount <= 0) {
            throw new AriesException("No tasks available.");
        }

        int oneBasedIndex;

        try {
            oneBasedIndex = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new AriesException("Please enter a valid task number.");
        }

        if (oneBasedIndex < 1 || oneBasedIndex > taskCount) {
            throw new AriesException("Task number out of range.");
        }

        int zeroBasedIndex = oneBasedIndex - 1;
        return zeroBasedIndex;
    }
}
