package sofi;

/**
 * Utility class for parsing user input commands and extracting relevant information.
 * Provides static methods to parse different types of commands and their parameters.
 */
public class Parser {
    
    /**
     * Parses the command type from user input.
     * 
     * @param userInput the raw user input string
     * @return the command type (bye, list, todo, deadline, event, mark, unmark, delete, or unknown)
     */
    public static String parseCommand(String userInput) {
        assert userInput != null : "User input cannot be null";
        // Identify the command type from user input
        if (userInput.equals("bye")) {
            return "bye";
        } else if (userInput.equals("list")) {
            return "list";
        } else if (userInput.startsWith("todo")) {
            return "todo";
        } else if (userInput.startsWith("deadline")) {
            return "deadline";
        } else if (userInput.startsWith("event")) {
            return "event";
        } else if (userInput.startsWith("mark")) {
            return "mark";
        } else if (userInput.startsWith("unmark")) {
            return "unmark";
        } else if (userInput.startsWith("delete")) {
            return "delete";
        } else if (userInput.startsWith("find")) {
            return "find";
        } else {
            return "unknown";
        }
    }

    /**
     * Extracts the description from a todo command.
     * 
     * @param userInput the todo command input
     * @return the todo description, empty string if not found
     */
    public static String parseTodoDescription(String userInput) {
        assert userInput != null : "User input cannot be null";
        return userInput.length() >= 5 ? userInput.substring(5).trim() : "";
    }

    /**
     * Parses a deadline command to extract description and deadline.
     * 
     * @param userInput the deadline command input
     * @return array containing [description, deadline]
     */
    public static String[] parseDeadline(String userInput) {
        assert userInput != null : "User input cannot be null";
        assert userInput.startsWith("deadline") : "Input should start with 'deadline'";
        assert userInput.contains(" /by ") : "Deadline command must contain ' /by '";
        String[] parts = userInput.split(" /by ", 2);
        assert parts.length == 2 : "Split should produce exactly 2 parts";
        String description = parts[0].length() >= 9 ? parts[0].substring(9).trim() : "";
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    /**
     * Parses an event command to extract description, start time, and end time.
     * 
     * @param userInput the event command input
     * @return array containing [description, from, to]
     */
    public static String[] parseEvent(String userInput) {
        assert userInput != null : "User input cannot be null";
        assert userInput.startsWith("event") : "Input should start with 'event'";
        assert userInput.contains(" /from ") : "Event command must contain ' /from '";
        assert userInput.contains(" /to ") : "Event command must contain ' /to '";
        String[] parts = userInput.split(" /from ", 2);
        assert parts.length == 2 : "Split should produce exactly 2 parts";
        String description = parts[0].length() >= 6 ? parts[0].substring(6).trim() : "";
        String fromTo = parts[1];
        String[] fromToParts = fromTo.split(" /to ", 2);
        assert fromToParts.length == 2 : "Split should produce exactly 2 parts";
        String from = fromToParts[0].trim();
        String to = fromToParts[1].trim();
        return new String[]{description, from, to};
    }

    /**
     * Extracts the task number from mark/unmark/delete commands.
     * 
     * @param userInput the command input
     * @return the task index (0-based)
     */
    public static int parseTaskNumber(String userInput) {
        assert userInput != null : "User input cannot be null";
        String[] tokens = userInput.split(" ", 2);
        // Let the method throw ArrayIndexOutOfBoundsException for invalid input
        // to maintain expected test behavior
        return Integer.parseInt(tokens[1].trim()) - 1;
    }

    /**
     * Extracts the search keyword from a find command.
     * 
     * @param userInput the find command input
     * @return the search keyword, empty string if not found
     */
    public static String parseFindKeyword(String userInput) {
        assert userInput != null : "User input cannot be null";
        assert userInput.startsWith("find") : "Input should start with 'find'";
        return userInput.length() >= 5 ? userInput.substring(5).trim() : "";
    }
}
