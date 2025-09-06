public class Parser {
    
    public static String parseCommand(String userInput) {
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
        } else {
            return "unknown";
        }
    }

    public static String parseTodoDescription(String userInput) {
        return userInput.length() >= 5 ? userInput.substring(5).trim() : "";
    }

    public static String[] parseDeadline(String userInput) {
        String[] parts = userInput.split(" /by ", 2);
        String description = parts[0].length() >= 9 ? parts[0].substring(9).trim() : "";
        String by = parts[1].trim();
        return new String[]{description, by};
    }

    public static String[] parseEvent(String userInput) {
        String[] parts = userInput.split(" /from ", 2);
        String description = parts[0].length() >= 6 ? parts[0].substring(6).trim() : "";
        String fromTo = parts[1];
        String[] fromToParts = fromTo.split(" /to ", 2);
        String from = fromToParts[0].trim();
        String to = fromToParts[1].trim();
        return new String[]{description, from, to};
    }

    public static int parseTaskNumber(String userInput) {
        String[] tokens = userInput.split(" ", 2);
        return Integer.parseInt(tokens[1].trim()) - 1;
    }
}
