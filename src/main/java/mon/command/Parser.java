package mon.command;

/**
 * Parses user input and returns the appropriate command.
 */
public class Parser {

    /**
     * Parses the given input string and returns the appropriate command.
     * 
     * @param input the user input string
     * @return the command to execute
     * @throws Exception if the input cannot be parsed
     */
    public static Command parse(String input) throws Exception {
        if (input == null) {
            throw new Exception("Input string cannot be null");
        }
        
        if (input.trim().isEmpty()) {
            throw new Exception("Empty command!");
        }

        String[] parts = input.trim().split(" ");
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return parseMarkCommand(parts);

            case "unmark":
                return parseUnmarkCommand(parts);

            case "delete":
                return parseDeleteCommand(parts);

            case "todo":
                String[] todoParts = input.split(" ", 2);
                boolean hasTooFewTodoParts = todoParts.length < 2;
                boolean isTodoDescriptionEmpty = todoParts.length >= 2 && todoParts[1].trim().isEmpty();
                boolean isTodoFormatInvalid = hasTooFewTodoParts || isTodoDescriptionEmpty;
                
                if (isTodoFormatInvalid) {
                    throw new Exception("Unknown format for todo command!\n" +
                            "    Expected format: todo <task_description>");
                }
                return new AddTodoCommand(todoParts[1].trim());

            case "deadline":
                String[] deadlineParts = input.split(" ", 2);
                boolean hasTooFewDeadlineParts = deadlineParts.length < 2;
                boolean isDeadlineDescriptionEmpty = deadlineParts.length >= 2 && deadlineParts[1].trim().isEmpty();
                boolean isDeadlineFormatInvalid = hasTooFewDeadlineParts || isDeadlineDescriptionEmpty;
                
                if (isDeadlineFormatInvalid) {
                    throw new Exception("Unknown format for deadline command!\n" +
                            "    Expected format: deadline <task_description> /by <date>");
                }

                String[] deadlineDetails = deadlineParts[1].split(" /by ", 2);
                if (deadlineDetails.length < 2) {
                    throw new Exception("Unknown format for deadline command!\n" +
                            "    Expected format: deadline <task_description> /by <date>");
                }
                return new AddDeadlineCommand(deadlineDetails[0].trim(), deadlineDetails[1].trim());

            case "event":
                String[] eventParts = input.split(" ", 2);
                boolean hasTooFewEventParts = eventParts.length < 2;
                boolean isEventDescriptionEmpty = eventParts.length >= 2 && eventParts[1].trim().isEmpty();
                boolean isEventFormatInvalid = hasTooFewEventParts || isEventDescriptionEmpty;
                
                if (isEventFormatInvalid) {
                    throw new Exception("Unknown format for event command!\n" +
                            "    Expected format: event <task_description> /from <start> /to <end>");
                }

                String[] eventDetails = eventParts[1].split(" /from ", 2);
                if (eventDetails.length < 2) {
                    throw new Exception("Unknown format for event command!\n" +
                            "    Expected format: event <task_description> /from <start> /to <end>");
                }

                String eventDescription = eventDetails[0].trim();
                String[] eventTimeDetails = eventDetails[1].split(" /to ", 2);
                if (eventTimeDetails.length < 2) {
                    throw new Exception("Unknown format for event command!\n" +
                            "    Expected format: event <task_description> /from <start> /to <end>");
                }

                String from = eventTimeDetails[0].trim();
                String to = eventTimeDetails[1].trim();
                return new AddEventCommand(eventDescription, from, to);

            case "find":
                if (parts.length < 2) {
                    throw new Exception("Unknown format for find command!\n" +
                            "    Expected format: find <keyword>");
                }
                return new FindCommand(parts[1].trim());

            default:
                throw new Exception("I don't know what that means :-(");
        }
    }

    /**
     * Parses the mark command with the given parts.
     * 
     * @param parts the command parts
     * @return the MarkCommand
     * @throws Exception if parsing fails
     */
    private static Command parseMarkCommand(String[] parts) throws Exception {
        if (parts.length < 2) {
            throw new Exception("Unknown format for mark command!\n" +
                    "    Expected format: mark <task_number>");
        }
        
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new MarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid task number for mark command!");
        }
    }

    /**
     * Parses the unmark command with the given parts.
     * 
     * @param parts the command parts
     * @return the UnmarkCommand
     * @throws Exception if parsing fails
     */
    private static Command parseUnmarkCommand(String[] parts) throws Exception {
        if (parts.length < 2) {
            throw new Exception("Unknown format for unmark command!\n" +
                    "    Expected format: unmark <task_number>");
        }
        
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new UnmarkCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid task number for unmark command!");
        }
    }

    /**
     * Parses the delete command with the given parts.
     * 
     * @param parts the command parts
     * @return the DeleteCommand
     * @throws Exception if parsing fails
     */
    private static Command parseDeleteCommand(String[] parts) throws Exception {
        if (parts.length < 2) {
            throw new Exception("Unknown format for delete command!\n" +
                    "    Expected format: delete <task_number>");
        }
        
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid task number for delete command!");
        }
    }
}
