package airy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class parses the input of the user
 */
public class Parser {

    /**
     * Parses the raw user input into a command and its arguments.
     *
     * @param input The full input string from the user.
     * @return A String array of size 2. The first element is the lowercase command,
     *         the second element is the remaining arguments string.
     */
    public String[] parse(String input) {
        String trimInput = input.trim();
        String args;

        // (\\s+) finds the first occurrence of whitespace and splits the string there
        String[] parts = trimInput.split("\\s+", 2);
        String command = parts[0].toLowerCase(); // First word is command
        if (parts.length == 2) {
            args = parts[1].trim();
        } else {
            args = "";
        }
        return new String[]{command, args};
    }

    /**
     * Parses the args to convert String to Int
     *
     * @param command The command being executed (e.g., "mark", "delete").
     * @param args The argument string provided by the user.
     * @param tasksSize The size of the ArrayList that contains all the tasks
     * @return The parsed task number, converted to a zero-based index.
     */
    public int parseTaskNum(String command, String args, int tasksSize) {
        if (args.isEmpty()) {
            throw new AiryException("Please enter a number after " + command + " to mark/unmark a task");
        }
        // Gets the number after the command, -1 cause array is 0 indexed
        int taskNum = Integer.parseInt(args.trim()) - 1;
        if (taskNum < 0 || taskNum > tasksSize - 1) {
            throw new AiryException("Task number does not exist");
        }
        return taskNum;
    }

    /**
     * Ensures there is an argument present for Tasks such as To do, Deadline, Event
     *
     * @param command The command being executed (e.g., "to do", "deadline").
     * @param args The argument string provided by the user.
     * @throws AiryException If the argument string is empty.
     */
    public void checkArg(String command, String args) {
        if (args.isEmpty()) {
            throw new AiryException("Please enter a task name after " + command);
        }
    }

    /**
     * Parses a Deadline task command into its individual args.
     *
     * @param args The full argument string for the command.
     * @return A String array containing the parsed components.
     */
    public String[] parseDeadline(String args) {
        String[] parts = args.split("/by");
        if (parts.length != 2) {
            throw new AiryException(
                    "Please do /by before entering the due date. E.g. deadline return book /by Sunday");
        }
        return trimParts(parts);
    }

    /**
     * Parses an Event task command into its individual args.
     *
     * @param args The full argument string for the command.
     * @return A String array containing the parsed components.
     */
    public String[] parseEvent(String args) {
        // Split string whenever u see /from or /to
        String[] parts = args.split("/from|/to");
        if (parts.length != 3) {
            throw new AiryException(
                    "Please do /from before entering the start date and /to before entering the end date"
                            + " E.g. event project meeting /from Mon 2pm /to 4pm");
        }
        return trimParts(parts);
    }

    /**
     * Parses Delete args to split them into individual args.
     * Allows user to mass delete.
     * E.g. Delete 2 3, extract 2 and 3 from there.
     *
     * @param args The full argument string for the command.
     * @param tasksSize The size of the ArrayList that contains all the tasks
     * @return An int array containing the parsed components.
     */
    public int[] parseDelete(String args, int tasksSize) {
        if (args.isEmpty()) {
            throw new AiryException("Please use 'delete <task numbers>' to remove one or more tasks.");
        }
        // Split string based on white spaces
        String[] parts = args.split("\\s+");

        // Trim & reduce the index by 1 for each int the user inputted
        int[] deleteIndexes = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            int deleteIndex = Integer.parseInt(parts[i].trim()) - 1;
            if (deleteIndex < 0 || deleteIndex > tasksSize - 1) {
                throw new AiryException("Task number does not exist");
            }
            deleteIndexes[i] = deleteIndex;
        }
        return deleteIndexes;
    }

    /**
     * Sorts the int array in descending order so that the delete indexing remains correct.
     * @param deleteIndexes Takes in the parsed int array of delete indexes.
     * @return Returns a sorted int array in descending order.
     */
    public int[] descDeleteArray(int[] deleteIndexes) {
        // Sort descending as if I delete e.g. task 1, then the next deletion e.g. task 3 is no longer accurate
        // as ArrayList auto decreases the index upon delete
        return Arrays.stream(deleteIndexes)
                .boxed() // Convert int to Integer for sorting
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue) // Convert stream of Integer into stream of int
                .toArray(); // Give me array of int
    }

    /**
     * Trims the parts to remove whitespace.
     *
     * @param parts A String array containing the untrimmed components.
     * @return A String array containing the trimmed components.
     */
    public String[] trimParts(String[] parts) {
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        return parts;
    }
}
