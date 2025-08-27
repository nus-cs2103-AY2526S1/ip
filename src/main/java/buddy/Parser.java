package buddy;

public class Parser {
    
    public static String parseCommand(String input) {
        String[] words = input.trim().split("\\s+");
        return words.length > 0 ? words[0].toLowerCase() : "";
    }
    
    public static String parseDescription(String input, String command) throws BuddyException {
        String description = input.substring(command.length()).trim();
        if (description.isEmpty()) {
            throw new BuddyException("The description of a " + command + " cannot be empty.");
        }
        return description;
    }
    
    public static String[] parseDeadline(String input) throws BuddyException {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new BuddyException("Please use the format: deadline <description> /by <time>");
        }
        return new String[]{parts[0].trim(), parts[1].trim()};
    }
    
    public static String[] parseEvent(String input) throws BuddyException {
        String[] parts = input.substring(5).split(" /from ");
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
        try {
            String numberStr = input.substring(command.length()).trim();
            int taskNumber = Integer.parseInt(numberStr);
            return taskNumber - 1; // Convert to 0-based index
        } catch (NumberFormatException e) {
            throw new BuddyException("Please provide a valid task number.");
        }
    }
}