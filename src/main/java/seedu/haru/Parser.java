package seedu.haru;

/**
 * Handles parsing of user input into commands and arguments.
 */
public class Parser {

    /**
     * Extracts the command word from the user input.
     *
     * @param input the full user input string
     * @return the command word in lowercase
     */
    public static String getCommand(String input) {
        return input.split(" ")[0].toLowerCase();
    }

    /**
     * Extracts the arguments from the user input.
     *
     * @param input the full user input string
     * @return the arguments string, or an empty string if none
     */
    public static String getArguments(String input) {
        int firstSpace = input.indexOf(" ");
        return firstSpace == -1 ? "" : input.substring(firstSpace + 1).trim();
    }

    /**
     * Parses the arguments for a Deadline command.
     *
     * @param args the argument string
     * @return a string array: [name, end]
     * @throws HaruException if the format is invalid or fields are missing
     */
    public static String[] parseDeadline(String args) throws HaruException {
        String[] parts = args.split("/by", 2); // limit to 2 parts
        String name = parts.length > 0 ? parts[0].trim() : "";
        String endDate = parts.length > 1 ? parts[1].trim() : "";

        if (name.isEmpty() && endDate.isEmpty()) {
            throw new HaruException("Please specify both the task name and the end date in yyyy-mm-dd format");
        } else if (endDate.isEmpty()) {
            throw new HaruException("Please specify the end date in yyyy-mm-dd format");
        }

        return new String[]{name, endDate};
    }


    /**
     * Parses the arguments for an Event command.
     *
     * @param args the argument string
     * @return a string array: [description, start, end]
     * @throws HaruException if the format is invalid or fields are missing
     */
    public static String[] parseEvent(String args) throws HaruException {
        // Split using regex to match /from or /to exactly once
        String[] parts = args.split("/from|/to", 3);
        String name = parts.length > 0 ? parts[0].trim() : "";
        String start = parts.length > 1 ? parts[1].trim() : "";
        String end = parts.length > 2 ? parts[2].trim() : "";

        if (name.isEmpty() && start.isEmpty() && end.isEmpty()) {
            throw new HaruException("Please specify the name, start and end date/time for the event");
        } else if (start.isEmpty()) {
            throw new HaruException("Please specify the start and end date/time of the event");
        } else if (end.isEmpty()) {
            throw new HaruException("Please specify the end date/time of the event");
        }

        return new String[]{name, end, start};
    }
}

