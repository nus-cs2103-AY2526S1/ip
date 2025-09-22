package izayoi.input;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles the string input of a user and extracts suitable arguments
 */
public class InputReader implements TaskDescriptor {
    private final String[] input;

    /**
     * Initializes a new InputReader for the provided String
     * @param input the string to be parsed
     */
    public InputReader(String input) {
        this.input = input.split("\\s+");
    }

    /**
     * Attempts to read the input as a task description, returning any arguments
     * @return a map of argument names and their values. The task description is mapped to "message"
     */
    @Override
    public Map<String, String> getTask() {
        HashMap<String, String> result = new HashMap<>();
        String context = "message";
        StringBuilder arguments = new StringBuilder();

        for (int i = 1; i < input.length; i++) {
            if (input[i].charAt(0) == '/') {
                result.put(context, arguments.toString().trim());
                context = input[i].substring(1);
                arguments = new StringBuilder();
                continue;
            }
            arguments.append(input[i]).append(" ");
        }
        result.put(context, arguments.toString());

        return result;
    }

    /**
     * Attempts to read the second provided argument as an integer
     * @return the integer obtained, or -1 if the integer is invalid
     */
    public int getIndex() {
        try {
            return Integer.parseInt(input[1]);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * Attempts to recognise the type of input of the provided String
     * @return the type of command issued in the String
     */
    public CommandType getCommandType() {
        return switch (input[0]) {
        case "find" -> CommandType.SEARCH;
        case "list" -> CommandType.LIST;
        case "bye" -> CommandType.EXIT;
        case "mark" -> CommandType.MARK;
        case "unmark" -> CommandType.UNMARK;
        case "todo" -> CommandType.TODO;
        case "deadline" -> CommandType.DEADLINE;
        case "event" -> CommandType.EVENT;
        case "timed" -> CommandType.TIMED;
        case "delete" -> CommandType.DELETE;
        default -> CommandType.UNKNOWN;
        };
    }
}
