public class Parser {
    public enum CommandType {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE,
        UNKNOWN
    }

    public static CommandType getCommandType(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        return switch (commandWord) {
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "bye" -> CommandType.BYE;
            default -> CommandType.UNKNOWN;
        };
    }
}
