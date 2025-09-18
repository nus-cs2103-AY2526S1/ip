package alune;

public enum Commands {
    HI, LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, CLEAR, FIND, UNDO, UPDATE, UNKNOWN;

    public static Commands fromString(String input) {
        if (input == null) {
            return null;
        }
        return switch (input.toLowerCase()) {
            case "hi" -> HI;
            case "list" -> LIST;
            case "bye" -> BYE;
            case "mark" -> MARK;
            case "unmark" -> UNMARK;
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            case "delete" -> DELETE;
            case "clear" -> CLEAR;
            case "find" -> FIND;
            case "undo" -> UNDO;
            case "update" -> UPDATE;
            default -> UNKNOWN;
        };
    }
}
