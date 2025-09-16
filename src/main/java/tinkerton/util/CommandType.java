package tinkerton.util;

public enum CommandType {
    BYE, LIST, SHOW, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN;

    public static CommandType command(String input) {
        try {
            return CommandType.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
