package buddy;

public class Parser {
    private static final int DEADLINE_COMMAND_LENGTH = 8;  // "deadline".length()
    private static final int EVENT_COMMAND_LENGTH = 5;     // "event".length()
    private static final int TASK_NUMBER_BASE = 1; // Tasks are 1-indexed for users
    
    public static String parseCommand(String input) {
        assert input != null : "Input string should not be null";
        String[] words = input.trim().split("\\s+");
        return words.length > 0 ? words[0].toLowerCase() : "";
    }
    
    public static String parseDescription(String input, String command) throws BuddyException {
        assert input != null : "Input string should not be null";
        assert command != null : "Command string should not be null";
        assert input.length() >= command.length() : "Input should be at least as long as command";
        
        String description = input.substring(command.length()).trim();
        if (description.isEmpty()) {
            throw new BuddyException("The description of a " + command + " cannot be empty.");
        }
        return description;
    }
    
    public static String[] parseDeadline(String input) throws BuddyException {
        String[] parts = input.substring(DEADLINE_COMMAND_LENGTH).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BuddyException("Please use the format: deadline <description> /by <time>");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }
    
    public static String[] parseEvent(String input) throws BuddyException {
        String[] parts = input.substring(EVENT_COMMAND_LENGTH).split(" /from ");
        if (parts.length < 2) {
            throw new BuddyException("Please use the format: event <description> /from <start> /to <end>");
        }
        
        String description = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length < 2 || description.isEmpty() || 
            timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new BuddyException("Please use the format: event <description> /from <start> /to <end>");
        }
        
        return new String[]{description, timeParts[0].trim(), timeParts[1].trim()};
    }
    
    public static int parseTaskNumber(String input, String command) throws BuddyException {
        assert input != null : "Input string should not be null";
        assert command != null : "Command string should not be null";
        
        try {
            String numberStr = input.substring(command.length()).trim();
            int taskNumber = Integer.parseInt(numberStr);
            assert taskNumber > 0 : "Task number should be positive";
            return taskNumber - TASK_NUMBER_BASE; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new BuddyException("Please provide a valid task number.");
        }
    }
}